import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbHelper {
    Connection con = null;

    public void OpenDatabase(){
        try {
            con = DriverManager.getConnection("jdbc:sqlite:myFirstDatabase");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
