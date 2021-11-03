<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인회원 상세(관리자)</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
<style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  legend {
  text-align: center;
  }
</style>
</head>>

<body>
   <b> 📖 개인회원 상세</b>
   <hr>
   <span>(${perMember.perNo})</span><br>
   <img src="..." alt="..." class="img-circle"><br>
   <span>이름ㅣ</span><span><a href='permemberdetail?no=${perMember.perNo}'>${perMember.perName}</span></a><br>
   <span>닉네임ㅣ</span><span>${perMember.perNickname}</span><br>
   <span>이메일ㅣ</span><span>${perMember.perEmail}</span><br>
   <span>가입일ㅣ</span><span>${perMember.perRegisteredDate}</span><br>
   <button type="button"  value="목록" formaction="permemberlist" class="btn btn-outline-dark">
    <a href='permemberlist'>목록</a>
   </button>
   <button type="button"  value="삭제" formaction="permemberdelete" class="btn btn-outline-dark">
     <a href='permemberdelete?no=${perMember.perNo}'>삭제</a>
   </button>
</body>
</html>




