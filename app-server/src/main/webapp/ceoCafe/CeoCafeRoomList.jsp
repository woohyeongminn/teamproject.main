<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>스터디 카페 룸 목록</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <style>
  #content {
    margin-left: 30px;
  }
  .col {
    width: 450px;
  }
   .ellipsis{
        width:300px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    } 
  </style>
</head>

<body>
<h5>스터디카페 룸 목록</h5>
<br>
<div id="content">

 <div class="row row-cols-1 row-cols-md-3 g-4">
  
  <c:forEach items="${cafeRoomList}" var="cafeRoom">
    <div class="col">
      <div class="card">
       
        <svg class="bd-placeholder-img rounded" width="425" height="200" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 200x200" preserveAspectRatio="xMidYMid slice" focusable="false">
        <title>Placeholder</title>
        <rect width="100%" height="100%" fill="#868e96"></rect>
        <text x="45%" y="50%" fill="#dee2e6" dy=".3em">이미지</text>
        </svg>
        
        <div class="card-body">
          ${cafeRoom.roomImg}<br>
          <a href='detail?roomno=${cafeRoom.roomNo}'><b>${cafeRoom.roomName}</b></a><br>
          <p class = 'ellipsis'>${cafeRoom.roomInfo}</p>
          인원수 | ${cafeRoom.people} <br>
          시간당 금액 | ${cafeRoom.roomPrice}<br>
        </div>
      </div>
    </div>
  </c:forEach>
  
  <c:if test="${empty cafeRoomList}">
	  <p>등록한 스터디룸이 없습니다.</p>
  </c:if>
  </div>
  <br>
  <button id='button'>
      <a href="addform?no=${cafeNo}">스터디 룸 추가</a>
    </button>
  
</div>
</body>
</html>