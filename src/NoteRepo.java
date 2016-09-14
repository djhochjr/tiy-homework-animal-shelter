import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by davehochstrasser on 9/6/16.
 */
public class NoteRepository {

    private final Connection conn;

    public NoteRepository(Connection conn) throws IOException {
        this.conn = conn;
    }

    public void createNote(Note note, int animalId) throws SQLException {
        PreparedStatement statement = this.conn
                .prepareStatement("INSERT INTO note (text, animalid) VALUES (?, ?) RETURNING noteid");

        statement.setString(1, note.getText());
        statement.setInt(2, animalId);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            note.setId(result.getInt("id"));
        }
    }

    public ResultSet listNotes(int animalId) throws SQLException {
        PreparedStatement statement = this.conn
                .prepareStatement("SELECT * FROM note WHERE animalId = ?");

        statement.setInt(1, animalId);

        return statement.executeQuery();

    }
}
