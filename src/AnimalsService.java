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

    public Animal getAnimal(int index) {
        try {
            return animalRepository.getAnimal(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
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
    public void updateAnimal(String name, String species, String breed, String description) {
        try {
            animalRepository.updateAnimal(name, species, breed, description);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAnimal(int index) {
        try {
            animalRepository.deleteAnimal(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
