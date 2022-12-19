import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "timeServlet", value = "/time")
public class TimeServlet extends HttpServlet{
  public void init(){}

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      DateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String currentTime = dtFormat.format(new Date());

      req.setAttribute("currentTime", currentTime);
      // resp.setHeader("currentTime", currentTime);
      req.getRequestDispatcher("/WEB-INF/time.jsp").forward(req, resp);
      // System.out.println(currentTime);
  }
}
// @WebServlet(name = "helloServlet", value = "/hello-servlet")
// public class HelloServlet extends HttpServlet {
//     private String message;

//     public void init() {
//         message = "Hello World!";
//     }

//     public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//         response.setContentType("text/html");

//         // Hello
//         PrintWriter out = response.getWriter();
//         out.println("<html><body>");
//         out.println("<h1>" + message + "</h1>");
//         out.println("</body></html>");
//     }

//     public void destroy() {
//     }
// }
