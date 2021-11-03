<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>🏘 스터디 카페</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <style>
  legend {
  text-align: center;
  }
  #content {
  float: center;
    margin-left: 20px;
    width: 58%;
    xborder: 1px solid black;
  }
  rect {
  fill: lightyellow;
  }
  text {
  fill: black;
  }
  a {
  color : black;
  text-decoration : blink;
  }
  a:hover {
  color : lightgray;
  }
  .col {
    width: 450px;
  }
  .card {
    height: 350px;
  }
  </style>
</head>
<body>
<br>
<legend><b> 🏘 스터디 카페 목록 </b></legend><br>
<hr>
  <div id="content">
    <div class="row row-cols-1 row-cols-md-3 g-4" style="float: left">
    <c:forEach items="${cafeList}" var="cafe">
      <div class="col">
        <div class="card">
          <svg class="bd-placeholder-img rounded" width="425" height="200" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 200x200" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#868e96"></rect><text x="45%" y="50%" fill="#dee2e6" dy=".3em">이미지</text></svg>
          <div class="card-body">
            <a href='cafeDetail?no=${cafe.no}'><b>${cafe.name}</b></a><br>
            ${cafe.location}<br>
            영업시간 ${cafe.openTime} ~ ${cafe.closeTime}<br>
            ⭐${cafe.avgReview}(${cafe.countReview})<br>
            <script>
         if(${cafe.cafeStatus == 1}) {
           document.write("<label for='f-cafeStatus'>❗ </label>승인 대기 중인 카페입니다.");
         } else if(${cafe.cafeStatus == 2}) {
           document.write("<label for='f-cafeStatus'>🔆 </label>현재 운영 중입니다.");
         } else if(${cafe.cafeStatus == 3}) {
           document.write("<label for='f-cafeStatus'>⛔ </label>운영 중단된 카페입니다.");
         } else if(${cafe.cafeStatus == 4}) {
           document.write("<label for='f-cafeStatus'>❌ </label>삭제된 카페입니다.");
         } 
       </script>
          </div>
        </div>
      </div>
    </c:forEach>
    </div>
  </div>

</body>
</html>