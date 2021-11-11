<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://static.msscdn.net/mfile_outsrc/js/vendor/jquery-1.11.1.min.js?20160201"></script>
<style>
* {
font-size: 14px;
}

body {
  height: auto;
}
a {
text-decoration: none;
}

legend {
  text-align: center;
}

.all-content {
  width: 100%;
  margin: 0 auto;
  height: 800px;
}

ul{list-style:none;}

.tabmenu{ 
  max-width:900px; 
  margin: 0 auto; 
  position:relative; 
}

.tabmenu > ul {
  padding: 0;
}

.tabmenu > ul > li{
  display:  inline-block;
  width:33.33%; 
  float:left;  
  text-align:center; 
  background :#f9f9f9;
}

.tabmenu > ul > li > a{
  display:block;
  line-height:40px;
  height:40px;
  text-decoration:none; 
  color: #000;
}

.tabCon{
  display:none; 
  padding: 20px;
  position:absolute;
  left:0;
  box-sizing: border-box; 
  border : 5px solid #f9f9f9;
  width: 900px;
  height: 620px;
}

.btnCon:target  {
  background : #ccc;
  
  }

.btnCon:target .tabCon{
  display: block;
}

.btnCon:target .tabbtn{
  font-weight: bold;
}

.profile {
  max-width: 500px;
  margin: 100px auto 0;
  background-color: white;
  border-radius: 15px;
  border: 2px solid rgb(110, 110, 110);
  text-align: center;
  padding-bottom: 30px;
  padding-left: 60px;
}

.profile > label {
  margin-right: 5px;
  text-align: center;
  font-weight: 600;
}

.profile > label, span {
  display: inline-block;
  padding: 5px 5px;
  width: 103px;
}

.profile .profile-header {
  padding: 0;
  height: 60px;
  display: flex;
  align-items: center; 
}

.profile .profile-header>a {
  display:inline-block;
  text-decoration:none;
  width: 110px;
  height: 110px;
  overflow: hidden;
  border-radius: 100px;
  position: absolute;
  left: 50%;
  margin-top: 30px;
  transform: translate(-50%, -50%);
  border: 2px solid rgb(110, 110, 110);
  background-color: white;
  vertical-align: middle;
}

 .profile .profile-header .profile-img {
    margin-top: 8px;
    margin-left: 3px;
}

 .btn_wrap {
  max-width: 420px;
  margin: 20px auto 0;
  text-align: center;
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-bottom: 100px;
 }
 
 .btn_wrap .btn {
  margin: 0 7px;
  padding: 5px 10px;
  height: auto;
  line-height: inherit;
 }
 
 button:hover {
  color: white;
}

#f-photo {
    width: 50%;
    margin: 0 auto;
    margin-bottom: 20px;
    display: block;
}

</style>

<script type="text/javascript">
function checkValue() {
  
  var form = document.ceoInfo;
  
  if(!form.name.value){
        alert("이름을 입력하세요.");
        return false;
   }
  
  if(!form.nickname.value){
        alert("닉네임을 입력하세요.");
        return false;
   }
  
  if(!form.tel1.value && !form.tel2.value && !form.tel3.value){
        alert("전화번호를 입력하세요.");
        return false;
   }

   if(!form.email.value){
        alert("이메일을 입력하세요.");
        return false;
    }
   
    if(!form.email.value != "checkEmail"){
        alert("이메일 중복체크를 해주세요.");
        return false;
    }
     
   if(!form.password.value){
        alert("비밀번호를 입력하세요.");
        return false;
    }
}

</script>
</head>

<body>
<br><br><br>
<div class="all-content"> 
  <div class="tabmenu">
   <ul>
    <li id="tab1" class="btnCon"><a class="tabbtn first" href="#tab1">내 프로필</a>
     <div class="tabCon" >
      <form action='update' method='post' enctype="multipart/form-data">
	      <div class="profile">
	        <div class="profile-header">
              <a href="../upload/ceoMember/${ceoMember.ceoPhoto}.jpg" >
                <img id="f-photo-image" style="margin-top: 8px;"   src="../upload/ceoMember/${ceoMember.ceoPhoto}_80x80.jpg">
              </a>
          </div>
          
          <input id='f-photo' type='file' name='photo' value='${ceoMember.ceoPhoto}'>
	
	        <label for='f-name' class='form-label'>이름</label>
	        <input id='f-name' type='text' name='name'  value='${ceoMember.ceoName}'><br>
	        
	        <label for='f-nickname' class='form-label'>닉네임</label>
	        <input id='f-nickname' type='text' name='nickname' value='${ceoMember.ceoNickname}'><br>
	        
	        <label for='f-tel' class='form-label'>전화번호</label>
	        <input id='f-tel' type='tel' name='tel' value='${ceoMember.ceoTel}'><br>
	        
	        <label for='f-email' class='form-label'>이메일</label>
	        <input id='f-email' type='email' name='email' value='${ceoMember.ceoEmail}'><br>
	        
	        <%-- <label for='f-password' class='form-label'>암호</label>
	        <input id='f-password' type='password' name='password' value='${ceoMember.ceoPassword}'><br> --%>
	        
	        <label for='f-registeredDate' class='form-label'>가입일</label>
	        <input id='f-registeredDate' type='registeredDate' style="border:0px"
	         name='registeredDate' readonly value='${ceoMember.ceoRegisteredDate}'><br>
	      </div>
	

	
	
	<section class="n-section-block">
      <table class="n-table table-row my-info-modify">
       <tr id="password-area">
         <th scope="row">비밀번호</th>
         <td><button  class="n-btn w100 btn-sm btn-default cert-hidden" id="change-password-btn">비밀번호 변경</button></td>
       </tr>
                     <!--비밀번호 변경-->
       <tr id="change-password-area" style="display: none">
         <th scope="row">비밀번호</th>
           <td colspan="2">
             <div class="my-info-modify">
                 <div class="my-info-modify">
                   <div class="input">
                       <label for="password">현재 비밀번호</label>
                       <input type="password" class="n-input" id="password">
                       <span id="password-invalid" class="validate danger"></span>
                   </div>
                   
                   <div class="input">
                       <label for="newPassword">신규 비밀번호</label>
                       <input type="password" class="n-input" id="newPassword" maxlength="20">
                       <span id = "new-password-invalid" class="validate danger"></span>
                       <span id="valid-newPassword" class="validate" style="display: none">사용 가능한 비밀번호입니다.</span>
                   </div>
                   
                   <div class="input">
                       <label for="confirmPassword">신규 비밀번호 재 입력</label>
                       <input type="password" class="n-input" id="confirmPassword" maxlength="20">
                       <span id="confirm-password-invalid" class="validate danger"></span>
                       <span  id="valid-confirmPassword" class="validate" style="display: none">사용 가능한 비밀번호입니다.</span>
                   </div>
                   <div class="btn-group">
                       <button type="button" class="n-btn btn-sm btn-lighter" id="change-password-cancel-btn">취소</button>
                       <button type="button" class="n-btn btn-sm btn-accent disabled" id="change-password-finish-btn" disabled >완료</button>
                   </div>
             </div>
           </td>
       </tr>
      </table>
     </section>
	
	
	
	
	
	
	
	
	
	      <div class="btn_wrap">
		      <button type="submit" class="btn btn-outline-dark">수정</button>
		      <a href='detail' class="btn btn-outline-dark">뒤로가기</a>
	      </div>
      </form>
     </div>
	  </li>
	  <li id="tab2" class="btnCon"><a class="tabbtn" href="cafe/detail">내 카페</a>
	   <div class="tabCon" >
	      <!-- <jsp:include page="../ceoCafe/CeoCafeMyDetail.jsp"/> -->
	      <!-- <a href='cafe/detail' class = "btn btn-outline-dark">내 카페</a> -->
	   </div>
	  </li>
	  <li id="tab3" class="btnCon"><a class="tabbtn" href="#tab3">내 문의내역</a>
	   <div class="tabCon" >
	       <!-- <a href='../askboard/mylist' class = "btn btn-outline-dark">내 문의게시판</a> -->
	   </div>
	  </li>
   </ul>
  </div> 
</div> 

 <script type="text/javascript" src="../js/update.js"></script>
 <script> location.href = "#tab1"; </script>
</body>
</html>