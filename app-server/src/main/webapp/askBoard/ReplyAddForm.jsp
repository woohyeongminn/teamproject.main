<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
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
 <br>
  <form> 
    <div class="title">
    <label for='f-title' class='form-label'>제목</label>
    <input type ='text' class="form-control" name='title' placeholder="제목을 입력하세요"><br>
    </div>
    
    <div class="content">
    <label for='f-content' class='form-label' size='100px'>내용</label>
    <input type='text' class="form-control" name='content' placeholder="내용을 입력하세요"><br>
    </div>
    
    <input type ='hidden' name='askNo' value='${askBoard.askNo}'></input> 
    
    <br><br>
     <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-outline-dark" type="submit" value="등록" formaction="replyadd">등록하기</button>
   </div> 
  </form>
    
    
    
    