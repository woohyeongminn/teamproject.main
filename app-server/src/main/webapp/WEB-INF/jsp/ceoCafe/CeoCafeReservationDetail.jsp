<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<style>
* {
  font-size: 14px;
}

a {
 text-decoration:none;
}

label {
  display: inline-block;
  margin-right: 5px;
  margin-left: 20px;
  width: 130px;
}

.all-content {
  width: 100%;
  margin: 0 auto;
}
</style>
</head>

<body>



<div class="card text-center">
  <div class="card-header">
    Featured
  </div>
  <div class="card-body">
    <h5 class="card-title"><label>스터디룸</label>${cafeReser.roomName}<br></h5>
    <p class="card-text">
    
    <label>예약자 이름</label>${memberNick}<br>
    <label>예약날짜</label>${cafeReser.reservationDate}<br>
    <label>이용날짜</label>${cafeReser.useDate}<br>
    <label>이용시간</label>${cafeReser.startTime} ~ ${cafeReserEndTime} (${cafeReser.useTime}시간)<br>
    <label>결제금액</label>${cafeReser.totalPrice}<br>
    <label>예약상태</label>${cafeReser.reservationStatusName}
    </p>
    <a href="#" class="btn btn-primary">Go somewhere</a>
  </div>
  <div class="card-footer text-muted">
    <label>리뷰</label>${reviewStatusLable}<br>
  </div>
</div>


<%--   <div class="all-content">
  <div id='content'>
    <label>예약날짜</label>${cafeReser.reservationDate}<br>
    <label>이용날짜</label>${cafeReser.useDate}<br>
    <label>예약장소</label>${cafeReser.cafe.name}<br>
    <label>스터디룸</label>${cafeReser.roomName}<br>
    
    <label>이용시간</label>${cafeReser.startTime} ~ ${cafeReserEndTime} (${cafeReser.useTime}시간)<br>
    <label>결제금액</label>${cafeReser.totalPrice}<br>
    <label>리뷰작성여부</label>${reviewStatusLable}<br>
    <label>예약상태</label>${cafeReser.reservationStatusName}
  </div>
<br>&nbsp;&nbsp; --%>

<!-- 예약 거절 버튼 -->
<c:if test="${cafeReser.reservationStatusName eq '예약완료'}">
<a href='rejectform?resno=${cafeReser.reservationNo}' class="btn btn-outline-dark">예약거절</a>
</c:if>

<!-- 결제 거절 버튼 -->
<c:if test="${cafeReser.reservationStatusName eq '결제완료'}">
<a href='#<%-- ?no=${cafeReser.reservationNo}--%>' class="btn btn-outline-dark">결제취소(환불)</a>
</c:if>

<a href="list?ceono=${ceoMember.ceoNo}" class="btn btn-outline-dark">목록</a>

</div>
</body>
