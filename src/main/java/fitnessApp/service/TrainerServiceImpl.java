package fitnessApp.service;
import fitnessApp.model.trainer.Trainer;

import java.util.ArrayList;
import java.util.List;

public class TrainerServiceImpl implements TrainerService {
    private List<Trainer> trainers = new ArrayList<>();

    @Override
    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }
    @Override
    public void removeTrainer(Trainer trainer) {
        trainers.remove(trainer);
    }
    @Override
    public List<Trainer> getAllTrainers() {
        return trainers;
    }

    @Override
    public boolean removeTrainerById(int trainerId) {
        return trainers.removeIf(trainer -> trainer.getId() == trainerId);
    }
}
