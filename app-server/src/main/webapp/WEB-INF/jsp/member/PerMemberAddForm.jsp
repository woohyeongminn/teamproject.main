<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="https://static.msscdn.net/mfile_outsrc/js/vendor/jquery-1.11.1.min.js?20160201"></script>
<style>
  form {
  max-width: 500px;
  }
  .btn {
   font-size: 14px;
   line-height: 12px;
  }
  b {
  text-align: center;
  font-size:20px
  }  
 .all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    font-size:14px;
  } 
  #top {
  text-align: center;
  }
</style>

<body>
  <div class="all-content">
   <br>
    <div id="top">
      <b style="font-size: 20px">🙋개인 회원 가입</b><br> 
    </div>
   <hr>
    <form id="member-form" action='add' name='perInfo' method='post' enctype="multipart/form-data" onsubmit="return checkValue()">
 
      <div id="mp">
        <label id='f-photo' for='f-photo' class="col-sm-2 col-form-label">사진</label>
        <input id='f-photo' type='file' name='photoFile' /><br>
      </div>

      <div id="mn">
        <label id='f-name' for='f-name' class="col-sm-2 col-form-label">이름</label>
        <input id='i-name' type='text' name='perName' placeholder="*필수"/><br>
      </div>
     
      
      <div id="mE">
	      <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
	      <input id='f-email' type='text' name='id' pattern="^[a-zA-Z0-9]+$" placeholder="*필수"/>@
	      <select name="site">
	        <option>naver.com</option>
	        <option>daum.net</option>
	        <option>gmail.com</option>
	        <option>kakao.com</option>
	      </select>
	      <input type="button" class="btn btn-outline-dark" value="중복확인" onclick="idOverlap()"/><br>
      </div>   
         
      <div id="mNn">
	      <label id='f-nicknam'for='f-nickname' class="col-sm-2 col-form-label">닉네임</label>
	      <input id='i-nickname' type='text' name='nick' placeholder="*필수" />
	      <input type="button" class="btn btn-outline-dark" value="중복확인" onclick="nickOverlap()"/><br>
      </div>
  
      <div id="mt">
	      <label id='f-tel'for='f-tel' class="col-sm-2 col-form-label">전화번호</label>
	      <input id='i-tel' type='text' name='tel' pattern="[0-9]+" minlength='3' maxlength='3'  style="width:50px;"/> -
	      <input type='text' name='tel' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:50px;"/> -
	      <input type='text' name='tel' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:50px;"/> <br>
      </div>
      
      <div id="mpw">
	      <label id='f-password' for='f-password' class="col-sm-2 col-form-label">비밀번호</label>
	      <input id='i-password' type='password' name='password'
	       pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{7,16}$"
	       title="영어(대소문자), 숫자, 특수문자를 포함해 8자 이상 16자 이하로 입력해주세요."
	       placeholder="*필수"/><br>
      </div>
     
     <div id="mpwc">
      <label id='f-passwordcheck' for='f-passwordcheck' class="col-sm-2 col-form-label">재입력</label>
      <input id='i-passwordcheck' type='password' name='perPassword' placeholder="비밀번호 확인"/><br>
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
  if (document.querySelector("#i-name").value == "" ||
      document.querySelector("#i-email").value == "" ||
      document.querySelector("#i-nickname").value == "" ||
      document.querySelector("#i-password").value == "") {
    window.alert("필수 입력 항목이 비어 있습니다.")
    //Swal.fire("필수 입력 항목이 비어 있습니다.")
    return false;
  }
};
</script>

<script>
function idOverlap(){
  
  var form = document.perInfo;
  
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
          alert("사용 가능한 이메일 입니다.");
        }else{  //ajax가 제대로 안됐을 때 .
          alert("이미 사용중인 이메일 입니다.");
        }
      },
      error : function(){
        alert("아이디 중복 확인 ajax 실행 실패");
      }
    });
    
  }

function nickOverlap(){
  
  var form = document.perInfo;
  
      console.log("nickOverlap 호출")
      console.log("닉네임 입력 값 : "+form.nick.value)
    $.ajax({
      type :"post",/* 전송 방식 */
      url :"nickOverlap", /* 컨트롤러 사용할 때. 내가 보낼 데이터의 주소. */
      data : {"nick" : form.nick.value},
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
    
  }
</script>
 



