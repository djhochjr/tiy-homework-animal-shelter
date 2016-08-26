import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by davehochstrasser on 8/23/16.
 */

/*Practically a mirror image of the Animal Service Class with ties to afford file storage.
*They are intertwined btu not mutually exclusive.
 */
public class AnimalRepository {

    private ArrayList<Animal> animals = new ArrayList<>();
    private final Path filePath;
    //Sets up json/gson conversion with a path for storage and retrieval.
    public AnimalRepository(String file) throws IOException {

        filePath = Paths.get(file);

        if (Files.exists(filePath)) {
            String json = new String(Files.readAllBytes(filePath));
            Type listType = new TypeToken<ArrayList<Animal>>() {
            }.getType();

            animals = new Gson().fromJson(json, listType);
        }
    }

    //ArrayList of animals
    public ArrayList<Animal> listAnimals() {
        return animals;
    }
    //Method to find an animal
    public Animal getAnimal(int index) {
        return animals.get(index);
    }
    //Method for creating a new animal employing persist.
    public void createAnimal
            (String name, String species, String breed, String description) throws IOException {
        Animal one = new Animal(name, species, breed, description);
        animals.add(one);

        persist();

    }
    public void updateAnimal
            (String name, String species, String breed, String description) throws IOException {
        Animal one = new Animal(name, species, breed, description);
        //animals.add(one);

        persist();

    }
    //Method for trashing an animal employing persist.
    public void deleteAnimal(int index) throws IOException {
        animals.remove(index);
        persist();
    }
    //The method for ensuring persistence to and from disk.
    private void persist() throws IOException {
        String json = new Gson().toJson(animals);
        Files.write(filePath, json.getBytes());
    }

}


