<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<style>


.all-content {
width: 800px;
    margin: 0 auto;
    margin-top: 50px;
}

table {
font-size: 12px;
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

<div id="search">
    <form action="list">
    <i class="fas fa-search"></i>
    <span>조회기간</span>
    <select name="searchDate">
      <option value="1">예약날짜</option>
      <option value="2">이용날짜</option>
    </select>
    <input type="date" name="startDate"> ~ <input type="date" name="endDate">
    <button class="btn btn-outline-dark btn-sm" style="line-height: normal;">조회</button>
    </form>
  </div>
  <div>
    <c:if test="${not empty startDate}">
      <i class="fas fa-check"></i>
      <span> 검색 | ${startDate} ~ ${endDate} </span> 
    </c:if>
  </div>
  
<c:if test='${not empty reserList}'>
<table class="table table-responsive text-cente">
<thead class="t-top">
  <tr>
    <th>번호</th>
    <th>예약일</th>
    <th>이용일</th>
    <th>이용 시간</th>
    <th>스터디룸</th>
    <th>결제 금액</th>
    <th>예약 상태</th>
  </tr>
</thead>
<tbody class="t-content">
  <c:forEach items="${reserList}" var="reservation">
  <tr>
      <td><a href='detail?resno=${reservation.reservationNo}'>${reservation.reservationNo}</a></td>
      <td>${reservation.reservationDate}</td>
      <td>${reservation.useDate}</td>
      <td>${reservation.startTime} ~ ${cafeReserEndTime} (${reservation.useTime}시간)</td>      <!-- 이용시간 -->
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
