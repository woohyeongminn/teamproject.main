<%@page import="com.ogong.pms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<style>
.btn {
font-size: 14px;
 line-height: 14px;
}
.all {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    text-align: center;
}
#createDt {
  text-align:right;
  font-size: 14px;
}
#name, #email, #tel {
text-align:left;
}
label {

}
</style>

<form>
	<div class="all">
	     <div id="photo">
	      <a href="${contextPath}/upload/member/${perMember.perPhoto}.jpg" >
	       <img id="f-photo-image" src="${contextPath}/upload/member/${perMember.perPhoto}_110x110.jpg" style="width: 110px">
	      </a>
	       <input type='hidden' name='perPhoto' value='${perMember.perPhoto}'>
	     </div>  
	    <div id="nickname">
	      <br><b>'${perMember.perNickname}'님</b>
	    </div>
	    <div id="createDt">
	      가입일: ${perMember.perRegisteredDate}
	    </div><hr>
	    <div id="name">
	      <label>이름</label>
	      <b>${perMember.perName}</b>
	    </div>
	    <div id="email">
	      <label>이메일</label>
	      <b>${perMember.perEmail}</b>
	    </div>        
	    <div id="tel">
	      <label>전화번호</label>
	      <b>${perMember.perTel}</b>
      </div><hr>
      
		<br><div class="d-grid gap-2 d-md-flex justify-content-md-end">
		  <input type="submit" value="프로필 수정" formaction="updateform" class ="btn btn-outline-dark"/>
		  <input type="submit" value="내 문의글" formaction="../askboard/permylist" class ="btn btn-outline-dark"/>
		  <input type="submit" value="내 북마크" formaction="../bookmark/list?perno=${loginUser.perNo}" class ="btn btn-outline-dark"/>
		  <input type="submit" value="내 리뷰" formaction="../cafe/reviewList" class ="btn btn-outline-dark"/>
		  <input type="submit" value="내 예약 " formaction="../cafe/reservationList"class ="btn btn-outline-dark"/>
		  <input type="submit" value="회원탈퇴" formaction="deleteform" class ="btn btn-outline-dark"/>
</div>
</div>
</form>

     
  <!-- 
<button class = "btn btn-outline-dark" ><a href='../study/list?perno=${loginUser.perNo}'>스터디 찾기</a></button>
<button class = "btn btn-outline-dark" ><a href='../cafe/list'>스터디카페 예약하기</a></button>
  -->
     