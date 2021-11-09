<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
<br><br>
<form action="reservationSelectTime">
스터디룸<br>
<div class="btn-group-vertical" role="group">
  <c:forEach items="${roomList}" var="studyRoom">
    <input type="radio" class="btn-check" name="roomName" id="${studyRoom.roomName}" value="${studyRoom.roomNo}" autocomplete="off">
    <label class="btn btn-outline-primary" for="${studyRoom.roomName}">${studyRoom.roomName}</label>
		<input type="hidden" name="cafeNo" value="${studyRoom.cafe.no}">
  </c:forEach>
</div>
<br>
<br>날짜<br>
<input type="date" name="date">
<input type="hidden" name="perNo" value="${loginUser.perNo}">
<input type="submit">
</form>
</body>
</html>