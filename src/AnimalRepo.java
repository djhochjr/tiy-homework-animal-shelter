import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by davehochstrasser on 8/23/16.
 */

/*Practically a mirror image of the Animal Service Class with ties to afford file storage.
*They are intertwined btu not mutually exclusive.
 */
public interface AnimalRepository {



    ResultSet listAnimals() throws SQLException;

    ResultSet listAnimalsById(Integer id) throws SQLException;

    ResultSet listAnimalsByType(String type) throws SQLException;

    ResultSet listAnimalsByName(String name) throws SQLException;

}


