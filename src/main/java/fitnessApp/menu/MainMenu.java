package fitnessApp.menu;

import fitnessApp.service.*;
import fitnessApp.util.PriceCalculator;
import java.util.Scanner;

public class MainMenu {
    private ClientService clientService;
    private TrainerService trainerService;
    private WorkoutService workoutService;
    private ReportService reportService;
    private PriceCalculator priceCalculator;

    public MainMenu(ClientService clientService, TrainerService trainerService, WorkoutService workoutService, ReportService reportService, PriceCalculator priceCalculator) {
        this.clientService = clientService;
        this.trainerService = trainerService;
        this.workoutService = workoutService;
        this.reportService = reportService;
        this.priceCalculator = priceCalculator;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=== FitnessAPP Main Menu ===");
            System.out.println("1. Clients Management");
            System.out.println("2. Trainers Management");
            System.out.println("3. Workout Classes Management");
            System.out.println("4. Generate Reports");
            System.out.println("5. Exit");
            System.out.print("================================\n");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    new ClientManagementMenu(clientService,priceCalculator).showMenu();
                    break;
                case 2:
                    new TrainerMenu(trainerService).showMenu();
                    break;
                case 3:
                    new WorkoutMenu(workoutService,trainerService,priceCalculator).showMenu();
                    break;
                case 4:
                    new ReportMenu(reportService,workoutService,trainerService,clientService).showMenu();
                    break;
                case 5:
                    System.out.println("Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
