<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 목록</title>
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
   <b> 📗 기업회원 목록</b>
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
          <c:forEach items="${ceoMemberList}" var="ceoMember">
             <tr>
              <td>(${ceoMember.ceoNo})</td>
              <td><a href='detail?no=${ceoMember.ceoNo}'>${ceoMember.ceoName}</a></td> 
              <td>${ceoMember.ceoNickname}</td> 
              <td>${ceoMember.ceoEmail}</td> 
              <td>${ceoMember.ceoRegisteredDate}</td>
             </tr>
         </c:forEach>
        </tbody>
  </table>
  </fieldset>
</body>
</html>