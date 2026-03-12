package fitnessApp.menu;
import fitnessApp.model.client.Client;
import fitnessApp.model.subscription.Subscription;
import fitnessApp.model.subscription.SubscriptionType;
import fitnessApp.service.*;
import fitnessApp.util.PriceCalculator;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ClientManagementMenu {
    private ClientService clientService;
    private PriceCalculator priceCalculator;
    private Scanner scanner = new Scanner(System.in);

    ClientManagementMenu(ClientService clientService, PriceCalculator priceCalculator) {
        this.clientService = clientService;
        this.priceCalculator = priceCalculator;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n=== CLIENT MANAGEMENT MENU ===");
            System.out.println("1. Add new client");
            System.out.println("2. Remove client by ID");
            System.out.println("3. Update client subscription");
            System.out.println("4. View all clients");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addClient();
                case 2 -> removeClientByID();
                case 3 -> updateClientSubscription();
                case 4 -> viewAllClients();
                case 0 -> System.out.println("Returning to main menu...\n");
                default -> System.out.println("Invalid option! Try again.");
            }
        } while (choice != 0);
    }

    public void addClient() {
        System.out.print("Enter client name: ");
        scanner.nextLine();
        String name = scanner.nextLine();


        System.out.println("Select subscription type: (STANDARD, PREMIUM)");
        String typeInput = scanner.nextLine().toUpperCase();
        SubscriptionType subscriptionType;
        try {
            subscriptionType = SubscriptionType.valueOf(typeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid subscription type. Client not added.");
            return;
        }


        double price = priceCalculator.subPrice(subscriptionType);
        System.out.println("Select subscription duration in days: ");
        int duration = scanner.nextInt();

        Subscription subscription = new Subscription(subscriptionType, price, duration);
        Client newClient = new Client(name);
        newClient.updateSubscription(subscription, LocalDate.now());

        clientService.addClient(newClient);
        System.out.println("Client added successfully.");
    }

    private void removeClientByID() {
        List<Client> clients = clientService.getAllClients();
        if (clients.isEmpty()) {
            System.out.println("No clients to remove.");
            return;
        }
        viewAllClients();

        System.out.print("Enter client ID to remove: ");
        int id = scanner.nextInt();
        Client clientToRemove = clientService.getClientByID(id);

        if(clientToRemove == null) {
            System.out.println("Client with ID " + id + " not found.");
            return;
        }

        clientService.removeClient(clientToRemove);
        System.out.println("Client removed successfully.");
        updateIDCounters();
    }

    private void updateClientSubscription() {
        viewAllClients();

        System.out.print("Enter client ID to update subscription: ");
        int id = scanner.nextInt();
        Client clientToUpdate = clientService.getClientByID(id);

        if(clientToUpdate == null) {
            System.out.println("Client with ID " + id + " not found.");
            return;
        }

        SubscriptionType subscriptionType = null;

        //IDK why but try catch doesn't work here: scanner just simply skips input
        System.out.println("Select new subscription type: STANDARD or PREMIUM");
        switch (scanner.next().toUpperCase()) {
            case "STANDARD" -> subscriptionType = SubscriptionType.STANDARD;
            case "PREMIUM" -> subscriptionType = SubscriptionType.PREMIUM;
            default -> {
                System.out.println("Invalid subscription type. Subscription not updated.");
                return;
            }
        }
        double price = priceCalculator.subPrice(subscriptionType);
        System.out.println("Select new subscription duration in days: ");
        int duration = scanner.nextInt();

        Subscription subscription = new Subscription(subscriptionType, price, duration);
        clientToUpdate.updateSubscription(subscription, LocalDate.now());

        System.out.println("Client subscription updated successfully.");
    }

    private void viewAllClients() {
        System.out.println("\n--- List of Clients ---");
        clientService.getAllClients().forEach(System.out::println);
    }

    private  void updateIDCounters() {
        List<Client> clients = clientService.getAllClients();
        long resetID = 1;
        for (Client client : clients) {
            client.setId(resetID);
            resetID++;
        }

        long maxId = 0;
        for (Client client : clients) {
            if (client.getId() > maxId) {
                maxId = client.getId();
            }
        }
        Client.updateIDCounter(maxId + 1);
    }
}
