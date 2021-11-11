<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 | 개인 회원 목록</title>
<style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
  }
  legend {
  text-align: center;
  }
  button[type=button] {
    margin-block: 10px;
    border-radius: 10px;
    background-color: beige;
    color: black;
  }
  button[type=button]:hover {
    background-color: blanchedalmond;
    color: black;
  }
  .btn-secondary:focus {
  background-color: beige;
  color: black;
  }
  button[type=button1] {
    margin-left: 15px;
    border-radius: 10px;
    border-color: lightgray;
    background-color: beige;
    color: black;
  }
  button[type=button1]:hover {
    background-color: blanchedalmond;
    color: black;
  }
  .dropdown-menu {
  background-color: rgba(211, 211, 211, 0);
  border: rgba(211, 211, 211, 0);
  }
  .btn-group {
  margin-top: 10px;
  display: block;
  }
  .offcanvas-start {
  width: 350px;
  }
  button[type=button2] {
  margin-left: 70px;
    color: black;
  }
  button[type=button2]:hover {
    color: black;
  }
  div {
  margin-right: 10px;
  }
</style>
</head>
<body>
<br>
  <fieldset>
<legend><b> 📗 개인 회원 목록 </b></legend><br>
<hr>
    <table class="table">

        <thead>
          <tr>
            <th>번호</th>
            <th>이름</th>
		       <th style="margin-left: auto;" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">닉네임</th>
            <th>이메일</th>
            <th>가입일</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${perMemberList}" var="perMember">
             <tr>
              <td>${perMember.perNo}</td>
              <td><a href='permemberdetail?no=${perMember.perNo}'>${perMember.perName}</a></td> 
              <td>${perMember.perNickname}</td> 
              <td>${perMember.perEmail}</td> 
              <td>${perMember.perRegisteredDate}</td>
             </tr>
         </c:forEach>
        </tbody>
  </table>
  </fieldset>

  
<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">

  <jsp:include page="AdminMenu.jsp"/>
    
</div>

</body>
</html>




