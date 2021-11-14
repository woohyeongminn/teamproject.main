<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<style>
  form {
  max-width: 500px;
  }
  .btn {
   font-size: 14px;
   line-height: 14px;
  }
  b {
  text-align: center;
  font-size:20px
  }  
 .all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    text-align: center;
  }  
</style>
<script type="text/javascript">
function checkValue() {
	
	var form = document.ceoInfo;
	
	if(!form.ceoName.value){
	      alert("이름을 입력하세요.");
	      return false;
	 }
	
	if(!form.ceoNickname.value){
	      alert("닉네임을 입력하세요.");
	      return false;
	  }
	
	if(!form.tel1.value && !form.tel2.value && !form.tel3.value){
        alert("전화번호를 입력하세요.");
        return false;
    }
	
	if(!form.ceoBossname.value){
        alert("대표자명을 입력하세요.");
        return false;
    }
	
	if(!form.ceoLicenseno.value){
        alert("사업자번호를 입력하세요.");
        return false;
    }

	  if(!form.ceoEmail.value){
	        alert("이메일을 입력하세요.");
	        return false;
	   }
	  
/* 	   if(!form.email.value != "checkEmail"){
	         alert("이메일 중복체크를 해주세요.");
	         return false;
	   }
	    */
	  if(!form.password.value){
	        alert("비밀번호를 입력하세요.");
	        return false;
	   }
	  
	  if(form.ceoPassword.value ==""){
	        alert("비밀번호 확인란에 입력해주세요.");
	        form.passwordcheck.focus();
	        return false;
	    }
	  
	  if(form.ceoPassword.value != form.password.value){
	        alert("비밀번호를 동일하게 입력하세요.");
	        form.passwordcheck.focus();
	        return false;
	   }
}
</script>
	<div class="all-content">
	<br>
  <b style="font-size:20px">🏢기업 회원가입 </b>
<body>
  <hr>
   <%--  <c:forEach items="${ceoMemberList}" var="ceoMember">
      <c:if test="${ceoMember.ceoNickname eq nickname}">
      </c:if>
    </c:forEach> --%>
  
  <form id="member-form" action='add' name='ceoInfo' method='post' enctype="multipart/form-data" onsubmit="return checkValue()">
	  <label for='f-name' class="col-sm-2 col-form-label">이름</label>
	  <input id='f-name' type='text' name='ceoName' placeholder="이름"/><br>
	  
	  <label for='f-nickname' class="col-sm-2 col-form-label">닉네임</label>
	  <input id='f-nickname' type='text' name='ceoNickname' placeholder="닉네임" />
	  <input type="button" class="btn btn-outline-dark" value="중복확인" /><br>

	  <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
	  <input id='f-photo' type='file' name='photoFile' /><br>
	  <%-- <p>사진 미선택시 기본 프로필 사진이 등록됩니다.</p>
	  <c:if test="${empty photo}">
	    <input id='f-photo' type='hidden' name='photoFile' value="ceoProfile"/><br>
	  </c:if> --%>
	  
	  <label for='f-tel' class="col-sm-2 col-form-label">전화번호</label>
	  <input id='f-tel' type='text' name='tel' pattern="[0-9]+" minlength='3' maxlength='3'  style="width:50px;"/> -
	  <input id='f-tel' type='text' name='tel' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:50px;"/> -
	  <input id='f-tel' type='text' name='tel' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:50px;"/> <br>
	  
	  <label for='f-bossname' class="col-sm-2 col-form-label">대표자명</label>
    <input id='f-bossname' type='text' name='ceoBossName' placeholder="대표자명"/><br>
    
    <label for='f-licenseno' class="col-sm-2 col-form-label">사업자 등록번호</label>
    <input id='f-licenseno' type='text' name='ceoLicenseNo'
     pattern="[0-9]{10}" title='10자리 숫자를 입력해주세요.' maxlength='10' placeholder="사업자 등록번호"/><br>
    
	  <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
	  <input id='f-email' type='text' name='ceoEmail' pattern="^[a-zA-Z0-9]+$" placeholder="이메일" onkeydown="inputEmail()"/>@
	  <select name="site">
		  <option>naver.com</option>
		  <option>daum.net</option>
		  <option>gmail.com</option>
		  <option>kakao.com</option>
	  </select>
	  <input type="button" class="btn btn-outline-dark" value="중복확인"/><br>
	  
	  <label for='f-password' class="col-sm-2 col-form-label">비밀번호</label>
	  <input id='f-password' type='password' name='password'
	   pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{7,16}$"
	   title="영어(대소문자), 숫자, 특수문자를 포함해 8자 이상 16자 이하로 입력해주세요."
	   placeholder="비밀번호"/><br>
	 
	  <label for='f-passwordcheck' class="col-sm-2 col-form-label">비밀번호 확인</label>
	  <input id='f-passwordcheck' type='password' name='ceoPassword' placeholder="비밀번호 확인"/><br>
    <hr>
     <div class="d-grid gap-2 d-md-flex justify-content-md-end">
       <button class="btn btn-outline-dark" type="submit" >가입하기</button> 
       <button class="btn btn-outline-dark" >취소하기</button>
     </div>
	 </form>
	 </div>
</body>
