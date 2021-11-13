<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
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
    width: 40%;
    margin: 8px 8px 0 0;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin-bottom: 15px;
}

.cafe-top .cafeImg {
  width: 400px;
  height: 300px;
  background-color: gray;
}

.cafe-bottom {
  width: 55%;
  text-align: left;
  padding: 5px 0;
}

.cafe-bottom > label {
   width: 22%;
   font-weight: bold;
   padding: 5px 0;
}

.cafe-bottom > span {
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
   width: 22%;
   font-weight: bold;
   padding: 5px 0;
}

.label-wrap > span {
  width:80%;
  height:80px;
  padding: 5px 0;
  overflow: scroll;
}

 #input-file-button {
      display: inline-table;
    width: 120px;
    height: 25px;
    line-height: 25px;
    padding: 2px;
    background-color: gray;
    border-radius: 5px;
    color: white;
    font-size: smaller;
    cursor: pointer;
    text-align: center;
    margin-top: 5px;
}

</style>

<body>
  <div class="all-content">
  <br>
  <p>스터디 카페를 등록하고 승인을 기다리세요:)</p>
  <hr>
    <form action='add' method='post' enctype="multipart/form-data">
    <div class="cafe-wrap">
		  <div class = "cafe-top">
		  
		    <!-- 여러개 등록 -->
		      <!-- <form action="upload.php" method="post" enctype="multipart/form-data"> -->
		        <!-- <input id="input-file" type="file" multiple="multiple" style='display:none' name='filename[]'/> -->
	          <div class="cafeImg">	        
		        </div>
		        <input id="input-file" type="file" multiple="multiple" style='display:none' name='photoFile'/>
		        <label id="input-file-button" for="input-file">파일 첨부</label>
		      <!-- </form> -->
		  </div>
	  
		  <!-- 카페 상세 글 부분 -->      
		  <div class="cafe-bottom">
			   <label for='f-bossName'>대표자</label><span>${ceoMember.ceoBossName}</span><br>
			   <label for='f-licenseNo'>사업자 등록번호</label><span>${ceoMember.ceoLicenseNo}</span><br>
			   <label for='f-name'>상호명</label>
			   <input id='f-name' type='text' name='name'><br>
			   <div class="label-wrap">
				   <label for='f-location'>주소</label>
				   <input id='f-location' type='text' name='location'>
			   </div>
		    
			   <div class="label-wrap">
			     <label for='f-info'>소개글</label>
			     <input id='f-info' type='text' name='info'>
			   </div>
			   <label for='f-tel'>전화번호</label><input id='f-tel' type='tel' name='phone'><br>
			   <label for='f-openTime'>운영시간</label><input id='f-openTime' type='time' name='inputOpenTime'> ~ <input id='f-closeTime' type='time' name='inputCloseTime'><br>
			   <label for='f-holiday'>이번주 휴무일</label><input id='f-holiday' type='date' name='holiday'><br>
			   <label for='f-viewCount'>상태</label>
			   <select name="cafeStatus">       
			     <option value="1" name="cafeStatus" selected>승인대기</option>
			     <option value="2" disabled>운영중</option>
			     <option value="3" disabled>운영중단</option>
			     <option value="4" disabled>삭제</option>
			   </select>
		  </div>
    </div>
    
    <div id='button'>
     <button id='b-btn' type="submit" value="등록" class="btn btn-outline-dark">등록</button>
    </div>
    
  </form>
</body>