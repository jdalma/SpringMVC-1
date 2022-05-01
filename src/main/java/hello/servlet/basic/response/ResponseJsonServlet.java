package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet" , urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // HTTP 응답으로 JSON을 반환할 때는 content-type을 application/json으로 지정해야 한다
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("테스트");
        helloData.setAge(10);

        // jackson 라이브러리가 제공하는 objectMapper.writerValueAsString()을 사용하면 객체를 JSON 문자로 변경할 수 있다
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
