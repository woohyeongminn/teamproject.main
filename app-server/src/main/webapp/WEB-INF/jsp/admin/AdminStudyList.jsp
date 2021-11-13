<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
* {
  font-size:14px;
}
</style>
</head>
<body>
  <br>
  <table class="table table-hover">
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
  </table>
<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
  <jsp:include page="AdminMenu.jsp"/>
</div>
</body>
</html>
