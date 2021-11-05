<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
  <!-- 부트스트랩 -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <!-- 아이콘 -->
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<style>
  .all-content {
    width: 100%;
    margin: 0 auto;
    padding: 40px;
    margin-top:50px;
  }
  
</style>
</head>

<body>
<!-- 비회원 헤더 -->
<jsp:include page="../header.jsp"/>

<!-- 개인 로그인했을 때 헤더 -->
<!--
<jsp:include page="../header.jsp">
  <jsp:param name="loginCeoUser" value="달러넣기{ceoMember.ceoNo}" />
</jsp:include>
-->

<!-- 기업 로그인했을 때 헤더 -->
<!--
<jsp:include page="../header.jsp">
  <jsp:param name="loginCeoUser" value="달러넣기{ceoMember.ceoNo}" />
</jsp:include>
-->

<section>
<div class="all-content">
<b style="font-size:20px"> title (ex.🖐 기업 회원 로그인) </b>
<hr>
<!-- 이 영역에 작성하기-->




<!---->
</div>
</section>

</body>
</html>