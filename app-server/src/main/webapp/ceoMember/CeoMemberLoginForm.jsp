<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>

  * {
  font-size: 14px;
  }
  
  label {
    margin-right: 10px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  legend {
  text-align: center;
  font-size: 16px;
  }
  
  form {
  max-width: 500px;
  }
  .input {
  display: inline-block;
  padding:10px;
  width:100%;
  margin-top: 10px;
  border-color : lightgray;
  border-radius: 6px;
  }
  .input[type=checkbox]:checked {
  background-color: black;
  }
  p {
  text-align-last: center;
  }
  div {
  margin-right: 10px;
  xdisplay: flex;
  align-items: center;
  flex-direction: row;
  justify-content: center;
  }
  a {
  color : black;
  text-decoration : blink;
  }
  a:hover {
  color : darkgray;
  }
  
  button {
   width: 100%
  }
  
  .form-check {
  display: block;
  padding: 0;
  margin-bottom: 12px;
  text-align: left;
  }

  .form-check span {
  font-size: 14px;
  }
  
   .c-top {
  width: 100%;
  padding: 20px 0 20px 50px;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
}

  .all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    padding: 50px;
    margin-top:40px;
    text-align: center;
  }
  .inner {
    max-width: 500px;
    margin : 10px 0;
  }
  
  .mb {
  margin : 0;
  text-align: center;
  }
  
  .mb a {
  padding: 5px;
  font-size: 14px;
  }
  
</style>
</head>

<body>
<div class="all-content">
<b style="font-size:20px"> 기업 회원 로그인 </b>
<br>
<hr>
<form action='login'>
	<!-- <label for='f-email' class='form-label' size='100px'>이메일</label> -->
	<input class="input" id='f-email' type='email' name='email' placeholder='E-mail'>
	<br>
	<!-- <label for='f-password' class='form-label' size='100px'>비밀번호</label> -->
	<input class="input" id='f-password' type='password' name='password' placeholder='Password'>
	<div class="inner">
	  <div class="form-check">
		   <input type="checkbox" class="rememberCheck" id="dropdownCheck">
		   <span>정보 기억하기</span>
	  </div>
	  <button type="submit" class="btn btn-outline-dark" value="로그인" >로그인</button> 
	</div>
</form>
<div class="mb">
	 <a href="addform">회원가입</a> |
	 <a href="#">비밀번호찾기</a>
</div>
</div>
</body>
</html>