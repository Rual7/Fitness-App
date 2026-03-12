package fitnessApp.menu;

import fitnessApp.service.ClientService;
import fitnessApp.service.ReportService;
import fitnessApp.service.TrainerService;
import fitnessApp.service.WorkoutService;

public class ReportMenu {
    private ReportService reportService;
    private WorkoutService workoutService;
    private ClientService clientService;
    private TrainerService trainerService;

    ReportMenu(ReportService reportService,
               WorkoutService workoutService,
               TrainerService trainerService,
               ClientService clientService) {
        this.reportService = reportService;
        this.workoutService = workoutService;
        this.trainerService = trainerService;
        this.clientService = clientService;
    }

    public void showMenu() {
        reportService.generateSummaryReport(
                workoutService.getAllWorkoutClasses(),
                trainerService.getAllTrainers(),
                clientService.getAllClients());
    }
}
