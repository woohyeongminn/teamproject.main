<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
* {
  font-size: 14px;
}

body {
  height: auto;
}

a {
  text-decoration: none;
}

legend {
  text-align: center;
}

.all-content {
  width: 100%;
  margin: 0 auto;
  height: 700px;
}

.c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
  text-align: center;
  font-size: 16px;
}

ul{list-style:none;}

.tabmenu{ 
  max-width:900px; 
  margin: 0 auto; 
  position:relative; 
}

.tabmenu > ul {
  padding: 0;
}

.tabmenu > ul > li{
  display:  inline-block;
  width:33.33%; 
  float:left;  
  text-align:center; 
  background :#f9f9f9;
}

.tabmenu > ul > li > a{
  display:block;
  line-height:40px;
  height:40px;
  text-decoration:none; 
  color: #000;
}

.tabCon{
  display:none; 
  padding: 20px;
  position:absolute;
  left:0;
  box-sizing: border-box; 
  border : 5px solid #f9f9f9;
  width: 900px;
  height: 620px;
}

.btnCon:target  {
  background : #ccc;
  
  }

.btnCon:target .tabCon{
  display: block;
}

.btnCon:target .tabbtn{
font-weight: bold;
}

.all-content {
	 max-width: 900px;
	 margin: 0 auto;
}

#aside {
  width: 45%;
  height: 100%;
  background-color: lightsteelblue;
  display: inline-block;
}

#content {
	width: 55%;
	margin-left: 20px;
}

.cafe-top {
	display: flex;
	flex-direction: row;
	justify-content: space-between;
	padding: 40px 20px 10px 20px;
	height:250px;
}

.cafe-form {  
  text-align: left;
}

.cafe-form > label {
	width: 120px;
	font-weight: bold;
	padding: 5px 0;
}

.cafe-form > span {
	display: inline-block;
	width: 300px;
	padding: 5px 0;
}

#c-image {
  display: table-cell;
  vertical-align: middle;
  text-align: center;
}

.cafe-bottom {
  width:100%;
  text-align: left;
  padding: 5px 20px;
}

.cafe-bottom > label {
 width: 15%;
 font-weight: bold;
 padding: 5px 0;
}

.cafe-bottom > span {
	width: 80%;
	padding: 5px 0;
}

.cafe-bottom-review {
  width: 100%;
  padding: 10px 20px;
  text-align: left;
}

.line {
	width: 100%;
	height: 4px;
	background: gray;
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
 
input {
	display: inline-block;
	width: 80%;
	line-height: 28px;
	margin: 5px 0;
}
</style>
</head>

<body>
  <br><br><br>
   <div class="all-content"> 
    <div class="tabmenu">
     <ul>
     <li id="tab1" class="btnCon"><a class="tabbtn first" href="../detail">내 프로필</a>
       <div class="tabCon" >
      </div>
     </li>
     <li id="tab2" class="btnCon"><a class="tabbtn" href="cafe/detail">내 카페</a>
       <div class="tabCon" >
         <form action='update' method='post' enctype="multipart/form-data">
				  <div class = "cafe-top">
					  <div id='aside'>
					    <span id='c-image'>대표이미지</span>
					    <!-- <form action="upload.php" method="post" enctype="multipart/form-data">
               <input id="input-file" type="file" multiple="multiple" style='display:none' name='filename[]'/>
               <label id="input-file-button" for="input-file">파일 첨부</label>
               </form> -->
					  </div>
					  <div id='content' class="cafe-form">
					  <h5>[${cafe.name}]</h5>
					  <input id='c-no' type='hidden' name="cafeno" value='${cafe.no}'>
					  <label for='f-bossName'>대표자</label><span>${cafe.ceoMember.ceoBossName}</span><br>
					  <label for='f-licenseNo'>사업자 등록번호</label><span>${cafe.ceoMember.ceoLicenseNo}</span><br>
					  <label for='f-location'>주소</label>
					  <input id='f-location' type='text' name='location' value='${cafe.location}' style="width:280px; white-space:normal;"><br>
					  </div>
				  </div>
				  
				  <div class="cafe-bottom">
				    <label for='f-info'>소개글</label>
				    <input id='f-info' type='text' name='info' value='${cafe.info}' style="height: 100px; white-space:normal; overflow-y:scroll"><br>
				    
				    <label for='f-tel'>전화번호</label>
				    <input id='f-tel' type='tel' name='tel' value='${cafe.phone}' size="auto"><br>
				    
				    <label for='f-openTime'>오픈시간</label>
				    <input id='f-openTime' type='time' name='openTime' value='${cafe.openTime}' size="auto"><br>
				    
				    <label for='f-closeTime'>마감시간</label>
				    <input id='f-closeTime' type='time' name='closeTime' value='${cafe.closeTime}' size="auto"><br>
				    
				    <label for='f-holiday'>이번주 휴무일</label>
				    <input id='f-holiday' type='date' name='holiday' value='${cafe.holiday}' size="auto"><br>
				    <label for='f-viewCount'>상태</label><span>${cafeStatus}</span>
				    
				    <select name="cafeStatus">
					      <c:if test='${cafe.cafeStatus==1}'>
					        <option value="${cafe.cafeStatus}">${cafeStatus}</option>
					      </c:if>
					      
					      <c:if test='${cafe.cafeStatus==2}'>
					        <option value="1" disabled>승인대기</option>
					        <option value="2" name="cafeStatus" >운영중</option>
					        <option value="3" name="cafeStatus" >운영중단</option>
					        <option value="4" disabled>삭제</option>
					      </c:if>
					  </select><br>
				   
				    <label for='f-review'>리뷰평점</label><span>⭐${cafe.avgReview} / ${cafe.countReview}개</span>
				    
				    <div id='button'>
			       <button type="submit" class="btn btn-outline-dark">수정</button>
			       <a href='detail?cafeno=${cafe.no}' class="btn btn-outline-dark">뒤로가기</a>
			      </div>
			   </div>
			 </form>
		  </div>
     </li>
     <li id="tab3" class="btnCon"><a class="tabbtn" href="#tab3">내 문의내역</a>
       <div class="tabCon" >
           <!-- <a href='../askboard/mylist' class = "btn btn-outline-dark">내 문의게시판</a> -->
       </div>
     </li>
   </ul>
  </div>
</div>
<script>
  location.href = "#tab2";
</script>
</body>
</html>