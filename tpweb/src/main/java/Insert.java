
import java.io.*;
import java.sql.PreparedStatement;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;

@WebServlet("/Insert")
public class Insert extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<head><title>servlet first</title>");
        out.println("<META content=\"charset=UTF-8\"> </head><body><center>");
        String table = req.getParameter("table");
        Connection con = (Connection) this.getServletContext().getAttribute("connexion");

        try {
            Map<String, String> parameters = new HashMap<String, String>();
            Enumeration<String> names = req.getParameterNames();
            while (names.hasMoreElements()) {
                String current = names.nextElement();
                parameters.put(current, req.getParameter(current));
            }
            if (parameters.size() > 1) {
                String keys = "";
                String values = "";
                Iterator<String> i = parameters.keySet().iterator();
                while (i.hasNext()) {
                    String current = i.next();
                    if (!current.equals("table")) {
                        keys += current + ", ";
                        try {
                            values += Integer.valueOf(parameters.get(current)) + ", ";
                        } catch (Exception e) {
                            values += "'" + parameters.get(current) + "', ";
                        }
                    }
                }
                String query = "insert into " + table + " (" + keys.substring(0, keys.length() - 2) + ") values(" + values.substring(0, values.length() - 2) + ");";
                System.out.println(query);
                PreparedStatement ps = con.prepareStatement(query);
                ps.executeUpdate();
                RequestDispatcher disp = req.getRequestDispatcher("Select?table=" + table);
                disp.forward(req, res);

            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        out.println("</center></body>");

    }
}