<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <title>공지게시판</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  <style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  </style>
</head>
<body>
<!-- <h1> ▶ 공지 목록 </h1> -->
<fieldset>
<legend ><b> 🔔 공지게시글 목록 </b></legend>
<hr>
<table class="table table-responsive">
<thead>
<tr>
<th>번호</th>
<th>제목</th>
<th>내용</th>
<th>파일</th>
<th>등록일</th>
</tr>
</thead>
<tbody>
<!--<caption>공지사항입니다.</caption>-->
<c:forEach items="${adminNoticeList}" var="noticeList">
<tr>
	<td>( ${noticeList.adminNotiNo} )</td>
	<td><a href='detail?no=${noticeList.adminNotiNo}'>${noticeList.adminNotiTitle}</a></td>
	<td>${noticeList.adminNotiContent}</td>
	<td>${noticeList.adminNotiFile}</td>
	<td>${noticeList.adminNotiRegisteredDate}</td>
</tr>
</c:forEach>
</tbody>
</table>
</fieldset>
</body>
</html>