import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/Select")
public class Select extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<!doctype html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">\n" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "<script src=\"https://code.jquery.com/jquery-1.12.4.min.js\"></script>\n" +
                "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
        out.println("<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.5.0/css/all.css\" integrity=\"sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU\" crossorigin=\"anonymous\">");
        out.println("<title>Affichage table</title></head>");
        out.println("<body><center style=\"margin-bottom: 10px;\"><div class=\"container-fluid\"><h1 class=\"h1\" style=\"font-family: -webkit-body; font-size: -webkit-xxx-large; color: black;\">Table " + req.getParameter("table") + "</h1></div>");

        Connection con = (Connection) this.getServletContext().getAttribute("connexion");
        System.out.println("Connexion récupérée");
        String table = req.getParameter("table");
        String query = "select * from " + table;

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int nbCols = rsmd.getColumnCount();
            out.println("<table class=\"table table-hover table-bordered\">");
            out.println("<thead style=\"background: black;\">");
            out.print("<tr>");
            for (int i = 1; i <= nbCols; i++) {
                out.println("<th style=\"text-align: center; color: white;\"scope=\"col\">" + rsmd.getColumnName(i).toUpperCase() + "</th>");
            }
            out.println("</tr>");
            out.println("</thead>");
            out.print("<tr>");
            while (rs.next()) {
                for (int i = 1; i <= nbCols; i++) {
                    out.println("<td scope=\"row\">" + rs.getString(i) + "</td>");
                }
                out.println("<td scope=\"row\"><a href=\"Delete?table=" + table + "&cle=" + rs.getString(1) + "\"><button class=\"fa fa-trash\" style=\"color: black;\"></button></a><input name=\"table\" type=\"hidden\" value=\"" + table + "\">");
                out.println("<td scope=\"row\"><a href=\"FormUpdate?table=" + table + "&cle=" + rs.getString(1) + "\"><button class=\"fa fa-pencil-alt\" style=\"color: black;\"></button></a><input name=\"table\" type=\"hidden\" value=\"" + table + "\">");
                out.println("</tr>");
            }
            out.println("<tr>");
            out.println("<br/>");
            out.println("<form method=\"get\" action=\"Insert\">");
            out.println("<div class=\"form-group\">");
            out.println("<input name=\"table\" type=\"hidden\" value=\"" + table + "\">");
            for (int i = 1; i <= nbCols; i++) {
                out.println("<td>");
                String type = "text";
                if (!rsmd.getColumnTypeName(i).equals("serial")) {
                    if (rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.NUMERIC) {
                        type = "number";
                    } else if (rsmd.getColumnType(i) == Types.DATE) {
                        type = "date";
                    }
                    out.println("<label class=\"label label-default\">" + rsmd.getColumnName(i) + "</label>");
                    out.println("<br/><input class=\"form-control\" placeholder=\"Veuillez entrer " + rsmd.getColumnName(i) + "\" name=\"" + rsmd.getColumnName(i) + "\" type=\"" + type + "\" required/><br/>");
                }
                out.println("</td>");
            }
            out.println("</div>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<button class=\"btn btn-danger\" style=\"width: 100px;\" type=\"reset\" value=\"Reset\">Effacer</button><button class=\"btn btn-primary\" style=\"margin-left: 5px; width: 100px;\" type=\"submit\" value=\"Submit\">Envoyer</button></form>");
            out.println("</center></body>");
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
