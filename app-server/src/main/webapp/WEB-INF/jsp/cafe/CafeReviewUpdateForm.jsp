<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<style>
  .all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    border: 1px solid;
    padding: 40px;
    border-radius: 14px;
  }
	</style>
</head>
<body>
<br><br>
<div class="all-content">
<form action="reviewUpdate" method="post" id="updateForm">
	<div id='content'>
    <div class="mb-3">
       <label for="recipient-name" class="col-form-label">평점</label>
       <input type="number" min="0" max="5" name="grade" class="form-control" id="recipient-name" value="${cafeReview.grade}">
     </div>
     <div class="mb-3">
       <label for="message-text" class="col-form-label">내용</label>
       <textarea class="form-control" id="message-text" name="content">${cafeReview.content}</textarea>
     </div>
	</div>
	<input type="hidden" name="reviewNo" value="${cafeReview.reviewNo}">
</form>
	<button class="btn btn-outline-dark" id="btnUpdate">수정</button>
	<button class="btn btn-outline-dark" id="btnCancel">취소</button>
</div>


<script>
document.querySelector("#btnUpdate").onclick = () => {
	var grade = document.querySelector('input[name="grade"]').value;
	var content = document.querySelector('textarea[name="content"]').value;
	
	if (grade.length == 0 || content.length == 0){
    swal.fire("평점과 내용을 모두 입력해주세요.");
    return false;
	} else {
    Swal.fire({
      title: '리뷰를 정말 수정하시겠습니까?',
      showCancelButton: true,
      confirmButtonText: '네',
      cancelButtonText: '아니오'
    }).then((result) => {
      if (result.value) { 
          document.querySelector("#updateForm").submit();
      }
    })
	}
}

document.querySelector("#btnCancel").onclick = () => {
	 location.href="reviewList";
}


</script>




          