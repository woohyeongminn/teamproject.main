<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <style>
  * {
  font-size: 14px;
  }
  
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
    size:100px;
  }
  .btn {
    line-height: 14px;
  }
  a {
  color: black;
  }  
  </style>
<fieldset>
<br>
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
   <a href='permylist' >내 글</a> |
   <a href='alllist' >전체 글</a>
</div>

<hr>
<table class="table table-responsive text-center">
<thead>
	<tr id="head">
		  <th>번호</th>
		  <th>제목</th>
		  <th>작성자</th>
		  <th>조회수</th>
		  <th>등록일</th>
		  <th>답변📔/📖</th>
	</tr>
</thead>

<c:forEach items="${perMyAskBoardList}" var="askBoard">
  <tr>
    <td>${askBoard.askNo}.</td>
    <td><a href='permydetail?askNo=${askBoard.askNo}'>${askBoard.askTitle}</a></td>
    <td>${askBoard.askMemberWriter.perNickname}</td>
    <c:choose>
      <c:when test="${askBoard.askStatus == 1}">    
        <td>${askBoard.askVeiwCount}</td>
      </c:when>
      <c:otherwise>
        <td>🔐</td>
      </c:otherwise>
    </c:choose>    
    <td>${askBoard.askRegisteredDate}</td>               
       <c:choose>
         <c:when test="${empty askBoard.reply}">
            <td> 📔 </td>
         </c:when>
        <c:otherwise>
           <td> 📖 </td>
        </c:otherwise>
       </c:choose> 
  </tr>
</c:forEach>
</table>
 <c:if test="${empty perMyAskBoardList}">
  <b style="font-size:14; text-align: center">❕❔ 등록한 게시글이 없습니다.</b>
 </c:if>
</fieldset>

<div class="d-grid gap-2 d-md-flex justify-content-md-end">
   <a href='peraddform' type="button" class="btn btn-outline-dark" >등록하기</a> 
</div>

<script>
document.querySelectorAll("tbody a").forEach((aTag) => {
  aTag.onclick = () => false;
});

var trList = document.querySelectorAll("tbody tr"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    //console.log(e.currentTarget.querySelector("a").href);
    //e.currentTarget.querySelector("a").click();
    window.location.href = e.currentTarget.querySelector("a").href;
    //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
  };
});
</script>











