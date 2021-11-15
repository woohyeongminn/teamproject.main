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
    margin-top: 5px;
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

</style>
<br><b>📝프로필 수정</b><hr>
<form method='post' enctype="multipart/form-data" onsubmit="return checkValue()">
  <div class="all">
    <div id="photo" >
       <input id='f-photo' type='file' name='photoFile' value='${perMember.perPhoto}'>
    <a href="${contextPath}/upload/member/${perMember.perPhoto}.jpg" >
      <img id="f-photo-image" src="${contextPath}/upload/member/${perMember.perPhoto}_110x110.jpg">
    </a>
    </div>
    
     <div class="profile">
      <div id="name">
       <label for='f-name' class='form-label'>이름</label>
       <input id='f-name' type='text' name='perName' value='${perMember.perName}'><br>
      </div>
      
      <div id="nickname"> 
       <label for='f-nickname' class='form-label'>닉네임</label>
       <input id='f-nickname' type='text' name='perNickname' value='${perMember.perNickname}'><br>
      </div>
      
      <div id="email"> 
       <label for='f-email' class='form-label'>이메일</label>
       <input id='f-email' type='email' name='perEmail' value='${perMember.perEmail}'><br>
      </div> 
      
      <div id="tel"> 
       <label for='f-tel' class='form-label'>전화번호</label>
       <input id='f-tel' type='tel' name='perTel' value='${perMember.perTel}'><br>
      </div>

      <div id="password"> 
       <label for='f-password' class='form-label'>비밀번호</label>
       <input id='f-password' type='password' name="perPassword"><br>
      </div>

      <input type="hidden" name="perNo" value="${perMember.perNo}"/>

      <div id="date"> 
       <label for='f-registeredDate' class='form-label'>가입일</label>
       <input id='f-registeredDate' name='ceoRegisteredDate' readonly value='${perMember.perRegisteredDate}'><br>
      </div>
    </div>
 
    <hr><div class="d-grid gap-2 d-md-flex justify-content-md-center">
      <input type="submit" value="수정하기" formaction="update" class ="btn btn-outline-dark"/>
      <input type="submit" value="취소하기" formaction="${contextPath}/app/member/detail" class ="btn btn-outline-dark"/>
    </div>
  </div>
</form>
     