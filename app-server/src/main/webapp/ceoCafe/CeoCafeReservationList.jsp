<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
* {
  font-size: 14px;
}

h3 {
  text-align: center;
  font-weight: bolder;
}

.all-content {
  width: 100%;
  margin: 0 auto;
}

.t-top {
  text-align: center;
}

.t-content {
  text-align: center;
}

</style>
</head>

<body>
<div class="all-content">
<c:if test='${not empty reserList}'>
<table class="table table-responsive text-cente">
<thead class="t-top">
  <tr>
    <th>번호</th>
    <th>예약 날짜</th>
    <th>이용 날짜</th>
    <th>예약 장소</th>
    <th>이용 시작 시간</th>
    <th>스터디룸</th>
    <th>결제 금액</th>
    <th>예약 상태</th>
  </tr>
</thead>
<tbody class="t-content">
  <c:forEach items="${reserList}" var="reservation">
  <tr>
      <td><a href='detail?resno=${reservation.reservationNo}'>${reservation.reservationNo}</a></td>
      <td><a href='detail?resno=${reservation.reservationNo}'>${reservation.reservationDate}</a></td>
      <td><a href='detail?resno=${reservation.reservationNo}'>${reservation.useDate}</a></td>
      <td>${reservation.cafe.name}</td>
      <td>${reservation.startTime}</td>      <!-- 이용시간 -->
      <td>${reservation.roomName}</td>       <!-- 스터디룸 -->
      <td>${reservation.totalPrice}</td>      
      <td>${reservation.reservationStatusName}</td>
  </tr>
  </c:forEach>
  </tbody>
</table>
</c:if>
<c:if test='${empty reserList}'>
   예약 내역이 없습니다.<br><br>  
</c:if>
</div>
</body>
</html>