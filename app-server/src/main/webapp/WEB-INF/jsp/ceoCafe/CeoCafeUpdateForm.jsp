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
    height: 370px;
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
  margin-top: 25px;
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

.cafe-bottom > input {
  width: 250px;
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

.label-wrap > input {
  width: 250px;
  box-sizing: border-box;
  margin-left: 5px;
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


.btn {
    padding: 5px 77px;
    margin-top: 32px;
    margin-right: 10px;
}

</style>

<body>
  <div class="all-content">
  <br><br>
    <div class="cafe-wrap">
      <div class="cafe-top">
      <h4 style="text-align: center;">[${cafe.name}]</h4>
      
        <c:choose>
	        <c:when test="${empty cafe.cafeImgs}">
	          <div style="width: 488px; height: 300px; margin-bottom:10px; background-color: lightgray"></div>
	          <span >등록된 카페 이미지가 없습니다.</span>
	        </c:when>
	        
	        <c:otherwise>
	          <div class="slide">
	            <ul>
	           <c:forEach items="${cafe.cafeImgs}" var="cafeImg">
	              <li><img src="${contextPath}/upload/cafe/${cafeImg.name}_488x300.jpg" style="width:100%"></li>
	           </c:forEach>
	           </ul>
	        </div>
	        </c:otherwise>
       </c:choose>
       <input id="input_file" type="file" name="photoFileList" multiple value="${cafe.cafeImgs}">
      </div>
    
      <!-- 카페 상세 글 부분 -->      
      <form action='update' class="cafe-bottom" method='post' enctype="multipart/form-data">
         <input id='c-no' type='hidden' name="no" value='${cafe.no}'>
         <label for='f-bossName'>대표자</label><span>${cafe.ceoMember.ceoBossName}</span><br>
         <label for='f-licenseNo'>사업자 등록번호</label><span>${cafe.ceoMember.ceoLicenseNo}</span><br>
         
        <%--  <label for='f-name'>상호명</label>
         <input id='f-name' type='text' name='name' value='${cafe.name}' readonly="readonly"><br> --%>
         
         <div class="label-wrap">
           <label for='f-location'>주소</label>
           <input id='f-location' type='text' name='location' value='${cafe.location}' style='white-space:normal;'><br>
         </div>
        
         <div class="label-wrap">
           <label for='f-info'>소개글</label>
           <input id='f-info' type='text' name='info' value='${cafe.info}' style="height:100px; white-space:normal; overflow-y:scroll"><br>
         </div>
         
         <label for='f-tel'>전화번호</label>
         <input id='f-tel' type='tel' name='tel' value='${cafe.phone}'><br>
         
         <label for='f-openTime'>운영시간</label>
         <input id='f-openTime' type='time' name='inputOpenTime' value='${cafe.openTime}' style="width: 105px"> ~ <input id='f-closeTime' type='time' name='inputCloseTime' value='${cafe.closeTime}'  style="width: 105px"><br>
         
         <label for='f-holiday'>휴무일</label>
         <input id='f-holiday' type='date' name='holiday' value='${cafe.holiday}' ><br>
         
         <label for='f-viewCount'>상태</label>
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
    
	    <div id='button-wrap'>
	     <button id='b-btn' type="submit" value="수정" class="btn btn-outline-dark">수정</button>
	     <a href="detail" class="btn btn-outline-dark">취소</a>
	    </div>
   </form>
   </div>
  </div>
</body>