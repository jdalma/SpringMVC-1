package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RequstParamServlet" , urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end\n");

        System.out.println("[단일 파라미터 조회] - start");
        String first = request.getParameter("first");
        String second = request.getParameter("second");
        System.out.println(first + " " + second);
        System.out.println("[단일 파라미터 조회] - end\n");

        System.out.println("[이름이 같은 복수 파라미터 조회] - start");
        String[] params = request.getParameterValues("first");
        for(String p : params){
            System.out.print(p + " ");
        }
        // 키 값이 중복일 때 getParameter를 사용하면 getParameterValues의 첫 번째 값을 반환한다.
        System.out.println("\n[이름이 같은 복수 파라미터 조회] - end\n");

        // POST 방식도 위와 같이 getParam 관련 메서드들로도 꺼낼 수 있다.
        // content-type : HTTP 메시지 바디의 데이터 형식을 지정한다
        // GET URL 쿼리 파라미터 형식으로 클라이언트에서 서버로 데이터를 전달할 때는 HTTP 메시지 바디를 사용하지 않기 때문에 content-type이 없다
        // POST HTML FORM 형식으로 데이터를 전달하면 HTTP 메시지 바디에 해당 데이터를 포함해서 보내기 때문에 바디에 포함된 데이터가 어떤 형식인지 content-type을 꼭 지정해야 한다.
        // 폼으로 데이터를 전송하는 형식을 application/x-www-form-urlencoded라 한다
    }

}
