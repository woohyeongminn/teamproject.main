<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<style>
* {
  font-size: 14px;
}
  
 a {
   text-decoration:none;
}

.template-content {
    height: 1100px;
}

 .all-content {
  width: 1000px;
  margin: 0 auto;
  height: 800px;
}

ul {
  list-style:none;
}

.cafe-wrap {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.cafe-top {
  width: 50%;
   margin: 8px 8px 0 0;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-bottom: 15px;
}

    .slide{height:300px;overflow:hidden;}
    .slide li:nth-child(1){display:inline-block; background:#ffa; width: 100%; height:300px;}



.cafe-bottom {
      width: 50%;
    text-align: left;
    padding: 5px 0;
    margin-top: 30px;
    margin-left: 15px;
}

.cafe-bottom label {
       width: 135px;
   font-weight: bold;
   padding: 5px 0;
}

.cafe-bottom span {
  width: 80%;
  padding: 5px 0;
}

.label-wrap {
  width: 100%;
  height: fit-content;
  display: flex;
    flex-direction: row;
}

.label-wrap > label {
   width: 135px;
   font-weight: bold;
   padding: 5px 0;
}

.label-wrap > span {
  width:80%;
  height:80px;
  padding: 5px 0;
  overflow: scroll;
}

.cafe-bottom-review {
  width: 100%;
  padding: 0 10px 30px 0px;
  text-align: left;
}
 
.line {
   width: 100%;
   height: 4px;
   background: gray;
}

.review-wrap {
  width: 830px;
  height: 180px;
  overflow: scroll;
}

#c-review-content {
  margin: 0;
}
  
#c-review {
  background-color: whitesmoke;
  height: fit-content;
  margin-bottom: 10px;
  padding: 10px;
}
  
button {
  border: 0;
  background: transparent;
}

.btn_wrap {
  max-width: 420px;
  margin: 20px auto 0;
  text-align: center;
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin-bottom: 100px;
}
 
.btn_wrap .btn {
  margin: 0 7px;
  padding: 5px 10px;
  height: auto;
  line-height: inherit;
}

</style>
</head>

<body>
<br><br><br>
<div class="all-content">
  <div class="cafe-wrap">
    <div class="cafe-top">
      <div class="slide">
          <ul>
            <li><img src="../../upload/cafe/${cafe.cafeImgs[0].name}" style="width:100%"></li>
          </ul>
      </div>
    </div>
    
    <!-- 룸 상세 글 부분 -->      
     <div class="cafe-bottom">
     <form action='updateform' method='post' enctype="multipart/form-data">
      <input id='c-no' type='hidden' value='${cafe.no}'>
      <h4 style="text-align: center; margin-bottom: 30px;">[${cafeRoom.roomName}]</h4>
      <div class="label-wrap">
        <label for='f-roomInfo'>소개글</label>
         <span style="height: fit-content;">${cafeRoom.roomInfo}</span>
      </div>
      <label for='f-people'>인원</label>
      <span>${cafeRoom.people}</span><br>
      <label for='f-roomPrice'>시간당금액</label>
      <span>${cafeRoom.roomPrice}</span><br>

    </form>
     </div>
  </div>
  
  <!-- 버튼 -->
  <div id='button_wrap'>
    <button id='b-but' type="submit" value="수정" formaction="updateform">
        <a href='updateform?roomno=${cafeRoom.roomNo}' class="btn btn-outline-dark"> 스터디룸 수정</a>
    </button>
    
    <button id='b-but' type="submit" value="삭제" formaction="deleteform">
        <a href='delete?roomno=${cafeRoom.roomNo}&cafeno=${cafeRoom.cafe.no}' class="btn btn-outline-dark">스터디룸 삭제</a>
    </button>
      <button id='b-but' type="submit" value="삭제" formaction="deleteform">
      <a href='list?cafeno=${cafeRoom.cafe.no}' class="btn btn-outline-dark">목록</a>
    </button>
    </div>
 </div>
</body>
</html>