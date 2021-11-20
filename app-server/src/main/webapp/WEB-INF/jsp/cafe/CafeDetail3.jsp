<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<style>
	*{
	 font-size: 13.5px;
	}
	h3 {
    font-weight: bolder;
  }
	a {
	 text-decoration:none;
	}
  .pt-4 {
    height: auto !important;
  }
  .c-content {
    width: 100%;
    max-width: 720px;
    margin: 0 auto;
    box-sizing: border-box;
    xborder: 1px solid black;
  }
  .c-body {
    padding: 26px 20px 28px;
    text-align: center;
    xborder: 1px solid gray;
  }
  .c-photo {
    height: 264px;
    background-size: cover;
    background-position: 50% 50%;
  }
  .c-place-section {
    padding: 16px 18px 2px;
  }
  .c-title {
    font-size: 1.2rem;
    font-weight: 700;
    letter-spacing: -1px;
  }
  .c-place-grade {
    margin-top: 3px;
  }
  .c-grade {
    font-size: 1.2rem;
  }
  .c-place-info-section {
    margin-top: 7px;
    border-top: 12px solid #9ba4a914;
    text-align: left;
  }
  .c-place-info>li+li {
    border-top: 1px solid;
    border-color: #ecf0f2;
  }
  .c-place-info {
    padding: 4px 18px 4px;
  }
  ul {
    list-style: none;
    margin-bottom: 0rem;
  }
  .c-info-list {
    padding: 10px 0;
    xborder: 1px solid black;
  }
  .c-icon {
    float: left;
    margin-right: 10px;
    xborder: 1px solid red;
  }
  .c-info-icon {
    fill: #9e9e9e;
    display: inline-block;
    width: 16px;
    height: 18px;
    margin-top: 2px;
    vertical-align: top;
  }
  .c-place-section-box {
    border-top: 12px solid #9ba4a914;
    background-color: #fff;
    text-align: left;
    padding: 0px 5px 2px;
  }
  .c-place-section-header {
    padding: 17px 18px 15px;
    border-bottom: 1px solid #ecf0f2;
    position: relative;
    font-size: 1.0rem;
    letter-spacing: -0.3px;
    color: #000;
    font-weight: bold;
    margin-bottom: 0;
  }
  .c-place-section-content ul {
    padding: 0;
  }
  .c-place-count {
    margin-left: 6px;
    color: #0abe16;
  }
  .c-review-content {
    padding: 0 18px 3px;
  }
  .c-review-list {
    xpadding: 0 0 18px;
    padding: 18px 0;
    border-bottom: 1px solid #ecf0f2;
  }
  .c-review-list-grade {
    float: left;
  }
  .c-review-list-btn {
    margin-left: 545px;
  }
  .c-review-list-content {
    font-size: 1.0rem;
    color: #424242;
    letter-spacing: -0.3px;
    line-height: 2.2rem;
    clear: left;
  }
  .c-review-list-info {
    display: flex;
  }
  .c-review-list-info img {
    width: 20px;
    height: 20px;
    border-radius: 50%;
  }
  .c-review-list-info span {
    padding-top: 2px;
    margin-right: 5px;
  }
  .c-room-list {
    padding: 22px 18px 18px;
    border-bottom: 1px solid #ecf0f2;
  }
  .c-room-list:last-child {
    border: none;
  }
  .c-room-list::after {
    display: block;
    clear: both;
    content: "";
  }
  .c-room-list-photo {
    float: right;
    width: 87px;
    height: 87px;
    margin: -4px 0 0 14px;
  }
  .c-room-photo {
    display: block;
    position: relative;
    width: 100%;
  }
  .c-room-photo::before {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 10;
    border: 1px solid rgba(0,0,0,.04);
    content: "";
}
  .c-room-photo > img {
    display: inline-block;
    vertical-align: top;
  }
  .c-room-name {
    margin-right: 6px;
    font-size: 1.0rem;
    font-weight: 500;
    color: #0068c3;
    line-height: 2.3rem;
  }
  .page-link {
    border: none;
    color: midnightblue;
  }
	</style>

<body>
<div class="c-content">
	<div class="c-body">
	  
	  <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
		  
		  <div class="carousel-indicators">
		    <c:forEach items="${cafe.cafeImgs}" var="cafeImg" varStatus="status">
		      <c:if test="${status.first}">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${status.index}" class="active" aria-current="true" aria-label="Slide ${status.count}"></button>
          </c:if>
          <c:if test="${not status.first}">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${status.index}" aria-label="Slide ${status.count}"></button>
          </c:if>
		    </c:forEach>
		  </div>
		  
		  <div class="carousel-inner">
		    <c:forEach items="${cafe.cafeImgs}" var="cafeImg" varStatus="status">
		      <c:if test="${status.first}">
		        <div class="carousel-item active c-photo" style="background-image: url(${contextPath}/upload/cafe/${cafeImg.name}.jpg)">
            </div>
		      </c:if>
		      <c:if test="${not status.first}">
		        <div class="carousel-item c-photo" style="background-image: url(${contextPath}/upload/cafe/${cafeImg.name}.jpg)">
            </div>
		      </c:if>
		    </c:forEach>
		  </div>
		  
		  <c:forEach items="${cafe.cafeImgs}" var="cafeImg" varStatus="status">
          <c:if test="${status.index > 0}">
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
			        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			        <span class="visually-hidden">Previous</span>
			      </button>
			      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
			        <span class="carousel-control-next-icon" aria-hidden="true"></span>
			        <span class="visually-hidden">Next</span>
			      </button>
          </c:if>
      </c:forEach>
		  
		</div> <!-- 카페 이미지 -->
	  
	  <div class="c-place-section">
      <div class="c-place-title">
        <span class="c-title">🏘 ${cafe.name}</span>
        <span class="c-grade"></span>
      </div>
      <div class="c-place-grade">
        <span class="c-grade-count"> 방문자리뷰 ${cafe.countReview}</span>
      </div>
	  </div>
	  
	  <div class="c-place-info-section" >
	   <ul class="c-place-info">
	     
	     <li class="c-info-list">
	       <strong class="c-icon">
	         <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 18" class="c-info-icon" aria-hidden="true"><path d="M2.92 1.15L.15 3.93a.5.5 0 0 0-.14.45 16.09 16.09 0 0 0 12.6 12.61.5.5 0 0 0 .46-.14l2.78-2.78a.5.5 0 0 0 0-.71l-4.18-4.18-.07-.06a.5.5 0 0 0-.64.06l-1.9 1.9-.28-.18a9.53 9.53 0 0 1-2.65-2.63L5.96 8 7.88 6.1a.5.5 0 0 0 0-.71L4.41 1.93l-.78-.78a.5.5 0 0 0-.7 0zm5.62 10.79l.37.21.09.04a.5.5 0 0 0 .49-.13l1.82-1.82 3.48 3.47-2.24 2.24-.07-.01A15.1 15.1 0 0 1 1.14 4.84l-.1-.4 2.24-2.23 3.54 3.53-1.84 1.84a.5.5 0 0 0-.08.6 10.54 10.54 0 0 0 3.64 3.76z"></path></svg>
	       </strong>
	       <div>
	         <span class="c-phone-value">${cafe.phone}</span>
	       </div>
	     </li>
	     
	     <li class="c-info-list">
         <strong class="c-icon">
           <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 18" class="c-info-icon" aria-hidden="true"><path d="M15 6.97a6.92 6.92 0 0 1-1.12 3.77l-5.51 7.08a.47.47 0 0 1-.74 0L2.1 10.71A6.93 6.93 0 0 1 1 6.97 7 7 0 0 1 8 0v.93V0a7 7 0 0 1 7 6.97zm-13 0c0 1.15.4 2.3.99 3.24L8 16.7l5.04-6.5A5.95 5.95 0 0 0 8 1C4.66 1 2 3.64 2 6.97zm6-1.54A1.58 1.58 0 0 0 8 8.6a1.57 1.57 0 0 0 0-3.16zm0-.93a2.51 2.51 0 0 1 0 5.02A2.51 2.51 0 1 1 8 4.5z"></path></svg>
         </strong>
         <div>
           <span class="c-location-value">${cafe.location}</span>
         </div>
       </li>
       
       <li class="c-info-list">
         <strong class="c-icon">
           <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 18" class="c-info-icon" aria-hidden="true"><path d="M8 16A7 7 0 1 0 8 2a7 7 0 0 0 0 14zm0 1A8 8 0 1 1 8 1a8 8 0 0 1 0 16zm.5-7.8l3.02 1.76a.5.5 0 0 1 .19.68.5.5 0 0 1-.69.19L7.8 9.96a.5.5 0 0 1-.3-.46v-5a.5.5 0 0 1 1 0v4.7z"></path></svg>
         </strong>
         <div>
           <span class="c-time-value">${cafe.openTime} ~ ${cafe.closeTime} (휴무일:${cafe.holiday})</span>
         </div>
       </li>
       
       <li class="c-info-list">
         <strong class="c-icon">
           <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 18" class="c-info-icon" aria-hidden="true"><path d="M8.5 2.05l5.17 4.65a1 1 0 0 1 .33.74V17a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V7.45a1 1 0 0 1 .33-.75L7.5 2.05V0h1v2.05zm4.34 5.44L8.34 3.3a.5.5 0 0 0-.68 0L3.16 7.5a.5.5 0 0 0-.16.36v8.65a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7.86a.5.5 0 0 0-.16-.37zM8 11a2 2 0 1 1 0-4 2 2 0 0 1 0 4zm0-1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"></path></svg>
         </strong>
         <div>
           <span class="c-viewcount-value">${cafe.viewCount}</span>
         </div>
       </li>
       
       <li class="c-info-list">
         <strong class="c-icon">
           <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 18" class="c-info-icon" aria-hidden="true"><path d="M11 15V3H2v12h9zm1-6h3v6a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V3a1 1 0 0 1 1-1h9a1 1 0 0 1 1 1v6zm0 1v5h2v-5h-2zM4 5.5h5v1H4v-1zM4 8h5v1H4V8zm0 2.5h3v1H4v-1z"></path></svg>
         </strong>
         <div>
           <span class="c-info-value">${cafe.info}</span>
         </div>
       </li>
       
	   </ul>
	  </div>
	  
	  <div class="c-place-section-box">
	   
	   <h2 class="c-place-section-header">예약
     <em class="c-place-count" style="font-style: normal;">${roomListSize}</em>
     </h2>
     
     <div class="c-place-section-content">
      <ul>
      <c:if test='${not empty roomList}'>
        <c:forEach items="${roomList}" var="room">
        <li class="c-room-list">
          
          <div class="c-room-list-photo">
            <a class="c-room-photo">
            <img src="${contextPath}/img/studyroom1.jpg" width="87" height="87">
            </a>
          </div>
          
          <div class="c-room-list-name-section">
            <div class="c-room-list-name">
            <a href="reservationStudyRoom?cafeNo=${cafe.no}&roomNo=${room.roomNo}">
              <span class="c-room-name">
                ${room.roomName}
              </span>
            </a>
            </div>
          </div>
          
        </li>
        </c:forEach>
      </c:if>
      </ul>
     </div>
	  </div>
	  
	  <div class="c-place-section-box">
	   
	   <h2 class="c-place-section-header">방문자 리뷰
	     <em class="c-place-count" id="review-count" style="font-style: normal;"></em>
	   </h2>
	   
	   <div class="c-place-section-content">
	     <div class="c-review-content">
	       <ul>
	       </ul>
	     </div>
	   </div>
	   
	   <div class="c-place-section-content">
	      <nav aria-label="Page navigation example">
	        <ul class="pagination justify-content-center">
	        </ul>
	      </nav>     
     </div> <!-- review pagination -->
	   
	  </div> <!-- .c-place-section-box -->
	</div> <!-- .c-body -->
</div> <!-- .c-content -->




<script src="https://code.jquery.com/jquery-1.12.4.min.js" type="text/javascript"></script> <!-- jQuery -->
<script>
reviewList();

var loginUser = '${loginUser.perNo}';

function reviewList(page){
	var currentPage = page;
    $.ajax({
          url : "cafeReviewList",
          type : "post",
          dataType : "json",
          data : {cafeNo : ${cafe.no}, pageNo : currentPage},
          success:function(data){
              //console.log(data);
              var arr = data['reviewList'];
              var totalPage = data['totalPage'];
              var pageNo = data['pageNo'];
              var count = data['count'];
              var grade = data['grade'];
              
              var strTag = "";
              for (let i in arr) {
                
                strTag += '<li class="c-review-list" data-no="'+arr[i].reviewNo+'">';
                strTag += '<div class="c-review-list-grade">';
                strTag += "<span id='c-grade'>";
                strTag += arr[i].star;
                strTag += '</span>&nbsp;';
                strTag += '<span style="font-size:12px;" id="review-grade">'+arr[i].grade+'</span><span style="font-size:12px;">/5</span>';
                strTag += '</div>';
                if (loginUser != ""){
	                if (loginUser == arr[i].member.perNo){
		                strTag += '     <div class="c-review-list-btn">';
		                strTag += '       <button class="btn btn-outline-dark btn-sm" id="btnUpdateForm'+i+'">수정</button>';
		                strTag += '       <button class="btn btn-outline-dark btn-sm" id="btnDelete'+i+'">삭제</button>';
		                strTag += '     </div>';
	                }
                }
                strTag += '   <div class="c-review-list-content">';
                strTag += '     <span id="review-content">'+arr[i].content+'</span>';
                strTag += '   </div>';
                strTag += '   <div class="c-review-list-info">';
                strTag += '     <div class="c-review-list-info-img">';
                strTag += '       <img src="${contextPath}/img/KakaoTalk_20211113_014317649.jpg">';
                strTag += '     </div>';
                strTag += '     <span style="font-size: smaller;">'+arr[i].member.perNickname+'</span>';
                strTag += '     <span style="font-size: smaller; color: #8f8f8f;">'+arr[i].registeredDateStr+'</span>';
                strTag += '   </div>';
                strTag += ' </li>';
                
                $(".c-review-content ul").html(strTag);
              }
              
              strTag = "";
              
              if (count > 0) {
              
	              strTag += '<li class="page-item">';
	              if (pageNo > 1){
		            	strTag += '  <a class="page-link" href="javascript:void(0);" onclick="reviewList('+pageNo+'-1)" aria-label="Previous">';
		            	strTag += '    <span aria-hidden="true">&laquo;</span>';
		            	strTag += '  </a>';
	              } else if (pageNo <= 1) {
	            	  strTag += '  <a class="page-link" href="#" aria-label="Previous">';
	                strTag += '    <span aria-hidden="true">&laquo;</span>';
	                strTag += '  </a>';
	              }
	            	strTag += '</li>';
	            	
	            	for (let p=1; p <= totalPage; p++){
	            		if (pageNo == p) {
	            			strTag += '<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="reviewList('+p+')" style="font-weight: bold;">'+p+'</a></li>';
	            		} else {
	            			strTag += '<li class="page-item"><a class="page-link" href="javascript:void(0);" onclick="reviewList('+p+')">'+p+'</a></li>';
	            		}
	            	}
	            	
	            	strTag += '<li class="page-item">';
	            	if (pageNo < totalPage) {
		            	strTag += ' <a class="page-link" href="javascript:void(0);" onclick="reviewList('+pageNo+'+1)" aria-label="Next">';
		            	strTag += '    <span aria-hidden="true">&raquo;</span>';
		            	strTag += ' </a>';
	            	} else if (pageNo >= totalPage) {
	            		strTag += ' <a class="page-link" href="#" aria-label="Next">';
	                strTag += '    <span aria-hidden="true">&raquo;</span>';
	                strTag += ' </a>';
	            	}
	            	strTag += '</li>';
	            	
	            	$(".pagination").html(strTag);
              } else {
            	  $(".c-review-content ul").html("<p style='padding-top: 30px;'>등록된 리뷰가 없습니다.</p>");
              }

              $("#review-count").text(count);
              $(".c-grade").text('⭐'+grade+'/5');
              
              for (let idx = 0; idx < arr.length; idx++){
            	  btnUpdateForm(document.querySelector("#btnUpdateForm"+idx),idx);
            	  btnDelete(document.querySelector("#btnDelete"+idx));
              }
						    
          }
      });
    
  }

function btnUpdateForm(e,idx){
	e.onclick = () => {
		var btnDiv = e.parentNode;
		var grade = e.parentNode.parentNode.childNodes[0].childNodes[2];
		var origin_grade = e.parentNode.parentNode.childNodes[0].childNodes[2].innerText;
		var content = e.parentNode.parentNode.childNodes[4];
		var origin_content = content.innerText;
 
    var strTag = "";
    strTag += '<button class="btn btn-outline-dark btn-sm" id="btnUpdate'+idx+'">등록</button>';
    strTag += ' <button class="btn btn-outline-dark btn-sm" id="btnCancel'+idx+'">취소</button>';
    
    $(btnDiv).html(strTag);
    
    $(grade).html('<input id="review-grade'+idx+'" type="number" min=0 max=5 style="width: 32px; height: 19px;" value="'+origin_grade+'">');
    $(content).html('<textarea id="review-content'+idx+'" style="width: 533px; height: 41px;">'+origin_content+'</textarea>');

    btnCancel(document.querySelector("#btnCancel"+idx),idx,origin_grade,origin_content,grade,content,btnDiv);
    btnUpdate(document.querySelector("#btnUpdate"+idx),idx);
  };
}

function btnCancel(e,idx,origin_grade,origin_content,grade,content,btnDiv){
	e.onclick = () => {
	
    var strTag = "";
    strTag += '<button class="btn btn-outline-dark btn-sm" id="btnUpdateForm'+idx+'">수정</button>';
    strTag += ' <button class="btn btn-outline-dark btn-sm" id="btnDelete'+idx+'">삭제</button>';

    $(btnDiv).html(strTag);
    
    $(grade).html('<span style="font-size:12px;" id="review-grade">'+origin_grade);
    $(content).html('<span id="review-content">'+origin_content+'</span>');
    
    btnUpdateForm(document.querySelector("#btnUpdateForm"+idx),idx);
    btnDelete(document.querySelector("#btnDelete"+idx));
  };
}

function btnUpdate(e,idx){
	e.onclick = () => {
		
		var reviewNo = e.parentNode.parentNode.getAttribute("data-no");
		var grade = $("#review-grade"+idx).val();
		var content = $("#review-content"+idx).val();
		
		if (grade.length == 0 || content.length == 0){
		    swal.fire("평점과 내용을 모두 입력해주세요.");
		    return false;
		  } else {
			  Swal.fire({
				   html: '<p style="font: message-box;font-weight: bold;margin-bottom: 0;">리뷰를 정말 수정하시겠습니까?</p>',
			     icon: 'warning',
           showCancelButton: true,
           confirmButtonText: '네',
           cancelButtonText: '아니오'
        }).then((result) => {
          if (result.value) { 
        	  $.ajax({
                  url : "reviewUpdate",
                  type : "post",
                  dataType : "text",
                  data : {reviewNo : reviewNo, grade : grade, content : content},
                  success:function(data){
                	  reviewList();
                  }
              });
          }
        })
		  }
	};
}

function btnDelete(e){
	  e.onclick = () => {
	    
	    var reviewNo = e.parentNode.parentNode.getAttribute("data-no");
	    
	    Swal.fire({
	    	html: '<p style="font: message-box;font-weight: bold;">리뷰를 삭제하시면 재작성이 불가합니다.<br>삭제하시겠습니까?</p>',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '네',
        cancelButtonText: '아니오'
      }).then((result) => {
        if (result.value) { 
        	$.ajax({
                url : "reviewDelete",
                type : "post",
                dataType : "text",
                data : {reviewNo : reviewNo},
                success:function(data){
                  reviewList();
                }
            });
         }
      })
	  };
	}
</script>



