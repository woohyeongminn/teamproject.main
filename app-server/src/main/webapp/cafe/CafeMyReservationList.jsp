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
  <link rel="stylesheet" href="../node_modules/sweetalert2/dist/sweetalert2.css">
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <script src="../node_modules/sweetalert2/dist/sweetalert2.js"></script>
  <style>
  h3 {
    text-align: center;
    font-weight: bolder;
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
<form id="reservationForm" action="reservationDelete" method="POST">
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
	    <td><input class="form-check-input" type="checkbox" id="checkboxNoLabel" name="reservationNo" value="${reservation.reservationNo}"></td>
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
	    <td>
		    <c:choose>
		      <c:when test="${reservation.wirteReviewLable eq '작성가능'}">
            <button type="button" class="btn btn-outline-dark btn-sm">리뷰쓰기</button>		      
		      </c:when>
		      <c:otherwise>
				    ${reservation.wirteReviewLable}		      
		      </c:otherwise>
		    </c:choose>
	    </td> 
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

</form>	
</div> <!-- .all-content -->
<jsp:include page="../footer.jsp"/>

<script>
var trList = document.querySelectorAll("tbody tr");
trList.forEach(function(tr) {
	
	if (tr.children[6].innerText != "예약완료") {
		tr.children[0].children[0].disabled = true;
	}

});

document.querySelector("#btnCancle").onclick = () => {
	const selectedList = document.querySelectorAll('input[name="reservationNo"]:checked');
	if (selectedList.length == 0){
		swal.fire("취소할 예약을 선택해주세요.");
		return false;
	} else {
		Swal.fire({
		      title: '예약을 정말 취소하시겠습니까?',
		      text: "취소하시면 다시 복구시킬 수 없습니다.",
		      icon: 'warning',
		      showCancelButton: true,
		      confirmButtonText: '네',
		      cancelButtonText: '아니오'
		    }).then((result) => {
		      if (result.value) { 
						  document.querySelector("#reservationForm").submit();
		      }
		    })
	}
	
}


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