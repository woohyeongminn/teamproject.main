<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
}

.tabmenu > ul > li > a{
}

.tabCon{
  display:none; 
  padding: 20px;
  position:absolute;
  left:0;
  box-sizing: border-box;
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

.profile > label {
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
<body>
    <div class="all-content"> 
		  <div class="tabmenu">
		   <ul>
		    <li id="tab1" class="btnCon"><a class="tabbtn first" href="#tab1"></a>
		    <div class="tabCon">
			  <form method='post' enctype="multipart/form-data">
			     <input type='hidden' name='ceoNo' value='${ceoMember.ceoNo}'>
			     <input type='hidden' name='ceoNo' value='${ceoMember.ceoPassword}'>

				   <div class="profile">
					    <div class="profile-header">
					    <c:choose>
						    <c:when test="${not empty ceoMember.ceoPhoto}">
	                <a href="${contextPath}/upload/ceoMember/${ceoMember.ceoPhoto}.jpg" >
	                    <img id="f-photo-image" src="${contextPath}/upload/ceoMember/${ceoMember.ceoPhoto}_80x80.jpg" style="width: 90px;">
	                </a>
	  			        <input type='hidden' name='ceoPhoto' value='${ceoMember.ceoPhoto}'>
						    </c:when>
						    <c:otherwise>
						       <a href="${contextPath}/img/ceoProfile.jpg" >
	                    <img id="f-photo-image" src="${contextPath}/img/ceoProfile.jpg" style="width: 90px">
	                 </a>
						    </c:otherwise>
					    </c:choose>
					    </div>

					    <label for='f-name' class='form-label'>이름</label>
			        <input id='f-name' type='text' name='ceoName' readonly value='${ceoMember.ceoName}'><br>
			        
			        <label class="profile-label" for='f-nickname' class='form-label'>닉네임</label>
			        <input id='f-nickname' type='text' name='ceoNickname' readonly value='${ceoMember.ceoNickname}'><br>
			        
			        <label for='f-tel' class='form-label'>전화번호</label>
			        <input id='f-tel' type='tel' name='ceoTel' readonly value='${ceoMember.ceoTel}'><br>
			        
			        <label for='f-bossName' class='form-label'>대표자명</label>
			        <input id='f-bossName' type='bossName' name='ceoBossName' readonly value='${ceoMember.ceoBossName}'><br>
			        
			        <label for='f-licenseNo' class='form-label'>사업자 번호</label>
			        <input id='f-licenseNo' type='licenseNo' name='ceoLicenseNo' readonly value='${ceoMember.ceoLicenseNo}'><br>
			        
			        <label for='f-email' class='form-label'>이메일</label>
			        <input id='f-email' type='email' name='ceoEmail' readonly value='${ceoMember.ceoEmail}'><br>
			        
			        <label for='f-registeredDate' class='form-label'>가입일</label>
			        <input id='f-registeredDate' type='text'
			         name='ceoRegisteredDate' readonly value='${ceoMember.ceoRegisteredDate}'><br>
					   </div>
					   
					   <div class="btn_wrap">
					     <input type="submit" value="프로필 수정하기" formaction="updateform" class ="btn btn-outline-dark"/>
               <input type="submit" value="탈퇴하기" formaction="deleteform" class ="btn btn-outline-dark"/>
               <a href="../askboard/ceomylist" class ="btn btn-outline-dark">내 문의내역</a>
			       </div>
			    </form>
			  </div>
			 </li>
			
	    </ul>
	   </div> 
   </div>
</body>
<script>
  location.href = "#tab1";
</script>