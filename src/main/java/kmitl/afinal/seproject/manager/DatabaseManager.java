package kmitl.afinal.seproject.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager instance;

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private DatabaseManager() {

    }

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://sedbinstance.cprxongiqwog.ap-southeast-1.rds.amazonaws.com:3306/sedb";

    //  Database credentials
    static final String USER = "adminseproject";
    static final String PASS = "sefinal2017";

    private Connection connection;

    public DatabaseManager init() {
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);
            // Open a connection
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
