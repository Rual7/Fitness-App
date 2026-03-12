package fitnessApp.model.trainer;
import fitnessApp.model.workout.WorkoutClass;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Abstract class representing a Trainer in FitZone+.
 * A trainer can teach multiple workout classes.
 */
public class Trainer {
    private static final AtomicLong ID_COUNTER = new AtomicLong(1);
    private long id;
    private String name;
    private TrainerType trainerType;
    private List<WorkoutClass> classesTaught;

    /**
     * Constructor.
     * @param name the name of the trainer
     */
    public Trainer(String name, TrainerType trainerType) {
        this.id = ID_COUNTER.getAndIncrement();
        this.name = name;
        this.trainerType =trainerType;
    }

    public static void updateIDCounter(long newCounter) {
        ID_COUNTER.set(newCounter);
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Trainer Name: " + name + "\n" +
                "TrainerType: " + trainerType + "\n" +
                "ClassesTaught: " + (classesTaught!=null ? classesTaught : "NONE") + "\n";
    }

    /**
     * Returns the type of the trainer (Permanent or External).
     * @return trainer type as a String
     */
    public TrainerType getTrainerType() {
        return trainerType;
    }

    /**
     * Adds a workout class that this trainer can teach.
     * @param workout the class to add
     */
    public void addWorkoutClass(WorkoutClass workout) {
        if (classesTaught != null && !classesTaught.contains(workout)) {
            classesTaught.add(workout);
        }
        else if (classesTaught == null) {
            classesTaught = new java.util.ArrayList<>();
            classesTaught.add(workout);
        }
    }

    /**
     * Removes a workout class from this trainer.
     * @param workout the class to remove
     */
    public void removeWorkoutClass(WorkoutClass workout) {
        classesTaught.remove(workout);
    }

    /**
     * Returns the list of workout classes this trainer teaches.
     * @return list of classes
     */
    public List<WorkoutClass> getClassesTaught() {
        return classesTaught;
    }

    //////////////// Getters /////////////////
    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}