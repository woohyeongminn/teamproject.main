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
</head>
<body>
<h1>예약 시간 확인</h1>
<br>
<form action="reservationEnd">
  <ol class="list-group list-group-numbered">
    <c:forEach items="${reservationInfo}" var="i">
      <li class="list-group-item">${fn:split(i.value, ',')[0]} ~ ${fn:split(i.value, ',')[1]}</li>
    </c:forEach>
  </ol>
<br>
이용 시간 : ${usingTime}시간<br>
총 금액 : ${totalPrice}원<br><br>
<input type="hidden" name="roomNo" value="${roomNo}">
<input type="hidden" name="startTime" value="${startTime}">
<input type="hidden" name="usingTime" value="${usingTime}">
<input type="hidden" name="totalPrice" value="${totalPrice}">
<input type="hidden" name="selectedDate" value="${selectedDate}">
<input type="hidden" name="people" value="${people}">
<input type="hidden" name="perNo" value="${perNo}">
<input type="submit">
</form>
<br>
<button type="button" class="btn btn-outline-dark"><a href="list">목록</a></button>
</body>
</html>