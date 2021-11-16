<%@page import="com.ogong.pms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="https://static.msscdn.net/mfile_outsrc/js/vendor/jquery-1.11.1.min.js?20160201"></script>
<style>
 .all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
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
 #title {
 text-align: center;
 } 
</style>

<div class="all-content">     
  <br><div id="title"><b>📝 프로필 수정</b></div><hr>
    <form action='update' name='perInfo' method='post' enctype="multipart/form-data" onsubmit="return checkValue()">
	   <div id="photo">
	    <input id='f-photo' type='file' name='photoFile' value='${perMember.perPhoto}'/>
	     <a href="${contextPath}/upload/member/${perMember.perPhoto}.jpg" >
	      <img id="f-photo-image" src="${contextPath}/upload/member/${perMember.perPhoto}_110x110.jpg">
	     </a>
	   </div>
	    <hr>
	      <div id="mN">
	        <label id='f-name' for='f-name' class="col-sm-2 col-form-label">이름</label>
	        <input type='text' name='perName' value="${perMember.perName}" readonly style="border:0 solid black"/><br>
	      </div>      
	      <div id="mE">
	        <label for='f-email' class="col-sm-2 col-form-label">이메일</label>
	        <input type='text' name='perEmail' value="${perMember.perEmail}" readonly style="border:0 solid black"/>  
	      </div>
	      <div id="mT">
	        <label for='f-tel' class="col-sm-2 col-form-label">전화번호</label>
	        <input type='text' name='perTel' value="${perMember.perTel}" readonly style="border:0 solid black"/>  
	      </div>        
	      <div id="mNn">
	        <label id='f-nicknam'for='f-nickname' class="col-sm-2 col-form-label">닉네임</label>
	        <input id='i-nickname' type='text' name='nick' value="${perMember.perNickname}" />
	        <input type="button" class="btn btn-outline-dark" value="중복확인" onclick="nickOverlap()"/><br>
	      </div>
	      <div id="mpw">
	        <label id='f-password' for='f-password' class="col-sm-2 col-form-label">비밀번호</label>
	        <input id='i-password' type='password' name='perPassword'
	         pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{7,16}$"
	         title="영어(대소문자), 숫자, 특수문자를 포함해 8자 이상 16자 이하로 입력해주세요."
	         placeholder="*입력하세요"/><br>
	      </div>  
	  
	  
	    <hr>  
	   <div class="d-grid gap-2 d-md-flex justify-content-md-center">
	    <input type="submit" value="🛠수정하기" class ="btn btn-outline-dark"/>
	    <input type="submit" value="❌취소하기" formaction="${contextPath}/app/member/detail" class ="btn btn-outline-dark"/>
	   </div>
	  </form>
</div>

<script>  
  document.querySelector("#all").onsubmit = () => {
  if (document.querySelector("#password").value == "") {
    alert("**비밀번호를 입력해주세요.")
    return false;
};
</script>
<script>
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