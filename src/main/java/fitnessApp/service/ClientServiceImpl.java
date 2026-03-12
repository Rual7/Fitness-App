package fitnessApp.service;

import fitnessApp.model.client.Client;

import java.util.List;
import java.util.ArrayList;

public class ClientServiceImpl implements ClientService {
    private List<Client> clients = new ArrayList<>();

    @Override
    public void addClient(Client client) {
        clients.add(client);
    }

    @Override
    public void removeClient(Client client) {
        clients.remove(client);
    }

    @Override
    public Client getClientByID(long id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    @Override
    public List<Client> getAllClients(){
        return clients;
    }
}
