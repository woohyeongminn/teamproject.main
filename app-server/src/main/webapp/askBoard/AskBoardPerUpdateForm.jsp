<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인회원 문의글 수정</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>

<style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  legend {
  text-align: center;
  }
</style>
</head>

<body>
  <h5>개인 회원 문의글 수정</h5>

  <form action="ceoupdate">
    span>(${perAskBoard.askNo})</span><br>
    
    <label for='f-title'>제목</label>
    <input id='f-title' type='text' name='title' value='${perAskBoard.askTitle}'><br>
    
    <label for='f-content'>내용</label>
    <input id='f-content' type='text' name='content' value='${perAskBoard.askContent}'><br>
    
    <span id='f-registeredDate'>${perAskBoard.askRegisteredDate}</span><br>
<
    <input type ='hidden' name='askNo' value='${perAskBoard.askNo}'>
  
    <button type="submit" value="수정">수정</button>

  </form>
  
 </body>
</html>