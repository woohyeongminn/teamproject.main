<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <style>
  html {
    height: 100%;
  }
  h3 {
    text-align: center;
    font-weight: bolder; 
  }
  a {
   text-decoration:none;
  }
  body {
    overflow: hidden;
    width: 100%;
    height: 100%;
  }
  #search {
    text-align: center;
    padding: 20px;
  }
  .form-select {
    display: inline-block;
  }
	div.template-wrap:nth-of-type(2) {
	  --desktopHeaderContentHeight: 60px;
    --headerVerticalPadding: 10px;
    --desktopNavHeight: calc(var(--headerVerticalPadding) + var(--desktopHeaderContentHeight) + var(--headerVerticalPadding));
    --desktopTopBarHeight: 20.80px;
    --desktopTopBarPadding: 20px;
    --desktopTopBar: calc(var(--desktopTopBarHeight) + var(--desktopTopBarPadding) + var(--desktopTopBarPadding));
	   height: calc(100% - var(--desktopNavHeight) - var(--desktopTopBar));
	}
	.template-wrap .template-content {
	  width: 100%;
	  height: 100%;
	}
  .c-content {
    display: flex;
    --desktopSearchBarHeight: 38px;
    --desktopSearchBarPadding: 20px;
    --desktopSearchBar: calc(var(--desktopSearchBarHeight) + var(--desktopSearchBarPadding) + var(--desktopSearchBarPadding));
    height: calc(100% - var(--desktopSearchBar));
  }
  #content {
    margin-left: 20px;
    width: 722px;
    float: left;
    overflow-y: scroll;
    overflow-x:hidden;
  }
  .col {
    width: 355px;
  }
  .card {
    height: 372px;
  }
  footer {
    display: none;
  }
  </style>
</head>
<body>

  <div id="search">
    <form action="search">
    <select name="where">
      <option value="1">지역</option>
      <option value="2">이름</option>
    </select>
    <input type="text" name="keyword">
    <input type="hidden" name="perNo" value="${perNo}">
    <button class="btn btn-outline-dark">검색</button>
    </form>
  </div>

<div class="c-content">
<div id="content">
<c:if test='${not empty cafeList}'>
    <div class="row row-cols-1 row-cols-md-3 g-4">
    <c:forEach items="${cafeList}" var="cafe">
    <c:if test='${cafe.countRoom > 0}'>
      <div class="col">
        <div class="card">
          <img src="../img/aaa.jpg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title" style="font-size:14px; font-weight: bold;">
              <a href='detail?no=${cafe.no}'>${cafe.name}</a>
            </h5>
            <p style="font-size:14px;">
            ${fn:split(cafe.location, ',')[0]}<br>
            ${cafe.openTime} ~ ${cafe.closeTime}<br>
            ⭐${cafe.avgReview}(${cafe.countReview})</p>
          </div>
        </div>
      </div>
    </c:if>
    </c:forEach>
    </div>
  </c:if>
<c:if test='${empty cafeList}'>
   검색 결과가 존재하지 않습니다.<br><br>  
</c:if>
</div>

<div id="map" style="width:calc(100% - 722px - 38px);"></div>
  
</div>  

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=19b698969a5fbbf08d3bddab4e1ceacc&libraries=services"></script>
<script>
   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
   mapOption = {
       center: new kakao.maps.LatLng(37.498004414546934, 127.02770621963765), // 지도의 중심좌표
       level: 3 // 지도의 확대 레벨
   };  
   
   //지도를 생성합니다    
   var map = new kakao.maps.Map(mapContainer, mapOption); 
   
   //주소-좌표 변환 객체를 생성합니다
   var geocoder = new kakao.maps.services.Geocoder();
   
   // 카페 주소 리스트
   var arr = new Array();
   <c:forEach items="${cafeList}" var="cafe">
     var cafeMap = new Map();
   
     var originLocation = "${cafe.location}";
     var editLocation = originLocation.split(",")[0];
   
     var cafeMap = new Map(); // Map으로 초기화시킨 객체는 iterable 객체이다. 
     cafeMap.set("name", "${cafe.name}");
     cafeMap.set("no", "${cafe.no}");
     cafeMap.set("location", editLocation);
     
     arr.push(cafeMap);
   </c:forEach>
   
   for (let i = 0; i < arr.length; i++) {
   
     //주소로 좌표를 검색합니다
     geocoder.addressSearch(arr[i].get("location"), function(result, status) {
   
       // 정상적으로 검색이 완료됐으면 
       if (status === kakao.maps.services.Status.OK) {
   
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
   
        arr[i].set("latlng", coords);
       }
       
       // 마커를 생성합니다
       var marker = new kakao.maps.Marker({
           map: map, // 마커를 표시할 지도
           position: arr[i].get("latlng") // 마커를 표시할 위치
       });
       
        // 인포윈도우로 장소에 대한 설명을 표시합니다
       var infowindow = new kakao.maps.InfoWindow({
           content: '<div style="width:150px;text-align:center;padding:6px 0;font-size:14px;">'+arr[i].get("name")+'</div>'
       });
       infowindow.open(map, marker);

       // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
       map.setCenter(arr[0].get("latlng"));
     });
     
   }
</script>

<!--
<table class="table table-striped text-center">
<thead>
  <tr>
    <th>번호</th>
    <th>이름</th>
    <th>주소</th>
    <th>운영시간</th>
    <th>조회수</th>
    <th>리뷰</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${cafeList}" var="cafe">
<tr>
    <td>${cafe.no}</td>
    <td><a href='detail?no=${cafe.no}'>${cafe.name}</a></td> 
    <td>${cafe.location}</td> 
    <td>${cafe.openTime} ~ ${cafe.closeTime}</td>
    <td>${cafe.viewCount}</td>
    <td>⭐${cafe.avgReview}(${cafe.countReview})</td>
</tr>
</c:forEach>

</tbody>
</table>

 -->