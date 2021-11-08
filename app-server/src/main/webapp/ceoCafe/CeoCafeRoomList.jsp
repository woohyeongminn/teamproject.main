<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <!-- 부트스트랩 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <!-- 아이콘 -->
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
  <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
</head>
<style>
  .all-content {
    width: 100%;
    margin: 0 auto;
    padding: 40px;
    margin-top:50px;
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
<div class="all-content">
<div class="c-top">
     스터디룸 목록
</div>
<section class="py-5">
    <div class="container px-4 px-lg-5 mt-5">
        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
          <c:forEach items="${cafeRoomList}" var="cafeRoom">
          <div class="col mb-5">
              <div class="card h-100">
                  <a href='detail?roomno=${cafeRoom.roomNo}'>
                    <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                  </a>
                  <div class="card-body p-3">
                      <div class="text-center">
                          <h5 class="fw-bolder"><a href='detail?roomno=${cafeRoom.roomNo}'><b>${cafeRoom.roomName}</b></a></h5>
                          <p>소개 | ${cafeRoom.roomInfo}</p>
                          <p>인원수 | ${cafeRoom.people}</p>
                          <p>시간당 금액 | ${cafeRoom.roomPrice}</p>
                      </div>
                  </div>
              </div>
          </div>
        </c:forEach>
  
		  <c:if test="${empty cafeRoomList}">
		    <p>등록한 스터디룸이 없습니다.</p>
		  </c:if>
		    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
          <div class="text-center"><a href="addform?cafeno=${cafeNo}">등록하기</a></div>
       </div>
    </div>
  </div>
</section>

</div>

</body>
</html>