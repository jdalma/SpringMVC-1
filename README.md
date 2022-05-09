[스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-1/dashboard)

# [Chapter1. Servlet](https://github.com/jdalma/SpringMVC-1/pull/1)

1. [HttpServletRequest HEADER , COOKIE 등 조회하기](https://github.com/jdalma/SpringMVC-1/pull/1/commits/1ecb280a27ba0d61f6f07099d0fde30892c3f1b0)
2. [HTTP 요청 데이터 GET 방식 쿼리 파라미터](https://github.com/jdalma/SpringMVC-1/pull/1/commits/f47791d1567e3f74b34176592ba3c2e13729b230)
3. [HTTP 요청 데이터 POST HTML Form 방식](https://github.com/jdalma/SpringMVC-1/pull/1/commits/588c17c608c5397e8c5cd1cb950f492a95772cdb)
4. [HTTP 요청 JSON 데이터를 Jackson을 사용하여 객체 매핑](https://github.com/jdalma/SpringMVC-1/pull/1/commits/5521a00ad93f980a7cd611760c5a6d69b95f53b4)
5. [HttpServletResponse Header , Cookie , Redirect , MessageBody 테스트](https://github.com/jdalma/SpringMVC-1/pull/1/commits/a0e20215ea0bab3c62bdfbbb2926538f5ef4b5b8)
6. [HTTP 응답 -> HTML , JSON형식](https://github.com/jdalma/SpringMVC-1/pull/1/commits/cf0200acae0889e41afc3fe296f457cddd7e47dd)

- **HTTP 요청 메세지** 로그로 확인하기
  - `logging.level.org.apache.coyote.http11=debug `
- `application/json` 은 스펙상 **utf-8** 형식을 사용하도록 정의되어 있다.
- 그래서 스펙에서 `charset=utf-8` 과 같은 추가 파라미터를 지원하지 않는다. 따라서 `application/json` 이라고만 사용해야지
- `application/json;charset=utf-8` 이라고 전달하는 것은 의미 없는 파라미터를 추가한 것이 된다. 
- **`response.getWriter()`를 사용하면 추가 파라미터를 자동으로 추가해버린다.**
- 이때는 **`response.getOutputStream()`으로 출력하면 그런 문제가 없다.**

***

# [Chapter2. Servlet , JSP , MVC Pattern](https://github.com/jdalma/SpringMVC-1/pull/2)

1. [Member (In Memory) 도메인 및 테스트 코드 추가](https://github.com/jdalma/SpringMVC-1/pull/2/commits/a0f659ca3b26e24fc6e4c9ec46ee91835d1e371c)
2. [gradle JSP 추가](https://github.com/jdalma/SpringMVC-1/pull/2/commits/6c9300c84fc5fdd0e113c530fa98d11d5a24c973)
3. [Servlet으로 회원 관리](https://github.com/jdalma/SpringMVC-1/pull/2/commits/c358fd1bf0825479d9d6248749029ddddb6b8efa)
4. [JSP로 회원 관리](https://github.com/jdalma/SpringMVC-1/pull/2/commits/313c5f90d2070fdde98ebe9d8763beebd2e2b531)
5. [MVC 패턴으로 회원관리](https://github.com/jdalma/SpringMVC-1/pull/2/commits/68b96b4bfad2257eac741055e538cc14ffb99ecd)

## **서블릿과JSP의 한계**
- 서블릿으로 개발할 때는 `뷰(View)`화면을 위한 HTML을 만드는 작업이 자바 코드에 섞여서 지저분하고 복잡했다.
- JSP를 사용한 덕분에 뷰를 생성하는 HTML 작업을 깔끔하게 가져가고 , 동적으로 변경이 가능하다
    - **하지만 비즈니스 로직과 HTML이 섞여 JSP가 너무 많은 역할을 한다.**

## **MVC 패턴의 등장**
1. `JSP`가 너무 많은 역할을 한다.
2. `UI`를 일부 수정하는 일과 `비즈니스 로직`을 수정하는 일은 각각 다르게 발생할 가능성이 매우 높고 대부분 서로에게 영향을 주지 않는다.
    - **이렇게 변경의 라이프 사이클이 다른 부분을 하나의 코드로 관리하는 것은 유지보수하기 좋지 않다.**
3. `JSP`같은 **뷰 템플릿**은 화면을 랜더링 하는데 최적화 되어 있기 때문에 해당 업무만 하는 것이 가장 효과적이다.

## **Controller**
- HTTP요청을 받아서 파라미터를 검증하고 , 비즈니스 로직을 실행
- `VIew`에 전달할 결과 데이터를 조회해서 `Model`에 담는다

![MVC](https://raw.githubusercontent.com/jdalma/jdalma.github.io/master/assets/images/spring-mvc/mvc.png)

## **Model**
- `View`에 출력할 데이터를 담아둔다
- *`View`가 필요한 데이터를 모두 `Model`에 담아서 전달해주는 덕분에 `View`는 비즈니스 로직이나 데이터 접근을 몰라도 되고 화면을 랜더링 하는 일에 집중할 수 있다*

## **View**
- `Model`에 담겨있는 데어티를 사용해서 화면을 그리는 일에 집중한다.

## **MVC 패턴의 한계**
1. **`forward(request , response)` 중복**
    - `View`로 이동하는 코드가 항상 중복 호출

```java
  RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
  dispatcher.forward(request , response);
```

2. **`ViewPath`에 중복**
    - **prefix** : `/WEB-INF/views`
    - **suffix** : `.jsp`
      - 만약 `.jsp`를 타임리프로 바꿔야 한다면 모든 코드를 다 변경해야 한다.

```java
  String viewPath = "/WEB-INF/views/new-form.jsp";
```

3. **공통 처리가 어렵다**
    - 단순히 공통 기능을 메서드로 추출해내면 될 것 같지만 , 결과적으로 공통 메서드를 항상 호출해야 하고 , 실수로 호출하지 않으면 문제가 된다
      - **호출하는 것 자체도 중복이다**
    - 위의 문제를 해결하려면 `Controller`호출 전에 먼저 공통 기능을 처리해야 한다.
    - **Front Controller 패턴**을 도입하면 이런 문제를 깔끔하게 해결할 수 있다.
      - *Spring MVC의 핵심도 바로 이 `Front Controller`에 있다*

## Form Action **절대 경로**
- `현재 URL이 속한 계층 경로` + `/save`

```html
    <!-- 상대경로 사용 -->
    <form action="save" method="post">
        username: <input type="text" name="username" />
        age: <input type="text" name="age" />
        <button type="submit">전송</button>
    </form>
```

## WEB-INF 폴더
- 외부에서 직접적으로 호출하지 못하게 , `Controller`에서 접근할 때 **WEB-INF**안에 페이지를 넣는다면 외부에서 직접 찾지 못한다.

## RequestDispatcher

- `dispatcher.forward(request , response);`
  - 다른 서블릿이나 JSP로 이동할 수 있게 서버 내부에서 재호출 
- `forward`
  - **서버 내부에서 일어나는 호출**이기 때문에 클라이언트가 전혀 인지하지 못 한다.
- `redirect`
  - **실제 클라이언트(웹 브라우저)** 에 응답이 나갔다가 , **클라이언트가 `redirect`경로로 다시 요청**한다.
  - **URL**경로도 변경된다.

```java
    String viewPath = "/WEB-INF/views/new-form.jsp";
    RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
    dispatcher.forward(request , response);
```

***

# Chapter3. MVC 프레임워크 만들기

![](https://raw.githubusercontent.com/jdalma/jdalma.github.io/master/assets/images/spring-mvc/mvc_v1.png)

## [V1 - Front Controller 도입](https://github.com/jdalma/SpringMVC-1/pull/4/commits/c63752bb81e031386fd2835e12e4552e1f06f9c7)

![](https://raw.githubusercontent.com/jdalma/jdalma.github.io/master/assets/images/spring-mvc/mvc_v2.png)

## **FrontController패턴**
- `Front Controller` **서블릿 하나**로 클라이언트의 요청을 받는다
- Front Controller가 **요청에 맞는 `Controller`를 찾아 호출**
- Front Controller를 제외한 **나머지 `Controller`는 서블릿을 사용하지 않아도 된다**
- 스프링 웹 MVC의 `DispatcherServlet`이 **FrontController** 패턴으로 구현되어 있다

![](https://raw.githubusercontent.com/jdalma/jdalma.github.io/master/assets/images/spring-mvc/frontController.png)
