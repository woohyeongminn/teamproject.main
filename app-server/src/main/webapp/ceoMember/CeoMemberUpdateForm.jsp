<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 수정</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
a {
text-decoration: none;
}
label {
  margin-right: 5px;
  text-align: center;
  display: inline;
  width: 60px;
}

legend {
  text-align: center;
}

.all-content {
  width: 100%;
  margin: 0 auto;
}

.c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
  text-align: center;
}

.profile {
  max-width: 400px;
  margin: 150px auto 0;
  background-color: white;
  border-radius: 15px;
  border: 2px solid rgb(110, 110, 110);
  text-align: center;
  padding-bottom: 30px;
}

.profile > label, span {
  display: inline-block;
  padding: 5px 0;
  font-size: 14px;
}

.profile input {
font-size: 14px;
}

.profile .profile-header {
  padding: 0;
  height: 48px;
  display: flex;
  align-items: center;
}

.profile .profile-header>a {
  display:inline-block;
  text-decoration:none;
  width: 100px;
  height: 100px;
  border-radius: 1000px;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  border: 2px solid rgb(110, 110, 110);
  background-color: white;
  vertical-align: middle;
}

 .profile .profile-header .profile-img {
    margin-top: 8px;
    margin-left: 3px;
}

 .btn_wrap {
  max-width: 420px;
  margin: 20px auto 0;
  text-align: center;
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-bottom: 100px;
 }
 
 .btn_wrap .btn {
  font-size: 12px;
  margin: 0 8px;
  padding: 8px 40px;
 }
 
 button:hover {
  color: white;
}
</style>
</head>

<body>
  <jsp:include page="../header.jsp"/>
    <div class="c-top">
        🙂 내 프로필 수정
    </div>
   <div class="all-content"> 
     
     <form action='update'>
      <div class="profile">
        <div class="profile-header">
           <a href="#"><img src="/ogong/img/logoface.png" alt="Profile Image" class="profile-img"></a>
        </div>

			  <label for='f-name' class='form-label'>이름</label>
			  <input id='f-name' type='text' name='name' placeholder='${ceoMember.ceoName}'><br>
			  
			  <label for='f-nickname' class='form-label'>닉네임</label>
			  <input id='f-nickname' type='text' name='nickname' placeholder='${ceoMember.ceoNickname}'><br>
			  
			  <label for='f-photo' class='form-label'>사진</label>
			  <input id='f-photo' type='text' name='photo' placeholder='${ceoMember.ceoPhoto}'><br>
			  
			  <label for='f-tel' class='form-label'>전화번호</label>
			  <input id='f-tel' type='tel' name='tel' placeholder='${ceoMember.ceoTel}'><br>
			  
			  <label for='f-email' class='form-label'>이메일</label>
			  <input id='f-email' type='email' name='email' placeholder='${ceoMember.ceoEmail}'><br>
			  
			  <label for='f-password' class='form-label'>암호</label>
			  <input id='f-password' type='password' name='password' placeholder='${ceoMember.ceoPassword}'><br>
			  
			  <label for='f-registeredDate' class='form-label'>가입일</label>
        <input id='f-registeredDate' type='registeredDate' style="border:0px"
         name='registeredDate' readonly value='${ceoMember.ceoRegisteredDate}'><br>
      </div>

      <div class="btn_wrap">
      <a href='updateform' class = "btn btn-outline-dark">수정</a>
      <a href='detail' class = "btn btn-outline-dark">뒤로가기</a>
      </a>
      </div>
     </form>
   </div>
   <jsp:include page="../footer.jsp"/>
 </body>
</html>