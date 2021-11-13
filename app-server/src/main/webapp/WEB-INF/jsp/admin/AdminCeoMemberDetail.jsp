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

body {
  height: auto;
}
a {
text-decoration: none;
}

legend {
  text-align: center;
}

.all-content {
  width: 100%;
  margin: 0 auto;
  height: 800px;
}

.profile {
  max-width: 500px;
  margin: 100px auto 0;
  background-color: white;
  border-radius: 15px;
  border: 2px solid rgb(110, 110, 110);
  text-align: center;
  padding-bottom: 30px;
  padding-left: 60px;
}

.profile > label {
  margin-right: 5px;
  text-align: center;
  font-weight: 600;
}

.profile > label, span {
  display: inline-block;
  padding: 5px 5px;
  width: 103px;
}

.profile input {
  border:0;
}

.profile input:focus {
  outline: none;
}


.profile .profile-header {
  padding: 0;
  height: 60px;
  display: flex;
  align-items: center; 
}

.profile .profile-header>a {
  display:inline-block;
  text-decoration:none;
  width: 110px;
  height: 110px;
  overflow: hidden;
  border-radius: 100px;
  position: absolute;
  left: 50%;
  margin-top: 30px;
  transform: translate(-50%, -50%);
  border: 2px solid rgb(110, 110, 110);
  background-color: white;
  vertical-align: middle;
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
  margin: 0 7px;
  padding: 5px 10px;
  height: auto;
  line-height: inherit;
 }
 
 button:hover {
  color: white;
}
 
</style>
</head>

<body>
  <br><br><br>
    <div class="all-content"> 
      <div class="profile">
	          <div class="profile-header">
                <a href="${contextPath}/upload/ceoMember/${ceoMember.ceoPhoto}.jpg" >
                    <img id="f-photo-image" src="${contextPath}/upload/ceoMember/${ceoMember.ceoPhoto}_80x80.jpg" style="width: 110px">
                </a>
                <input type='hidden' name='ceoPhoto' value='${ceoMember.ceoPhoto}'>
              </div>
	        
	        <label for='f-name' class='form-label'>이름</label>
	        <input id='f-name' type='text' name='ceoName' readonly value='${ceoMember.ceoName}'><br>
	        
	        <label class="profile-label" for='f-nickname' class='form-label'>닉네임</label>
	        <input id='f-nickname' type='text' name='ceoNickname' readonly value='${ceoMember.ceoNickname}'><br>
	        
	        <label for='f-tel' class='form-label'>전화번호</label>
	        <input id='f-tel' type='tel' name='ceoTel' readonly value='${ceoMember.ceoTel}'><br>
	        
	        <label for='f-bossName' class='form-label'>대표자명</label>
	        <input id='f-bossName' type='bossName' name='ceoBossName' readonly value='${ceoMember.ceoBossName}'><br>
	        
	        <label for='f-licenseNo' class='form-label'>사업자 번호</label>
	        <input id='f-licenseNo' type='licenseNo' name='ceoLicenseNo' readonly value='${ceoMember.ceoLicenseNo}'><br>
	        
	        <label for='f-email' class='form-label'>이메일</label>
	        <input id='f-email' type='email' name='ceoEmail' readonly value='${ceoMember.ceoEmail}'><br>
	        
	        <label for='f-registeredDate' class='form-label'>가입일</label>
	        <input id='f-registeredDate' type='text'
	         name='ceoRegisteredDate' readonly value='${ceoMember.ceoRegisteredDate}'><br>
        </div>
        
        <div class="btn_wrap">
          <a href='list' class = "btn btn-outline-dark">목록</a>
          <a href='delete?ceono=${ceoMember.ceoNo}' class = "btn btn-outline-dark">탈퇴시키기</a>
        </div>
      </div>
</body>
</html>