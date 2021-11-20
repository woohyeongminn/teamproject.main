<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <link rel="canonical" href="https://getbootstrap.kr/docs/5.1/examples/blog/">

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
    .inner-page {
		  height: 81vmin;
		}

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
    <link href="https://fonts.googleapis.com/css?family=Playfair&#43;Display:700,900&amp;display=swap" rel="stylesheet">
  </head>
  <body>

<main class="container">
  <div class="row g-5">
    <div class="col-md-8">
      <h3 class="pb-4 mb-4 fst-italic border-bottom">
        ${study.subjectName}
      </h3>

      <article class="blog-post">
        <h2 class="blog-post-title">${study.studyTitle}</h2>
        <p class="blog-post-meta">
        <td><fmt:formatDate value="${study.registeredDate}" pattern="yyyy-MM-dd" /></td> by ${study.owner.perNickname}</p>
        <p>${study.introduction}</p>
        <hr>
      </article>

      <article class="blog-post">
        <h3>About</h3>
        <table class="table">
          <thead>
            <tr>
              <th>대면 상태</th>
              <th>지역</th>
              <th>인원수</th>
              <th>비고</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>${study.faceName}</td>
              <td>${study.area}</td>
              <td>${study.numberOfPeple}</td>
              <td>
              <c:if test="${study.studyStatus eq '1'}">진행</c:if>
              <c:if test="${study.studyStatus eq '2'}">종료</c:if>
              </td>
            </tr>
          </tbody>
        </table>
      </article>
    </div>

    <div class="col-md-4">
      <div class="position-sticky" style="top: 2rem;">
        <div class="p-4 mb-3 bg-light rounded">
          <h4 class="fst-italic">
          <c:choose>
		        <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus ne '2'}">모집중</c:when>
		        <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus ne '2'}">모집중</c:when>
		        <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus eq '2'}">모집완료</c:when>
		        <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus eq '2'}">모집완료</c:when>
		      </c:choose>
          </h4>
          <p class="mb-0">
          참여 <ins>${study.waitingCountMember}</ins><sub>명</sub><br>
          승인 대기 <ins>${study.countMember}</ins><sub>명</sub><br>
          북마크 <ins>${study.countBookMember}</ins><sub>명</sub>
          </p>
        </div>
      </div>
    </div>
  </div>
</main>

  </body>
</html>
