<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>실행 오류!</title>
  <link rel="stylesheet" href="${contextPath}/node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="${contextPath}/node_modules/sweetalert2/dist/sweetalert2.css">
  <link rel="stylesheet" href="${contextPath}/css/common.css">
  
  <script src="${contextPath}/node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="${contextPath}/node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="${contextPath}/node_modules/sweetalert2/dist/sweetalert2.js"></script>
</head>
<body>

<div class="error-container">

<jsp:include page="header.jsp"/>
 
<div id="error-content">
<pre>
<%
Exception error = (Exception) request.getAttribute("error");
error.printStackTrace(new PrintWriter(out));
%>
</pre>
</div><!-- #content --> 

<jsp:include page="footer.jsp"/>

</div><!-- .container -->

</body>
</html>








