<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 | 개인 회원 상세</title>
<style>
 * {
  font-size: 14px;
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
    .btn {
    line-height: 14px;
  }
     #info {
  text-align-last: center;
  }
</style>
</head>

<body>
   <hr>
   <div id="info">
   <span>${perMember.perNo}</span><br>
   <img src="..." alt="..." class="img-circle"><br>
   <span>이름ㅣ</span><span><a href='permemberdetail?no=${perMember.perNo}'>${perMember.perName}</span></a><br>
   <span>닉네임ㅣ</span><span>${perMember.perNickname}</span><br>
   <span>이메일ㅣ</span><span>${perMember.perEmail}</span><br>
   <span>가입일ㅣ</span><span>${perMember.perRegisteredDate}</span><br>
   </div>
   <br>
   <button type="button"  value="목록" formaction="permemberlist" class="btn btn-outline-dark">
    <a href='permemberlist'>목록</a>
   </button>
   <button type="button"  value="삭제" formaction="permemberdelete" class="btn btn-outline-dark">
     <a href='permemberdelete?no=${perMember.perNo}'>삭제</a>
   </button>
</body>
</html>




