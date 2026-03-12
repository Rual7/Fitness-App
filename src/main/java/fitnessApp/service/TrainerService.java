package fitnessApp.service;
import fitnessApp.model.trainer.Trainer;
import java.util.List;

/**
 * Service interface for managing trainers.
 */
public interface TrainerService {

    /**
     * Adds a trainer to the system.
     * @param trainer the trainer to add
     */
    void addTrainer(Trainer trainer);

    /**
     * Removes a trainer.
     * @param trainer the trainer to remove
     */
    void removeTrainer(Trainer trainer);

    /**
     * Retrieves all trainers.
     * @return a list of all trainers
     */
    List<Trainer> getAllTrainers();

    boolean removeTrainerById(int trainerId);
}
