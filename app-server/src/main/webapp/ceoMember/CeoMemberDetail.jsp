<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

.profile input {
  border:0;
}

.profile input:focus {
  outline: none;
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
</style>
</head>

<body>
    <br><br><br>
    <div class="all-content"> 
		  <div class="tabmenu">
		   <ul>
		    <li id="tab1" class="btnCon"><a class="tabbtn first" href="#tab1">내 프로필</a>
		    <div class="tabCon" >
			  <form action='updateform' method='post' enctype="multipart/form-data">
				   <div class="profile">
					   <div class="profile-header">
					      <a href="../upload/ceoMember/${ceoMember.ceoPhoto}.jpg" >
						        <img id="f-photo-image" style="margin-top: 8px;"   src="../upload/ceoMember/${ceoMember.ceoPhoto}_80x80.jpg">
						    </a>
					   </div>
					    <label for='f-name' class='form-label'>이름</label>
			        <input id='f-name' type='text' name='name' readonly value='${ceoMember.ceoName}'><br>
			        
			        <label class="profile-label" for='f-nickname' class='form-label'>닉네임</label>
			        <input id='f-nickname' type='text' name='nickname' readonly value='${ceoMember.ceoNickname}'><br>
			        
			        <label for='f-tel' class='form-label'>전화번호</label>
			        <input id='f-tel' type='tel' name='tel' readonly value='${ceoMember.ceoTel}'><br>
			        
			        <label for='f-bossName' class='form-label'>대표자명</label>
			        <input id='f-bossName' type='bossName' name='bossName' readonly value='${ceoMember.ceoBossName}'><br>
			        
			        <label for='f-licenseNo' class='form-label'>사업자 번호</label>
			        <input id='f-licenseNo' type='licenseNo' name='licenseNo' readonly value='${ceoMember.ceoLicenseNo}'><br>
			        
			        <label for='f-email' class='form-label'>이메일</label>
			        <input id='f-email' type='email' name='email' readonly value='${ceoMember.ceoEmail}'><br>
			        
			        <label for='f-registeredDate' class='form-label'>가입일</label>
			        <input id='f-registeredDate' type='registeredDate'
			         name='registeredDate' readonly value='${ceoMember.ceoRegisteredDate}'><br>
					   </div>
					   
					   <div class="btn_wrap">
					     <a href='updateform' class = "btn btn-outline-dark">프로필 수정하기</a>
					     <a href='deleteform' class = "btn btn-outline-dark">탈퇴하기</a>
			       </div>
			    </form>
			  </div>
			 </li>
			 <li id="tab2" class="btnCon"><a class="tabbtn" href="cafe/detail">내 카페</a>
			  <div class="tabCon" >
			     <%-- <jsp:include page="../ceoCafe/CeoCafeMyDetail.jsp"/> --%>
				    <!-- <a href='cafe/detail' class = "btn btn-outline-dark">내 카페</a> -->
			  </div>
			 </li>
			 <li id="tab3" class="btnCon"><a class="tabbtn" href="../askboard/mylist">내 문의내역</a>
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