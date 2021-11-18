<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<style>
* {
  font-size: 12px;
}

a {
 text-decoration:none;
}

.all-content {
  width: 100%;
  margin: 0 auto;
}

.text-center {
margin: 0 auto;
margin-top: 50px;
    width: 400px;
    height: 340px;
}

.card-text {
    padding: 0 80px;
    text-align: left;
}

.card-text > span{
text-align: center;
    display: inline-block;
    width: 135px;
}

label {
text-align: left;
    display: inline-block;
    width: 70px;
    padding: 5px 0;
}

.card-body{
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
}

.btn {
        margin: 0 80px;
}
</style>
</head>

<body>


<div class="all-content">
<div class="card text-center">
  <div class="card-header">
  <p style="font-weight: bold; margin: 0; font-size: 14px;">${cafeReser.reservationStatusName}</p>
    
  </div>
  <div class="card-body">
    
    <p class="card-text">
    <label>스터디룸</label>
    <span>${cafeReser.roomName}</span><br>
    
    <label>예약자</label>
    <span>${memberNick}</span><br>
    
    <label>예약일</label>
    <span>${cafeReser.reservationDate}</span><br>
    
    <label>이용일</label>
    <span>${cafeReser.useDate}</span><br>
    
    <label>이용시간</label>
    <span>${cafeReser.startTime} ~ ${cafeReserEndTime} (${cafeReser.useTime}시간)</span><br>
    
    <label>결제금액</label>
    <span>${cafeReser.totalPrice}</span><br>
    
    <label>리뷰</label>
    <span>${reviewStatusLable}</span><br>
    </p>
    
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
  <div class="card-footer text-muted">
    
  </div>
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



</div>
</body>
