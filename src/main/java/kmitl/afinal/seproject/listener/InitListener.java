package kmitl.afinal.seproject.listener;

import kmitl.afinal.seproject.manager.DatabaseManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener()
public class InitListener implements ServletContextListener {

    private Connection connection;

    public void contextInitialized(ServletContextEvent sce) {
        connection = DatabaseManager.getInstance().init().getConnection();
        sce.getServletContext().setAttribute("connection", connection);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
