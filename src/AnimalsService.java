import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by davehochstrasser on 8/21/16.
 */
public class AnimalsService {
    private AnimalRepository animalRepository;


    public AnimalsService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public ArrayList<Animal> listAnimals() {
        return animalRepository.listAnimals();
    }

    public void createAnimal(String name, String species, String breed, String description) {
        try {
            animalRepository.createAnimal(name, species, breed, description);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateAnimal(String name, String species, String breed, String description, int index) {
        try {
            animalRepository.updateAnimal(name, species, breed, description,index);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Animal deleteAnimal(int index) {
        try {
            animalRepository.deleteAnimal(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //both of the following are tested with the scope above in AnimalServiceTest
    public Animal getAnimal(int index) {
        return animalRepository.getAnimal(index);
    }
    public void clearAnimals() {
        animalRepository.clearAnimals();
    }

}
