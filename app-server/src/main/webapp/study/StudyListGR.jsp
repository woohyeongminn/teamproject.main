<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>목록 | 스터디 찾기</title>
<link rel="stylesheet" type="text/css" href="../header.css">
</head>
<body>
<jsp:include page="../header.jsp"/>
	<h1>📖 스터디 목록</h1>
	<a href='form'>스터디 등록</a>
	<br>
	<table border='1'>
		<thead>
			<tr>
				<th>번호</th>
				<th>북마크</th>
				<th>제목</th>
				<th>대면/비대면</th>
				<th>조장</th>
				<th>분야</th>
				<th>지역</th>
				<th>인원수</th>
				<th>최대 인원수</th>
			</tr>
			</thread>
		<tbody>

			<c:forEach items="${studyList}" var="study">
				<!-- <c:choose>
					<c:when test="${study.studyTitle}.contains(탈퇴)">(${study.studyNo})<br>스터디명 : ${study.studyTitle}</c:when>
					<c:otherwise>${study.studyNo}</c:otherwise>
					<c:choose>
						<c:when test="${study.countMember ne study.numberOfPeple}">[모집중]</c:when>
						<c:otherwise>[모집완료]</c:otherwise>
					</c:choose>
				</c:choose> -->
				<tr>
					<td>${study.studyNo}</td>
					<td>${study.countBookMember}</td>
					<td><a href='detail?no=${study.studyNo}'>${study.studyTitle}</a></td>
					<td>${study.faceName}</td>
					<td>${owner.perNickname}</td>
					<td>${study.subjectName}</td>
					<td>${study.area}</td>
					<td>${study.countMember}</td>
					<td>${study.numberOfPeple}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
