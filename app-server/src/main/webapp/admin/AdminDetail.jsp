<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

<!-- 아이콘 -->
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<meta charset="UTF-8">

<style>
   .card {
    max-width: 400px;
    margin: 200px auto 0;
    background-color: white;
    text-align: center;
    font-size: 14px;
    border-radius: 15px;
    border: 2px solid rgb(110, 110, 110);
  }
  .card .card-header {
    height: 28px;
    display: flex;
    align-items: center;
    flex-direction: row;
    justify-content: center;
    margin-left: 10px;
    background-color: white;
    border: white;
    border-radius: 50px;
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
  #box {
  text-align: center;
  margin: 0 0 50px 0;
  }
  button[type=submit] {
  font-size: 14px;
  line-height: 14px;
  }
  a {
  color : black;
  text-decoration : auto;
  }
  a:hover {
  color : white;
  }
  .offcanvas-start {
  width: 350px;
  }
  .btn-group {
  margin-top: 10px;
  display: block;
  }
  .btn-secondary:focus {
  background-color: beige;
  color: black;
  }
  button[type=button2] {
  margin-left: 70px;
    color: black;
    font-size: 14px;
  }
  button[type=button2]:hover {
    color: black;
  }
  button[type=button] {
    margin-block: 10px;
    border: 1.5px solid;
    border-radius: 10px;
    background-color: white;
    color: black;
    font-size: 14px;
    line-height: 14px;
  }
  button[type=button]:hover {
    background-color: beige;
    color: black;
  }
  .dropdown-menu {
  background-color: rgba(211, 211, 211, 0);
  border: rgba(211, 211, 211, 0);
  }
	button[type=button1] {
	    margin-left: 15px;
	    margin-bottom: 5px;
	    border-radius: 5px;
	    border: 1px solid;
	    background-color: white;
	    color: black;
	    font-size: 14px;
	}
  button[type=button1]:hover {
    background-color: beige;
    color: black;
  }
</style>

</head>
<body>

<section>
<fieldset>

<div class="card">

  <div class="card-header">
  <i class="far fa-address-card" style="margin-left: auto;" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample"></i>
      <img src="/ogong/img/logoface.png" alt="Profile Image" class="profile-img">
  </div>

  <div class="profile">
    <p><label for='f-nickName' class='form-label'>닉네임&emsp;</label>
    <input id='f-profile' type='text' name='nickName' value='${loginAdmin.masterNickname} 👑' readonly></p>
    <br>
    <p><label for='f-email' class='form-label'>이메일&emsp;</label>
    <input id='f-profile' type='email' name='email' value='${loginAdmin.masterEmail}' readonly></p>
    <br>
    <br>
  </div>

  <div id="box">
    <button type="submit" class="btn btn-outline-dark" value="변경" id="submit"><a href='updateForm'>변경</a></button> 
    <button type="submit" class="btn btn-outline-dark" value="로그아웃" id="submit"><a href='logout'>로그아웃</a></button> 
  </div>
  
</div>
</fieldset>
</section>
  
<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">

  <jsp:include page="AdminMenu.jsp"/>
  
  </div>
    
</body>
</html>