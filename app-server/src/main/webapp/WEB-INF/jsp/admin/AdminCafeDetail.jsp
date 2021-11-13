<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

  <style>
  * {
  font-size: 14px;
  }
/*   #all {
  margin: 80px 0 0 0;
  } */
   .all-content {
  width: 65%;
  margin: 0 auto;
  height: 800px;
	}
	 
	.template-content {
	    height: 1300px;
	}
	
	ul {
	list-style:none;
	}
	.cafe-top {
  width: 100%;
  height: 300px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-bottom: 15px;
  }

    .slide{height:300px;overflow:hidden;}
    .slide ul{width:calc(100% * 4);display:flex;animation:slide 8s infinite;} /* slide를 8초동안 진행하며 무한반복 함 */
    .slide li{width:calc(100% / 4);height:300px;}
    .slide li:nth-child(1){background:#ffa;}
    .slide li:nth-child(2){background:#faa;}
    .slide li:nth-child(3){background:#afa;}
    .slide li:nth-child(4){background:#aaf;}
    @keyframes slide {
      0% {margin-left:0;} /* 0 ~ 10  : 정지 */
      10% {margin-left:0;} /* 10 ~ 25 : 변이 */
      25% {margin-left:-100%;} /* 25 ~ 35 : 정지 */
      35% {margin-left:-100%;} /* 35 ~ 50 : 변이 */
      50% {margin-left:-200%;}
      60% {margin-left:-200%;}
      75% {margin-left:-300%;}
      85% {margin-left:-300%;}
      100% {margin-left:0;}
    }

	#content {
	  display: block;
	  width: 100%;
	  padding: 10px 10px 0 10px;
	}
	#content > label {
  width: 120px;
  font-weight: bold;
  padding: 5px 0;
}

  #content > span {
      display: inline-block;
    width: 122px;
  padding: 5px 0;
  }

	.cafe-bottom {
	  width: 100%;
	  text-align: left;
	  padding: 5px 10px;
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
	.label-wrap {
  width: 100%;
  height: fit-content;
  display: flex;
  flex-direction: row;
  
}

.label-wrap > label {
   width: 15%;
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
  /* padding: 0 10px 30px 10px; */
  text-align: left;
}
 
.line {
   width: 100%;
   height: 4px;
   background: gray;
}

.review-wrap {
  width: 800px;
  height: 180px;
  overflow-y: scroll;
}

.review-wrap::-webkit-scrollbar {
    width: 10px;
  }
.review-wrap::-webkit-scrollbar-thumb {
    background-color: rgb(247, 231, 215);
    border-radius: 10px;
    background-clip: padding-box;
    border: 2px solid transparent;
  }
.review-wrap::-webkit-scrollbar-track {
    background-color: rgb(250, 250, 234);
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
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
 div#button_wrap {
    margin: 20px 0;
}
/* .btn_wrap .btn {
  margin: 0 7px;
  padding: 5px 10px;
  height: auto;
  line-height: inherit;
} */
  /* #aside {
     width: 120px;
     height: 200px;
     float: left;
     background-color: lightyellow;
     display: table;
  } */
  /* #c-image {
    display: table-cell;
    vertical-align: middle;
    text-align: center;
  } */
  /* #content {
     margin-left: 130px;
  } */
  /* label {
    display: inline-block;
    margin-right: 5px;
    width: 130px;
  } */
  /* #c-review {
    width: 427px;
    background-color: whitesmoke;
    height: 80px;
    margin-bottom: 10px;
  }
  #c-grade {
     margin-left: 41px;
     vertical-align: 4px;
  } */
  button[type=submit] {
    font-size: 14px;
    line-height: 14px;
  }
  a {
  color : black;
  text-decoration:none;
  }
  a:hover {
  color : white;
  }
  </style>
  
<c:if test='${cafe.cafeStatus == 4}'>
<style>
#deleted {
display: none;
}
</style>
</c:if>

<c:if test='${cafe.cafeStatus != 4 && cafe.cafeStatus != 1}'>
<style>
#agree {
display: none;
}
</style>
</c:if>

</head>

<div class="all-content"> 
  <input id='c-no' type='hidden' value='${cafe.no}'><br>

  <!-- <div id='aside'>
    <span id='c-image'>대표이미지</span>
  </div> -->
  
  <div class = "cafe-top">
      <h4>[${cafe.name}]</h4>
      
        <div class="slide">
          <ul>
            <li><img src="../../upload/cafe/${cafe.cafeImgs[0].name}" style="width:100%"></li>
            <li><img src="../../upload/cafe/${cafe.cafeImgs[1].name}" style="width:100%"></li>
            <li><img src="../../upload/cafe/${cafe.cafeImgs[2].name}" style="width:100%"></li>
          </ul>
        </div>
       </div>
  
  <%-- <div id='content'>
    <label for='f-bossName'>대표자</label><span id='deleted'>${cafe.ceoMember.ceoBossName}</span><br>
    <label for='f-licenseNo'>사업자 등록 번호</label><span id='deleted'>${cafe.ceoMember.ceoLicenseNo}</span><br>    
    <label for='f-info'>소개글</label>${cafe.info}<br>
    <label for='f-location'>주소</label>${cafe.location}<br>
    <label for='f-tel'>전화번호</label>${cafe.phone}<br>
    <label for='f-openTime'>오픈 시간</label>${cafe.openTime}<br>
    <label for='f-closeTime'>마감 시간</label>${cafe.closeTime}<br>
    <label for='f-holiday'>이번 주 휴무일</label>${cafe.holiday}<br>
    <label for='f-viewCount'>조회수</label>${cafe.viewCount}<br>
    <label for='f-review'>리뷰 평점</label>⭐${cafe.avgReview}(${cafe.countReview})<br> --%>
    
    <!-- 카페 상세 글 부분 -->      
      <div id='content'>
        <label for='f-bossName'>대표자</label><span>${cafe.ceoMember.ceoBossName}</span><br>
        <label for='f-licenseNo'>사업자 등록번호</label><span>${cafe.ceoMember.ceoLicenseNo}</span><br>
        <div class="label-wrap"><label for='f-location'>주소</label> <span style="height: fit-content;">${cafe.location}</span></div>
      </div>
      <div class="cafe-bottom">
        <div class="label-wrap"><label for='f-info'>소개글</label><span style="height: fit-content;">${cafe.info}</span></div>
        <label for='f-tel'>전화번호</label><span>${cafe.phone}</span><br>
        <label for='f-openTime'>운영시간</label><span>${cafe.openTime} AM ~ ${cafe.closeTime} PM</span><br>
        <label for='f-holiday'>이번주 휴무일</label><span>${cafe.holiday}</span><br>
        <label for='f-review'>리뷰평점</label><span>⭐${cafe.avgReview} / ${cafe.countReview}개</span><br>
      
   <script>
     if(${cafe.cafeStatus == 1}) {
       document.write("<label for='f-cafeStatus'>운영 상태</label>승인 대기");
     } else if(${cafe.cafeStatus == 2}) {
       document.write("<label for='f-cafeStatus'>운영 상태</label>운영 중");
     } else if(${cafe.cafeStatus == 3}) {
       document.write("<label for='f-cafeStatus'>운영 상태</label>운영 중단");
     } else if(${cafe.cafeStatus == 4}) {
       document.write("<label for='f-cafeStatus'>운영 상태</label>삭제");
     } 
   </script>
  </div>
  
        <!-- 리뷰 시작부분에 선 -->
      <div class="cafe-bottom-review">
        <hr style="border-top: 4px double #bbb; text-align: center; display: inline-block; width: 48%; margin: 6px 0">
        <i class="far fa-comments" style="color:#bbb; font-size: large;"></i>
        <hr style="border-top: 4px double #bbb; text-align: center; display: inline-block; width: 48%;  margin: 6px 0">
    
<%-- <c:if test='${not empty reviewList}'>
  <c:forEach items="${reviewList}" var="review">
    <div id='c-review'>
      <span>${review.member.perNickname}</span> 
      <span id='c-grade'>
        <c:set var="grade" value="${review.grade}" /> 
          <% 
          int grade = (int) pageContext.getAttribute("grade");
          String star = CafeHandlerHelper.getReviewGradeStatusLabel(grade);
          pageContext.setAttribute("star", star);
          %>
      ${star}(${review.grade}/5)
      </span>
      <span id='c-grade'>${review.registeredDate}</span>
      <br><p id='c-review-content'>${review.content}</p><br>
    </div>
  </c:forEach>
</c:if> --%>

<!-- 리뷰 보여지는 부분 -->
       <c:if test='${not empty reviewList}'>
        <div class = "review-wrap">
         <c:forEach items="${reviewList}" var="review">
          <div id='c-review'>
          <p id='c-review-content'>${review.content}</p>
           <span id='c-grade'>
             <c:set var="grade" value="${review.grade}" /> 
                <% 
                int grade = (int) pageContext.getAttribute("grade");
                String star = CafeHandlerHelper.getReviewGradeStatusLabel(grade);
                pageContext.setAttribute("star", star);
                %>
             <span style="font-size: 12px;">${star}(${review.grade}/5)</span>
           </span>
           <span style="font-size: 12px;"> | ${review.member.perNickname} | ${review.registeredDate}</span>
          </div>
         </c:forEach>
         </div>
       </c:if>
       
       <c:if test='${empty reviewList}'>
		   등록된 리뷰가 없습니다.<br><br>  
	   	 </c:if>
       </div>

  <div id='button_wrap'>
	  <button type="submit" class="btn btn-outline-dark"><a href="/ogong/admin/cafeList">목록</a></button>
	  <button id="agree" type="submit" class="btn btn-outline-dark"><a href="/ogong/admin/cafeControl?no=${cafe.no}">승인</a></button>
	  <button id="deleted" type="submit" class="btn btn-outline-dark"><a href="/ogong/admin/cafeDelete?cafeNo=${cafe.no}">삭제</a></button>
	  <button type="submit" class="btn btn-outline-dark" value="로그아웃" ><a href='/ogong/admin/logout'>로그아웃</a></button> 
  </div>

</div>
