<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 게시글 등록(개인 회원)</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>

<style>
  label { 
    font-family: '굴림체';
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
    size:100px;
    font-size: 20px;
  }
  
   h1 {
      background-color: blanchedalmond;
      text-align: center;
      color: black;
      margin-top: 10px;
      font-size: 50px;
  }
  
  p{ 
    font-family: '굴림체';
    text-align: center;
    font-size: 20px;
  }
  
  legend {
  text-align: center;
  }
  
  .btn {
   border-radius: 4px;
   background-color: blanchedalmond;
   color: black;
   font-size: 18px;
  }
  .btn:hover {
   background-color: beige;
   color: black;
  }
</style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<h1>💬문의글 등록결과</h1> 
  <p>문의글을 등록했습니다.</p>

   <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-outline-primary me-md-2 btn-small" 
     type="submit" value="등록" formaction="peradd">
     <a class="btn btn-info" href="permylist?perNo=${loginUser.perNo}">보러가기</a>
     </button>
   </div> 

</body>
</html>