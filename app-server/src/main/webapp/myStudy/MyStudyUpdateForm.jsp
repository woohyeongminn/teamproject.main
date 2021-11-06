<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>상세 | 내 스터디</title>
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
  <h1>📖 내 스터디 수정</h1>
  <form action='update'>
  <input type="hidden" name="studyno" value="${study.studyNo}">
        <label for='f-studyTitle'>제목</label> <input id='f-studyTitle' type='text' name='studytitle'><br>
        <label for='f-numberOfPeple'>최대 인원수</label> <input id='f-numberOfPeple' type='text' name='numberofpeple'><br>
        <p>
        1. 대면<br>
        2. 비대면<br>
        3. 대면/비대면<br>
        </p>
        <label for='f-faceNo'>대면 상태</label> <input id='f-faceNo' type='text' name='faceno'><br>
        <label for='f-introduction'>소개글</label> <input id='f-introduction' type='text' name='introduction'><br>
    <button type="submit" value="수정">수정</button><br>
  </form>
</body>
</html>
