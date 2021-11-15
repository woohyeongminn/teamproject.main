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
#f-photo-image {
 border-radius: 20px; 
 border-style: ridge;
}
.round {
  max-width: 500px;
  background-color: white;
  border-radius: 15px;
  border: 2px solid rgb(110, 110, 110);
  text-align: center;
  padding-bottom: 14px;
  padding-left: 10px;
}
.in {
margin-top: 10px
}
#createDt {
  text-align:right;
  font-size: 14px;
}
#nickname, #email, #tel {
text-align:left;
}
#crud {
 font-size: 14px;
 margin-left: 310px;
}
#pN {
margin-left: 22px;
}
#pE {
margin-left: 24px;
}
#pT {
margin-left: 8px;
}

</style>

<form>
	<div class="all">
	     <br><div class="photo">
	      <a href="${contextPath}/upload/member/${perMember.perPhoto}.jpg" >
	       <img id="f-photo-image" src="${contextPath}/upload/member/${perMember.perPhoto}_110x110.jpg" style="width: 110px">
	      </a>
	       <input type='hidden' name='perPhoto' value='${perMember.perPhoto}'>
	     </div>  
	    <div id="name">
	      <b class="col-sm-2 col-form-label">'${perMember.perName}'님</b>
	    </div>
	    <div id="createDt" >
	      가입일: ${perMember.perRegisteredDate}
	    </div><hr>
	   <div class="round"> 
	    <div class="in">
		    <div id="nickname">
		      <label>닉네임</label>
		      <b id="pN">${perMember.perNickname}</b>
		    </div>
		    <div id="email">
		      <label>이메일</label>
		      <b id="pE">${perMember.perEmail}</b>
		    </div>        
		    <div id="tel">
		      <label>전화번호</label>
		      <b id="pT">${perMember.perTel}</b>
	      </div>
	      <div id="crud">
				  <a href="updateform" >프로필 수정</a> |
		      <a href="deleteform">회원 탈퇴</a>
	      </div>
	    </div>
    </div>
		<hr><br><div class="d-grid gap-2 d-md-flex justify-content-md-center">
		  <input type="submit" value="📚내 북마크" formaction="../bookmark/list?perno=${loginUser.perNo}" class ="btn btn-outline-dark"/>
		  <input type="submit" value="🧾내 예약 " formaction="../cafe/reservationList"class ="btn btn-outline-dark"/>
		  <input type="submit" value="✒내 리뷰" formaction="../cafe/reviewList" class ="btn btn-outline-dark"/>
		  <input type="submit" value="💬내 문의글" formaction="../askboard/permylist" class ="btn btn-outline-dark"/>
   </div>
</div>
</form>

     
  <!-- 
<button class = "btn btn-outline-dark" ><a href='../study/list?perno=${loginUser.perNo}'>스터디 찾기</a></button>
<button class = "btn btn-outline-dark" ><a href='../cafe/list'>스터디카페 예약하기</a></button>
  -->
     