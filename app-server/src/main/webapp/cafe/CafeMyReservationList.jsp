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
  #content {
    margin-left: 30px;
  }
  .col {
    width: 450px;
  }
  </style>
</head>
<body>
<h1>내 스터디카페 예약 목록</h1>
<br>

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
    <td><a href='detail?no=${reservation.reservationNo}'>${reservation.reservationNo}</a></td>
    <td>${reservation.reservationDate}</td> 
    <td>${reservation.useDate}</td> 
    <td>${reservation.cafe.name}</td> 
    <td>${reservation.totalPrice}</td> 
    <td>${reservation.reservationStatusName}</td> 
</tr>
</c:forEach>

</tbody>
</table>


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