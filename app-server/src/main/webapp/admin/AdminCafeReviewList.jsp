<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
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
  text-decoration : auto;
  }
  a:hover {
  color : lightgray;
  }
  </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<br>
<legend><b> 🔖 스터디 카페 리뷰 목록 </b></legend><br>
<hr>

<c:if test='${not empty reviewList}'>
<table class="table table-striped text-center">
	<thead>
	  <tr>
	    <th>번호</th>
	    
        <c:choose>
          <c:when test="${not empty loginAdmin}">
           <th style="margin-left: auto;" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">스터디카페</th>
          </c:when>
          <c:otherwise>
           <th>스터디카페</th>
          </c:otherwise>
        </c:choose>
	    
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
	      <button class="btn btn-outline-dark"><a href="reviewDelete?reviewNo=${review.reviewNo}">삭제</a></button>
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

</body>
</html>