package peaksoft.config;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author kurstan
 * @created at 19.01.2023 11:45
 */
public class DatabaseConnection {

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/kustan",
                    "postgres",
                    "postgres");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
