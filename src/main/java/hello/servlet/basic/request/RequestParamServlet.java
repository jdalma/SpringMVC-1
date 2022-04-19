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
    }

}
