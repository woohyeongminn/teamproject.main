<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="all-content">
  <p>답변이 등록되었습니다.</p>
   <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class = "btn btn-outline-dark" type="submit" value="등록" formaction="peradd">
     <a href="../admin/askboard/list">문의글 목록</a>
     </button>
   </div> 
</div>   