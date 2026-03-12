package fitnessApp.service;
import fitnessApp.model.workout.WorkoutClass;
import fitnessApp.model.trainer.Trainer;

import java.util.ArrayList;
import java.util.List;

public class WorkoutServiceImpl implements WorkoutService {
    List<WorkoutClass> workoutClasses = new ArrayList<>();

    @Override
    public void addWorkoutClass(WorkoutClass workoutClass) {
        workoutClasses.add(workoutClass);
    }

    @Override
    public void removeWorkoutClass(WorkoutClass workoutClass) {
        workoutClasses.remove(workoutClass);
    }

    @Override
    public List<WorkoutClass> getAllWorkoutClasses() {
        return workoutClasses;
    }

    @Override
    public void assignTrainerToWorkoutClass(WorkoutClass workoutClass, Trainer trainer) {
        workoutClass.setTrainer(trainer);
    }
}
