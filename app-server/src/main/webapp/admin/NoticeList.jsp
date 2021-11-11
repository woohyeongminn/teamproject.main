<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

   <!-- 아이콘 -->
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
  
  <style>
  * {
  font-size: 14px;
  }
  .all-content {
  width: 100%;
  max-width: 900px;
  margin: 0 auto;
  font-size: 14px;
  }
  a {
  color : black;
  text-decoration : auto;
  }
  a:hover {
  color : white;
  }
  #addbutton {
  text-align: center;
  margin: 50px 0 0 0;
  }
  #add:hover {
  color : white;
  }
  button[type=submit] {
    font-size: 14px;
    line-height: 14px;
  }
</style>

</head>

<section>
<fieldset>

<br>
<div class="all-content">

<table class="table table-responsive text-center">
<thead>

<tr>
  <th>번호</th>
  
  <c:choose>
    <c:when test="${not empty loginAdmin}">
     <th style="margin-left: auto;" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">제목</th>
    </c:when>
    <c:otherwise>
     <th>제목</th>
    </c:otherwise>
  </c:choose>
  
  <th>등록일</th>
</tr>

</thead>
<tbody>

<c:forEach items="${adminNoticeList}" var="noticeList">
  <tr data-no="${noticeList.adminNotiNo}">
    <td><b> ${noticeList.adminNotiNo} </b></td>
    <td><a href='detail?no=${noticeList.adminNotiNo}'>${noticeList.adminNotiTitle}</a></td>
    <td>${noticeList.adminNotiRegisteredDate}</td>
  </tr>
</c:forEach>

</tbody>
</table>

<c:choose>
<c:when test="${not empty loginAdmin}">

  <div id="addbutton">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
    <button type="submit" class="btn btn-outline-dark" value="등록"><a href='form' id='add'>등록</a></button>
    </div>
  </div>
  
  <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">

		<jsp:include page="AdminMenu.jsp"/>
    
  </div>
  
  </c:when>
  <c:otherwise>
  
  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
    <button type="submit" class="btn btn-outline-dark"><a href="../index.jsp">이전</a></button>
  </div>

</c:otherwise>
</c:choose>

</div>
</fieldset>
</section>

<script>
document.querySelectorAll("tbody a").forEach((aTag) => {
  aTag.target.onclick = () => false;
});

var trList = document.querySelectorAll("tbody tr");
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
      window.location.href = e.currentTarget.querySelector("a").href; // 방법 2) 현재 페이지를 그 링크로 바꾸게 해라
    };
  });
</script>

