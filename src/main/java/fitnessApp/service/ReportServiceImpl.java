package fitnessApp.service;
import fitnessApp.model.client.Client;
import fitnessApp.model.workout.WorkoutClass;
import fitnessApp.model.trainer.Trainer;

import java.util.List;

public class ReportServiceImpl implements ReportService {
    private ClientService clientService;
    private TrainerService trainerService;
    private WorkoutService workoutService;

    public ReportServiceImpl(ClientService clientService, TrainerService trainerService, WorkoutService workoutService) {
        this.clientService = clientService;
        this.trainerService = trainerService;
        this.workoutService = workoutService;
    }

    @Override
    public void generateSummaryReport(List<WorkoutClass> workouts, List<Trainer> trainers, List<Client> clients) {
        System.out.println("\n============================     ===     ============================");
        System.out.println("\nWorkout Summary Report:");
        for (WorkoutClass workout : workouts) {
            System.out.println(workout);
        }

        System.out.println("\nTrainer Summary Report:");
        for (Trainer trainer : trainers) {
            System.out.println(trainer);
        }

        System.out.println("\n============================     ===     ============================");

        System.out.println("\nClient Report:");
        for (fitnessApp.model.client.Client client : clients) {
            System.out.println(client);
        }
        System.out.println("\n============================     ===     ============================\n");
    }
}
