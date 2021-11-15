<%@page import="com.ogong.pms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<style>
.profile {
		 max-width: 500px;
		 margin: 10px auto 0;
		 background-color: white;
		 border-radius: 15px;
     border: 2px solid rgb(110, 110, 110);
     text-align: center;
     padding-bottom: 30px;
     padding-left: 60px;
     font-size:14px;
}
#f-photo-image {
 border-radius: 20px; 
 border-style: ridge;
}
.btn {
    font-size: 14px;
    line-height: 14px;
}
b {
		margin-left: 300px;
		font-size: 14px;
}
.all {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    text-align: center;
}
#name {
    margin-top: 15px;
    margin-right: 213px;
}
#nickname {
    margin-right: 197px;
}
#email{
    margin-right: 195px;
}
#tel {
    margin-right: 179px;
}
#date {
    margin-right: 191px;
}
#password {
    margin-right: 175px;
}
#i-name, #i-nickname, #i-email,
#i-password, #i-registeredDate, #i-tel {
  xborder:0 solid black;

}
#i-name {
  margin-top:10px;
  margin-right: 70px;
}
#i-nickname {
  margin-right: 83px;
}
#i-email {
  margin-right: 83px;
}
#i-tel {
  margin-right: 97px;
}
#i-password {
  margin-right: 97px;
}

#i-registeredDate {
  margin-right: 83px;
}

</style>
<br><b>📝프로필 수정</b><hr>
<form method='post' enctype="multipart/form-data" onsubmit="return checkValue()">
  <div class="all">
  
       <input id='f-photo' type='file' name='photoFile' value='${perMember.perPhoto}'>
    <a href="${contextPath}/upload/member/${perMember.perPhoto}.jpg" >
      <img id="f-photo-image" src="${contextPath}/upload/member/${perMember.perPhoto}_110x110.jpg">
    </a><hr>
    
     <div class="profile">

       <label for='f-name' class='form-label'>이름</label>
       <input id='i-name'  type='text' name='perName' value='${perMember.perName}' readonly><br>
      
       <label for='f-nickname' class='form-label'>닉네임</label>
       <input id='i-nickname' type='text' name='perNickname' value='${perMember.perNickname}'><br>
      
       <label for='f-email' class='form-label'>이메일</label>
       <input id='i-email'  type='email' name='perEmail' value='${perMember.perEmail}'><br>
      
       <label for='f-tel' class='form-label'>전화번호</label>
       <input id='i-tel' type='tel' name='perTel' value='${perMember.perTel}'><br>

       <label for='f-password' class='form-label'>비밀번호</label>
       <input id='i-password' type='password' name="perPassword" placeholder="*****"><br>
      
      <input type="hidden" name="perNo" value="${perMember.perNo}"/>

       <label for='f-registeredDate' class='form-label'>가입일</label>
       <input id='i-registeredDate'  name='perRegisteredDate' readonly value='${perMember.perRegisteredDate}'><br>
    </div>
 
    <hr><div class="d-grid gap-2 d-md-flex justify-content-md-center">
      <input type="submit" value="수정하기" formaction="update" class ="btn btn-outline-dark"/>
      <input type="submit" value="취소하기" formaction="${contextPath}/app/member/detail" class ="btn btn-outline-dark"/>
    </div>
  </div>
</form>

<script>  
  document.querySelector("#all").onsubmit = () => {
  if (document.querySelector("#password").value == "") {
    alert("**비밀번호를 입력해주세요.")
    return false;
};
</script>



     