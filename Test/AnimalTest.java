import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by davehochstrasser on 8/25/16.
 */
public class AnimalTest {

    private Animal animal;

    @Before
    public void createAnAnimal() {
        //arrange
        animal = new Animal("Fred", "Cat", "", "Cute");

    }
    @Test
    public void nameWillSetNameTest() {

        // Act
        animal.setName("dave");

        //Assert
        assertTrue(animal.getName()=="dave");

    }

    @Test
    public void speciesWillSetSpeciesTest() {
        //Act
        animal.setSpecies("dog");

        //Assert
        assertTrue(animal.getSpecies()== "dog");
    }


    @Test
    public void breedWillSetBreedTest() {
        //Act
        animal.setBreed("Black Lab");
        //Assert
        assertTrue(animal.getBreed()=="Black Lab");
    }

    @Test
    public void setDescription() {
        //Act
        animal.setDescription("black and hairy");
        //Assert
        assertTrue(animal.getDescription() == "black and hairy");
    }

}


