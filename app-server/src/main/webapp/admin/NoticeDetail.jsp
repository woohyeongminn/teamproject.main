<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <title>공지게시판</title>
<body>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
   <!-- 아이콘 -->
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
  <style>
  label {
    margin-right: 5px;
    text-align: center;
    /* display: inline; */
    width: 60px;
  }
  label#content {
    position: relative;
    bottom: 400px;
  }
  legend {
    text-align: center;
  }
  a {
  color : black;
  text-decoration : blink;
  }
  a:hover {
  color : white;
  }
  button[type=submit] {
    margin-bottom: 300px;
    margin-right: 10px;
  }
  input {
  width: 700px;
  font-size: 14px;
  text-align: center;
  border : white;
  outline-color : lightgray;
  /* margin-left: 80px; */
  font-weight: 400;
  }
  #f-content {
  text-align: justify;
  margin: 0;
  word-wrap: break-word;
  width: 700px;
  font-size: 14px;
  min-height: 370px;
  letter-spacing: 0;
  border: 0px solid white;
  outline-color: lightgray;
  border-radius: 1px;
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
  .all-content {
    width: 100%;
    max-width: 900px;
    margin: 0 auto;
  }
  </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<fieldset>
<section>
<div class="c-top">
        🔔 공지게시글 상세
      </div>
<div class="all-content">
<table class="table table-responsive text-center">
<thead>
<tr>
  <th scope="col"></th>
  <th scope="col">🔔 ${adminNotice.adminNotiTitle}</th>
</tr>
<!-- <th>번호</th>
<th>제목</th>
<th>내용</th>
<th>파일</th>
<th>등록일</th>
</tr> -->

<%-- <tr>
<th scope="row"><label for='f-no'>번호</label></th>
<td>( ${adminNotice.adminNotiNo} )</td>
</tr> --%>
<tr>
<th scope="row"><label for='f-content'>내용</label></th>
<td>${adminNotice.adminNotiContent}</td>
</tr>
<tr>
<th scope="row"><label for='f-filepath'>파일</label></th>
<td>${adminNotice.adminNotiFile}</td>
</tr>
<tr>
<th scope="row"><label for='f-registeredDate'>등록일</label></th>
<td>${adminNotice.adminNotiRegisteredDate}</td>
</tr>
</thead>
</table>


<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="목록"><a href='list'>목록</a></button>
<c:if test="${not empty loginAdmin}">
<button type="submit" class="btn btn-outline-dark" value="변경"><a href='Updateform?no=${adminNotice.adminNotiNo}'>변경</a></button>
<button type="submit" class="btn btn-outline-dark" value="삭제"><a href='delete?no=${adminNotice.adminNotiNo}'>삭제</a></button></body>
</c:if>

</div>
</fieldset>
</section>
 <jsp:include page="../footer.jsp"/>
</body>
</html>