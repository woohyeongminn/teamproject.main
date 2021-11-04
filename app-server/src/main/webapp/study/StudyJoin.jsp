<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>참여 신청 | 스터디 찾기</title>
<link rel="stylesheet" type="text/css" href="../header.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" type="text/css" 
        href="../node_modules/sweetalert2/dist/sweetalert2.css">
</head>
<body>
<jsp:include page="../header.jsp"/>
<!-- <button id="join" type="button">참여 신청</button>
<button id="join" type="button">
  <a href='join?perno=${member.perNo}&studyno=${study.studyNo}'>참여 신청</a>
</button> -->

<c:if test="${study.status eq 2}">
완료된 스터디 입니다.
</c:if>

<c:forEach var="guilder" items="${study.members}">
<c:if test="${guilder.perNo eq member.perNo}">
이미 참여 중인 스터디입니다.
</c:if>
</c:forEach>

<c:forEach var="memberWating" items="${study.watingMember}">
<c:if test="${memberWating.perNo eq member.perNo}">
이미 승인 대기 중인 스터디입니다.
</c:if>
</c:forEach>

<c:if test="${fn:length(study.members) eq study.numberOfPeple}">
참여 가능 인원수를 초과하였습니다.
</c:if>

<script>
  var onlineBtn = document.getElementById("join");
  onlineBtn.addEventListener('click', function() {
      Swal.fire(
              '참여 신청완료',
              '승인이 완료될 때까지 기다려 주세요.',
              'info'
              )
  });
</script>
</body>
</html>
