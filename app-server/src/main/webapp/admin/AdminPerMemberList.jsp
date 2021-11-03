<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인회원 목록(관리자)</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
<style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
  }
  legend {
  text-align: center;
  }
</style>
</head>

<body>
  <hr>
  <fieldset>
  <legend>
   <b> 📗 개인회원 목록</b>
  </legend>
    <table class="table">

        <thead>
          <tr>
            <th>번호</th>
            <th>이름</th>
            <th>닉네임</th>
            <th>이메일</th>
            <th>가입일</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${perMemberList}" var="perMember">
             <tr>
              <td>(${perMember.perNo})</td>
             

              <td><a href='permemberdetail?no=${perMember.perNo}'>${perMember.perName}</a></td> 
              
              
              <td>${perMember.perNickname}</td> 
              
              
              <td>${perMember.perEmail}</td> 
              
             
              <td>${perMember.perRegisteredDate}</td>
              
             </tr>
         </c:forEach>
        </tbody>
  </table>
  </fieldset>
</body>
</html>




