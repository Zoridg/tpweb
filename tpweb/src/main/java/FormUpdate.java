import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/BDD/FormUpdate")
public class FormUpdate extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<!doctype html>");
        out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"couleur.css\"><title>Formulaire Update</title></head>");
        out.println("<body><center><h1>Formulaire Update</h1>");
        out.println("<form method=\"post\" action=\"Update\">");
        out.println("</center></body>");
    }
}
