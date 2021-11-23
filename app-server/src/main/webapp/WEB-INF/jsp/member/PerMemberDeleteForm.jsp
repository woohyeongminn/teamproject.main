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
   line-height: 12px;
  }
  #all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    font-size:14px;
  } 
  #top {
  text-align: center;
  }
  a {
  color: black;
  }
  #f-stauts {
  padding-right: 30px;
  }
</style>

<body>
  <div id="all-content">
   <br>
    <div id="top">
      <b style="font-size: 18px">🚫회원 탈퇴</b><br> 
    </div>
    <br>
    <b>
    1. 탈퇴 신청한 아이디로는 즉시 재가입이 불가능 합니다.
    </b>
    <br>
    <b>
    2. 탈퇴 후 해당계정은 복구할 수 없습니다.
    </b>
    <br>
    <b>    
    3. 등록된 게시글 및 이용내역은 삭제되지 않습니다.
       (삭제 후 탈퇴해주세요.)
    </b>
    <hr>
    <form id="member-form" action='delete' name='perInfo' method='post' enctype="multipart/form-data" onsubmit="return checkValue()">
      <div id="me">
        <label id='f-email' for='f-email' class="col-sm-2 col-form-label">이메일</label>
        <input id='i-email' type='text' name='perEmail' value="${perMember.perEmail}"
        style="border:0 solid black" readonly/><br>
      </div>

      <div id="mp">
        <label id='f-password' for='f-password' class="col-sm-2 col-form-label">비밀번호</label>
        <input id='i-password' type='password' name='perPassword' placeholder="*비밀번호"/><br>
      </div>
      
      <input type="hidden" name="perNo" value="${perMember.perNo}">
      
	    <label for='f-status'>탈퇴사유</label>
	    <select id="f-status" name='reason' >
	      <option value='1' >아이디 변경</option>
	      <option value='2' >사이트 이용 불만</option>
	      <option value='3' >사이트 이용 안함</option>
	      <option value='4' >기타</option>
	    </select><br> 

      <div id="etc">
        <label id='f-etc' for='f-etc' class="col-sm-2 col-form-label">기타</label>
        <input id='i-etc' type='text' name='etc' placeholder="*사유를 입력해주세요"/><br>
      </div>         
      
       <div class="d-grid gap-2 d-md-flex justify-content-md-end">
         <button class="btn btn-outline-dark" type="submit" onclick="deleteMember()">❌탈퇴하기</button> 
       </div><hr> 
       <div class="d-grid gap-2 d-md-flex justify-content-md-end">
         <a href="detail">취소하기</a>
       </div> 
   </form>
  </div>
</body>

<script>  
  document.querySelector("#all-content").onsubmit = () => {
   if (document.querySelector("#i-password").value == "" ) {
    alert("**비밀번호를 입력해주세요.")
    return false;
  }
};

function deletePop() {
	alert("정말 탈퇴하시겠습니까?")
}

var fStatus = document.querySelector("#f-status");
var etcRow = document.querySelector("#etc");

etcRow.style["display"] = "none";

fStatus.addEventListener("input", function() {
  if (fStatus.value == "4") {
	  etcRow.style["display"] = "";
  } else {
	  etc.style["display"] = "none";
  }
});

function deleteMember(){
	alert("탈퇴 되었습니다. 이용해주셔서 감사합니다.")
}

</script>
 



