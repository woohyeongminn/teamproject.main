<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인회원 로그인</title>
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
   * {
  font-size: 14px;
  }
  
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
    size:100px;
  }
  
  .btn {
    line-height: 14px;
  }
    p {
  text-align-last: center;
  }
</style>
</head>

<body>
  <br>
 <button class = "btn btn-outline-dark" ><a href='detail'>마이페이지</a></button>
 <button class = "btn btn-outline-dark" ><a href='../bookmark/list?perno=${loginUser.perNo}'>내 북마크</a></button>
 <button class = "btn btn-outline-dark" ><a href='../study/list?perno=${loginUser.perNo}'>스터디 찾기</a></button>
 <button class = "btn btn-outline-dark" ><a href='../cafe/list'>스터디카페 예약하기</a></button>
 <button class = "btn btn-outline-dark" ><a href='../cafe/reservationList'>내 예약 목록</a></button>
 <button class = "btn btn-outline-dark" ><a href='../cafe/reviewList'>내 리뷰 목록</a></button>
 <button class = "btn btn-outline-dark" ><a href='../askboard/mylist'>내 문의게시판</a></button>
<%--  <button><a href='../mystudy/ownerList?perNo=${perMember.perNo}'>내 스터디(조장)</a></button>
 <button><a href='../mystudy/guilderList?perNo=${perMember.perNo}'>내 스터디(구성원)</a></button>
 <button><a href='../mystudy/waitinglist?perNo=${perMember.perNo}'>내 스터디(승인 대기)</a></button> --%>
 <button class = "btn btn-outline-dark" ><a href='../mystudy/list'>내 스터디 목록(통합)</a></button>
 </body>
</html>
