import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by davehochstrasser on 8/30/16.
 */
public class AnimalServiceTest {
    AnimalRepository animalRepository = new AnimalRepository("test.json");
    AnimalsService service = new AnimalsService(animalRepository);

    public AnimalServiceTest() throws IOException {
    }

    @Test
    public void returnListOfAnimalsWhenCalledTest() throws IOException{
        //Arrange

        service.clearAnimals();
        service.createAnimal("Joe", "dog", "Lab", "Black");

        //Act
        Animal anAnimal = service.getAnimal(0);


        //Assert
        assertThat(anAnimal.getName(),is(equalTo("Joe")));
    }

    @Test
    public void createAnimalWhenCalledTest() throws IOException{
        //Arrange
        service.clearAnimals();
        service.createAnimal("Sam","cat","calico","mixed");
        service.createAnimal("Bill","ferret","","Grey");
        service.createAnimal("Alex","pig","pot belly","pink");
        //Act
        Animal anAnimal = service.getAnimal(1);
        //Assert
        assertThat(anAnimal.getName(),is(equalTo("Bill")));
        assertThat(anAnimal.getBreed(),is(equalTo("")));
        service.clearAnimals();
    }

    @Test
    public void updateAnimalWhenCalled() throws IOException{
        //Arrange
        service.clearAnimals();
        service.createAnimal("Bill","ferret","","Grey");

        //Act
        service.updateAnimal("Joe","rat","","yellow",0);


        //Assert
        assertThat(service.getAnimal(0).getName(),is(equalTo("Joe")));

    }

    @Test
    public void deleteAnimalWhenCalled() throws IOException {
        //Arrange
        service.clearAnimals();
        service.createAnimal("Bill","ferret","","Grey");
        //Act
        service.deleteAnimal(0);


        //Assert
        assertThat(service.listAnimals(),is(not(equalTo(0))));

    }
















}
