<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
  height: 700px;
}

.c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
  text-align: center;
  font-size: 16px;
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
  max-width: 400px;
  margin: 100px auto 0;
  background-color: white;
  border-radius: 15px;
  border: 2px solid rgb(110, 110, 110);
  text-align: center;
  padding-bottom: 30px;
}

.profile > label {
  margin-right: 5px;
  text-align: center;
  display: inline;
  width: 60px;
}

.profile > label, span {
  display: inline-block;
  padding: 5px 0;
  width: 100px;
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
  width: 100px;
  height: 100px;
  border-radius: 1000px;
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
      <form action='update'>
	      <div class="profile">
	        <div class="profile-header">
	           <a href="#"><img src="/ogong/img/logoface.png" alt="Profile Image" class="profile-img"></a>
	        </div>
	
	        <label for='f-name' class='form-label'>이름</label>
	        <input id='f-name' type='text' name='name' value='${ceoMember.ceoName}'><br>
	        
	        <label for='f-nickname' class='form-label'>닉네임</label>
	        <input id='f-nickname' type='text' name='nickname' value='${ceoMember.ceoNickname}'><br>
	        
	        <label for='f-photo' class='form-label'>사진</label>
	        <input id='f-photo' type='text' name='photo' value='${ceoMember.ceoPhoto}'><br>
	        
	        <label for='f-tel' class='form-label'>전화번호</label>
	        <input id='f-tel' type='tel' name='tel' value='${ceoMember.ceoTel}'><br>
	        
	        <label for='f-email' class='form-label'>이메일</label>
	        <input id='f-email' type='email' name='email' value='${ceoMember.ceoEmail}'><br>
	        
	        <label for='f-password' class='form-label'>암호</label>
	        <input id='f-password' type='password' name='password' value='${ceoMember.ceoPassword}'><br>
	        
	        <label for='f-registeredDate' class='form-label'>가입일</label>
	        <input id='f-registeredDate' type='registeredDate' style="border:0px"
	         name='registeredDate' readonly value='${ceoMember.ceoRegisteredDate}'><br>
	      </div>
	
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
 <script>
  location.href = "#tab1";
 </script>
</body>
</html>