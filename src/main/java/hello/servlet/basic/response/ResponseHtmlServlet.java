package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHtmlServlet" , urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-Type : text/html;charset=utf-8
        response.setContentType("text/html");
        // HTTP 응답으로 HTML을 반환할 때는 content-type을 text/html로 지정해야 한다.
        response.setCharacterEncoding("utf-8");

        PrintWriter writer = response.getWriter();
        writer.print("<html>");
        writer.print("<body>");
        writer.print("<div> Hello </div>");
        writer.print("</body>");
        writer.print("</html>");
    }
}
