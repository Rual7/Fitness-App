package fitnessApp.model.subscription;

/**
 * Abstract class representing a subscription in FitZone+.
 * Holds common information for all subscription types.
 */
public class Subscription {

    protected SubscriptionType subscriptionType;
    protected double basePrice;
    protected int durationInDays;
    protected double finalPrice;

    /**
     * Constructor for subscription.
     * @param subscriptionType the subscription type name
     * @param basePrice the base price
     * @param durationInDays the duration of the subscription in days
     * preferably 30, 90, 365 days
     */
    public Subscription(SubscriptionType subscriptionType, double basePrice, int durationInDays) {
        this.subscriptionType = subscriptionType;
        this.basePrice = basePrice;
        this.durationInDays = durationInDays;
        this.finalPrice = calculateFinalPrice();
    }
    /**
     * Calculates the final price of the subscription, possibly applying discounts.
     * @return final price
     */
    public double calculateFinalPrice(){
        return fitnessApp.util.PriceCalculator.calculateFinalPrice(this);
    }
    public double calculateFinalPrice(double discountPercentage){
        return fitnessApp.util.PriceCalculator.calculateFinalPrice(this, discountPercentage);
    }


    //////////////// Getters /////////////////
    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public double getFinalPrice() {
        return finalPrice;
    }
    public int getDurationInDays() {
        return durationInDays;
    }

}
