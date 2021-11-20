<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<meta charset="UTF-8">
<style>
* {
  font-size: 13px;
}
a {
 text-decoration:none;
}
a:hover {
  color: rgb(155 136 131);
}
button > a:hover {
  color: white;
}
.all-content {
  width: 100%;
  max-width: 900px;
  margin: 0 auto;
  padding: 20px 0;
}
.dropdown a > span {
  font-size: 14px;
}
.page-link {
  color: black;
}
</style>
<body>
<div class="all-content">
<i class="fas fa-angle-double-right"></i>
<span> 총 <strong style="font-size: 12px;">${count}</strong>개의 리뷰를 작성하셨습니다. </span>
<br><br>
<table class="table table-responsive text-center align-middle">
	<thead>
	  <tr>
	    <th width="150px">스터디카페</th>
	    <th>별점</th>
	    <th width="400px">내용</th>
	    <th>등록일</th>
	    <th>수정</th>
	    <th>삭제</th>
	  </tr>
	</thead>
  <tbody>

	<c:forEach items="${reviewList}" var="review">
	 <tr>
	    <td><a href='detail?no=${review.cafe.no}'>${review.cafe.name}</a></td>
	    <td>
	    <c:set var="grade" value="${review.grade}" /> 
	          <% 
	          int grade = (int) pageContext.getAttribute("grade");
	          String star = CafeHandlerHelper.getReviewGradeStatusLabel(grade);
	          pageContext.setAttribute("star", star);
	          %>
	      ${star}(${review.grade}/5)
	    </td>
	    <td>${review.content}</td>
	    <td>${review.registeredDate}</td>
	    <td>
	      <button class="btn btn-outline-dark btn-sm"><a href="reviewUpdateForm?reviewNo=${review.reviewNo}">수정</a></button>
	    </td>
	    <td>
	      <button class="btn btn-outline-dark btn-sm" onclick='return btnDelete(${review.reviewNo})'>삭제</button>
	    </td>
	 </tr>
	</c:forEach>
  </tbody>
</table>
<c:if test='${empty reviewList}'>
   <p class="text-center">등록된 리뷰가 없습니다.</p>
</c:if>
<br>

<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
    <li class="page-item">
      <c:if test="${pageNo > 1}">
        <a class="page-link" href="reviewList?pageNo=${pageNo-1}" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </c:if>
      <c:if test="${pageNo <= 1}">
        <a class="page-link" href="#" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
        </a>
      </c:if>
    </li>
    <c:forEach var="page" begin="1" end="${totalPage}">
    <c:choose>
      <c:when test="${pageNo == page}">
        <li class="page-item"><a class="page-link" href="reviewList?pageNo=${page}" style="font-weight: bold;">${page}</a></li>
      </c:when>
      <c:otherwise>
        <li class="page-item"><a class="page-link" href="reviewList?pageNo=${page}">${page}</a></li>
      </c:otherwise>    
    </c:choose>
    </c:forEach>
    <li class="page-item">
      <c:if test="${pageNo < totalPage}">
        <a class="page-link" href="reviewList?pageNo=${pageNo+1}" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </c:if>
      <c:if test="${pageNo >= totalPage}">
        <a class="page-link" href="#" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
        </a>
      </c:if>
      
    </li>
  </ul>
</nav> <!-- pagination -->
</div>


<script>
function btnDelete(reviewNo) {
	Swal.fire({
		html: '<p style="font: message-box;font-weight: bold;">리뷰를 삭제하시면 재작성이 불가합니다.<br>삭제하시겠습니까?</p>',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: '네',
    cancelButtonText: '아니오'
  }).then((result) => {
    if (result.value) { 
    	var form = document.createElement('form');
   	  form.setAttribute('method', 'post');
   	  form.setAttribute('action', 'reviewDelete');

   	  var hiddenField = document.createElement('input');
   	  hiddenField.setAttribute('type', 'hidden');
   	  hiddenField.setAttribute('name', "reviewNo");
   	  hiddenField.setAttribute('value', reviewNo);
   	  form.appendChild(hiddenField);

   	  document.body.appendChild(form);
   	  
	    form.submit();
	    return true;
    }
  })
}

</script>

