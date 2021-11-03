<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 목록</title>
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
</head>

<body>
   <b> 📖 기업회원 상세</b>
   <hr>
   <span>(${ceoMember.ceoNo})</span><br>
   <img src="..." alt="..." class="img-circle"><br>
   <span>이름ㅣ</span><span><a href='detail?no=${ceoMember.ceoNo}'>${ceoMember.ceoName}</span></a><br>
   <span>닉네임ㅣ</span><span>${ceoMember.ceoNickname}</span><br>
   <span>이메일ㅣ</span><span>${ceoMember.ceoEmail}</span><br>
   <span>가입일ㅣ</span><span>${ceoMember.ceoRegisteredDate}</span><br>
   <button type="button"  value="목록" formaction="/admin/ceomember/list" class="btn btn-outline-dark">
    <a href='list'>목록</a>
   </button>
	 <button type="button"  value="삭제" formaction="/admin/ceomember/delete" class="btn btn-outline-dark">
	   <a href='delete?no=${ceoMember.ceoNo}'>삭제</a>
	 </button>
</body>
</html>