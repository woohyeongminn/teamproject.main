<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<style>
* {
  font-size: 14px;
}
  
 a {
   text-decoration:none;
}

.template-content {
    height: 900px;
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
  margin-bottom: 15px;
}


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

<body>
<br><br><br>
<div class="all-content">
<form action='updateform' method='post' enctype="multipart/form-data">
  <div class="cafe-wrap">
    <div class="cafe-top">
      <c:choose>
         <c:when test="${not empty cafeRoom.roomImg}">
           <a href='detail?roomno=${cafeRoom.roomNo}'>
             <img class="card-img-top" src="${contextPath}/upload/cafe/${cafeRoom.roomImg}_450x300.jpg" alt="..." />
           </a>
         </c:when>
         <c:otherwise>
             <div style="width: 450px; height: 300px; background-color: lightgray;"></div><br>
             <span>등록된 스터디룸 사진이 없습니다.</span>
         </c:otherwise>
       </c:choose>
       <br>
       <input id="input-roomFile" type="file" name='photoFile'/>
    </div>
    
    <!-- 룸 상세 글 부분 -->      
     <div class="cafe-bottom">
     
     
      <input type ='hidden' name='cafeno' value='${cafeRoom.cafe.no}'>
      <input type ='hidden' name='roomNo' value='${cafeRoom.roomNo}'>
      
      <label for='f-roomInfo'>룸 이름</label><br>
      <input id='f-roomName' type='text' name='roomName' value='${cafeRoom.roomName}' placeholder="${cafeRoom.roomName}" class="form-control"><br>
      
      <label for='f-roomInfo'>소개글</label><br>
      <input id='f-roomInfo' type='text' name='roomInfo'  value='${cafeRoom.roomInfo}' style="height: 200px;" class="form-control"><br>
      
      <label for='f-people'>인원</label>
      <input id='f-roomPeople' type="number" pattern="\d*" name='people'  value='${cafeRoom.people}' class="form-control"><br>
      
      <label for='f-roomPrice'>시간당금액</label>
      <input id='f-roomPrice' type='tel' pattern="\d*"  name='roomPrice'  value='${cafeRoom.roomPrice}'  class="form-control"><br>

     </div>
    
  </div>
  
  <!-- 버튼 -->
  <div id='button_wrap'>
    <button type="submit" value="수정" formaction="update" class="btn btn-outline-dark">수정</button>
     <a href="detail?roomno=${cafeRoom.roomNo}" class="btn btn-outline-dark">뒤로가기</a>
  </div>
  </form>
 </div>
</body>
