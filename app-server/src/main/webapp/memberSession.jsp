<%@page import="com.ogong.pms.domain.CeoMember"%>
<%@page import="com.ogong.pms.domain.Admin"%>
<%@page import="com.ogong.pms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Member loginUser = (Member)session.getAttribute("loginUser");
    CeoMember loginCeoUser = (CeoMember)session.getAttribute("loginCeoUser");
    Admin loginAdmin = (Admin)session.getAttribute("loginAdmin");
   
    if(loginUser==null || loginCeoUser==null || loginAdmin==null)
    {
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