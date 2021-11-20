<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <meta charset="UTF-8">
  <!-- 아이콘 -->
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
  <!-- Bootstrap icons-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
</head>
<style>
* {
  font-size: 14px;
}

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

<body>
  <div class="all-content">

		<div id="search">
		  <form action="search" method='get'>
		    <select name="where">
		      <option value="name">이름</option>
		    </select>
		    <input type="text" name="keyword">
		    <input type="hidden" name="cafeno" value="${cafeNo}">
		    <button class="btn btn-outline-dark btn-sm">검색</button>
		  </form>
		</div>
		          
		<section class="py-5">
		    <div class="container px-4 px-lg-5 mt-5">
		        <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
		          
		          <c:forEach items="${cafeRoomList}" var="cafeRoom">
		          <div class="col mb-5">
		              <div class="card h-100">
		                  <a href='detail?roomno=${cafeRoom.roomNo}'>
		                    <img class="card-img-top" src="${contextPath}/upload/cafe/${cafeRoom.roomImg}_80x80.jpg" alt="..." />
		                  </a>
		                  <div class="card-body p-3">
		                      <div class="text-center">
		                          <c:if test="${cafeRoom.roomStatus == 1}">
		                          <p>운영중</p>
		                          </c:if>
		                          <c:if test="${cafeRoom.roomStatus == 2}">
		                          <p style="color:red">운영중단</p>
		                          </c:if>
		                          <h5 class="fw-bolder">
			                          <c:if test="${cafeRoom.roomStatus == 1}">
			                            <a href='detail?roomno=${cafeRoom.roomNo}'><b>${cafeRoom.roomName}</b></a>
			                          </c:if>
			                          <c:if test="${cafeRoom.roomStatus == 2}">
			                            <b>${cafeRoom.roomName}</b>
			                          </c:if>
		                          </h5>
		                          <p>소개 | ${cafeRoom.roomInfo}</p>
		                          <p>인원수 | ${cafeRoom.people}</p>
		                          <p>시간당 금액 | ${cafeRoom.roomPrice}</p>
		                      </div>
		                  </div>
		              </div>
		          </div>
		          </c:forEach>
		  
						  <c:if test="${empty cafeRoomList}">
						    <span style="width:100%; text-align: center;">검색 결과가 존재하지 않습니다.</span>
						    
						    <c:if test="${empty keyword}">
						      <span style="width: fit-content">등록한 스터디룸이 없습니다.</span>
						    </c:if>
						  </c:if>
						  
						   <!-- 검색 결과
              <c:if test='${empty cafeRoomList}'>
                검색 결과가 존재하지 않습니다.<br><br>
              </c:if> -->
			    </div>
			    
			    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent" style="margin-top: 20px;">
               <div class="text-center" style="margin-bottom: 10px;">
                  <a href="list?cafeno=${cafeNo}"  class="btn btn-outline-dark" style="width: 78px;">목록</a>
                </div>
			       <div class="text-center"><a href="addform?cafeno=${cafeNo}"  class="btn btn-outline-dark">등록하기</a></div>
			    </div>
		  </div>
		</section>
  </div>
</body>
