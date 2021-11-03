<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>예약 내역 상세</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <style>
  a {
   text-decoration:none;
  }
  label {
    display: inline-block;
    margin-right: 5px;
    margin-left: 20px;
    width: 130px;
  }
  </style>
</head>

<body>
<br>
<h3> 📝 예약 내역 상세</h3><br>
<h1>예약 내역 상세보기</h1>
<hr>
  <div id='content'>
    <label>예약날짜</label>${cafeReser.reservationDate}<br>
    <label>이용날짜</label>${cafeReser.useDate}<br>
    <label>예약장소</label>${reservation.cafe.name}<br>
    <label>스터디룸</label>${reservation.roomName}<br>
    <label>이용시간</label>${cafeReser.startTime} ~ ${cafeReserEndTime} (${cafeReser.useTime}시간)<br>
    <label>결제금액</label>${cafeReser.totalPrice}<br>
    <label>리뷰작성여부</label>${reviewStatusLable}<br>
    <label>예약상태</label>${cafeReser.reservationStatusName}
  </div>
<br>&nbsp;&nbsp;

<!-- 예약 거절 버튼 -->

<%-- <c:if test="${reserStatusLable eq '예약완료'}">
<button type="button" class="btn btn-outline-dark"><a href="reservationDelete?perNo=${memberNo}&reservationNo=${cafeReser.reservationNo}">예약취소</a></button>
</c:if>

<button type="button" class="btn btn-outline-dark"><a href="reservationList?perNo=${memberNo}">목록</a></button> --%>




</body>
</html>
