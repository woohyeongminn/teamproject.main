<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 상세(관리자)</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
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
   <b> 💬 문의글 상세</b>
   <hr>
   <c:if test="${false}" var="result1">
   <form action='updateform'>
     <span id='no' name='no'>(${askBoard.askNo})</span><br>
     <span>제목ㅣ</span> <span>${askBoard.askTitle}</span><br>
     <span>내용ㅣ</span> <span>${askBoard.askContent}</span><br>
     <span>작성일ㅣ</span> <span>${askBoard.perRegisteredDate}</span><br>
    
    <button type="submit" value="수정" formaction="updateform">
      <a href='updateform?no=${askBoard.askNo}'>프로필 수정하기</a>
    </button>
   
    <button type="submit" value="삭제" formaction="delete">
      <a href='delete?no=${askBoard.askNo}'>탈퇴하기</a>
    </button>
    
    <button type="submit" value="로그아웃">
      <a href='logout'>로그아웃</a>
    </button>
   </form>
   </c:if>
</body>
</html>



