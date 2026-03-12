package fitnessApp.model.workout;
import fitnessApp.model.trainer.Trainer;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Represents a workout class in FitZone+.
 * Each class has a type, intensity, base price, and assigned trainer.
 */
public class WorkoutClass {
    private static final AtomicLong ID_counter = new AtomicLong(1);
    private long id;
    private String title;
    private WorkoutType type;
    private IntensityLevel intensity;
    private double basePrice;
    private Trainer trainer;

    /**
     * Constructor.
     * @param title the name of the class
     * @param type the workout type
     * @param intensity the intensity level
     * @param basePrice base price of the class
     */
    public WorkoutClass(String title, WorkoutType type, IntensityLevel intensity, double basePrice) {
        this.id = ID_counter.getAndIncrement();
        this.title = title;
        this.type = type;
        this.intensity = intensity;
        this.basePrice = basePrice;
    }

    public static void updateIDCounter(long newCounter) {
        ID_counter.set(newCounter);
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Title: " + title + "\n" +
                "Type: " + type + "\n" +
                "Intensity: " + intensity + "\n" +
                "Price: " + basePrice + "\n" +
                "Trainer: " + (trainer != null ? trainer.getName() : "None") + "\n";
    }

    //////////////// Getters /////////////////
    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public WorkoutType getType() {
        return type;
    }
    public IntensityLevel getIntensity() {
        return intensity;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public Trainer getTrainer() {
        return trainer;
    }

    /**
     * Assigns a trainer to this workout class.
     * @param trainer the trainer to assign
     */
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setIdCounter(long newCounter) {
        ID_counter.set(newCounter);
    }
}
