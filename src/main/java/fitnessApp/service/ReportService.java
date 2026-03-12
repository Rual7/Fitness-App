package fitnessApp.service;
import fitnessApp.model.workout.WorkoutClass;
import fitnessApp.model.trainer.Trainer;
import fitnessApp.model.client.Client;

import java.util.List;

/**
 * Service interface for generating reports.
 */
public interface ReportService {
    /**
     * Generates a summary report of workouts, trainers and clients.
     * @param workouts the list of workout classes
     * @param trainers the list of trainers
     * @param clients the list of clients
     */
    public void generateSummaryReport(List<WorkoutClass> workouts, List<Trainer> trainers, List<Client> clients);

}
