package fitnessApp.app;

import fitnessApp.menu.*;
import fitnessApp.service.*;
import fitnessApp.util.*;

public class fitnessApp {
        public static void main(String[] args) {
            ClientService clientService = new ClientServiceImpl();
            TrainerService trainerService = new TrainerServiceImpl();
            WorkoutService workoutService = new WorkoutServiceImpl();
            ReportService reportService = new ReportServiceImpl(clientService, trainerService, workoutService);
            PriceCalculator priceCalculator = new PriceCalculator();

            MainMenu mainMenu = new MainMenu(clientService, trainerService, workoutService, reportService, priceCalculator);
            mainMenu.start();
        }
}
