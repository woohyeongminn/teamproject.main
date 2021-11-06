<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <title>📢 공지게시판</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
   <!-- 아이콘 -->
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
  <style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  button[type=button] {
    margin-block: 10px;
    border-radius: 10px;
    background-color: beige;
    color: black;
  }
  button[type=button]:hover {
    background-color: blanchedalmond;
    color: black;
  }
  .btn-secondary:focus {
  background-color: beige;
  color: black;
  }
  button[type=button1] {
    margin-left: 15px;
    border-radius: 10px;
    border-color: lightgray;
    background-color: beige;
    color: black;
  }
  button[type=button1]:hover {
    background-color: blanchedalmond;
    color: black;
  }
  .dropdown-menu {
  background-color: rgba(211, 211, 211, 0);
  border: rgba(211, 211, 211, 0);
  }
  .btn-group {
  margin-top: 10px;
  display: block;
  }
  .offcanvas-start {
  width: 350px;
  }
  button[type=button2] {
  margin-left: 70px;
    color: black;
  }
  button[type=button2]:hover {
    color: black;
  }
  div {
  margin-right: 10px;
  }
  a {
  color : black;
  text-decoration : auto;
  }
  a:hover {
  color : lightgray;
  }
  #add:hover {
  color : white;
  }
  .c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  text-align: center;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
}
  .c-top:hover {
    color: cornflowerblue;
  }
  </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<fieldset>
<section>
<div class="c-top" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
      🔔 공지게시글 목록
      </div>
<table class="table table-responsive">
<thead>
<tr>
<th>번호</th>
<th>제목</th>
<th>내용</th>
<th>파일</th>
<th>등록일</th>
</tr>
</thead>
<tbody>
<c:forEach items="${adminNoticeList}" var="noticeList">
<tr>
	<td>( ${noticeList.adminNotiNo} )</td>
	<td><a href='detail?no=${noticeList.adminNotiNo}'>${noticeList.adminNotiTitle}</a></td>
	<td>${noticeList.adminNotiContent}</td>
	<td>${noticeList.adminNotiFile}</td>
	<td>${noticeList.adminNotiRegisteredDate}</td>
</tr>
</c:forEach>
</tbody>
</table>
</fieldset>
<c:choose>
<c:when test="${not empty loginAdmin}">

<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="등록"><a href='form' id='add'>등록</a></button>
</div>

<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
  <div class="offcanvas-header">
    <h4 class="offcanvas-title" id="offcanvasExampleLabel">👑 관리자 👑</h4>
    <button type="button2" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    <hr>
  </div>

  <div class="offcanvas-body">
    <div>
      <b>이동하고 싶은 탭을 선택해 주세요!</b>
    </div>

    <div class="btn-group dropend">
      <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
        👑 관리자 페이지
      </button>
      <div class="dropdown-menu" role="menu" style="border-color: white;">
        <button class="dromdown-item" type="button1">
          <a href='logout' style="color: black;">🖐 로그아웃</a></button><br>
        <button class="dromdown-item" type="button1">
          <a href='/ogong/admin/detail' style="color: black;">🙂 마이페이지</a></button>
      </div>
    </div>
    
    <div class="btn-group dropend">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
		    📁 회원 관리
		  </button>
		  <div class="dropdown-menu" role="menu" style="border-color: white;">
		    <button class="dromdown-item" type="button1">
		      <a href="/ogong/admin/permemberlist" style="color: black;">🎓 개인 회원</a></button><br>
		    <button class="dromdown-item" type="button1">
		      <a href="/ogong/admin/ceomember/list" style="color: black;">👔 기업 회원</a></button>
		  </div>
		</div>
      
    <div class="btn-group dropend">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
		    📖 스터디 관리
		  </button>
		  <div class="dropdown-menu" role="menu" style="border-color: white;">
		    <button class="dromdown-item" type="button1">
		      <a href="study/list" style="color: black;">📚 스터디 목록</a></button><br>
		    <!-- <button class="dromdown-item" type="button1">
		      <a href="/study/list" style="color: black;">📔 스터디 삭제</a></button> -->
		  </div>
		</div>
		
		<div class="btn-group dropend">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
		    🏘 장소 관리
		  </button>
		  <div class="dropdown-menu" role="menu" style="border-color: white;">
		    <button class="dromdown-item" type="button1">
		      <a href="/ogong/admin/cafeList" style="color: black;">📝 장소 목록</a></button><br>
		    <button class="dromdown-item" type="button1">
		      <a href="/ogong/admin/reviewList" style="color: black;">🔖 장소 리뷰</a></button>
		  </div>
		</div>
		
		<div class="btn-group dropend">
		  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
		    💌 고객센터 관리
		  </button>
		  <div class="dropdown-menu" role="menu" style="border-color: white;">
		    <button class="dromdown-item" type="button1">
		      <a href="/ogong/adminNotice/list" style="color: black;">📢 공지사항</a></button><br>
		    <button class="dromdown-item" type="button1">
		      <a href="/ogong/admin/askboardlist" style="color: black;">💬 문의사항</a></button>
		  </div>
		</div>
      
    </div>
</div>
</c:when>
<c:otherwise>

<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark"><a href="../index.jsp">이전</a></button>
</div>

</c:otherwise>
</c:choose>
</section>
 <jsp:include page="../footer.jsp"/>
</body>
</html>