import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.*;

@WebListener
public class MyContexteListener implements ServletContextListener {
    Connection con = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.postgresql.Driver");
            // connexion à la base
            String url = "jdbc:postgresql://localhost:5432/da2i";
            String nom = "postgres";
            String mdp = "ln9aqz9u";
            con = DriverManager.getConnection(url, nom, mdp);
            sce.getServletContext().setAttribute("connexion", con);
            System.out.println("Connexion OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            con = (Connection) sce.getServletContext().getAttribute("connexion");
            con.close();
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
