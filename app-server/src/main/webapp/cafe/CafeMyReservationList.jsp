<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <style>
  h3 {
    text-align: center;
    font-weight: bolder;
  }
  .all-content {
    width: 100%;
    max-width: 900px;
    margin: 0 auto;
  }
  .c-top {
    width: 100%;
    padding: 20px 0 20px 50px;
    font-weight: bold;
    background-color: rgb(247, 231, 215);
    text-align: center;
  }
  
  /*footer 시작*/
	footer {
	  font-size: 14px;
	  padding: 8px 0;
	  background-color: whitesmoke;
	  position: absolute;
    width: 100%;
    bottom: 0;
    left: 0;
	}
	
	.footer_company {
	  display: flex;
	  margin-left: 20px;
	}
	
	.footer_company li a{
	  padding: 2px 10px 2px 0;
	}
	
	.footer_address {
	  margin-left: 20px;
	}
	
	.footer_copyright {
	  margin-left: 20px;
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
    <th>번호</th>
    <th>예약날짜</th>
    <th>이용날짜</th>
    <th>예약장소</th>
    <th>결제금액</th>
    <th>상태</th>
  </tr>
</thead>
<tbody>

	<c:forEach items="${reserList}" var="reservation">
	<tr>
	    <td><a href='reservationDetail?reservationNo=${reservation.reservationNo}'>${reservation.reservationNo}</a></td>
	    <td>${reservation.reservationDate}</td> 
	    <td>${reservation.useDate}</td> 
	    <td>${reservation.cafe.name}</td> 
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
<footer>
      <ul class="footer_company">
        <li><a href="#">오늘의 공부 소개</a></li>
        <li><a href="#">이용약관</a></li>
        <li><a href="#" class="orange">개인정보처리방침</a></li>
        <li><a href="#">1:1문의</a></li>
        <li><a href="#">법적고지</a></li>
        <li><a href="#">사이트맵</a></li>
      </ul>
      <hr>
      <div class="footer_address">
        <p>상호명 : 오늘의 공부&emsp;ㅣ&emsp;주소 : (우)1111 서울특별시 강남구 역삼동 819-3 삼오빌딩</p>
        <p>FAX : 0505-111-1111&emsp;ㅣ&emsp;Email: bit.study2@gmail.com</p>
        <p>고객센터 : 1577-1111 (평일 09:00 ~ 18:00 / 점심 12:00 ~ 13:00)</p>
        <p>사업자등록번호 : 391-11-1111&emsp;ㅣ&emsp;통신판매업신고번호: 제 2021-서울강남-1111 호 ㅣ 대표: 엄땡땡</p>
      </div>
      <hr>
      <p class="footer_copyright">COPYRIGHTⓒ2021 TODAYSTUDY. ALL RIGHTS RESERVED.</p>
</footer>
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