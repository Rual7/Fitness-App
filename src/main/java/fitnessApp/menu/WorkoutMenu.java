package fitnessApp.menu;

import fitnessApp.model.trainer.Trainer;
import fitnessApp.model.workout.WorkoutClass;
import fitnessApp.model.workout.WorkoutType;
import fitnessApp.model.workout.IntensityLevel;
import fitnessApp.service.TrainerService;
import fitnessApp.service.WorkoutService;
import fitnessApp.util.PriceCalculator;

import java.util.List;
import java.util.Scanner;

public class WorkoutMenu {
    private WorkoutService workoutService;
    private TrainerService trainerService;
    private Scanner scanner = new Scanner(System.in);
    private PriceCalculator priceCalculator;

    WorkoutMenu(WorkoutService workoutService, TrainerService trainerService, PriceCalculator priceCalculator) {
        this.workoutService = workoutService;
        this.priceCalculator = priceCalculator;
        this.trainerService = trainerService;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n=== WORKOUT MANAGEMENT MENU ===");
            System.out.println("1. Add new workout class");
            System.out.println("2. Remove workout class by ID");
            System.out.println("3. View all workout classes");
            System.out.println("4. Assign trainer to workout class");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addWorkout();
                case 2 -> removeWorkoutByID();
                case 3 -> viewAllWorkouts();
                case 4 -> assignTrainerToWorkout();
                case 0 -> System.out.println("Returning to main menu...\n");
                default -> System.out.println("Invalid option! Try again.");
            }
        } while (choice != 0);
    }
    private void addWorkout() {
        System.out.print("Enter workout class name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.print("Enter workout class type: (YOGA, CROSSFIT, PILATES): ");
        String typeInput = scanner.nextLine().toUpperCase();
        WorkoutType workoutType;
        try {
            workoutType = WorkoutType.valueOf(typeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid workout type. Workout class not added.");
            return;
        }

        System.out.print("Enter intensity level (EASY, MEDIUM, HARD): ");
        String intensityInput = scanner.nextLine().toUpperCase();
        IntensityLevel intensityLevel;
        try {
            intensityLevel = IntensityLevel.valueOf(intensityInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid intensity level. Workout class not added.");
            return;
        }
        double basePrice = priceCalculator.workPrice(workoutType);
        WorkoutClass newWorkoutClass = new WorkoutClass(name, workoutType, intensityLevel, basePrice);

        workoutService.addWorkoutClass(newWorkoutClass);
        System.out.println("Workout class added successfully.");
    }
    private void viewAllWorkouts() {
        System.out.println("\n--- List of Workouts ---");
        workoutService.getAllWorkoutClasses().forEach(System.out::println);
    }
    private void removeWorkoutByID() {
        if(workoutService.getAllWorkoutClasses().isEmpty()) {
            System.out.println("No workout classes available to remove.");
            return;
        }

        viewAllWorkouts();
        System.out.print("Enter the ID of the workout class to remove: ");
        int workoutId = scanner.nextInt();
        WorkoutClass workoutClass = workoutService.getAllWorkoutClasses().get(workoutId);
        if(workoutClass != null) {
            workoutService.removeWorkoutClass(workoutClass);
            System.out.println("Workout class removed successfully.");
            updateAllWorkouts();
        } else {
            System.out.println("Invalid workout ID.");
        }
    }

    private void assignTrainerToWorkout() {
        if(workoutService.getAllWorkoutClasses().isEmpty()) {
            System.out.println("No workout classes available to assign a trainer.");
            return;
        }

        viewAllWorkouts();

        System.out.print("Enter the ID of the workout class to assign a trainer: ");
        int workoutId = scanner.nextInt();
        if(workoutId < 0 || workoutId >= workoutService.getAllWorkoutClasses().size() + 1) {
            System.out.println("Invalid workout ID.");
            return;
        }
        WorkoutClass workoutClass = null;
        for (var workoutClass1 : workoutService.getAllWorkoutClasses()) {
            if (workoutClass1.getId() == workoutId) {
                workoutClass = workoutClass1;
            }
        }

        if(trainerService.getAllTrainers().isEmpty()) {
            System.out.println("No trainers available to assign.");
            return;
        }

        System.out.println("\n--- List of Trainers ---");
        trainerService.getAllTrainers().forEach(System.out::println);
        System.out.print("Enter the ID of the trainer to assign: ");
        int trainerId = scanner.nextInt();
        if(trainerId < 0 || trainerId >= trainerService.getAllTrainers().size() + 1) {
            System.out.println("Invalid trainer ID.");
            return;
        }
        Trainer trainer = null;
        for (var trainer1 : trainerService.getAllTrainers()) {
            if (trainer1.getId() == trainerId) {
                trainer = trainer1;
            }
        }

        if(workoutClass != null && trainer != null) {
            workoutService.assignTrainerToWorkoutClass(workoutClass, trainer);
            trainer.addWorkoutClass(workoutClass);
            System.out.println("Trainer assigned successfully.");
        } else {
            System.out.println("Invalid workout or trainer ID.");
        }
    }

    private void updateAllWorkouts() {
        List<WorkoutClass> workoutClasses = workoutService.getAllWorkoutClasses();
        long resetID = 1;
        for (WorkoutClass workoutClass : workoutClasses) {
            workoutClass.setId(resetID);
            resetID++;
        }

        long maxId = 0;
        for (WorkoutClass workoutClass : workoutClasses) {
            if (workoutClass.getId() > maxId) {
                maxId = workoutClass.getId();
            }
        }
        WorkoutClass.updateIDCounter(maxId + 1);

    }
}
