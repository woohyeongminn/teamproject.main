<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<style>
  a {
   text-decoration:none;
  }
  h3 {
    font-weight: bolder;
  }
  </style>
</head>
<body>
<br>
<h3> 🙌예약완료 </h3><br>
📝 내 스터디에 모임 일정 등록하기
<c:if test='${not empty myStudy}'>
<form action="calenderAdd">
 <select name="myStudy">
    <c:forEach items="${myStudy}" var="study">
      <option value="${study.study_no}">${study.name}</option>
    </c:forEach>
  </select>
  <button class="btn btn-outline-dark">등록</button>
</form>
</c:if>
<br>
<button type="button" class="btn btn-outline-dark"><a href="list?perNo=${perNo}">목록</a></button>
<button type="button" class="btn btn-outline-dark"><a href="reservationList?perNo=${perNo}">내 예약내역 보기</a></button>
</body>
</html>