<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="https://static.msscdn.net/mfile_outsrc/js/vendor/jquery-1.11.1.min.js?20160201"></script>
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
<body>
  <div class="all-content">
   <br>
   <b style="font-size: 20px">🙋개인 회원 가입</b><br> 
   <hr>
    <form id="member-form" action='add' name='perInfo' method='post' enctype="multipart/form-data" onsubmit="return checkValue()">
      
      <div id="mn">
	      <label for='f-name' class="col-sm-2 col-form-label">이름</label>
	      <input id='f-name' type='text' name='perName' placeholder="*필수"/><br>
      </div>
     
      <div id="mNn">
	      <label for='f-nickname' class="col-sm-2 col-form-label">닉네임</label>
	      <input id='f-nickname' type='text' name='perNickname' placeholder="*필수" />
	      <input type="button" class="btn btn-outline-dark" value="중복확인" /><br>
      </div>
  
      <div id="mp">
	      <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
        <input id='f-photo' type='file' name='photoFile' /><br>
      </div>
  
      <div id="mt">
	      <label for='f-tel' class="col-sm-2 col-form-label">전화번호</label>
	      <input id='f-tel' type='text' name='tel' pattern="[0-9]+" minlength='3' maxlength='3'  style="width:50px;"/> -
	      <input id='f-tel' type='text' name='tel' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:50px;"/> -
	      <input id='f-tel' type='text' name='tel' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:50px;"/> <br>
      </div>

      <div id="mE">
	      <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
	      <input id='f-email' type='text' name='perEmail' pattern="^[a-zA-Z0-9]+$" placeholder="*필수" onkeydown="inputEmail()"/>@
	      <select name="site">
	        <option>naver.com</option>
	        <option>daum.net</option>
	        <option>gmail.com</option>
	        <option>kakao.com</option>
	      </select>
	      <input type="button" class="btn btn-outline-dark" value="중복확인"/><br>
      </div>      
      
      <div id="mpw">
	      <label for='f-password' class="col-sm-2 col-form-label">비밀번호</label>
	      <input id='f-password' type='password' name='password'
	       pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{7,16}$"
	       title="영어(대소문자), 숫자, 특수문자를 포함해 8자 이상 16자 이하로 입력해주세요."
	       placeholder="*필수"/><br>
      </div>
     
     <div id="mpwc">
      <label for='f-passwordcheck' class="col-sm-2 col-form-label">비밀번호 확인</label>
      <input id='f-passwordcheck' type='password' name='perPassword' placeholder="비밀번호 확인"/><br>
     </div>
      <hr>
       <div class="d-grid gap-2 d-md-flex justify-content-md-center">
         <button class="btn btn-outline-dark" type="submit" >✔가입하기</button> 
         <a type="button" class="btn btn-outline-dark" href="${contextPath}/app/index">❌취소하기</a>
       </div> 
   </form>
   </div>
</body>











<script type="text/javascript">
function checkValue() {
  
  var form = document.ceoInfo;
  
  if(!form.perName.value){
        alert("이름을 입력하세요.");
        return false;
   }
  
  if(!form.perNickname.value){
        alert("닉네임을 입력하세요.");
        return false;
    }
  
  if(!form.tel1.value && !form.tel2.value && !form.tel3.value){
        alert("전화번호를 입력하세요.");
        return false;
    }

    if(!form.perEmail.value){
          alert("이메일을 입력하세요.");
          return false;
     }
    
/*     if(!form.email.value != "checkEmail"){
           alert("이메일 중복체크를 해주세요.");
           return false;
     }
      */
    if(!form.password.value){
          alert("비밀번호를 입력하세요.");
          return false;
     }
    
    if(form.perPassword.value ==""){
          alert("비밀번호 확인란에 입력해주세요.");
          form.passwordcheck.focus();
          return false;
      }
    
    if(form.perPassword.value != form.password.value){
          alert("비밀번호를 동일하게 입력하세요.");
          form.passwordcheck.focus();
          return false;
     }
}
</script>

<script>
document.querySelector("#member-form").onsubmit = () => {
  if (document.querySelector("#f-name").value == "" ||
      document.querySelector("#f-email").value == "" ||
      document.querySelector("#f-nickname").value == "" ||
      document.querySelector("#f-password").value == "") {
    window.alert("필수 입력 항목이 비어 있습니다.")
    //Swal.fire("필수 입력 항목이 비어 있습니다.")
    return false;
  }
};
</script>

 



