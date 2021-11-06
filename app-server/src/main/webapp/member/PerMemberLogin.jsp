<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인회원 로그인</title>
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
<jsp:include page="../header.jsp"/>
  <h4>개인 회원 로그인 성공</h4>

 <p>'${loginUser.perNickname}'님 환영합니다! 🖐</p>
 <button><a href='detail'>마이페이지</a></button>
 <button><a href='../bookmark/list?perno=${loginUser.perNo}'>내 북마크</a></button>
 <button><a href='../study/list?perno=${loginUser.perNo}'>스터디 찾기</a></button>
 <button><a href='../cafe/list?perNo=${perMember.perNo}'>스터디카페 예약하기</a></button>
 <button><a href='../cafe/reservationList'>내 예약 목록</a></button>
 <button><a href='../cafe/reviewList'>내 리뷰 목록</a></button>
 <button><a href='../askboard/permylist?perNo=${loginUser.perNo}'>내 문의게시판</a></button>
 <button><a href='../mystudy/ownerList?perNo=${perMember.perNo}'>내 스터디(조장)</a></button>
 <button><a href='../mystudy/guilderList?perNo=${perMember.perNo}'>내 스터디(구성원)</a></button>
 <button><a href='../mystudy/waitinglist?perNo=${perMember.perNo}'>내 스터디(승인 대기)</a></button>
 <button><a href='../mystudy/list?perNo=${perMember.perNo}'>내 스터디 목록(통합)</a></button>
 </body>
</html>
