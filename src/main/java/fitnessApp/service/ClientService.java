package fitnessApp.service;

import fitnessApp.model.client.Client;

import java.util.List;

public interface ClientService {

    /**
     * Adds a client to the system.
     * @param client the client to add
     */
    void addClient(Client client);

    /**
     * Removes a client.
     * @param client the client to remove
     */
    void removeClient(Client client);

    /**
     * Retrieves a client by their ID.
     * @param id the ID of the client
     * @return the client with the specified ID, or null if not found
     */
    Client getClientByID(long id);

    /**
     * Retrieves all clients.
     * @return a list of all clients
     */
    public List<Client> getAllClients();

    }
