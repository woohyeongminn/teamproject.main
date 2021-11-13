<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <link rel="canonical" href="https://getbootstrap.kr/docs/5.1/examples/sticky-footer/">

    <!-- Bootstrap core CSS -->
<link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Favicons -->
<link rel="apple-touch-icon" href="/docs/5.1/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/5.1/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/5.1/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon.ico">
<meta name="theme-color" content="#7952b3">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

    <!-- Custom styles for this template -->
    <!-- <link href="sticky-footer.css" rel="stylesheet"> -->
  </head>
  <body class="d-flex flex-column h-100">
    
<!-- Begin page content -->
<main class="flex-shrink-0">
  <div class="container">
    <h1 class="mt-5">${study.studyTitle}</h1>
    <span>${study.owner.perNickname}</span>
    <td><fmt:formatDate value="${study.registeredDate}" pattern="yyyy.MM.dd" /></td>
    <p class="lead">${study.introduction}</p>
  </div>
</main>

<footer class="footer mt-auto py-3 bg-light">
  <div class="container">
    <div class="btn-group" role="group" aria-label="Basic outlined example">
		  <c:choose>
		    <c:when test="${study.owner.perNo eq loginUser.perNo}">
			    <button class="btn btn-outline-light">
			      <a href='updateform?studyno=${study.studyNo}'>수정</a>
			    </button>
			    <button class="btn btn-outline-light">
			      <a href='delete?studyno=${study.studyNo}'>삭제</a>
			    </button>
		    </c:when>
		    <c:when test="${study.owner.perNo ne member.perNo}">
			    <button class="btn btn-outline-light">
			      <a href='join?studyno=${study.studyNo}'>참여 신청</a>
			    </button>
		    </c:when>
	    </c:choose>
	    <c:if test="${myBookmark == '0'}">
		    <button class="btn btn-outline-light">
		      <a href='${contextPath}/app/bookmark/add?studyno=${study.studyNo}'>북마크 추가</a>
		    </button>
		  </c:if>
      <c:if test="${myBookmark == '1'}">
		    <button class="btn btn-outline-light">
		      <a href='${contextPath}/app/bookmark/delete?studyno=${study.studyNo}'>북마크 삭제</a>
		    </button>
			</c:if>
	    <button class="btn btn-outline-light">
	      <a href='list'>목록</a>
	    </button>
		</div>
  </div>
</footer>

  </body>
</html>
