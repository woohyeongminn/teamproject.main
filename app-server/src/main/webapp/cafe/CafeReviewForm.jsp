<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
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
	<style>
  .all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    text-align: center;
  }
  .c-top {
    width: 100%;
    padding: 20px 0 20px 50px;
    font-weight: bold;
    background-color: rgb(247, 231, 215);
    text-align: center;
  }
	</style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="c-top">
🔖 리뷰 등록
</div>
<br><br>
<div class="all-content">
<form action="reviewAdd">
	<div id='content'>
    내용 <input type="text" name="content"><br>
    평점(0~5점) <input type="number" min="0" max="5" name="grade">
	</div>
	<input type="hidden" name="reservationNo" value="${reservationNo}">
	<input type="submit" class="btn btn-outline-dark">
</form>
</div>
</body>
</html>
