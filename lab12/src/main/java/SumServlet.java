import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "sumServlet", value = "/sum")
public class SumServlet extends HttpServlet {
    // private String message;
    // public void init() {
    //     message = "Hello World!";
    // }
    // @Override
    // protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //     response.setContentType("text/html");

    //     // Hello
    //     PrintWriter out = response.getWriter();
    //     out.println("<html><body>");
    //     out.println("<h1>" + message + "</h1>");
    //     out.println("</body></html>");
    // }
    public void init(){}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        int a = Integer.parseInt(request.getParameter("a"));
        int b = Integer.parseInt(request.getParameter("b"));
        out.println("<h1> calculation result = " + (a + b)  + "</h1>");
        out.println("</body></html>");
    }
}
