<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <style>
  * {
  font-size: 14px;
  }
  .allbox {
  max-width:1000px; 
  margin: 0 auto; 
  position:relative; 
  }
   p#titlename {
   font-weight: bolder;
   font-size: 20px;
  }
  #countProgressing {
  color: white;
  outline: none;
  border: white;
  }
  #not_countProgressing {
  color: black;
  outline: none;
  border: white;
  width: 300px;
  text-align: center;
  font-size: 16px;
  }
  thead, tbody, tfoot, tr, td, th {
    border-color: inherit;
    border-style: solid;
    border-width: 0;
    text-align: center;
  }
  td#box_two {
    border-color: white;
  }
  td#boxsize {
    width: 300px;
    border-color: white;
  }
  td#textbox {
    text-overflow: ellipsis;
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
	<p id="titlename">📖 | To-Do List 목록 </p>
	
	<table class="table table-hover">
		<thead>
		
			<tr id="topbox">
				<th>상태</th>
				<th>번호</th>
				<th>내용</th>
				<th>비고</th>
				<th>작성자</th>
				<th>날짜</th>
			</tr>
			
			</thead>
		<tbody>
		
		<tr>
			<c:forEach items="${countProgressing}" var="todo">
				<tr data-no="${todo.todoNo}">
					<td>${todo.todocomplete}</td>
					<td>${todo.todoNo}</td>
					<td id="textbox"><a href="detail?todono=${todo.todoNo}&studyno=${study.studyNo}&perno=${member.perNo}">${todo.todoContent}</a></td>
					<td id="textbox">${todo.todoRemark}</td>
					<td>${todo.todoWriter.perNickname}</td>
					<td>${todo.todoDate}</td>
				</tr>
			</c:forEach>
		
		<td id="box_two"></td>
    <td id="box_two"></td>
    <td id="boxsize">
      <c:if test='${empty countProgressing}'>
        <input id="not_countProgressing" type="text" name="countProgressing" value="[등록된 To-Do List가 없습니다.]" readonly>
      </c:if>
    </td>
    <td id="box_two"></td>
    <td id="box_two"></td>
    <td id="box_two"></td>
		</tr>
		
		</tbody>
	</table>
	
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button class="btn btn-outline-dark"><a href="addform?studyno=${study.studyNo}&perno=${member.perNo}">등록</a></button>
  </div>
 
</div>

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