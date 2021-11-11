<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
  * {
  font-size: 14px;
  }
  div {
  margin-right: 10px;
  }
  a {
  color : black;
  text-decoration : auto;
  }
  a:hover {
  color : white;
  }
  button[type=submit] {
    font-size: 14px;
    line-height: 14px;
  }
  </style>
</head>

<br>
<c:if test='${not empty reviewList}'>
<table class="table table-striped text-center">
	<thead>
	  <tr data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
	    <th>번호</th>
	    <th>스터디카페</th>
	    <th>별점</th>
	    <th>내용</th>
	    <th>등록일</th>
	    <th>삭제</th>
	  </tr>
	</thead>
  <tbody>

	<c:forEach items="${reviewList}" var="review">
	 <tr>
	    <td>${review.reviewNo}</td>
	    <td>${review.cafe.name}</td>
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
	      <button type="submit" class="btn btn-outline-dark"><a href="reviewDelete?reviewNo=${review.reviewNo}">삭제</a></button>
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

<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">

  <jsp:include page="AdminMenu.jsp"/>
    
</div>
