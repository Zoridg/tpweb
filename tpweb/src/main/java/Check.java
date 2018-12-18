import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Check extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String table = req.getParameter("table");
        int cpt = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = (Connection) this.getServletContext().getAttribute("connexion");

        try {
            String queryPK = " SELECT c.column_name, c.ordinal_position " +
                    "FROM information_schema.key_column_usage AS c " +
                    "LEFT JOIN information_schema.table_constraints AS t " +
                    "ON t.constraint_name = c.constraint_name " +
                    "WHERE t.table_name = \'" + table + "\' AND t.constraint_type = 'PRIMARY KEY';";
            System.out.println(queryPK);
            ps = con.prepareStatement(queryPK);
            rs = ps.executeQuery();

            while (rs.next()) {
                this.getServletContext().setAttribute("primarykey" + cpt, rs.getString(1));
                System.out.println("Attribute set : " + "primarykey" + cpt + " : " + rs.getString(1));
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        RequestDispatcher disp = req.getRequestDispatcher("Select?table=" + table);
        disp.forward(req, res);
    }
}
