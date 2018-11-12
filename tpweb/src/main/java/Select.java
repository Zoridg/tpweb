import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/BDD/Select")
public class Select extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<!doctype html>");
        out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"couleur.css\"><title>Affichage table</title></head>");
        out.println("<body><center><h1>Table " + req.getParameter("table") + "</h1>");

        Connection con = (Connection) this.getServletContext().getAttribute("connexion");
        System.out.println("Connexion récupérée");
        ArrayList<String> meta;
        // Enregistrement du driver
        try {
            String table = req.getParameter("table");
            String query = "select * from " + table;
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int nbCols = rsmd.getColumnCount();
            out.println("<table>");
            out.print("<tr>");
            for (int i = 1; i <= nbCols; i++) {
                out.println("<th>" + rsmd.getColumnName(i).toUpperCase() + "</th>");
            }
            out.println("</tr>");
            out.print("<tr>");
            while (rs.next()) {
                for (int i = 1; i <= nbCols; i++) {
                    out.println("<td>" + rs.getString(i) + "</td>");
                }
                out.println("<td><a href=\"Delete?table=" + table + "&cle=" + rs.getString(1) + "\">Del</a><input name=\"table\" type=\"hidden\" value=\"" + table + "\">");
                out.println("</tr>");
            }
            out.println("<tr>");
            out.println("<form method=\"get\" action=\"Insert\"><input name=\"table\" type=\"hidden\" value=\"" + table + "\">");
            for (int i = 0; i < nbCols; i++) {
                out.println("<td>");
                String type = "text";
                if (!rsmd.getColumnTypeName(i + 1).equals("serial")) {
                    if (rsmd.getColumnType(i + 1) == Types.INTEGER
                            || rsmd.getColumnType(i + 1) == Types.NUMERIC)
                        type = "number";
                    else if (rsmd.getColumnType(i + 1) == Types.DATE)
                        type = "date";
                    out.println(rsmd.getColumnName(i + 1) + "<br/><input placeholder=\"Veuillez entrer "
                            + rsmd.getColumnName(i + 1) + "\" name=\""
                            + rsmd.getColumnName(i + 1) + "\" type=\"" + type + "\" required/><br/>");
                }
                out.println("<td");
            }

            out.println("</tr>");
            out.println("</table>");
            out.println("<button type=\"reset\" value=\"Reset\">Effacer</button><button type=\"submit\" value=\"Submit\">Envoyer</button></form>");
            out.println("</center></body>");
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
