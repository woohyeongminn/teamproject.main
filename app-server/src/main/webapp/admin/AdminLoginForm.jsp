<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
   <!-- 아이콘 -->
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
     <!-- JQUERY -->
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
  
<style>
  legend {
  text-align: center;
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
  font-size: 14px;
  }
  input[type=checkbox]:checked {
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
   width: 100%;
  }
  input#dropdownCheck {
    width: 15px;
    height: 15px;
  }
  .form-check {
  display: block;
  padding: 0;
  margin-bottom: 12px;
  margin-left: 25px;
  }

  .form-check span {
  font-size: 14px;
  }
  
  .all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    padding: 50px;
    margin-top: 100px;
  }
  .inner {
    max-width: 500px;
    margin : 10px 0;
  }
  
  #remember {
   font-size: 12px;
  }

  button[type=submit] {
   font-size: 14px;
  }
  
  .mb {
  margin : 0;
  text-align: center;
  }
  
  .mb a {
  padding: 5px;
  font-size: 14px;
  }
  .c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  text-align: center;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
}

</style>
</head>

<body>

	<section>
	<div class="c-top">
	      🖐 오늘의 공부 로그인
	      </div>
	      
	<div class="all-content">
	<legend><b> 👑 관리자 로그인 </b></legend>
	<br>
	<hr>
	
	<form action='login'>
	      <input class="input" id='f-email' type='email' name='email' placeholder='E-mail'>
	      <br>
	      <input class="input" id='f-password' type='password' name='password' placeholder='Password'>
	  <div class="inner">
		  <div class="form-check">
		    <input type="checkbox" class="form-check-input" id="dropdownCheck">
		      <span id="remember">
		      정보 기억하기
		     </span>
		  </div>
	      <button type="submit" class="btn btn-outline-dark" value="로그인" >로그인</button> 
	  </div>
	</form>
	
	</div>
	</section>

</body>
</html>