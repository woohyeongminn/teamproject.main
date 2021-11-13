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
  </style>
</head>
<body>
<fieldset>
<br>
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
<tbody>

<c:forEach items="${myAskBoardList}" var="askBoard">
  <tr>
    <c:if test="${askBoard.askMemberWriter.perNo == loginCeoUser.ceoNo}">
        <td>${askBoard.askNo}.</td>
        <td><a href='permydetail?askNo=${askBoard.askNo}'>${askBoard.askTitle}</a></td>
        <td>${askBoard.askMemberWriter.perNickname}</td>
        <td>${askBoard.askVeiwCount}</td>
        <td>${askBoard.askRegisteredDate}</td>               
       <c:choose>
         <c:when test="${empty askBoard.reply}">
            <td> 📔 </td>
         </c:when>
         <c:otherwise>
           <td> 📖 </td>
        </c:otherwise>
       </c:choose> 
    </c:if>
  </tr>
</c:forEach>
</table>
</fieldset>
</tbody> 
   <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class = "btn btn-outline-dark" type="submit" value="등록" formaction="add">
     <a href='ceoaddform'>등록하기</a>
     </button>

   </div>
   <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class = "btn btn-outline-dark" type="submit" value="목록" formaction="add">
     <a href='alllist'>문의글 전체보기</a>
   </button>












