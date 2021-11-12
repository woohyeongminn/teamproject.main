<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내 예약 목록 상세보기</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<style>
  a {
   text-decoration: none;
   color: black;
  }
  a:visited {
    color: black;
  }
  label {
    xdisplay: inline-block;
    margin-right: 5px;
    margin-left: 20px;
    xwidth: 130px;
  }
  h3 {
    text-align: center;
    font-weight: bolder;
  }
  .all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    text-align: center;
    padding: 80px 0;
  }
  .c-top {
    width: 100%;
    padding: 20px 0 20px 50px;
    font-weight: bold;
    background-color: rgb(247, 231, 215);
    text-align: center;
  }
	</style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="c-top">
📝 예약 내역
</div>
<br><br>
<div class="all-content">
	<div id='content'>
    <label>예약날짜</label><span>${cafeReser.reservationDate}</span><br>
		<label>이용날짜</label><span>${cafeReser.useDate}</span><br>
		<label>예약장소</label><span>${cafeName}</span><br>
		<label>스터디룸</label><span>${cafeRoomName}</span><br>
		<label>이용시간</label><span>${cafeReser.startTime} ~ ${cafeReserEndTime} (${cafeReser.useTime}시간)</span><br>
		<label>결제금액</label><span>${cafeReser.totalPrice}</span><br>
		<label>리뷰작성여부</label><span>${reviewStatusLable}</span><br>
		<label>예약상태</label><span>${reserStatusLable}</span>
	</div>
<br>&nbsp;&nbsp;

<c:if test="${reviewStatusLable eq '작성대기'}">
  <button type="button" class="btn btn-outline-dark" id="btnReviewAdd">
    <a href="reviewAddForm?reservationNo=${cafeReser.reservationNo}">리뷰등록</a>
  </button>
</c:if>

<c:if test="${reserStatusLable eq '예약완료'}">
  <button type="button" class="btn btn-outline-dark">
    <a href="reservationDelete?reservationNo=${cafeReser.reservationNo}">예약취소</a>
  </button>
</c:if>

  <button type="button" class="btn btn-outline-dark">
    <a href="reservationList">목록</a>
  </button>
</div>

<jsp:include page="../footer.jsp"/>

<script>
document.querySelector("#btnReviewAdd").onclick = () => {
	
	var useDate = document.querySelector("#content span:nth-child(3)");
	console.log(useDate);
	
	return false;
}
</script>
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