import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/FormUpdate")
public class FormUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
        out.println("<title>Update</title></head>");
        out.println("<body><center><h1 class=\"h1\">Formulaire Update</h1>");
        String table = req.getParameter("table");
        String cle = req.getParameter("cle");
        Connection con = (Connection) this.getServletContext().getAttribute("connexion");

        try {
            String select = "Select * from " + table;
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            String id = rsmd.getColumnName(1);
            int nbCols = rsmd.getColumnCount();

            String selectLine = "select * from " + table + " where " + id + " = " + cle;
            ps = con.prepareStatement(selectLine);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            out.println("<form method=\"post\" action=\"FormUpdate\">");
            out.println("<div class=\"form-group\">");
            out.println("<input name=\"table\" type=\"hidden\" value=\"" + table + "\">");
            out.println("<input name=\"cle\" type=\"hidden\" value=\"" + cle + "\">");
            rs.next();
            for (int i = 1; i <= nbCols; i++) {
                String type = "text";
                if (!rsmd.getColumnTypeName(i).equals("serial")) {
                    if (rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.NUMERIC) {
                        type = "number";
                    } else if (rsmd.getColumnType(i) == Types.DATE) {
                        type = "date";
                    }
                    out.println("<label class=\"label label-default\">" + rsmd.getColumnName(i) + "</label>");
                    out.println("<br/><input class=\"form-control\" style=\"width: 7%;\"name=\"" + rsmd.getColumnName(i) + "\" type=\"" + type + "\" value=\"" + rs.getString(i) + "\" required/><br/>");
                }
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        out.println("<button class=\"btn btn-danger\" style=\"width: 100px;\" type=\"reset\" value=\"Reset\">Effacer</button><button class=\"btn btn-primary\" style=\"margin-left: 5px; width: 100px;\" type=\"submit\" value=\"Submit\">Envoyer</button></div>");
        out.println("</form>");
        out.println("</center></body>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Connection con = (Connection) this.getServletContext().getAttribute("connexion");
        String table = req.getParameter("table");
        String cle = req.getParameter("cle");
        String query = "select * from " + table + " limit 1";

        try {
            Map<String, String> parameters = new HashMap<String, String>();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int nbCols = rsmd.getColumnCount();

            String primary = "";
            String col;
            String value;
            rs.next();
            for (int i = 1; i <= nbCols; i++) {
                if (!rsmd.getColumnTypeName(i).equals("serial")) {
                    col = rsmd.getColumnName(i);
                    if (rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.NUMERIC) {
                        value = req.getParameter(col);
                    } else {
                        value = "\'" + req.getParameter(col) + "\'";
                    }
                    parameters.put(col, value);
                } else {
                    primary = rsmd.getColumnName(i);
                }
            }
            col = "";
            value = "";

            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                col += entry.getKey() + ", ";
                value += entry.getValue() + ", ";
            }
            query = "UPDATE " + table + " SET (" + col.substring(0, col.length() - 2) + ") = (" + value.substring(0, value.length() - 2)
                    + ") WHERE " + primary + " = " + cle + ";";
            System.out.println(query);

            ps = con.prepareStatement(query);
            ps.executeUpdate();

            RequestDispatcher disp = req.getRequestDispatcher("Select?table=" + table);
            disp.forward(req, res);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
