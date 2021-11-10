<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인회원 로그인</title>
<style>
  * {
  font-size: 14px;
  }
  
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
    size:100px;
  }
  
  .btn {
    line-height: 14px;
  }
    label {
    margin-left: 10px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  
  input {
  border : white;
  outline-color : lightgray;
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
  color : white;
  }
    .all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
  }
  .inner {
    float: left;
  }
  
</style>
</head>
<body>

<br>
<div class="all-content">
<fieldset>
<br>
<hr>
<table class="table table-responsive">
<td><form action='login' method="post"></td>
<br>
<p><label for='f-email' class='form-label' size='100px'>이메일</label>
<input id='f-email' type='email' name='email' placeholder='아이디' size='20'></p>
<br>
<p><label for='f-password' class='form-label' size='100px'>비밀번호</label>
<input id='f-password' type='password' name='password' placeholder='패스워드' size='20'><br></p>
<br>
</table>
</fieldset>
    <div class="inner">
  <span ></span>
	    <div class="form-check">
		    <input type="checkbox" class="form-check-input" id="dropdownCheck">
		    <label class="form-check-label" for="dropdownCheck">
		    정보 기억하기
		    </label>
	    </div>
	 </div>
   <div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class= "btn btn-outline-dark" value="로그인" >로그인</button> 
  </form>
  </div>

  <div>
    <div class="dropdown-divider"></div>
     <a class="dropdown-item" href="addform">회원가입</a>
     <a class="dropdown-item" href="#">비밀번호찾기</a>
    </div>
  </div>

</body>
</html>