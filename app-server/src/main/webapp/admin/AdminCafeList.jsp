<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>🏘 스터디 카페</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <style>
  legend {
  text-align: center;
  }
  legend:hover {
    color: lightgrey;
  }
  h3 {
    text-align: center;
    font-weight: bolder;
  }
  rect {
  width: 414px;
  fill: lightyellow;
  }
  text {
  fill: black;
  }
  button[type=button] {
    margin-block: 10px;
    border-radius: 10px;
    background-color: beige;
    color: black;
  }
  button[type=button]:hover {
    background-color: blanchedalmond;
    color: black;
  }
  .btn-secondary:focus {
  background-color: beige;
  color: black;
  }
  button[type=button1] {
    margin-left: 15px;
    border-radius: 10px;
    border-color: lightgray;
    background-color: beige;
    color: black;
  }
  button[type=button1]:hover {
    background-color: blanchedalmond;
    color: black;
  }
  .dropdown-menu {
  background-color: rgba(211, 211, 211, 0);
  border: rgba(211, 211, 211, 0);
  }
  .btn-group {
  margin-top: 10px;
  display: block;
  }
  .offcanvas-start {
  width: 350px;
  }
  button[type=button2] {
  margin-left: 70px;
    color: black;
  }
  button[type=button2]:hover {
    color: black;
  }
  div {
  margin-right: 10px;
  }
  a {
  color : black;
  text-decoration : blink;
  }
  a:hover {
  color : lightgray;
  }
  body {
    overflow: hidden;
    width: 100%;
    height: 100%;
  }
  #search {
    text-align: center;
  }
  .form-select {
    display: inline-block;
  }
  .c-content {
    height: 530px;
  }
  #content {
    margin-left: 20px;
    width: 47%;
    xborder: 1px solid black;
    float: left;
    overflow-y: scroll;
    height: inherit;
    overflow-x:hidden
  }
  .col {
    width: 355px;
  }
  .card {
    height: 433px;
  }
  .c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  text-align: center;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
}
  .c-top:hover {
    color: cornflowerblue;
  }
  </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<section>
<div class="c-top" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
      🏘 스터디 카페 목록
      </div>
<br>
  <div id="search">
    <form action="search">
    <select name="where">
      <option value="1">지역</option>
      <option value="2">이름</option>
    </select>
    <input type="text" name="keyword">
    <button class="btn btn-outline-dark">검색</button>
    </form>
  </div>
  <br>

<div class="c-content">
<div id="content">
<c:if test='${not empty cafeList}'>
    <div class="row row-cols-1 row-cols-md-3 g-4">
    <c:forEach items="${cafeList}" var="cafe">
      <div class="col">
        <div class="card">
          <img src="../img/aaa.jpg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">
              <a href='cafeDetail?no=${cafe.no}'>${cafe.name}</a>
            </h5>
            <p>
            ${fn:split(cafe.location, ',')[0]}<br>
            ${cafe.openTime} ~ ${cafe.closeTime}<br>
            ⭐${cafe.avgReview}(${cafe.countReview})</p>
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
  </c:if>
  
  <c:if test='${empty cafeList}'>
   검색 결과가 존재하지 않습니다.<br><br>  
</c:if>
</div>
  
  <div id="map" style="width:770px;height:530px;"></div>
  
</div>  
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 APP KEY를 사용하세요"></script>
  <script>
    var container = document.getElementById('map');
    var options = {
      center: new kakao.maps.LatLng(33.450701, 126.570667),
      level: 3
    };
    var map = new kakao.maps.Map(container, options);
  </script>
  
<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">

  <jsp:include page="AdminMenu.jsp"/>
    
</div>

</section>
   <jsp:include page="../footer.jsp"/>
</body>
</html>