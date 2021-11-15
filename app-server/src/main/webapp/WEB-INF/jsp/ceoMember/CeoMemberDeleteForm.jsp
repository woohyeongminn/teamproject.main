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
  padding-bottom: 30px;
  text-align: center;
}

.profile > p {
text-align: center;
margin-bottom: 25px;
}

.profile .label-wrap {
  margin-right: 15px;
}
    
.profile .label-wrap > label {
  font-weight: 600;
}

.profile .label-wrap > label, span {
  display: inline-block;
  padding: 5px 0;
  width: 65px;
  font-size: 16px;
}

.profile .label-wrap > input {
    width: 220px;
    padding: 4px 6px;
    font-size: 16px;
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

#f-registeredDate {
  border:0;
}

#f-registeredDate:focus {
  border:0;
  outline: none;
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

<body>
<br><br><br>
<div class="all-content">
  <div class="tabmenu">
   <ul>
    <li id="tab1" class="btnCon"><a class="tabbtn first" href="#tab1">내 프로필</a>
    <div class="tabCon">
    <form action='delete' method='post'>
      <input type='hidden' name='ceoNo' value='${ceoMember.ceoNo}'>
    
     <div class="profile">
        <h4>회원 탈퇴 안내</h4>
        <p> 게시글 및 댓글, 좋아요 등은 탈퇴 시 자동 삭제되지 않고 그대로 남아있습니다.<br>
        삭제를 원하는 게시글이 있다면 반드시 탈퇴 전 삭제하시기 바랍니다.<br>
        회원탈퇴 처리 후에는 회원님의 개인정보를 복원할 수 없으며,<br>
        회원탈퇴 진행 시 해당 아이디는 영구적으로 삭제되어 재가입이 불가합니다.<br>
        본인확인을 위해 비밀번호 입력 후 회원탈퇴가 가능합니다. </p>
       
       <div class = "label-wrap">
	       <label for='f-email' class='form-label'>이메일</label>
	       <input id='f-email' type='email' name='ceoEmail' value='${ceoMember.ceoEmail}' readonly style="border: 0; outline: none;"><br>
       </div>
       
       <div class = "label-wrap">
	       <label for='f-password' class='form-label'>암호</label>
	       <input id='f-password' type='password' name='inputCeoPassword'>
	       <a href='detail' class="btn btn-outline-dark">확인</a>
       </div>
       
     </div>
        
        <div class="btn_wrap">
          <button type="submit" class="btn btn-outline-dark">탈퇴</button>
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
 <script type="text/javascript" src="../js/update.js"></script>
 <script>
  location.href = "#tab1";
 </script>