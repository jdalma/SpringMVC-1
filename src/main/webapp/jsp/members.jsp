<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jeonghyeonjun
  Date: 2022/05/04
  Time: 8:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    for(int i = 0 ; i < 10 ; i++){
        Member m1 = new Member("t" + i , 1234);
        memberRepository.save(m1);
    }
    // 저장된 모든 멤버
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
<%
    for (Member member : members) {
        out.write("<tr>");
        out.write("<td>" + member.getId() + "</td>");
        out.write("<td>" + member.getUsername() + "</td>");
        out.write("<td>" + member.getAge() + "</td>");
        out.write("</tr>");
    }
%>
</table>
</body>
</html>
