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
  </style>
</head>

<body>
  <input id='c-no' type='hidden' value='${cafe.no}'><br>
  <b>👩‍🏫 내 스터디카페 상세</b>
  <hr>
  <h5>[${cafeRoom.roomName}]</h5>
  <div id='aside'>
    <span id='c-image'>대표이미지</span>
  </div>
  <form action='updateform'>
  <div id='content'>
    <label for='f-image'>대표이미지</label><span>${cafeRoom.roomImg}</span><br>
    <label for='f-roomInfo'>소개글</label><span>${cafeRoom.roomInfo}</span><br>
    <label for='f-people'>인원</label><span>${cafeRoom.people}</span><br>
    <label for='f-roomPrice'>시간당금액</label><span>${cafeRoom.roomPrice}</span><br>
  </div>
   <div id='button'>
	   <button id='b-but' type="submit" value="수정" >
	      <a href='updateform?no=${cafeRoom.roomNo}&cafeno=${cafeRoom.cafe.no}'> 스터디룸 수정</a>
	   </button>
	   <button id='b-but' type="submit" value="삭제" >
	      <a href='delete?no=${cafeRoom.roomNo}&cafeno=${cafeRoom.cafe.no}'>스터디룸 삭제</a>
	    </button>
    </div>
    </form>
</body>
</html>