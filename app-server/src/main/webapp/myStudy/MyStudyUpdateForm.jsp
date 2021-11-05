<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>수정 | 내 스터디</title>
<link rel="stylesheet" type="text/css" href="../header.css">
<style>
label {
  margin-right: 5px;
  text-align: right;
  display: inline-block;
  width: 60px;
}
</style>
</head>
<body>
<jsp:include page="../header.jsp"/>
  <h1>🖊 내 스터디 상세</h1>
  <form action='update'>
    <span>번호 </span><span>${study.studyNo}</span><br>
    
    <span>북마크 </span><span>${study.countBookMember}</span><br>
    
    <label for='f-studyTitle'>제목</label>
    <input id='f-studyTitle' type='text' name='studyTitle' value='${study.studyTitle}'><br>
    
    <span>조장 </span><span>${study.owner.perNickname}</span><br>
    
    <span>분야 </span><span>${study.subjectName}</span><br>
    
    <span>지역 </span><span>${study.area}</span><br>
    
    <span>인원수 </span><span>${study.countMember}</span><br>
    
    <label for='f-numberOfPeple'>최대 인원수</label>
    <input id='f-numberOfPeple' type='text' name='numberOfPeple' value='${study.numberOfPeple}'><br>
    
    <p>
    [ 대면 상태 ]<br>
    1. 대면<br>
    2. 비대면<br>
    3. 대면/비대면<br>
    </p>
    <label for='f-faceNo'>대면 상태</label> <input id='f-faceNo' type='text' name='faceNo'><br>
    
    <!-- <label for='f-faceName'>대면 상태</label>
    <input id='f-faceName' type='text' name='faceName' value='${study.faceName}'><br> -->
    
    <label for='f-introduction'>소개글</label>
    <input id='f-introduction' type='text' name='introduction' value='${study.introduction}'><br>
    
    <span>활동 점수 </span><span>${study.point}</span><br>
    
    <input type ='hidden' name='perno' value='${member.perNo}'>
    <input type ='hidden' name='studyno' value='${study.studyNo}'>
    
    <c:if test="${study.owner.perNo eq member.perNo}">
      <button type="submit" value="수정">수정</button>
    </c:if>
    <br>
  </form>
</body>
</html>
