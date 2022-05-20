package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import javafx.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/v1/ 에 속해 있는 요청들은 모두 여기서 처리한다
@WebServlet(name = "frontControllerServletV1" , urlPatterns = ("/front-controller/v1/*"))
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String , ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerServletV1() {
        // 2022.05.10 다형성을 활용
        controllerV1Map.put("/front-controller/v1/members/new-form" , new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save" , new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members" , new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        System.out.println("FrontController-V1" + requestURI);
        ControllerV1 controller = controllerV1Map.get(requestURI);

        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request , response);
    }
}
