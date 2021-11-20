<%@page import="java.time.LocalTime"%>
<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
	<style>
	*{
	 font-size: 13.5px;
	}
	h3 {
    font-weight: bolder;
  }
	a {
	 text-decoration:none;
	}
  .pt-4 {
    height: auto !important;
  }
  .dropdown a > span {
    font-size: 14px;
  }
  .c-content {
    width: 100%;
    max-width: 720px;
    margin: 0 auto;
    box-sizing: border-box;
  }
  .c-body {
    padding: 26px 20px 28px;
  }
  .reservation-title p {
    font-size: large;
  }
  .reservation-title p span {
    font-size: large;
    color: rgb(155 136 131);
  }
  .reservation-info {
    height: 550px;
  }
  .info-box {
    border: 1px solid rgb(155 136 131);
    border-radius: 20px;
    margin-top: 23px;
    padding: 15px;
  }
  .p-3 {
    padding: 0.5rem !important;
  }
  .col-3 .p-3 {
    font-weight: bold;
  }
  .link-box {
    margin-top: 60px;
  }
  .link-box a:hover {
    color: rgb(155 136 131);
    font-weight: bold;
    font-size: 1.2em;
    
  }
	</style>

<body>
<div class="c-content">
	<div class="c-body">
	  
	  <div class="reservation-title">
     <p>✨ <span>예약이 정상적으로 완료</span>되었습니다. </p>
    </div>
	  
	  <div class="reservation-info">
	   <div class="info-box">

        <div class="container">
				  <div class="row g-0">
				    <div class="col-3">
				      <div class="p-3">예약번호</div>
				    </div>
				    <div class="col-9">
				      <div class="p-3">${reservation.reservationNo}</div>
				    </div>
				    <div class="col-3">
              <div class="p-3">장소</div>
            </div>
            <div class="col-9">
              <div class="p-3">${reservation.cafe.name} - ${reservation.roomName}</div>
            </div>
            <div class="col-3">
              <div class="p-3">일시</div>
            </div>
            <div class="col-9">
            
            <c:set var="startTime" value="${reservation.startTime}" /> 
			      <c:set var="useTime" value="${reservation.useTime}" /> 
			         <% 
			         LocalTime startTime = (LocalTime) pageContext.getAttribute("startTime");
			         int useTime = (int) pageContext.getAttribute("useTime");
			         LocalTime endTime = startTime.plusHours(useTime);
			         pageContext.setAttribute("endTime", endTime);
			         %>
            
              <div class="p-3">${reservation.useDate}&nbsp;${reservation.startTime} ~ ${endTime} (${reservation.useTime}시간)</div>
            </div>
				    <div class="col-3">
				      <div class="p-3">예약자 정보</div>
				    </div>
				    <div class="col-9">
				      <div class="p-3">${loginUser.perNickname}</div>
				    </div>
				    <div class="col-3">
              <div class="p-3">예약자 연락처</div>
            </div>
            <div class="col-9">
              <div class="p-3">${loginUser.perTel}</div>
            </div>
            
            <div class="col-3">
              <div class="p-3">결제방법</div>
            </div>
            <c:if test="${reservation.reservationStatus == 1}">
              <div class="col-9">
                <div class="p-3">현장결제</div>
              </div>
            </c:if>
            <c:if test="${reservation.reservationStatus == 2}">
              <div class="col-9">
                <div class="p-3">${reservation.paymentType}</div>
              </div>
            </c:if>
            
            <c:if test="${reservation.reservationStatus == 1}">
	            <div class="col-3">
	              <div class="p-3">금액</div>
	            </div>
	            <div class="col-9">
	              <div class="p-3"><span id="price"></span>원</div>
	            </div>
            </c:if>
            
            <c:if test="${reservation.reservationStatus == 2}">
            <div class="col-3">
              <div class="p-3">결제금액</div>
            </div>
            <div class="col-9">
              <div class="p-3"><span id="price"></span>원</div>
            </div>
            </c:if>
            
				  </div>
				</div>
	     
	   </div> <!-- .info-box -->
	   
	   <div class="link-box text-center">
			  <a href="${contextPath}/app/index">
			   <i class="fas fa-long-arrow-alt-right"></i>
			   <span>메인으로 돌아가기</span>
			  </a>
			  &nbsp;&nbsp;&nbsp;&nbsp;
			  <a href="${contextPath}/app/cafe/reservationList">
		     <i class="fas fa-long-arrow-alt-right"></i>
		     <span>내 예약내역 보러가기</span>
		    </a>
	    </div>
	  </div> <!-- .reservation-info -->
	  
	</div> <!-- .c-body -->
</div> <!-- .c-content -->




<script src="https://code.jquery.com/jquery-1.12.4.min.js" type="text/javascript"></script> <!-- jQuery -->
<script>
$(function() {
var original = "${reservation.totalPrice}";
var fomatting = original.replace(/\B(?=(\d{3})+(?!\d))/g, ",");

$("#price").text(fomatting);
}); 


</script>



