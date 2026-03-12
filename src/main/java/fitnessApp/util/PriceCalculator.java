package fitnessApp.util;

import fitnessApp.model.subscription.Subscription;
import fitnessApp.model.subscription.SubscriptionType;
import fitnessApp.model.workout.WorkoutType;

/**
 * Utility class responsible for calculating prices of subscriptions.
 * Can handle discounts, surcharges, promotions, etc.
 */
public class PriceCalculator {

    public static double subPrice(SubscriptionType subscriptionType) {
        double basePrice;
        switch (subscriptionType) {
            case STANDARD -> basePrice = 50.0;
            case PREMIUM -> basePrice = 80.0;
            default -> throw new IllegalArgumentException("Unknown subscription type: " + subscriptionType);
        }
        return basePrice;
    }

    /**
     * Calculate the final price of a subscription after applying discounts or surcharges.
     *
     * @param subscription The subscription for which to calculate the price.
     * @return The final price after adjustments.
     */
    public static double calculateFinalPrice(Subscription subscription) {
        double basePrice = subscription.getBasePrice();
        int durationInDays = subscription.getDurationInDays();

        double standardPrice = basePrice * ((double) durationInDays / 30);
        if(subscription.getSubscriptionType() == fitnessApp.model.subscription.SubscriptionType.PREMIUM)
            return standardPrice * 1.2;
        return standardPrice;
    }



    /**
     * Calculate the final price of a subscription after applying discounts or surcharges.
     * @param subscription The subscription for which to calculate the price.
     * @param discountPercentage The discount percentage to apply (0-100).
     * @return The final price after adjustments.
     */
    public static double calculateFinalPrice(Subscription subscription, double discountPercentage) {
        double basePrice = subscription.getBasePrice();
        int durationInDays = subscription.getDurationInDays();

        double standardPrice = basePrice * ((double) durationInDays / 30);
        if(subscription.getSubscriptionType()== fitnessApp.model.subscription.SubscriptionType.PREMIUM)
            return standardPrice * 1.2 - standardPrice * 1.2 *(discountPercentage/100);
        return standardPrice - standardPrice*(discountPercentage/100) ;
    }

    public static double workPrice (WorkoutType workoutType) {
        double basePrice;
        switch (workoutType) {
            case YOGA -> basePrice = 15.0;
            case CROSSFIT -> basePrice = 20.0;
            case PILATES -> basePrice = 25.0;
            default -> throw new IllegalArgumentException("Unknown workout type: " + workoutType);
        }
        return basePrice;
    }
}
