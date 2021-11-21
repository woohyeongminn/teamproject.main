<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>


<style>

  
 .all-content {
    width: 100%;
    max-width: 600px;
    margin: 0 auto;
    text-align: center;
    margin-top: 50px;
  }
  
  form {
  max-width: 600px;
  font-size: 14px;
  text-align: left;
  }
  
  .col-form-label {
   width: 120px;
  margin-left: 105px;
  }
  
  .btn {
   font-size: 10px;
    line-height: 10px;
    padding: 6px 12px;
    margin-bottom: 2px;
  }
  
  b {
  text-align: center;
  }  

  
  input:invalid {
    color: gray;
  } 
  
  .s-btn {
    padding: 10px 20px;
    font-size: 13px;
    margin-top: 9px;
  }
   
</style>

	<div class="all-content">
	<br>
  <b style="font-size:14px">🏢기업 회원가입 </b>
<body>
  <hr>
  
  <form id="member-form" action='add' name='ceoInfo' method='post' enctype="multipart/form-data" onsubmit="return checkValue()">
	  <label for='f-name' class="col-sm-2 col-form-label">이름</label>
	  <input id='f-name' type='text' name='ceoName' placeholder="*필수"/><br>
	  
	  <label for='f-nickname' class="col-sm-2 col-form-label">닉네임</label>
	  <input id='f-nickname' type='text' name='ceoNickname' placeholder="*필수" />
	  <input type="button" class="btn btn-outline-dark" value="중복확인" onclick="nickOverlap()"/><br>

	  <label for='f-photo' class="col-sm-2 col-form-label">사진</label>
	  <input id='f-photo' type='file' name='photoFile' /><br>
	  <%-- <p>사진 미선택시 기본 프로필 사진이 등록됩니다.</p>
	  <c:if test="${empty photo}">
	    <input id='f-photo' type='hidden' name='photoFile' value="ceoProfile"/><br>
	  </c:if> --%>
	  
	  <label for='f-tel' class="col-sm-2 col-form-label">전화번호</label>
	  <input id='f-tel' type='text' name='tel1' pattern="[0-9]+" minlength='3' maxlength='3'  style="width:50px;"/> -
	  <input id='f-tel' type='text' name='tel2' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:50px;"/> -
	  <input id='f-tel' type='text' name='tel3' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:50px;"/> <br>
	  
	  <label for='f-bossname' class="col-sm-2 col-form-label">대표자명</label>
    <input id='f-bossname' type='text' name='ceoBossName' placeholder="*필수"/><br>
    
    <label for='f-licenseno' class="col-sm-2 col-form-label">사업자 등록번호</label>
    <input id='f-licenseno' type='text' name='ceoLicenseNo'
     pattern="[0-9]{10}" title='10자리 숫자를 입력해주세요.' maxlength='10' placeholder="*필수"/>
    <input type="button" class="btn btn-outline-dark" value="중복확인" onclick="licenseOverlap()"/><br>
    
	  <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
	  <input id='f-email' type='text' name='id' pattern="^[a-zA-Z0-9]+$" placeholder="*필수"/> @ 
	  <select name="site" style="height: 24px;">
		  <option>naver.com</option>
		  <option>daum.net</option>
		  <option>gmail.com</option>
		  <option>kakao.com</option>
	  </select>
	  <input type="button" class="btn btn-outline-dark" value="중복확인" onclick="idOverlap()"/><br>
	  <input type="hidden" name="idDuplication" value="idUncheck"/>
	  
	  <label for='f-password' class="col-sm-2 col-form-label">비밀번호</label>
	  <input id='f-password' type='password' name='password'
	   pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{7,16}$"
	   title="영어(대소문자), 숫자, 특수문자를 포함해 8자 이상 16자 이하로 입력해주세요."
	   placeholder="*필수"/><br>
	 
	  <label for='f-passwordcheck' class="col-sm-2 col-form-label">비밀번호 확인</label>
	  <input id='f-passwordcheck' type='password' name='ceoPassword' placeholder="비밀번호 확인"/><br>
    <hr>
     <div class="d-grid gap-2 d-md-flex justify-content-md-center">
       <input type="submit" class="btn btn-outline-dark s-btn" value="✔가입하기"/> 
       <a type="button" class="btn btn-outline-dark s-btn" href="${contextPath}/app/index">❌취소하기</a>
     </div>
	 </form>
	 </div>
</body>

<script>
function idOverlap(){
	
	var form = document.ceoInfo;
	
      console.log("idOverlap 호출")
      console.log("아이디 입력 값 : "+form.id.value)
    $.ajax({
      type :"post",/* 전송 방식 */
      url :"idOverlap", /* 컨트롤러 사용할 때. 내가 보낼 데이터의 주소. */
      data : {"id" : form.id.value+"@"+form.site.value},
      /* JSON형식 안에 JSON 형식으로 표현한 데이터. 
            "파라미터 이름" : 폼태그에 적은 NAME 값.ID입력창의 NAME값.value 여러 개도 가능
      data :{ "id" : joinForm.id.value, 
      "id1" : joinForm.password.value}, 이렇게도 사용 가능.         
      */
      dataType : "text",  /* text, xml, html, script, json, jsonp 가능 */
            //정상적인 통신을 했다면 function은 백엔드 단에서 데이터를 처리.
            
      success : function(data){ 
        
    	  console.log(data);
    	  
    	  if(data=="1"){
          alert("이 아이디는 사용 가능합니다.");
          form.idDuplication.value = "idCheck";
        }else{  //ajax가 제대로 안됐을 때 .
          alert("이 아이디는 사용  불가능합니다.");
        }
      },
      error : function(){
        alert("아이디 중복 확인 ajax 실행 실패");
      }
    });
    
  };

function nickOverlap(){
 
 var form = document.ceoInfo;
 
     console.log("nickOverlap 호출")
     console.log("닉네임 입력 값 : "+form.ceoNickname.value)
   $.ajax({
     type :"post",/* 전송 방식 */
     url :"nickOverlap", /* 컨트롤러 사용할 때. 내가 보낼 데이터의 주소. */
     data : {"nick" : form.ceoNickname.value},
     /* JSON형식 안에 JSON 형식으로 표현한 데이터. 
           "파라미터 이름" : 폼태그에 적은 NAME 값.ID입력창의 NAME값.value 여러 개도 가능
     data :{ "id" : joinForm.id.value, 
     "id1" : joinForm.password.value}, 이렇게도 사용 가능.         
     */
     dataType : "text",  /* text, xml, html, script, json, jsonp 가능 */
           //정상적인 통신을 했다면 function은 백엔드 단에서 데이터를 처리.
           
     success : function(data){ 
       
       console.log(data);
       
       if(data=="1"){
         alert("사용 가능한 닉네임 입니다.");
       }else{  //ajax가 제대로 안됐을 때 .
         alert("이미 사용중인 닉네임 입니다.");
       }
     },
     error : function(){
       alert("닉네임 중복 확인 ajax 실행 오류");
     }
   });
   
 };
  
 function licenseOverlap(){
	 
	 var form = document.ceoInfo;
	 
	     console.log("licenseOverlap 호출")
	     console.log("사업자번호 입력 값 : "+form.ceoLicenseNo.value)
	   $.ajax({
	     type :"post",/* 전송 방식 */
	     url :"licenseOverlap", /* 컨트롤러 사용할 때. 내가 보낼 데이터의 주소. */
	     data : {"license" : form.ceoLicenseNo.value},
	     /* JSON형식 안에 JSON 형식으로 표현한 데이터. 
	           "파라미터 이름" : 폼태그에 적은 NAME 값.ID입력창의 NAME값.value 여러 개도 가능
	     data :{ "id" : joinForm.id.value, 
	     "id1" : joinForm.password.value}, 이렇게도 사용 가능.         
	     */
	     dataType : "text",  /* text, xml, html, script, json, jsonp 가능 */
	           //정상적인 통신을 했다면 function은 백엔드 단에서 데이터를 처리.
	           
	     success : function(data){ 
	       
	       console.log(data);
	       
	       if(data=="1"){
	         alert("사용 가능한 사업자번호 입니다.");
	       }else{  //ajax가 제대로 안됐을 때 .
	         alert("이미 사용중인 사업자번호 입니다.");
	       }
	     },
	     error : function(){
	       alert("사업자번호 중복 확인 ajax 실행 오류");
	     }
	   });
	   
	 };
  
</script>



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
  
  if(!form.ceoBossName.value){
        alert("대표자명을 입력하세요.");
        return false;
    }
  
  if(!form.ceoLicenseNo.value){
        alert("사업자번호를 입력하세요.");
        return false;
    }

    if(!form.id.value){
          alert("이메일을 입력하세요.");
          return false;
     }
    
    /*if(form.id.value != "idCheck"){
           alert("이메일 중복체크를 해주세요.");
           return false;
     }*/
     
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