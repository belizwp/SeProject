package kmitl.afinal.seproject.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener()
public class InitListener implements ServletContextListener {

    private DataSource dataSource;
    private Connection connection;

    public void contextInitialized(ServletContextEvent sce) {
        try {
            dataSource = getSeDb();
            connection = dataSource.getConnection();
            sce.getServletContext().setAttribute("dataSource", dataSource);
            sce.getServletContext().setAttribute("connection", connection);
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DataSource getSeDb() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/sedb");
    }
}
