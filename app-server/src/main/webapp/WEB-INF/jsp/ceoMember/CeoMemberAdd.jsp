<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  legend {
  text-align: center;
  }
</style>
</head>

<body>
	<h1>회원등록결과</h1>
	<p>회원을 등록했습니다.</p>
	<!-- <a class="btn btn-info" href="form">로그인 하러가기</a> -->
	<a class="btn btn-info" href="${contextPath}/app/ceomember/form">로그인 하러가기</a>
</body>
</html>