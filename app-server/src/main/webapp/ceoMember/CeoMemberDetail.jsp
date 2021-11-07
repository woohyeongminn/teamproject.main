<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 상세</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>

a {
text-decoration: none;
}
label {
	margin-right: 5px;
	text-align: center;
	display: inline;
	width: 60px;
}

legend {
	text-align: center;
}

.all-content {
	width: 100%;
	margin: 0 auto;
}

.c-top {
	width: 100%;
	padding: 20px 0 20px 0px;
	font-weight: bold;
	background-color: rgb(247, 231, 215);
	text-align: center;
}

.profile {
	max-width: 400px;
	margin: 150px auto 0;
	background-color: white;
	border-radius: 15px;
	border: 2px solid rgb(110, 110, 110);
	text-align: center;
	padding-bottom: 40px;
}

.profile>span {
	display: inline-block;
	padding: 5px 0;
}

.profile .profile-header {
	padding: 0;
	height: 48px;
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
	transform: translate(-50%, -50%);
	border: 4px solid wheat;
	background-color: white;
	vertical-align: middle;
}

 .profile .profile-header .profile-img {
	margin-top: 5px;
}

</style>
</head>

<body>
  <jsp:include page="../header.jsp"/>
    <div class="c-top">
        🙂 마이페이지
    </div>
   <div class="all-content"> 
	   <form action='updateform'>
		   <div class="profile">
		   <div class="profile-header">
		      <a href="#">
		        <img src="/ogong/img/logoface.png" alt="Profile Image" class="profile-img">
		      </a>
		   </div>
		   <span>이름ㅣ</span> <span>${ceoMember.ceoName}</span><br>
		   <span>닉네임ㅣ</span> <span>${ceoMember.ceoNickname}</span><br>
		   <span>이메일ㅣ</span> <span>${ceoMember.ceoEmail}</span><br>
		   <span>전화번호ㅣ</span> <span>${ceoMember.ceoTel}</span><br>
		   <span>대표자명ㅣ</span> <span>${ceoMember.ceoBossName}</span><br>
		   <span>사업자 번호ㅣ</span> <span>${ceoMember.ceoLicenseNo}</span><br>
		   <span>가입일ㅣ</span> <span>${ceoMember.ceoRegisteredDate}</span><br>
		   </div>
	     <button type="submit" style="border:0px; background-color: transparent;">
	       <a href='updateform' class = "btn btn-outline-dark">프로필 수정하기</a>
	     </button>
	    <a href='deleteform' class = "btn btn-outline-dark">탈퇴하기</a>
	     <a href='cafe/wrap' class = "btn btn-outline-dark">내 카페</a>
       <a href='../askboard/mylist' class = "btn btn-outline-dark">내 문의게시판</a>
	    </form>
    </div>
</body>
</html>