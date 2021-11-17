<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>비밀번호 변경</title>
<style>
</style>
<script type="text/javascript">
function checkValue() {
	  
	  var form = document.ceoPw;
	  
	  if(form.oldPw.value ==""){
		    alert("기존 비밀번호를 입력해주세요.");
		    form.oldPw.focus();
		    return false;
		}
	  
		if(form.newPw.value ==""){
		    alert("변경할 비밀번호를 입력해주세요.");
		    form.newPw.focus();
		    return false;
		}
		
		if(form.newPw.value != form.newPwChk.value){
		    alert("변경할 비밀번호를 동일하게 입력하세요.");
		    form.newPwChk.focus();
		    return false;
		}
}
</script>
    
</head>
<body>

<form action='updatepassword' method='post' name="ceoPw">

	<input type='hidden' name='ceoNo' value='${ceoMember.ceoNo}'>
	      
	<label for='oldPw' class='form-label'>기존 비밀번호</label>
	<input id='oldPw' type='text' name='oldPw'/><br>
	
	<label for='newPw' class='form-label'>변경 비밀번호</label>
	<input id='newPw' type='text' name='newPw'
	 pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{7,16}$"
   title="영어(대소문자), 숫자, 특수문자를 포함해 8자 이상 16자 이하로 입력해주세요."
   placeholder="*필수"/><br>

	<label for='newPwChk' class='form-label'>변경 비밀번호 확인</label>
	<input id='newPwChk' type='text' name='newPwChk'
	pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{7,16}$"
   title="영어(대소문자), 숫자, 특수문자를 포함해 8자 이상 16자 이하로 입력해주세요."
   placeholder="*필수"/><br>
	
	<button type="submit" class="btn btn-outline-dark">확인</button>
</form>

</body>
</html>
