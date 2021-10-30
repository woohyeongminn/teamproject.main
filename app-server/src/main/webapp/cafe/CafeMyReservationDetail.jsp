<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
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
<h1>예약 내역 상세보기</h1>
<hr>
	<div id='content'>
    <label>예약날짜</label>${cafeReser.reservationDate}<br>
		<label>이용날짜</label>${cafeReser.useDate}<br>
		<label>예약장소</label>${cafeName}<br>
		<label>스터디룸</label>${cafeRoomName}<br>
		<label>이용시간</label>${cafeReser.startTime} ~ ${cafeReserEndTime} (${cafeReser.useTime}시간)<br>
		<label>결제금액</label>${cafeReser.totalPrice}<br>
		<label>리뷰작성여부</label>${reviewStatusLable}<br>
		<label>예약상태</label>${reserStatusLable}
	</div>
<br>&nbsp;&nbsp;
<c:if test="${reviewStatusLable eq '작성대기'}">
  <button type="button" class="btn btn-outline-dark"><a href="reviewAdd?perNo=${memberNo}&reservationNo=${cafeReser.reservationNo}">리뷰등록</a></button>
</c:if>
&nbsp;&nbsp;<button type="button" class="btn btn-outline-dark"><a href="myReservationList?perNo=${memberNo}">목록</a></button>
</body>
</html>

<!-- 

      System.out.printf(" (%d)\n 예약날짜 : %s\n 이용날짜 : %s\n 예약장소 : %s\n"
          + " 이용시간 : %s ~ %s (%s시간)\n 스터디룸 : %s\n"
          + " 결제금액 : %d원\n 리뷰작성여부 : %s\n 상태 : %s\n"
          , cafeReser.getReservationNo(), cafeReser.getReservationDate(), cafeReser.getUseDate()
          , cafeReserCafe.getName(), cafeReser.getStartTime()
          , cafeReser.getStartTime().plusHours(cafeReser.getUseTime())
          , cafeReser.getUseTime(), cafeRoom.getRoomName(), cafeReser.getTotalPrice() 
          , reviewStatusLable , reserStatusLable);

 -->