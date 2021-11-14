<%@page import="java.time.LocalTime"%>
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
  .all-content {
    width: 100%;
    margin: 0 auto;
    padding: 80px 0;
  }
  </style>
</head>
<body>

<div class="all-content">
<form id="reservationForm" action="reservationDelete" method="POST">
<c:if test='${not empty reserList}'>
<table class="table table-responsive text-center align-middle">
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
            <button type="button" class="btn btn-outline-dark btn-sm" data-bs-toggle="modal" data-bs-target="#exampleModal">리뷰쓰기</button>		      
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

<!-- 리뷰쓰기 창 -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">리뷰쓰기</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form id="reviewForm" action="reviewAdd" method="POST">
          <div class="mb-3">
            <label for="recipient-name" class="col-form-label">평점</label>
            <input type="number" min="0" max="5" name="grade" class="form-control" id="recipient-name">
          </div>
          <div class="mb-3">
            <label for="message-text" class="col-form-label">내용</label>
            <textarea class="form-control" id="message-text" name="content"></textarea>
          </div>
            <input type="hidden" name="reservationNo" id="reservationNo">
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">닫기</button>
        <button type="button" class="btn btn-outline-dark" id="btnReviewAdd">등록</button>
      </div>
    </div>
  </div>
</div> <!-- .modal -->

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

var exampleModal = document.getElementById('exampleModal');
exampleModal.addEventListener('show.bs.modal', function (event) {
  // Button that triggered the modal
  var button = event.relatedTarget;
  var reservationNo = button.parentNode.parentNode.children[0].children[0].value;
  
  var modalTitle = exampleModal.querySelector('.modal-title');
  var modalBodyInput = exampleModal.querySelector('.modal-body input');
  var modalBodyReservationInput = exampleModal.querySelector('.modal-body input[name="reservationNo"]');

  modalTitle.textContent = '리뷰 작성';
  modalBodyReservationInput.value = reservationNo;
})

document.querySelector("#btnReviewAdd").onclick = () => {
  
  var grade = document.querySelector('input[name="grade"]');
  var content = document.querySelector('textarea[name="content"]');
  
   if (grade.value.length == 0 || content.value.length == 0){
    swal.fire("평점과 내용을 모두 입력해주세요.");
    return false;
  } else {
    Swal.fire({
          title: '리뷰를 정말 등록하시겠습니까?',
          showCancelButton: true,
          confirmButtonText: '네',
          cancelButtonText: '아니오'
        }).then((result) => {
          if (result.value) { 
              document.querySelector("#reviewForm").submit();
          }
        })
  } 
}

</script>