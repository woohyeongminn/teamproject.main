<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 | 문의 게시글</title>
 <style>
  * {
  font-size: 14px;
  }
  
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
    size:100px;
  }
  
  .btn {
    line-height: 14px;
  }
</style>
</head>
<body>
  <form action="ceoupdate" method="post">
    <span>(${ceoAskBoard.askNo})</span><br>
    
    <label for='f-title'>제목</label>
    <input id='f-title' type='text' name='title' value='${ceoAskBoard.askTitle}'><br>
    
    <label for='f-content'>내용</label>
    <input id='f-content' type='text' name='content' value='${ceoAskBoard.askContent}'><br>
    
    <span id='f-registeredDate'>${ceoAskBoard.askRegisteredDate}</span><br>
    <input type ='hidden' name='askNo' value='${ceoAskBoard.askNo}'>
  
    <button class = "btn btn-outline-dark" type="submit" value="수정">수정</button>
  </form>
 </body>
</html>