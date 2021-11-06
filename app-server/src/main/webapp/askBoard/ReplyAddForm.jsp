<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 게시글 등록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<style>

.btn {
   border-radius: 4px;
   background-color: blanchedalmond;
   color: black;
   font-size: 18px;
  }
  .btn:hover {
   background-color: beige;
   color: black;
  }

</style>
</head>

<body>
<jsp:include page="../header.jsp"/>
  <h5> 📕답변 등록 </h5>
  <form action='replyadd'>
  
    <div class="title">
    <label for='f-title' class='form-label'>제목</label>
    <input id='f-title' type='text' name='title' placeholder="제목을 입력하세요"><br>
    </div>
    
    <div class="content">
    <label for='f-content' class='form-label' size='100px'>내용</label>
    <input id='f-content' type='text' name='content' placeholder="내용을 입력하세요"><br>
    </div>
    
    <input type ='hidden' name='askNo' value='${askBoard.askNo}'></input> 
    
    <br><br>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-primary me-md-2 btn-small" 
     type="submit" value="등록" >
     등록하기
     </button>
   </div> 
  </form>
</body>
</html>   
    
    
    
    