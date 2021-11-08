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
.all-content {
  width: 100%;
  margin: 0 auto;
}

.c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
  text-align: center;
}


</style>
</head>

<body>
  <jsp:include page="../header.jsp"/>
  <div class="c-top">
      📝 예약내역 상세
    </div>
  <div class="all-content">
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
<br>&nbsp;&nbsp;

<!-- 예약 거절 버튼 -->
<c:if test="${cafeReser.reservationStatusName eq '예약완료'}">
<a href='rejectform?resno=${cafeReser.reservationNo}' class="btn btn-outline-dark">예약거절</a>
</c:if>

<!-- 결제 거절 버튼 -->
<c:if test="${cafeReser.reservationStatusName eq '결제완료'}">
<a href='#<%-- ?no=${cafeReser.reservationNo}--%>' class="btn btn-outline-dark">결제 환불</a>
</c:if>

<a href="list?ceono=${ceoMember.ceoNo}" class="btn btn-outline-dark">목록</a>

</div>
</body>
</html>
