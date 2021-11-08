<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>예약 내역 목록</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <style>
  h3 {
    text-align: center;
    font-weight: bolder;
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
        📝 예약 내역 목록
    </div>
  <div class="all-content">
<c:if test='${not empty reserList}'>
<table class="table table-striped text-center">
<thead>
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
<tbody>
  <c:forEach items="${reserList}" var="reservation">
  <tr>
      <td><a href='detail?resno=${reservation.reservationNo}'>${reservation.reservationNo}</a></td>
      <td>${reservation.reservationDate}</td>
      <td>${reservation.useDate}</td>
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