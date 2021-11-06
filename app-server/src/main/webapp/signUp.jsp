<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 공부 회원가입</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>

.s {
    width: 100%;
    max-width: 1000px;
    margin: 0 auto;
    padding: 50px;
    margin-top:50px;
    display: flex;
    flex-direction: column;
}
  .all-content {
    width: 100%;
    margin: 0 auto;
    padding: 50px;
    margin-top:50px;
    display: flex;
  }

  .signUp {
    text-decoration:none;
    display: inline-block;
    width: 50%;
    height: 250px;
    margin: 50px;
    background-color:  #ccc0ae;
    border-radius: 30px;
  }

  .signUp span {
    display: inline-block;
    vertical-align: middle;
    font-weight: 900;
    font-size: 30px;
    color: white;
    margin-top: 90px;
    margin-left: 20px;
    line-height: 1.1;
  }

 .signUp:hover {
  background-color: #f0e3d4;
  color:  #616161;
}
</style>
</head>

<body>
<jsp:include page="header.jsp"/>
<section class='s'>
	<b style="font-size:20px; padding-left:20px; margin: auto;"> 🎓 오늘의 공부에 오신 걸 환영합니다! 회원가입을 진행해 주세요! 🎓 </b>
	<p b style="padding-left:20px;"></p>
	<div class="all-content">
		<a class="signUp" href="/ogong/member/addform" value="개인회원 가입" ><span>개인<br>회원가입</span></a> 
		<a class="signUp" href="/ogong/ceomember/addform" value="기업회원 가입" ><span>기업<br>회원가입</span></a> 
	</div>
</section>
</body>
</html>