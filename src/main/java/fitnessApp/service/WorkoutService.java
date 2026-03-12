package fitnessApp.service;
import fitnessApp.model.trainer.Trainer;
import fitnessApp.model.workout.WorkoutClass;
import java.util.List;


/**
 * Service interface for managing workouts.
 */
public interface WorkoutService {
    /**
     * Adds a workout class to the system.
     * @param workoutClass the workout class to add
     */
    void addWorkoutClass(WorkoutClass workoutClass);

    /**
     * Removes a workout class.
     * @param workoutClass the workout class to remove
     */
    void removeWorkoutClass(WorkoutClass workoutClass);

    /**
     * Retrieves all workout classes.
     * @return a list of all workout classes
     */
    List<WorkoutClass> getAllWorkoutClasses();

    /**
     * Assigns a trainer to a workout class.
     * @param workoutClass the workout class
     * @param trainer the trainer to assign
     */
    public void assignTrainerToWorkoutClass(WorkoutClass workoutClass, Trainer trainer);
}
