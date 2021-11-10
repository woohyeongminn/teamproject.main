<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
  .card {
    width: 400px;
    margin: 200px 0 0 400px;
    background-color: white;
    text-align: center;
    border-radius: 15px;
    border: 2px solid rgb(110, 110, 110);
    align-items: center;
    flex-direction: column;
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
  .profile {
  max-width: 400px;
  margin: 50px auto 0;
  background-color: white;
  text-align: center;
  font-size: 14px;
  }
  #f-profile {
    text-align: center;
  }
  button[type=submit] {
  margin-inline: auto;
  text-align: center;
  margin: 0 0 30px 0;
  font-size: 14px;
  line-height: 14px;
  }
</style>


</head>
<body>

<section>
<fieldset>

<form action='update' name='adminPro' method='post' onsubmit="return notEmpty()">

<div class="card">
  <div class="card-header">
    <img src="/ogong/img/logoface.png" alt="Profile Image" class="profile-img">
  </div>

  <div class="profile">
    <p><label for='f-nickName' class='form-label'>닉네임　&emsp;</label>
    <input id='f-profile' type='text' name='nickName' placeholder='닉네임 👑'></p>
    <br>
    <p><label for='f-email' class='form-label'>이메일　&emsp;</label>
    <input id='f-profile' type='email' name='email' placeholder='이메일'></p>
    <br>
    <p><label for='f-password' class='form-label'>비밀번호&emsp;</label>
    <input id='f-profile' type='password' name='password' placeholder='비밀번호'></p>
    <br>
    <br>
  </div>

<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="변경" >변경</button> 
</div>

</div>
</form>

</fieldset>
</section>

<script type="text/javascript">
function notEmpty() {
  
  var valueForm = document.adminPro;
  
  if(!valueForm.nickName.value){
    Swal.fire("닉네임을 입력하세요.");
    return false;
  }
  
  if(valueForm.nickName.value == ""){
    Swal.fire("하나 이상의 문자열을 입력하세요.");
      return false;
    }
  
  if(!valueForm.email.value){
    Swal.fire("이메일을 입력하세요.");
    return false;
  }
  
  if(valueForm.email.value == ""){
    Swal.fire("하나 이상의 문자열을 입력하세요.");
      return false;
    }
  
  if(!valueForm.password.value){
    Swal.fire("비밀번호를 입력하세요.");
    return false;
  }
  
  if(valueForm.password.value == ""){
    Swal.fire("하나 이상의 문자열을 입력하세요.");
      return false;
    }
}
</script>

</body>
</html>