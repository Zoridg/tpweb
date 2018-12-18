import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/servlet-Info")
public class Info extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<HTML><BODY>");
        out.println("<table border=1>");
        Enumeration enTetes = req.getHeaderNames();
        while (enTetes.hasMoreElements())
        {
            String enTete = (String) enTetes.nextElement();
            out.println("<tr><td>" + enTete
                    + "</td><td>" + req.getHeader(enTete)
                    + "</td></tr>");
        }
        out.println("</table>");
        out.println("</HTML>");
    }
}
