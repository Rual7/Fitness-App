package fitnessApp.menu;

import fitnessApp.model.trainer.TrainerType;
import fitnessApp.model.trainer.Trainer;
import fitnessApp.service.TrainerService;

import java.util.List;
import java.util.Scanner;

public class TrainerMenu {
    private TrainerService trainerService;
    private Scanner scanner = new Scanner(System.in);

    TrainerMenu(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n=== TRAINER MANAGEMENT MENU ===");
            System.out.println("1. Add new trainer");
            System.out.println("2. Remove trainer by ID");
            System.out.println("3. View all trainers");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addTrainer();
                case 2 -> removeTrainerByID();
                case 3 -> viewAllTrainers();
                case 0 -> System.out.println("Returning to main menu...\n");
                default -> System.out.println("Invalid option! Try again.");
            }
        } while (choice != 0);
    }

    private void addTrainer() {
        System.out.print("Enter trainer name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();

        System.out.print("Enter trainer type (PERMANENT/EXTERNAL): ");
        String typeInput = scanner.nextLine().toUpperCase();
        TrainerType trainerType;
        try {
            trainerType = TrainerType.valueOf(typeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid trainer type. Trainer not added.");
            return;
        }

        Trainer newTrainer = new Trainer(name, trainerType);
        trainerService.addTrainer(newTrainer);
        System.out.println("Trainer added successfully.");

    }

    private void removeTrainerByID() {
        List<Trainer> trainers = trainerService.getAllTrainers();
        if (trainers.isEmpty()) {
            System.out.println("No trainers to remove.");
            return;
        }

        viewAllTrainers();

        System.out.print("Enter Trainer ID to remove: ");
        int trainerId = scanner.nextInt();
        boolean removed = trainerService.removeTrainerById(trainerId);
        if (removed) {
            System.out.println("Trainer with ID " + trainerId + " has been removed.");
            updateIDCounters();
        } else {
            System.out.println("Trainer with ID " + trainerId + " not found.");
        }
    }

    private void viewAllTrainers() {
        System.out.println("\n--- List of Trainers ---");
        trainerService.getAllTrainers().forEach(System.out::println);
    }

    private void updateIDCounters() {
        List<Trainer> trainers = trainerService.getAllTrainers();
        long resetID = 1;
        for (Trainer trainer : trainers) {
            trainer.setId(resetID);
            resetID++;
        }

        long maxId = 0;
        for (Trainer trainer : trainers) {
            if (trainer.getId() > maxId) {
                maxId = trainer.getId();
            }
        }
        Trainer.updateIDCounter(maxId + 1);
    }

}
