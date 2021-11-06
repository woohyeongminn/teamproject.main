<%@page import="com.ogong.pms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Member loginUser = (Member)session.getAttribute("loginUser");
    String a;
   
    if(loginUser==null)
    {
      a = "a";
      response.sendRedirect("login.jsp");
    }
    
    request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body>
</body>
</html>