<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 스터디카페 룸 상세</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
* {
  font-size: 14px;
}

a {
 text-decoration:none;
}

label {
  display: inline-block;
  margin-right: 5px;
  width: 130px;
}

#aside {
   width: 120px;
   height: 171px;
   float: left;
   background-color: lightsteelblue;
   display: table;
}

#content {
   margin-left: 130px;
}

#c-image {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
}

#c-grade {
   margin-left: 41px;
   vertical-align: 4px;
}

#c-review {
  width: 427px;
  background-color: whitesmoke;
  height: 80px;
  margin-bottom: 10px;
}

.all-content {
  width: 100%;
  margin: 0 auto;
  padding: 40px;
  margin-top:50px;
}

.c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
  text-align: center;
}
</style>
</head>

<body>
<div class="all-content">
  <h5>[${cafeRoom.roomName}]</h5>
  <div id='aside'>
    <span id='c-image'>대표이미지</span>
  </div>
  <form action='updateform'>
  <div id='content'>
	  <c:if test="${cafeRoom.roomStatus == 1}">
	  <p>운영중</p>
	  </c:if>
	  <c:if test="${cafeRoom.roomStatus == 2}">
	  <p style="color:gray">운영중단</p>
	  </c:if>
    <label for='f-image'>대표이미지</label><span>${cafeRoom.roomImg}</span><br>
    <label for='f-roomInfo'>소개글</label><span>${cafeRoom.roomInfo}</span><br>
    <label for='f-people'>인원</label><span>${cafeRoom.people}</span><br>
    <label for='f-roomPrice'>시간당금액</label><span>${cafeRoom.roomPrice}</span><br>
  </div>
   <div id='button'>
	      <a href='updateform?roomno=${cafeRoom.roomNo}' class="btn btn-outline-dark"> 스터디룸 수정</a>
	      <a href='delete?roomno=${cafeRoom.roomNo}&cafeno=${cafeRoom.cafe.no}' class="btn btn-outline-dark">스터디룸 삭제</a>
        <a href='list?cafeno=${cafeRoom.cafe.no}' class="btn btn-outline-dark">목록</a>
    </div>
    </form>
</div>
</body>
</html>