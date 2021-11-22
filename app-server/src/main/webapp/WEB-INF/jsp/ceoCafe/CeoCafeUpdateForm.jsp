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

.inner-page {
  height: 1300px;
  text-align: center;
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
  flex-direction: column;
  justify-content: space-between;
}

.cafe-top {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin: 0 auto;
    margin-bottom: 15px;
    width: 60%;
}

.cafe-top .cafeImg {
  width: 600px;
  height: 250px;
  background-color: gray;
}

.cafe-bottom {
  text-align: left;
  padding: 5px 0;
  width: 55%;
  margin: 0 auto;
  margin-right: 178px;
}

.cafe-bottom > label {
   width: 22%;
   font-weight: bold;
   padding: 5px 0;
   margin-bottom: 7px;
}

.cafe-bottom > span {
  width: 80%;
  padding: 5px 0;
}

.label-wrap {
  height: fit-content;
  display: flex;
}

.label-wrap > label {
   width: 22%;
   font-weight: bold;
   padding: 5px 0;
   margin-bottom: 7px;
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

.form-control {
  display: inline-block;
  width: 325px;
}

.addBtn {
  width: 78%;
    height: 44px;
    margin-top: 28px;
    margin-left: 2px;
}
</style>

<body>
  <div class="all-content">
  <br>
  <h4 style="text-align: center;">[${cafe.name}]</h4>
  <hr>
    <form action='update' name='cafeInfo' method='post' enctype="multipart/form-data">
    <input type="hidden" name="name" value="${cafe.name}"/>
    <div class="cafe-wrap">
      <div class = "cafe-top">
      
      <c:choose>
          <c:when test="${empty cafe.cafeImgs}">
            <div style="width: 488px; height: 300px; margin-bottom:10px; margin: 0 auto; background-color: lightgray"></div>
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
      
       <br>
       <input id="input_file" type="file" name="photoFileList" multiple style="margin: 0 auto; margin-left: 205px;">
      
    </div>
    
      <!-- 카페 상세 글 부분 -->      
      <div class="cafe-bottom">
         <label for='f-bossName'>대표자</label><span>${cafe.ceoMember.ceoBossName}</span><br>
         <label for='f-licenseNo'>사업자 등록번호</label><span>${cafe.ceoMember.ceoLicenseNo}</span><br>
         
         <!-- <label for='f-name'>상호명</label>
         <input id='f-name' type='text' name='name' class="form-control"><br> -->
         
         <br>
         <div class="label-wrap">
            <label for='f-location'>주소</label>
            <button type="button" class="btn btn-default" style="padding: 5px 10px; border: 1px solid; margin-left: 5px;"
             onclick="execPostCode();"><i class="fa fa-search"></i> 우편번호 찾기</button>                               
         </div>
         
         <div class="label-wrap">
           <label> </label>
            <div class="form-group" style="margin-left: 5px;">                   
                <input class="form-control" placeholder="우편번호" name="addr1" id="addr1" type="text" readonly>
                <br>
                <input class="form-control" placeholder="도로명 주소" name="addr2" id="addr2" type="text" readonly value="${addr1}"/>
                <br>
                <input class="form-control" placeholder="상세주소" name="addr3" id="addr3" type="text" value="${addr2}"/>
            </div>
          </div>
         
         <br>
         <div class="label-wrap">
           <label for='f-info'>소개글</label>
           <input id='f-info' type='text' name='info' class="form-control" value='${cafe.info}'
           style="width:54%; margin-left: 5px; height: 100px; overflow-y:scroll;">
         </div>

         <br>
         <label for='f-tel'>전화번호</label>
         <input id='f-tel' type='text' name='tel1' pattern="[0-9]+" minlength='3' maxlength='3' class="form-control" style="width:55px;" value="${tel1}"/> -
         <input id='f-tel' type='text' name='tel2' pattern="[0-9]+" minlength='4' maxlength='4' class="form-control" style="width:55px;" value="${tel2}"/> -
         <input id='f-tel' type='text' name='tel3' pattern="[0-9]+" minlength='4' maxlength='4' class="form-control" style="width:55px;" value="${tel3}"/> <br>

         <label for='f-openTime'>운영시간</label>
         <input id='f-openTime' type='time' name='inputOpenTime' class="form-control" style="width: 125px;" value='${cafe.openTime}'>
          ~ 
         <input id='f-closeTime' type='time' name='inputCloseTime' class="form-control"  style="width: 125px;" value='${cafe.closeTime}'><br>
         
         <label for='f-holiday'>휴무일</label>
         <input id='f-holiday' type='date' name='holiday' class="form-control" value='${cafe.holiday}'><br>

         <label for='f-viewCount'>상태</label>
         <select name="cafeStatus" class="form-control">
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

        <br>
        <div id='button-wrap'>
	       <button id='b-btn' type="submit" value="수정" class="btn btn-outline-dark" style="width: 41%;">수정</button>
	       <a href="detail" class="btn btn-outline-dark" style="width: 41%;">취소</a>
        </div>
      
      </div>
    </div>
  </form>
</div>
</body>

<!-- 다음 주소 검색 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
<script src='${contextPath}/node_modules/jquery/dist/jquery.js'></script>
<script src="${contextPath}/node_modules/blueimp-file-upload/js/vendor/jquery.ui.widget.js"></script>
<script src="${contextPath}/node_modules/blueimp-file-upload/js/jquery.iframe-transport.js"></script>
<script src="${contextPath}/node_modules/blueimp-file-upload/js/jquery.fileupload.js"></script>

<script type="text/javascript">
function execPostCode() {
    new daum.Postcode({
        oncomplete: function(data) {
           // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

           // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
           // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
           var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
           var extraRoadAddr = ''; // 도로명 조합형 주소 변수

           // 법정동명이 있을 경우 추가한다. (법정리는 제외)
           // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
           if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
               extraRoadAddr += data.bname;
           }
           // 건물명이 있고, 공동주택일 경우 추가한다.
           if(data.buildingName !== '' && data.apartment === 'Y'){
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
           }
           // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
           if(extraRoadAddr !== ''){
               extraRoadAddr = ' (' + extraRoadAddr + ')';
           }
           // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
           if(fullRoadAddr !== ''){
               fullRoadAddr += extraRoadAddr;
           }

           // 우편번호와 주소 정보를 해당 필드에 넣는다.
           console.log(data.zonecode);
           console.log(fullRoadAddr);
           
           document.getElementById('addr1').value = data.zonecode; //5자리 새우편번호 사용
           document.getElementById('addr2').value = fullRoadAddr;
       }
    }).open();
}

</script>