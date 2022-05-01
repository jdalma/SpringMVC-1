package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name ="responseHeaderServlet" , urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK);
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        // [response-headers]
        response.setHeader("Content-Type" , "text/plain;charset=utf-8");
            // [Cache 없애는 설정]
        response.setHeader("Cache-Control" , "no-cache , no-store , must-revalidate");
        response.setHeader("Pragma" , "no-cache");
            // [/Cache 없애는 설정]

        // [header 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);

        // [message body]
        PrintWriter writer = response.getWriter();
        writer.println("abc");
    }

    private void content(HttpServletResponse response){
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//        response.setContentLength(3); // 생략시 자동 생성
    }

    private void cookie(HttpServletResponse response){
        Cookie cookie = new Cookie("myCookie" , "good");
        cookie.setMaxAge(600); // 초;
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FOUND); // 302
//        response.setHeader("Location"  , "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
