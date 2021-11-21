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
  width: 60%;
  margin: 12px auto;
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
  <p>스터디 카페를 등록하고 승인을 기다리세요:)</p>
  <hr>
    <form action='add' name='cafeInfo' method='post' enctype="multipart/form-data" onsubmit="return checkValue()">
    <div class="cafe-wrap">
      <div class = "cafe-top">
      
      <!-- 여러개 등록 -->
      <div class="cafeImg"></div><br>
      
      <input id="input_file" type="file" name="photoFileList" multiple>
      <!-- <span style="font-size:10px; color: gray; text-align:left; ">※첨부파일은 최대 10개까지 등록이 가능합니다.</span>
      
      <div class="data_file_txt" id="data_file_txt" style="margin:40px;">
		    <span>첨부 파일 :</span>
		    <br>
		    <div id="articlefileChange">
		    </div>
		  </div> -->
    </div>
    
      <!-- 카페 상세 글 부분 -->      
      <div class="cafe-bottom">
         <label for='f-bossName'>대표자</label><span>${ceoMember.ceoBossName}</span><br>
         <label for='f-licenseNo'>사업자 등록번호</label><span>${ceoMember.ceoLicenseNo}</span><br>
         
         <label for='f-name'>상호명</label>
         <input id='f-name' type='text' name='name' class="form-control"><br>
         
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
						    <input class="form-control" placeholder="도로명 주소" name="addr2" id="addr2" type="text" readonly/>
						    <br>
						    <input class="form-control" placeholder="상세주소" name="addr3" id="addr3" type="text"/>
					  </div>
          </div>
         
         <br>
         <div class="label-wrap">
           <label for='f-info'>소개글</label>
           <input id='f-info' type='text' name='info' class="form-control" style="width:54%; margin-left: 5px; height: 100px;">
         </div>

         <br>
         <label for='f-tel'>전화번호</label>
         <input id='f-tel' type='text' name='tel1' pattern="[0-9]+" minlength='3' maxlength='3' class="form-control" style="width:55px;"/> -
		     <input id='f-tel' type='text' name='tel2' pattern="[0-9]+" minlength='3' maxlength='4' class="form-control" style="width:55px;"/> -
		     <input id='f-tel' type='text' name='tel3' pattern="[0-9]+" minlength='3' maxlength='4' class="form-control" style="width:55px;"/> <br>

         <label for='f-openTime'>운영시간</label>
         <input id='f-openTime' type='time' name='inputOpenTime' class="form-control" style="width: 125px;">
          ~ 
         <input id='f-closeTime' type='time' name='inputCloseTime' class="form-control"  style="width: 125px;"><br>
         
         <label for='f-holiday'>이번주 휴무일</label>
         <input id='f-holiday' type='date' name='holiday' class="form-control"><br>
         
         <label for='f-viewCount'>상태</label>
         <select name="cafeStatus" class="form-control">       
           <option value="1" name="cafeStatus" selected>승인대기</option>
           <option value="2" disabled>운영중</option>
           <option value="3" disabled>운영중단</option>
           <option value="4" disabled>삭제</option>
         </select>
         
         <div id='button'>
			     <button id='b-btn' value="등록" class="btn btn-outline-dark addBtn">등록</button>
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




<script type="text/javascript">
function checkValue() {
  
  var form = document.cafeInfo;
  
  if(!form.name.value){
        alert("상호명을 입력하세요.");
        return false;
   }
  
  if(!form.addr1.value && !form.addr2.value && !form.addr3.value){
        alert("주소를 입력하세요.");
        return false;
    }
  
    if(!form.info.value){
        alert("소개글을 입력하세요.");
        return false;
    }
    
    if(!form.tel1.value && !form.tel2.value && !form.tel3.value){
        alert("전화번호를 입력하세요.");
        return false;
    }

    if(!form.inputOpenTime.value && !form.inputCloseTime.value){
        alert("운영시간을 입력하세요.");
        return false;
     }    
   
}
</script>