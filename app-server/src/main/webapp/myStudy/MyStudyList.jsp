<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>목록 | 조장인 스터디</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
*{
  margin:0; padding:0;
  font-size:15px; 
  line-height:1.3;
}
ul{list-style:none;}

.tabmenu{ 
  max-width:900px; 
  margin: 0 auto; 
  position:relative; 
}
.tabmenu ul li{
  display:  inline-block;
  width:33.33%; 
  float:left;  
  text-align:center; 
  background :#f9f9f9;
}
.tabmenu ul li a{
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
  left:0; top:40px; 
  box-sizing: border-box; 
  border : 5px solid #f9f9f9;
  width: 900px;
}
.btnCon:target  {
  background : #ccc;
}
.btnCon:target .tabCon{
  display: block;
}
 .c-top {
  width: 100%;
  padding: 20px 0 20px 50px;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
  text-align: center;
}
</style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="c-top">
  👩‍👧‍👧 ‍스터디 목록
</div>
<br><br><br>
<div class="tabmenu">
  <ul>
    <li id="tab1" class="btnCon"><a class="btn first" href="#tab1">👤 조장 | 스터디 목록</a>
      <div class="tabCon" >
      <br>
      <h3>📖 | 👤 조장 | 스터디 목록</h3>
      <br><br>
	    <table class="table table-hover">
	    <thead>
	      <tr>
	        <th>번호</th>
	        <th>북마크</th>
	        <th>제목</th>
	        <th>대면/비대면</th>
	        <th>조장</th>
	        <th>분야</th>
	        <th>지역</th>
	        <th>인원수</th>
	        <th>최대 인원수</th>
	      </tr>
	      </thread>
		    <tbody>
		      <c:forEach items="${ownerStudyList}" var="study">
		        <tr>
		          <td>${study.studyNo}</td>
		          <td>${study.countBookMember}</td>
		          <td><a href='../mystudy/detail?studyNo=${study.studyNo}'>${study.studyTitle}</a></td>
		          <td>${study.faceName}</td>
		          <td>${study.owner.perNickname}</td>
		          <td>${study.subjectName}</td>
		          <td>${study.area}</td>
		          <td>${study.countMember}</td>
		          <td>${study.numberOfPeple}</td>
		        </tr>
		      </c:forEach>
		    </tbody>
		  </table>
		  <c:if test="${empty ownerStudyList}">
		    조장으로 참여 중인 스터기가 없습니다.
		  </c:if>
      
      </div>
      
    </li>
    <li id="tab2" class="btnCon"><a class="btn" href="#tab2">👨‍👩 구성원 | 스터디 목록</a>
      <div class="tabCon" >
      <br>
      <h3>📖 | 👨‍👩‍👧‍👧 구성원 | 스터디 목록</h3>
      <br><br>
		  <table class="table table-hover">
		    <thead>
		      <tr>
		        <th>번호</th>
		        <th>북마크</th>
		        <th>제목</th>
		        <th>대면/비대면</th>
		        <th>조장</th>
		        <th>분야</th>
		        <th>지역</th>
		        <th>인원수</th>
		        <th>최대 인원수</th>
		      </tr>
		    </thread>
		    <tbody>
		      <c:forEach items="${guilderMembers}" var="study">
		        <tr>
		          <td>${study.studyNo}</td>
		          <td>${study.countBookMember}</td>
		          <td><a href='../mystudy/detail?studyNo=${study.studyNo}'>${study.studyTitle}</a></td>
		          <td>${study.faceName}</td>
		          <td>${study.owner.perNickname}</td>
		          <td>${study.subjectName}</td>
		          <td>${study.area}</td>
		          <td>${study.countMember}</td>
		          <td>${study.numberOfPeple}</td>
		        </tr>
		      </c:forEach>
		    </tbody>
		  </table>
		  <c:if test="${empty guilderMembers}">
		  구성원으로 참여 중인 스터디가 없습니다.
		  </c:if>
      </div>
      
    </li>    
    <li id="tab3" class="btnCon"><a class="btn" href="#tab3">📖 참여 대기중</a>
      <div class="tabCon" >
      <br>
      <h3>📖 참여 대기중 스터디 목록</h3>
      <br><br>
	    <table class="table table-hover">
	    <thead>
	      <tr>
	        <th>번호</th>
	        <th>북마크</th>
	        <th>제목</th>
	        <th>대면/비대면</th>
	        <th>조장</th>
	        <th>분야</th>
	        <th>지역</th>
	        <th>인원수</th>
	        <th>최대 인원수</th>
	      </tr>
	      </thread>
		    <tbody>
		      <c:forEach items="${waitingStudyList}" var="study">
		        <tr>
		          <td>${study.studyNo}</td>
		          <td>${study.countBookMember}</td>
		          <td><a href='waitingdetail?studyNo=${study.studyNo}'>${study.studyTitle}</a></td>
		          <td>${study.faceName}</td>
		          <td>${study.owner.perNickname}</td>
		          <td>${study.subjectName}</td>
		          <td>${study.area}</td>
		          <td>${study.countMember}</td>
		          <td>${study.numberOfPeple}</td>
		        </tr>
		      </c:forEach>
		    </tbody>
		  </table>
		  <c:if test="${empty waitingStudyList}">
		    참여 대기중인 스터기가 없습니다.
		  </c:if>
      
      </div>
      
    </li>
  </ul>
</div>

<script>
location.href = "#tab1";
</script>
  
</body>
</html>