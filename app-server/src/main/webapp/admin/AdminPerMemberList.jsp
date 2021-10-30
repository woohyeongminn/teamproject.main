<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<title>개인회원목록</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
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
   <b> 📗개인회원 목록</b>
  </legend>
    <table class="table">

			<thead>
			  <tr>
			    <th>번호</th>
			    <th>이름</th>
			    <th>닉네임</th>
			    <th>이메일</th>
			    <th>전화</th>
			    <th>등록일</th>
         </tr>
          </thead>
          <tbody>
          <c:forEach items="${memberList}" var="member">
					 <tr>
					
						<td>${member.perNo}</td>
						<td>${member.perName}</td>
						<td>${member.perNickname}</td>
						<td>${member.perEmail}</td>
						<td>${member.perRegisteredDate}</td>
						
             </tr>
         </c:forEach>
        </tbody>
  </table>
  </fieldset>
</body>
</html>




