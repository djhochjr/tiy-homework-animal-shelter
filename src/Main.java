import java.io.IOException;

/**
 * Created by davehochstrasser on 8/21/16.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        AnimalRepository animalRepository = new AnimalRepository("animals.json");
        AnimalsService service = new AnimalsService(animalRepository);

        MenuService menu = new MenuService();
        //driver code to help with testing
        //service.createAnimal("joe","dog","lab", "black");
        boolean repeat = true;
        //while statement loops thru below
        while (repeat) {
            int action = MenuService.promptForMainMenuSelection();
            //if else ifs carry following to menuServices
            if (action == MenuService.LIST_ANIMAL) {
                menu.displayAnimalsList(service);
            } else if (action == MenuService.CREATE_ANIMAL) {
                menu.createAnimal(service);
            } else if (action == MenuService.VIEW_DETAILS) {
                menu.viewDetails(service);
            } else if (action == MenuService.EDIT_ANIMAL) {
                menu.editAnimal(service);
            } else if (action == MenuService.DELETE_ANIMAL) {
                menu.deleteAnimal(service);

            } else if (action == MenuService.QUIT) {
                repeat = menu.quit();
            }
        }
    }
}




