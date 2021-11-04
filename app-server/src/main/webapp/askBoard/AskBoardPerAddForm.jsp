<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 게시글 등록(개인 회원)</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
    size:100px;
    }
  
  legend {
  text-align: center;
  }
 
  h5 {
      background-color: rgb(46, 45, 45);
      text-align: center;
      color: white;
      margin-top: 10px;
      font-size: 50px;
  }

  .title {
    f
  }
  

</style>
</head>

<body>
  <h5>💬문의글 등록</h5>
  <form action='peradd'>
  
    <label for='f-status'>공개</label>
    <select id="f-status" name='status' >
    <option value='1' name='status'>공개</option>
    <option value='2' name='status'>비공개</option>
    </select><br> 
    
    <div id="passwordRow">
      <label for='f-tempPW' size='100px'>🔑비밀번호</label>
      <input id='f-tempPW' type='password' name='tempPW' placeholder="4자리"><br>
    </div>
    
  
    <div class="title">
    <label for='f-title' class='form-label'>제목</label>
    <input id='f-title' type='text' name='title' placeholder="제목을 입력하세요"><br>
    </div>
    
    <div class="content">
    <label for='f-content' class='form-label' size='100px'>내용</label>
    <input id='f-content' type='text' name='content' placeholder="내용을 입력하세요"><br>
    </div>

    <input type ='hidden' name='writer' value='${member.perNo}'></input>      
  
    <br><br>
    <button type="submit" class="btn btn-primary" style='background-color: rgb(46, 45, 45)'>
      등록하기
    </button>
  </form>




<script>
var fStatus = document.querySelector("#f-status");
var passwordRow = document.querySelector("#passwordRow");

passwordRow.style["display"] = "none";

fStatus.addEventListener("input", function() {
	if (fStatus.value == "2") {
		passwordRow.style["display"] = "";
	} else {
	   passwordRow.style["display"] = "none";
	}
});
</script>
 </body>
</html>




