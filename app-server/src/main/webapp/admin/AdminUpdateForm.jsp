<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 프로필</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
<style>
  legend {
  text-align: center;
  }
  input {
    margin: 0 80px 0 50px;
    text-align: center;
    display: inline-block;
    padding: 5px;
    padding-left: 20px;
    padding-right: 20px;
    width: 70%;
    border-color: lightgray;
    border-radius: 6px;
  }
  p {
  text-align-last: center;
  margin-top: 50px;
  margin-bottom: 50px;
  }
  div {
  margin-right: 10px;
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: center;
  flex-direction: column;
  }
  a {
  color : black;
  text-decoration : blink;
  }
  a:hover {
  color : white;
  }
  .card .card-header {
    padding: 0;
    height: 20px;
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: center;
    margin-left: 10px;
    background-color: white;
    border: white;
  }
  .card .card-header .profile-img {
    width: 100px;
    height: 100px;
    border-radius: 1000px;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    border: 4px solid wheat;
    background-color: white;
    box-shadow: 0 0 10px lightyellow;
  }
  .card .card-header .profile-img:hover {
    width: 120px;
    height: 120px;
    transform: translate(-50%, -50%);
    border: 4px solid wheat;
  }
  .card {
    max-width: 420px;
    margin: 150px auto 0;
    background-color: white;
    text-align: center;
    font-size: 15px;
    border-radius: 15px;
    border: 2px solid rgb(110, 110, 110);
  }
  .c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  text-align: center;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
  }
  button[type=submit] {
  margin-inline: auto;
  text-align: center;
  margin: 30px 0;
  }
</style>
<script type="text/javascript">
function notEmpty() {
  
  var valueForm = document.adminPro;
  
  if(!valueForm.nickName.value){
    alert("닉네임을 입력하세요.");
    return false;
  }
  
  if(valueForm.nickName.value == ""){
      alert("하나 이상의 문자열을 입력하세요.");
      return false;
    }
  
  if(!valueForm.email.value){
    alert("이메일을 입력하세요.");
    return false;
  }
  
  if(valueForm.email.value == ""){
      alert("하나 이상의 문자열을 입력하세요.");
      return false;
    }
  
  if(!valueForm.password.value){
    alert("비밀번호를 입력하세요.");
    return false;
  }
  
  if(valueForm.password.value == ""){
      alert("하나 이상의 문자열을 입력하세요.");
      return false;
    }
}


</script>
</head>
<body>
<jsp:include page="../header.jsp"/>
<fieldset>
<section>
<div class="c-top">
        🙂 마이페이지
      </div>
<table class="table table-responsive">

<div class="card">
  <div class="card-header">
    <img src="/ogong/img/logoface.png" alt="Profile Image" class="profile-img">
</div>

<form action='update' name='adminPro' onsubmit="return notEmpty()">

<p><input id='f-nickName' type='text' name='nickName' placeholder='닉네임'><br>

<br><input id='f-email' type='email' name='email' placeholder='이메일' onkeydown="inputEmail()"><br>

<br><input id='f-password' type='password' name='password' placeholder='비밀번호'></p>
<br>
</div>

</table>
</fieldset>
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="변경" >변경</button> 
</div>
</form>
</section>
 <jsp:include page="../footer.jsp"/>
</body>
</html>