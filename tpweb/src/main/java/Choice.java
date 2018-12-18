
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/Choice")
public class Choice extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<!doctype html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">\n" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "<script src=\"https://code.jquery.com/jquery-1.12.4.min.js\"></script>\n" +
                "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
        out.println("<title>Choix de la table</title></head>");
        out.println("<body style=\"padding: 20px;\"><center style=\"margin-bottom: 10px;\">");
        out.println("<br /><br /><h1>Choisissez une table</h1>");
        out.println("<form method=\"get\" action=\"Select\">");
        out.println("<div class=\"form-group\">");
        out.println("<input class=\"form-control\" style=\"width: 40%;\" placeholder=\"Selectionnez une table\" name=\"table\" type=\"text\" required>");
        out.println("<br />");
        out.println("<button class=\"btn btn-danger\" style=\"width: 100px; margin-top: 5px;\" type=\"reset\" value=\"Reset\">Effacer</button>" +
                "<button class=\"btn btn-primary\" style=\"margin-left: 5px; margin-top: 5px; width: 100px;\" type=\"submit\" value=\"Submit\">Envoyer</button></form>");
        out.println("</div></form></body></html>");
    }
}