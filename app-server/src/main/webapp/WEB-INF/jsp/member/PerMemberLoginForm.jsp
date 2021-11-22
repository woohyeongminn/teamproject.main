<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<style>
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
  
  .btn {
   width: 100%;
   font-size: 14px;
   line-height: 24px;
   
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

<div class="all-content">
  <b style="font-size:20px"> 🙋개인 회원 로그인 </b>
  <br>
  <hr>
  <form id="member-form" action='login' method='post'>
    <input class="input" id='f-email' type='email' name='email' placeholder='E-mail' value="${cookie.email.value}">
    <br>
    <input class="input" id='f-password' type='password' name='password' placeholder='Password'>
      <div class="inner">
        <div class="form-check">
           <input id="f-saveEmail" type="checkbox" class="rememberCheck" name="saveEmail" ${not empty cookie.email ? "checked":""}>
           <span>이메일 기억하기</span>
        </div>
        <button type="submit" class="btn btn-outline-dark" value="로그인" >로그인</button> 
      </div>
  </form>
  <div class="mb">
     <a href="addform">회원가입</a> |
     
     <a href="findemailform">이메일찾기</a> |
     <a href="getpwbyemailform">비밀번호찾기</a>
  </div>
</div>    

<script>
document.querySelector("#member-form").onsubmit = () => {
  if (document.querySelector("#f-email").value == "" ||
      document.querySelector("#f-password").value == "") {
    window.alert("이메일 패스워드를 입력해주세요.")
    //Swal.fire("필수 입력 항목이 비어 있습니다.")
    return false;
  }
};

function checkCapsLock(event)  {
	  if (event.getModifierState("CapsLock")) {
	    document.getElementById("message").innerText 
	      = "Caps Lock이 켜져 있습니다."
	  }else {
	    document.getElementById("message").innerText 
	      = ""
	  }
	}
</script>
