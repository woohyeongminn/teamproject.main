<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
  * {
  font-size: 14px;
  }
  .allbox {
  max-width:1000px; 
  margin: 0 auto; 
  position:relative; 
  }
  tr#first {
    border-bottom: solid;
    line-height: 50px;
  }
  th#title {
    font-weight: bolder;
    font-size: 18px;
  }
  th#date {
    font: small-caption;
    width: 100px;
  }
  tr#bottom {
    border-color: black;
    border-bottom: solid;
  }
  th#middlebox {
  text-align: justify;
   width: 100px;
   vertical-align: top;
  }
  label {
   display: inline-block;
   margin-left: 10px;
  }
  input {
  font-size: 14px;
   line-height: 14px;
   width: 700px;
   border: white;
   outline-color: white;
  }
  #todoContent {
  text-align: justify;
  margin: 0;
  word-wrap: break-word;
  width: 700px;
  height: 100px;
  font-size: 14px;
  max-height: 110px;
  letter-spacing: 0;
  border: 0px solid white;
  outline-color: white;
  border-radius: 1px;
  }
  button {
  font-size: 14px;
  line-height: 14px;
  }
  a {
  color : black;
  text-decoration : auto;
  }
  a:hover {
  color : white;
  }
</style>

<br><br><br>

<div class="allbox">

    <table class="table table-hover text-center">
    <thead>
    
    <tr id="first">
      <th scope="col"></th>
      <th scope="col" id="title">📖 | To-Do List 상세</th>
      <th scope="col" id="date">${todo.todoDate}</th>
    </tr>
    
    <tr>
      <th scope="row" id="middlebox"><label for='status'>상태</label></th>
      <td>
        <c:choose>
        <c:when test="${todo.todoStatus == 1}">
        <input id='status' type='text' name='status' value="진행 중" readonly>
        </c:when>
        <c:otherwise>
        <input id='status' type='text' name='status' value="완료" readonly>
        </c:otherwise>
        </c:choose>
      </td>
      <td></td>
    </tr>
    
    <tr>
      <th scope="row" id="middlebox"><label for='todono'>번호</label></th>
      <td><input id='todono' type='text' name='todono' value="${todo.todoNo}" readonly></td>
      <td></td>
    </tr>
    
    <tr>
      <th scope="row" id="middlebox"><label for='todoContent'>내용</label></th>
      <td><textarea id='todoContent' type='text' name='todoContent' rows='5' wrap="virtual" readonly>${todo.todoContent}</textarea></td>
      <td></td>
    </tr>
    
    <tr>
      <th scope="row" id="middlebox"><label for='todoRemark'>비고</label></th>
      <td><input id='todoRemark' type='text' name='todoRemark' value="${todo.todoRemark}" readonly></td>
      <td></td>
    </tr>
    
    <tr id="bottom">
      <th scope="row" id="middlebox"><label for='todoWriter'>작성자</label></th>
      <td><input id='todoWriter' type='text' name='todoWriter' value="${todo.todoWriter.perNickname}" readonly></td>
      <td></td>
    </tr>
    
    </thead>
    </table>
    
  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button class="btn btn-outline-dark"><a href="list?todono=${todo.todoNo}&perno=${member.perNo}&studyno=${study.studyNo}">목록</a></button>
      <c:if test='${todo.todoWriter.perNickname eq member.perNickname}'>
      <button class="btn btn-outline-dark"><a href="updateform?todono=${todo.todoNo}&perno=${member.perNo}&studyno=${study.studyNo}">변경</a></button>
      <button class="btn btn-outline-dark"><a href="delete?todono=${todo.todoNo}&perno=${member.perNo}&studyno=${study.studyNo}">삭제</a></button>
      </c:if>
  </div>

</div>

