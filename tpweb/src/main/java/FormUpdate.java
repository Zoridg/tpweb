import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/FormUpdate")
public class FormUpdate extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<!doctype html>");
        out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"couleur.css\"><title>Formulaire Update</title></head>");
        out.println("<body><center><h1>Formulaire Update</h1>");
        String table = req.getParameter("table");
        String cle = req.getParameter("cle");
        Connection con = (Connection) this.getServletContext().getAttribute("connexion");

        try {
            String select = "Select * from " + table;
            System.out.println(select);
            PreparedStatement ps = con.prepareStatement(select);
            System.out.println(select);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            String id = rsmd.getColumnName(1);
            int nbCols = rsmd.getColumnCount();

            String selectLine = "SELECT * FROM " + table + " WHERE " + id + " = " + cle;
            System.out.println(selectLine);
            ps = con.prepareStatement(select);
            System.out.println(select);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            out.println("<form method=\"get\" action=\"Update\"><input name=\"table\" type=\"hidden\" value=\"" + table + "\">");
            for (int i = 1; i <= nbCols; i++) {
                String type = "text";
                if (!rsmd.getColumnTypeName(i).equals("serial")) {
                    if (rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.NUMERIC){
                        type = "number";
                    }
                    else if (rsmd.getColumnType(i) == Types.DATE){
                        type = "date";
                    }
                    rs.next();
                    out.println(rsmd.getColumnName(i) + "<br/><input name=\"" + rsmd.getColumnName(i) + "\" type=\"" + type + "\" value=\"" + rs.getString(i) + "\" required/><br/>");
                }
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        out.println("<button type=\"reset\" value=\"Reset\">Effacer</button><button type=\"submit\" value=\"Submit\">Envoyer</button></form>");
        out.println("</form>");
        out.println("</center></body>");
    }
}
