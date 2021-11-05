<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <title>공지게시판</title>
<body>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <style>
  legend {
  text-align: center;
  }
  p {
    height: 50px;
    text-align: center;
  }
  </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<fieldset>
<br>
<legend><b> 🔔 공지게시글 등록 </b></legend><br>
<hr>
<br>
  <p>공지글 등록이 완료되었습니다.</p>
  <p>공지게시판 목록으로 돌아갑니다.</p>
<br>
</fieldset>
</body>
</html>