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
<h1>예약현황</h1>
<form action="reservationPay">
  <c:forEach items="${statusOfNumber}" var="i">
	  <ul class="list-group">
			<li class="list-group-item">
			  <c:choose>
			    <c:when test="${fn:split(i.value, ',')[2] eq '예약 불가'}">
				    <input class="form-check-input me-1" type="checkbox" value="${i.key},${i.value}" aria-label="${i.value}" name="selectedTime" disabled>
				    ${fn:split(i.value, ',')[0]} ~ ${fn:split(i.value, ',')[1]} : ${fn:split(i.value, ',')[2]}
			    </c:when>
			    <c:otherwise>
	          <input class="form-check-input me-1" type="checkbox" value="${i.key},${i.value}" aria-label="${i.value}" name="selectedTime">
	          ${fn:split(i.value, ',')[0]} ~ ${fn:split(i.value, ',')[1]} : ${fn:split(i.value, ',')[2]}
			    </c:otherwise>
			  </c:choose>
      </li>
	  </ul>
  </c:forEach>
인원수 : <input type="number" min="1" max="${people}" name="people"><br>
<input type="hidden" name="roomNo" value="${roomNo}">
<input type="hidden" name="selectedDate" value="${selectedDate}">
<input type="hidden" name="perNo" value="${perNo}">
<input type="submit">
</form>
<br>
<button type="button" class="btn btn-outline-dark"><a href="list">목록</a></button>
</body>
</html>