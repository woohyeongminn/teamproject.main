<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
* {
  margin:0;
  padding:0;
  font-size:14px;
  line-height:1.5;
}
.template-content {
  height: 1500px;
  overflow: scroll;
}
.card-body {
  flex: 1 1 auto;
  padding: 1rem 1rem;
  height: 160px;
}
#empty-bookmark {
  text-align: center;
}
</style>
</head>
<body>
<br>
<br>
<c:if test='${not empty studyList}'>
<div id="content">
<input type="hidden" name="loginUser" value="${loginUser.perNo}">
  <div class="row row-cols-1 row-cols-md-3 g-5">
  <c:forEach items="${studyList}" var="study">
  <div class="col">
  <div class="card">
    <div class="card-header">
      ${study.subjectName}
    </div>
    <div class="card-body">
      <h5 class="card-title" style="font-weight: bold">
        <a href='detail?studyno=${study.studyNo}'>${study.studyTitle}</a>
      </h5>
      <p class="card-text">${study.introduction}</p>
      ${study.faceName}<br>
      인원 ${study.countMember}/${study.numberOfPeple}<br>
      ${study.owner.perNickname}
      ⭐${study.countBookMember}
    </div>
  </div>
  </div>
 </c:forEach>
 </div>
 </div>
</c:if>
<div id="empty-bookmark">
<c:if test='${empty studyList}'>
   북마크한 스터디가 없습니다.
</c:if>
</div>
</body>
</html>
<!-- <table class="table table-hover">
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
					<td><a href='detail?studyno=${study.studyNo}'>${study.studyTitle}</a></td>
					<td>${study.subjectName}</td>
					<td>${study.countMember}</td>
					<td>${study.numberOfPeple}</td>
					<td>${study.owner.perNickname}</td>
					<td>${study.faceName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table> -->
