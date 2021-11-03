<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>목록 | 내 북마크</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
	<h1>🌟 내 북마크 목록</h1>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>북마크</th>
				<th>제목</th>
				<th>분야</th>
				<th>인원수</th>
				<th>최대 인원수</th>
				<th>조장</th>
				<th>대면/비대면</th>
			</tr>
				</thread>
		<tbody>

			<c:forEach items="${studyList}" var="study">
				<tr>
					<td>${study.studyNo}</td>
					<td>${study.countBookMember}</td>
					<td><a href='detail?perno=${member.perNo}&studyno=${study.studyNo}'>${study.studyTitle}</a></td>
					<td>${study.subjectName}</td>
					<td>${study.countMember}</td>
					<td>${study.numberOfPeple}</td>
					<td>${study.owner.perNickname}</td>
					<td>${study.faceName}</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</body>
</html>
