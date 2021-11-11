<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <style>
  * {
   font-size: 14px;
  }
  rect {
  fill: lightyellow;
  }
  text {
  fill: black;
  }
  #search {
    text-align: center;
    padding: 20px;
  }
  div {
  margin-right: 10px;
  }
  #content {
    float: center;
    margin-left: 50px;
    margin-top: 50px;
    height: 650px;
    ine-height: 27px;
    overflow-y: scroll;
    overflow-x: hidden;
    xborder: 1px solid black;
  }
  
  /* content 안의 스크롤 색상 지정 */
  #content::-webkit-scrollbar {
    width: 10px;
  }
  #content::-webkit-scrollbar-thumb {
    background-color: rgb(247, 231, 215);
    border-radius: 10px;
    background-clip: padding-box;
    border: 2px solid transparent;
  }
  #content::-webkit-scrollbar-track {
    background-color: rgb(250, 250, 234);
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
  }
  
  a {
  color : black;
  text-decoration : blink;
  }
  a:hover {
  color : lightgray;
  }
  .col {
    width: 355px;
  }
  .card {
    height: 450px;
  }
  .btn-sm, .btn-group-sm > .btn {
    padding: 0.25rem 0.5rem;
    font-size: 0.875rem;
    line-height: 14px;
    margin-bottom: 2px;
    border-radius: 0.2rem;
  }
  </style>
  
</head>
<body>
<br>

  <div id="search">
    <form action="search">
    <select name="where" style="font-size: 12px;">
      <option value="1" style="font-size: 12px; line-height: 14px;">지역</option>
      <option value="2" style="font-size: 12px; line-height: 14px;">이름</option>
    </select>
    <input type="text" name="keyword" style="font-size: 12px;">
    <input type="hidden" name="perNo" value="${perNo}">
    <button class="btn btn-outline-dark btn-sm">검색</button>
    </form>
  </div>

  <div id="content">
  <c:if test='${not empty cafeList}'>
    <div class="row row-cols-1 row-cols-md-3 g-4" style="float: left">
    <c:forEach items="${cafeList}" var="cafe">
      <div class="col">
        <div class="card">
          <img src="../img/aaa.jpg" class="card-img-top" alt="..." data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
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
  </c:if>
  </div>
  
  <c:if test='${empty cafeList}'>
   검색 결과가 존재하지 않습니다.<br><br>  
  </c:if>

  </div>  
</div>   
  
<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">

  <jsp:include page="AdminMenu.jsp"/>
    
</div>