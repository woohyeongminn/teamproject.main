<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
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
color: black;
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
    text-align: left;
    padding-bottom: 30px;
    padding-left: 100px;
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
    font-size: 13px;
    margin-right: 135px;
}

#f-registeredDate , #f-email {
  border:0;
}

#f-registeredDate, #f-email:focus {
  border:0;
  outline: none;
}

</style>



<body>
<br><br><br>
<div class="all-content">
  <div class="tabmenu">
   <ul>
    <li id="tab1" class="btnCon"><a class="tabbtn first" href="#tab1">내 프로필</a>
    <div class="tabCon">
    
    <form action='update' method='post' enctype="multipart/form-data">
      <input type='hidden' name='ceoNo' value='${ceoMember.ceoNo}'>
    
     <div class="profile">
      <div class="profile-header">
        <a href="${contextPath}/upload/ceoMember/${ceoMember.ceoPhoto}.jpg" >
          <img id="f-photo-image" src="${contextPath}/upload/ceoMember/${ceoMember.ceoPhoto}_110x110.jpg">
        </a>
       </div>
        
       <input id='f-photo' type='file' name='photoFile' value='${ceoMember.ceoPhoto}'>

       <label for='f-name' class='form-label'>이름</label>
       <input id='f-name' type='text' name='ceoName' value='${ceoMember.ceoName}' style="width: 150px;"><br>
       
       <label for='f-nickname' class='form-label'>닉네임</label>
       <input id='f-nickname' type='text' name='ceoNickname' value='${ceoMember.ceoNickname}' style="width: 150px;"><br>
       
       <label for='f-tel' class='form-label'>전화번호</label>
	     <input id='f-tel' type='text' name='tel1' pattern="[0-9]+" minlength='3' maxlength='3'  style="width:40px;" value="${tel1}"/> -
	     <input id='f-tel' type='text' name='tel2' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:40px;" value="${tel2}"/> -
	     <input id='f-tel' type='text' name='tel3' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:40px;" value="${tel3}"/> <br>
       
       <label for='f-email' class='form-label'>이메일</label>
       <input id='f-email' type='email' name='ceoEmail' value='${ceoMember.ceoEmail}' readonly><br>
       
       <label for='f-password' class='form-label'>비밀번호</label>
       <a href='javascript:openPwPopup()' class="btn-default btn-sm" role="button" style="border: 1px solid; border-radius: 3px;">비밀번호 변경</a><br>
       
       <label for='f-registeredDate' class='form-label'>가입일</label>
       <input id='f-registeredDate' type='text' name='ceoRegisteredDate' readonly value='${ceoMember.ceoRegisteredDate}'><br>
     </div>
     
	      <div class="btn_wrap">
		      <button type="submit" class="btn btn-outline-dark">수정</button>
		      <a href='detail' class="btn btn-outline-dark">뒤로가기</a>
	      </div>
      </form>
     </div>
     </li>
     <li id="tab2" class="btnCon"><a class="tabbtn" href="${contextPath}/app/askboard/mylist">내 문의내역</a>
        <div class="tabCon" >
        </div>
     </li>
    </ul>
   </div> 
  </div>
</body>
 <script>
  location.href = "#tab1";
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

};

function openPwPopup() {
	window.open("openPwPopup", "비밀번호 변경", "width=640px, height=400")
	
};

</script>