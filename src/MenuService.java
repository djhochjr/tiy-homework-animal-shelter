import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by davehochstrasser on 8/21/16.
 */

/*creates Class MenuService to declare values final for certain variables.
*so the will hold true throughout the project.
 */
public class MenuService {

    public static final int LIST_ANIMAL = 1;
    public static final int CREATE_ANIMAL = 2;
    public static final int VIEW_DETAILS = 3;
    public static final int EDIT_ANIMAL = 4;
    public static final int DELETE_ANIMAL = 5;
    public static final int QUIT = 6;

    /*sets up the actual --Main Menu-- that prints to the screen
    *along with calling another method wiatForInt which allows for user input
     */
    public static int promptForMainMenuSelection() {
        System.out.println("\n-- Main Menu --\n" +
                "\n" +
                "1) List animals\n" +
                "2) Create an animal\n" +
                "3) View animal details\n" +
                "4) Edit an animal\n" +
                "5) Delete an animal\n" +
                "6) Quit\n");
        //calls the method
        return waitForInt("Please choose an option :");
    }

    /*sets up the user to input data throughout the program.
    *makes sure what's entered is within a given range
    *if not loops them back to try again.
     */
    public static int waitForInt(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);

        String input = scanner.nextLine();

        int value;
        try {
            value = Integer.parseInt(input);

        } catch (Exception e) {
            System.out.println("\nPlease provide a number.\n");

            value = waitForInt(message);
        }
        if (value <= 0 || value > 6) {
            System.out.println("\nError: Sorry, that isn't a valid option.");
            System.out.println("Please choose options 1-6");
        }

        return value;

    }

    //loop to show animals present if any. if none lets the user know
    public void displayAnimalsList(AnimalsService service) {
        ArrayList<Animal> animals = service.listAnimals();
        int x = 1;
        if ((animals.size()) == (0)) {
            System.out.println("Nothing like an animal yet. Try again!!");
        } else {
            System.out.println("--List of Animals--");
            //gets animals in stated format and lists them accordingly
            for (Animal anAnimal : animals) {
                String name = anAnimal.getName();
                String species = anAnimal.getSpecies();
                System.out.printf("%s) %s \t %s\n", x, name, species);
                x++;
            }

        }


    }

    /*method for creating animal with catches for
     *mandatory fields. Employs mustDo method for catch
    */
    public void createAnimal(AnimalsService service) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Animal> animals = service.listAnimals();
        String name, species, breed, description;

        System.out.println("--Create an Animal--");
        System.out.println("\n Please fill in the blanks.");

        System.out.println("\n Animal name: ");
        name = mustDo();

        System.out.println("Species: ");
        species = mustDo();

        System.out.println("Breed(optional): ");
        breed = scanner.nextLine();

        System.out.println("Description: ");
        description = mustDo();

        service.createAnimal(name, species, breed, description);


    }
    //carries through viewing details, making sure an animal from the list is chosen. Prompts if not.
    public void viewDetails(AnimalsService service) {
        System.out.println("--View Details--\n");
        int option = waitForInt("What is the numeric value of the animal you'd like to view") - 1;
        ArrayList<Animal> animals = service.listAnimals();
        if ((option <= animals.size() - 1) && option >= 0) {

            System.out.println("\n Name: " + animals.get(option).getName());
            System.out.println("Species: " + animals.get(option).getSpecies());
            System.out.println("Breed: " + animals.get(option).getBreed());
            System.out.println("Description: " + animals.get(option).getDescription());
        } else {
            System.out.println("Please choose from the list.");
            displayAnimalsList(service);
            viewDetails(service);
        }
    }
    //Gives option to edit animal in toto or just parts ensuring that only the data that's meant to be changed is.
    public void editAnimal(AnimalsService service) {
        Scanner scanner = new Scanner(System.in);
        //String input = scanner.nextLine();

        System.out.println("--Edit Animal--\n");
        int option = waitForInt("What is the numeric value of the animal you'd like to edit") - 1;
        ArrayList<Animal> animals = service.listAnimals();
        Animal anAnimal = animals.get(option);

        String name, species, breed, description;

        if ((option <= animals.size() - 1) && option >= 0) {
            System.out.printf(" Name [%s]:\n", animals.get(option).getName());
            name = scanner.nextLine();
            System.out.printf("Species [%s]:\n ", animals.get(option).getSpecies());
            species = scanner.nextLine();
            //animals.get(option).setSpecies(scanner.nextLine());
            System.out.printf("Breed [%s]:\n ", animals.get(option).getBreed());
            breed = scanner.nextLine();
            //animals.get(option).setBreed(scanner.nextLine());
            System.out.printf("Description [%s]:\n ", animals.get(option).getDescription());
            description = scanner.nextLine();
            //animals.get(option).setDescription(scanner.nextLine());

            Animal revisedAnimal = noEnter(name, species, breed, description, anAnimal);

            System.out.println("Excellent!! Your update was successful.\n"
                    + revisedAnimal.getName() + " "
                    + revisedAnimal.getSpecies() + " "
                    + revisedAnimal.getBreed() + " "
                    + revisedAnimal.getDescription());
            service.updateAnimal(name, species, breed, description);

        } else {
            System.out.println("Wrong answer!!! Try again, please.");
            editAnimal(service);
        }

    }
    //Method for deleting an animal with catch to make sure that's the desire.
    public void deleteAnimal(AnimalsService service) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--Delete Animal--\n");
        int option = waitForInt("What is the numeric value of the animal you'd like to delete") - 1;
        ArrayList<Animal> animals = service.listAnimals();
        if ((option <= animals.size() - 1) && option >= 0) {

            System.out.printf(" Name : " + animals.get(option).getName());
            System.out.println("Species: " + animals.get(option).getSpecies());
            System.out.println("Breed: " + animals.get(option).getBreed());
            System.out.println("Description: " + animals.get(option).getDescription());
            System.out.println("Are you sure?");

            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println(" Gone");
                service.deleteAnimal(option);
            } else if (scanner.nextLine().equalsIgnoreCase("no")) {
                deleteAnimal(service);
            }
        } else {
            System.out.println("Hey! That wasn't a valid entry. Try again!");
            deleteAnimal(service);
        }

    }
    //Boolean method with safety catch.
    public boolean quit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nAre you sure you want to quit (yes or no)?");
        System.out.println("If you choose 'yes', all of your data will be lost!");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("yes")) {
            System.out.println(" See ya");
            return false;
        } else if (input.equalsIgnoreCase("no")) {
            return true;
        } else {
            System.out.println(" Wrong answer, try again!!");
            quit();
        }
        return true;
    }
    //Method for ensuring certain fields are filled when creating an animal
    String mustDo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("")) {
            System.out.println("A value must be entered in this field.");
            mustDo();
        }
        return input;
    }
    //Method employed in editAnimal keeping data from being changed when just 'enter' is stroked.
    public Animal noEnter(String name, String species, String breed, String description, Animal animals) {

        if (!name.equals("")) {
            animals.setName(name);
        }
        if (!species.equals("")) {
            animals.setSpecies(species);
        }
        if (!breed.equals("")) {
            animals.setBreed(breed);
        }
        if (!description.equals("")) {
            animals.setDescription(description);
        }

        return animals;
    }

}








