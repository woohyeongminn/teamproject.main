<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <form action="perupdate" method="post">
    <span>(${perAskBoard.askNo})</span><br>
    
    <label for='f-title'>제목</label>
    <input id='f-title' type='text' name='askTitle' value='${perAskBoard.askTitle}'><br>
    
    <label for='f-content'>내용</label>
    <input id='f-content' type='text' name='askContent' value='${perAskBoard.askContent}'><br>
    
    <span id='f-registeredDate'>${perAskBoard.askRegisteredDate}</span><br>
    <input type ='hidden' name='askNo' value='${perAskBoard.askNo}'>
  
    <button class = "btn btn-outline-dark"  type="submit" value="수정">수정</button>
  </form>
