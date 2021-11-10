<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
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
    font-weight: bolder;
  }
	a {
	 text-decoration:none;
	}
	label {
	  display: inline-block;
	  margin-right: 5px;
	  width: 130px;
	}
	#c-detail-content {
    padding-left: 34px;
	}
	#aside {
	   width: 120px;
	   height: 171px;
	   float: left;
	   background-color: lightsteelblue;
	   display: table;
	}
	#content {
	   margin-left: 130px;
	}
	#c-image {
	  display: table-cell;
	  vertical-align: middle;
	  text-align: center;
	}
	#c-grade {
	   margin-left: 41px;
	   vertical-align: 4px;
	}
	#c-review {
	  width: 427px;
	  background-color: whitesmoke;
	  height: 80px;
	  margin-bottom: 10px;
	}
	.c-top {
    width: 100%;
    padding: 20px 0 20px 50px;
    font-weight: bold;
    background-color: rgb(247, 231, 215);
    text-align: center;
  }
	</style>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="c-top">
🏘 ${cafe.name}
</div>
<br><br>
<div id="c-detail-content">
	<input id='c-no' type='hidden' value='${cafe.no}'>
	<div id='aside'>
    <span id='c-image'>대표이미지</span>
	</div>
	<div id='content'>
    <label for='f-info'>소개글</label>${cafe.info}<br>
		<label for='f-location'>주소</label>${cafe.location}<br>
		<label for='f-tel'>전화번호</label>${cafe.phone}<br>
		<label for='f-openTime'>오픈시간</label>${cafe.openTime}<br>
		<label for='f-closeTime'>마감시간</label>${cafe.closeTime}<br>
		<label for='f-holiday'>이번주 휴무일</label>${cafe.holiday}<br>
		<label for='f-viewCount'>조회수</label>${cafe.viewCount}<br>
		<label for='f-review'>리뷰평점</label>⭐${cafe.avgReview}(${cafe.countReview})
	</div>
<br>
<c:if test='${not empty reviewList}'>
  <c:forEach items="${reviewList}" var="review">
    <div id='c-review'>
		  <span>${review.member.perNickname}</span> 
		  <span id='c-grade'>
			  <c:set var="grade" value="${review.grade}" /> 
				  <% 
				  int grade = (int) pageContext.getAttribute("grade");
				  String star = CafeHandlerHelper.getReviewGradeStatusLabel(grade);
				  pageContext.setAttribute("star", star);
				  %>
		  ${star}(${review.grade}/5)
		  </span>
		  <span id='c-grade'>${review.registeredDate}</span>
		  <br><p id='c-review-content'>${review.content}</p><br>
		</div>
	</c:forEach>
</c:if>
<c:if test='${empty reviewList}'>
	 등록된 리뷰가 없습니다.<br><br>	
</c:if>
<button type="button" class="btn btn-outline-dark" onclick="reserBtn_click(${loginUser.perNo}); return false;">스터디룸 예약</button>
<button type="button" class="btn btn-outline-dark"><a href="list">목록</a></button>
</div>

<script>
function reserBtn_click(user) {
  if (user == null) {
	  alert('로그인 한 회원만 예약 가능합니다.');
  } else {
	  location.href="reservation?no="+${cafe.no};
  }
}
</script>
</body>
</html>

<!-- 
if (cafe.getCafeStatus() == Cafe.STOP) {
        // 카페가 운영중단 상태일때는 예약 메뉴 출력 안하고 상세보기만
        return;
      }

      int roomCount = 0;
      List<CafeRoom> roomList = cafeRoomDao.getCafeRoomList();
      for (CafeRoom cafeRoom : roomList) {
        if (cafeRoom.getCafe().getNo() == cafe.getNo()) {
          roomCount++;
        }
      }
      out.println("<a href='list'>목록</a>");

      if (roomCount != 0) {
        out.println("<button>스터디룸 예약</button>");
      }
 -->