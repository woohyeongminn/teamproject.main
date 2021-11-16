<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
#search {
  text-align:center;
}
#empty-study {
  text-align: center;
}
</style>
</head>
<body>
  <br>
  <br>
  <!-- 검색 -->
  <div id="search">
    <form action="search" method='get'>
    <select name="where">
      <option value="1">분야</option>
      <option value="2">제목</option>
      <option value="3">지역</option>
    </select>
    <input type="text" name="keyword">
    <button class="btn btn-outline-dark btn-sm">검색</button>
    </form>
  </div>
  <br>
  <br>
  <!-- 스터디 목록 -->
  <c:if test='${not empty studyList}'>
  <div id="content">
    <div class="row row-cols-1 row-cols-md-3 g-5">
    <c:forEach items="${studyList}" var="study">
    <div class="col">
    <div class="card">
      <div class="card-header">
        <c:choose>
        <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus ne '2'}">
          <button type="button" class="btn btn-primary btn-sm">모집중</button>
        </c:when>
        <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus ne '2'}">
          <button type="button" class="btn btn-primary btn-sm">모집중</button>
        </c:when>
        <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus eq '2'}">
          <button type="button" class="btn btn-secondary btn-sm">모집완료</button>
        </c:when>
        <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus eq '2'}">
          <button type="button" class="btn btn-secondary btn-sm">모집완료</button>
        </c:when>
      </c:choose>
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
<div id="empty-study">
<c:if test='${empty studyList}'>
  검색 결과가 존재하지 않습니다.<br><br>
</c:if>
</div>
<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
  <jsp:include page="AdminMenu.jsp"/>
</div>
</body>
</html>
  <!-- <table class="table table-hover">
    <thead>
      <tr>
        <th>번호</th>
        <th>북마크</th>
        <th data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">제목</th>
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
        <tr>
          <td>${study.studyNo}</td>
          <td>${study.countBookMember}</td>
          <td><a href='detail?studyno=${study.studyNo}'>${study.studyTitle}</a></td>
          <td>${study.faceName}</td>
          <td>${study.owner.perNickname}</td>
          <td>${study.subjectName}</td>
          <td>${study.area}</td>
          <td>${study.countMember}</td>
          <td>${study.numberOfPeple}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table> -->
