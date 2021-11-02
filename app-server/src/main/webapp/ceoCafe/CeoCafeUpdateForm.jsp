<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 스터디카페 수정</title>
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
  }
  #content {
     margin-left: 130px;
  } 

  #input-file-button {
  display: inline-table;
  width: 120px;
  padding: 2px;
  background-color: gray;
  border-radius: 5px;
  color: white;
  font-size: smaller;
  cursor: pointer;
  text-align: center;
  margin-top: 5px;
 }

 #c-image {
  width: 120px;
  height: 150px;
  background-color: darkgray;
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
#button {
  margin-left: 130px;
  margin-top: 30px;
}
#b-but {
  width: 140px;
  padding: 5px;
  margin-right: 10px;
  background-color: dimgray(209, 209, 209);
  border-radius: 4px;
  color: black;
  font-size: smaller;
  cursor: pointer;
  text-align: center;
}
</style>
</head>

<body>
  
  <b>👩‍🏫 내 스터디카페 수정</b>
  <hr>
  <h5>[${cafe.name}]</h5>
    <div id='aside'>
      <span id='c-image'>대표이미지</span>
      <form action="upload.php" method="post" enctype="multipart/form-data">
        <input id="input-file" type="file" multiple="multiple" style='display:none' name='filename[]'/>
        <label id="input-file-button" for="input-file">
          파일 첨부
        </label>
      </form>
    </div>
    <form action='update'>
    <div id='content'>
      <input type ='hidden' name='cafeno' value='${cafe.no}'><br>
      <input type ='hidden' name='no' value='${ceoMember.ceoNo}'>
      <label for='f-bossName'>대표자</label><span>${cafe.ceoMember.ceoBossName}</span><br>
      <label for='f-licenseNo'>사업자 등록번호</label><span>${cafe.ceoMember.ceoLicenseNo}</span><br>
      
      <label for='f-info'>소개글</label>
      <input id='f-info' type='text' name='info' value='${cafe.info}' size="auto"><br>
      
      <label for='f-location'>주소</label>
      <input id='f-location' type='text' name='location' value='${cafe.location}' size="auto"><br>
      
      <label for='f-tel'>전화번호</label>
      <input id='f-tel' type='tel' name='tel' value='${cafe.phone}' size="auto"><br>
  
      <label for='f-openTime'>오픈시간</label>
      <input id='f-openTime' type='time' name='openTime' value='${cafe.openTime}' size="auto"><br>
  
      <label for='f-closeTime'>마감시간</label>
      <input id='f-closeTime' type='time' name='closeTime' value='${cafe.closeTime}' size="auto"><br>
      
      <label for='f-holiday'>이번주 휴무일</label>
      <input id='f-holiday' type='date' name='holiday' value='${cafe.holiday}' size="auto"><br>
      
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
      
      <label for='f-review'>리뷰평점</label>⭐${cafe.avgReview}(${cafe.countReview})
    </div>
    <div id='button'>
     <button id='b-but' type="submit" value="수정" formaction="update">수정</button>
     <button id='b-but' type="submit" value="상세"><a href='delete?no=${cafe.no}'>뒤로가기</a></button>
    </div>
  </form> 
</body>
</html>