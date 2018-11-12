
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/BDD/Delete")
public class Delete extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<head><title>servlet Lister</title>");
        out.println("<META content=\"charset=UTF-8\"> </head><body><center>");
        String table = req.getParameter("table");
        String cle = req.getParameter("cle");
        Connection con = (Connection) this.getServletContext().getAttribute("connexion");

        try {
            String select = "Select * from " + table;
            PreparedStatement ps = con.prepareStatement(select);
            System.out.println(select);
            ResultSet rs = ps.executeQuery();
            String id = rs.getMetaData().getColumnLabel(1);
            String delete = "Delete from " + table + " where " + id + " = " + cle;
            ps = con.prepareStatement(delete);
            System.out.println(delete);
            ps.executeUpdate();

            RequestDispatcher disp = req.getRequestDispatcher("Select?table=" + table);
            disp.forward(req, res);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}