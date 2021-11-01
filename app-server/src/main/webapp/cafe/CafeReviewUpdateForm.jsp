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

	</style>
</head>
<body>
<h1>리뷰 수정</h1>
<hr>
<form action="reviewUpdate">
	<div id='content'>
    내용 <input type="text" name="content" value="${cafeReview.content}"><br>
    평점(0~5점) <input type="number" min="0" max="5" name="grade" value="${cafeReview.grade}">
	</div>
	<input type="hidden" name="perNo" value="${perNo}">
	<input type="hidden" name="reviewNo" value="${cafeReview.reviewNo}">
	<input type="submit">
</form>
</body>
</html>
