<%@page import="java.time.LocalTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>내 예약 목록</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <style>
  h3 {
    text-align: center;
    font-weight: bolder;
  }
  tr a {
    text-decoration: none;
    color: black;
  }
  tr a:visited {
    color: black;
  }
  tr:hover {
    cursor: pointer;
  }
  .all-content {
    width: 100%;
    xmax-width: 900px;
    margin: 0 auto;
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
📝 내 예약 목록
</div>
<br>
<div class="all-content">
<c:if test='${not empty reserList}'>
<table class="table table-striped text-center">
<thead>
  <tr>
    <th>선택</th>
    <th>예약날짜</th>
    <th>이용날짜</th>
    <th>이용시간</th>
    <th>스터디카페 - 스터디룸</th>
    <th>결제금액</th>
    <th>예약상태</th>
    <th>리뷰</th>
  </tr>
</thead>
<tbody>

	<c:forEach items="${reserList}" var="reservation">
	<tr>
	    <td><input class="form-check-input" type="checkbox" id="checkboxNoLabel" data-no="${reservation.reservationNo}"></td>
	    <td>${reservation.reservationDate}</td> 
	    <td>${reservation.useDate}</td> 
	    
	    <c:set var="startTime" value="${reservation.startTime}" /> 
	    <c:set var="useTime" value="${reservation.useTime}" /> 
         <% 
         LocalTime startTime = (LocalTime) pageContext.getAttribute("startTime");
         int useTime = (int) pageContext.getAttribute("useTime");
         LocalTime endTime = startTime.plusHours(useTime);
         pageContext.setAttribute("endTime", endTime);
         %>
	    
	    <td>${reservation.startTime} ~ ${endTime} (${reservation.useTime}시간)</td> 
	    <td>${reservation.cafe.name} - ${reservation.roomName}</td> 
	    <td>${reservation.totalPrice}</td> 
	    <td>${reservation.reservationStatusName}</td> 
	    <td>${reservation.wirteReviewLable}</td> 
	</tr>
	</c:forEach>
	
	</tbody>
</table>
</c:if>
<c:if test='${empty reserList}'>
   예약 내역이 없습니다.<br><br>  
</c:if>

<div class="btnSection text-center">
  <button type="button" class="btn btn-outline-dark" id="btnCancle">
    예약취소
  </button>
</div>

</div> <!-- .all-content -->
<jsp:include page="../footer.jsp"/>

<script>
document.querySelectorAll("tbody a").forEach((a) => {
	a.onclick = () => false;
});
var trList = document.querySelectorAll("tbody tr");
trList.forEach(function(tr) {
	
	if (tr.children[6].innerText != "예약완료") {
		//tr.children[0].disabled = true;
		tr.children[0].children[0].disabled = true;
	}

	tr.onclick = (e) => {
		// window.location.href = e.currentTarget.querySelector("a").href;
	}
	
});

/* var checkboxes = document.querySelectorAll("#checkboxNoLabel");
checkboxes.forEach((cbx) => {
	console.log(cbx);
}); */


</script>
</body>
</html>


<!-- 

      //      if (reserList.isEmpty()) {
      //        System.out.println(" >> 예약 내역이 존재하지 않습니다.");
      //        return;
      //      } else {
      //        for (CafeReservation myReservationList : reserList) {
      //          System.out.printf(" (%d) | 예약날짜 : %s | 이용날짜 : %s | 예약장소 : %s | 결제금액 : %d원 | 상태 : %s\n", 
      //              myReservationList.getReservationNo(), 
      //              myReservationList.getReservationDate(),
      //              myReservationList.getUseDate(),
      //              myReservationList.getCafe().getName(), 
      //              myReservationList.getTotalPrice(),
      //              myReservationList.getReservationStatusName());
      //          System.out.println();
      //        

 -->