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
    text-align: center;
    font-weight: bolder;
  }
  a {
   text-decoration:none;
  }
  </style>
</head>
<body>
<br>
<h3> 🔖 내 리뷰 목록 </h3><br>

<c:if test='${not empty reviewList}'>
<table class="table table-striped text-center">
	<thead>
	  <tr>
	    <th>번호</th>
	    <th>스터디카페</th>
	    <th>별점</th>
	    <th>내용</th>
	    <th>등록일</th>
	    <th>수정</th>
	    <th>삭제</th>
	  </tr>
	</thead>
  <tbody>

	<c:forEach items="${reviewList}" var="review">
	 <tr>
	    <td>${review.reviewNo}</td>
	    <td><a href='detail?no=${review.cafe.no}&perNo=${perNo}'>${review.cafe.name}</a></td>
	    <td>
	    <c:set var="grade" value="${review.grade}" /> 
	          <% 
	          int grade = (int) pageContext.getAttribute("grade");
	          String star = CafeHandlerHelper.getReviewGradeStatusLabel(grade);
	          pageContext.setAttribute("star", star);
	          %>
	      ${star}(${review.grade}/5)
	    </td>
	    <td>${review.content}</td>
	    <td>${review.registeredDate}</td>
	    <td>
	      <button class="btn btn-outline-dark"><a href="reviewUpdateForm?perNo=${perNo}&reviewNo=${review.reviewNo}">수정</a></button>
	    </td>
	    <td>
	      <button class="btn btn-outline-dark"><a href="reviewDelete?perNo=${perNo}&reviewNo=${review.reviewNo}">삭제</a></button>
	    </td>
	 </tr>
	</c:forEach>
  </tbody>
</table>
</c:if>

<c:if test='${empty reviewList}'>
   등록된 리뷰가 없습니다.<br><br>  
</c:if>
<br>

<!-- 

for (CafeReview cafeReview : reviewList) {
        if (cafeReview.getReviewStatus() == 2) {
          System.out.printf(" \n (%s)", cafeReview.getReviewNo());
          System.out.println(" >> 삭제한 리뷰입니다.\n");
          continue;
        }

        Cafe cafe = cafeDao.findByCafeNo(cafeReview.getCafe().getNo());

        System.out.printf(" (%d)\n [%s]\n 별점 : %s (%d)\n 내용 : %s\n 등록일 : %s\n",
            cafeReview.getReviewNo(), 
            cafe.getName(), 
            CafeHandlerHelper.getReviewGradeStatusLabel(cafeReview.getGrade()), 
            cafeReview.getGrade(), 
            cafeReview.getContent(), 
            cafeReview.getRegisteredDate());
        System.out.println();
      }

 -->