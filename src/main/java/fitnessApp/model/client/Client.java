package fitnessApp.model.client;
import fitnessApp.model.subscription.Subscription;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public class Client {
    private static final AtomicLong ID_Counter = new AtomicLong(1);
    private long id;
    private String name;
    private Subscription subscription;
    private LocalDate subscriptionStartDate;
    private LocalDate subscriptionEndDate;

    /**
     * Constructs a new client with the given name and subscription.
     * @param name the name of the client
     */
    public Client(String name) {
        this.id = ID_Counter.getAndIncrement();
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
               "Client Name: " + name + "\n" +
               "Subscription: " + subscription.getSubscriptionType().name() + "\n" +
               "Subscription Start Date: " + subscriptionStartDate + "\n" +
               "Subscription End Date: " + subscriptionEndDate + "\n" +
               "Subscription Active: " + (isSubscriptionActive() ? "Yes" : "No") + "\n";
    }

    //////////////// Getters /////////////////

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Subscription getSubscription() {
        return subscription;
    }
    public LocalDate getSubscriptionStartDate() {
        return subscriptionStartDate;
    }
    public LocalDate getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    /**
     * Assigns a new subscription to the client and updates the start and end dates.
     * @param subscription the new subscription
     * @param subscriptionStartDate the start date for the subscription
     */
    public void updateSubscription(Subscription subscription, LocalDate subscriptionStartDate) {
        this.subscription = subscription;
        this.subscriptionStartDate = subscriptionStartDate;
        this.subscriptionEndDate = this.subscriptionStartDate.plusDays(subscription.getDurationInDays());
    }

    /**
     * Checks if the client's subscription is currently active.
     * @return true if active, false otherwise
     */
    public boolean isSubscriptionActive() {
        LocalDate today = LocalDate.now();
        return (today.isEqual(subscriptionStartDate) || today.isAfter(subscriptionStartDate)) &&
                (today.isEqual(subscriptionEndDate) || today.isBefore(subscriptionEndDate));
    }
    public void setId(long id) {
        this.id = id;
    }
    public static void updateIDCounter(long newID) {
        ID_Counter.set(newID);
    }
}
