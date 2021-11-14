<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

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
    <h1 class="mt-5">${freeBoard.freeBoardTitle}</h1>
      <span>${freeBoard.freeBoardWriter.perNickname}</span>
      <td><fmt:formatDate value="${freeBoard.freeBoardRegisteredDate}" pattern="yyyy.MM.dd" /></td>
    <p class="lead">${freeBoard.freeBoardContent}</p>
  </div>
</main>

<!-- 버튼 -->
<footer class="footer mt-auto py-3 bg-light">
  <div class="container">
    <div class="btn-group" role="group" aria-label="Basic outlined example">
    <button class="btn btn-outline-light">
      <a href='list?studyno=${freeBoard.studyNo}'>목록</a>
    </button>
    <button class="btn btn-outline-light">
      <a href='updateform?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}'>수정</a>
    </button>
    <button class="btn btn-outline-light">
      <a href='delete?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}'>삭제</a>
    </button>
    </div>
  </div>
</footer><br>

<!-- 댓글 -->
<div class="card">
  <div class="card-body">
  <span>댓글</span>
  <form action='/ogong/freeboard/comment/add' method='post'>
    <input type='hidden' name='studyno' value='${freeBoard.studyNo}'/>
    <input type='hidden' name='freeboardno' value='${freeBoard.freeBoardNo}'/>
    <textarea id='f-commentText' type="text" name='commenttext' class="form-control"></textarea>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button class="btn btn btn-light btn-sm">등록</button>
    </div>
  </form>
  </div>
</div>
<br>
  <c:if test="${empty commentList}">등록된 댓글이 없습니다.</c:if>
  <c:forEach items="${commentList}" var="comment">
  <div class="card">
  <div class="card-body">
    <span style="font-weight: bold">${comment.commentWriter.perNickname}</span><br>
    <span>${comment.commentText}</span><br>
    <span>${comment.commentRegisteredDate}</span>
    <div class="btn-group" role="group" aria-label="Basic outlined example">
    <button class="btn btn-link">
      <a href='comment/updateform?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}&commentno=${comment.commentNo}'>수정</a>
    </button>
    <button class="btn btn-link">
      <a href='comment/delete?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}&commentno=${comment.commentNo}'>삭제</a>
    </button>
    </div>
    </div>
    </div>
  </c:forEach>
  </body>
</html>
