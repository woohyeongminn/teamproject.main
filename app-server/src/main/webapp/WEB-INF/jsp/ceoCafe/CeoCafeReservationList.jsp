<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<style>
* {
  font-size: 14px;
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
      <td><${reservation.reservationDate}</td>
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

<script>
document.querySelectorAll("tbody a").forEach((aTag) => {
  aTag.onclick = () => false;
});

var trList = document.querySelectorAll("tbody tr"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    //console.log(e.currentTarget.querySelector("a").href);
    //e.currentTarget.querySelector("a").click();
    window.location.href = e.currentTarget.querySelector("a").href;
    //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
  };
});
</script>
