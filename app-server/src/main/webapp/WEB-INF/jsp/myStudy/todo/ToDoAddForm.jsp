<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<style>
  * {
  font-size: 14px;
  }
  .all-content {
    width: 100%;
    max-width: 900px;
    margin: 0 auto;
    font-size: 14px;
    text-align: center;
  }
  input {
    border: white;
    outline-color: white;
    text-align: justify;
    width: 700px;
  }
  #f-content {
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
  border-color: white;
  border-radius: 1px;
  }
  tr#first {
    border-bottom: solid;
    line-height: 50px;
  }
  th#title {
    font-weight: bolder;
    font-size: 18px;
  }
  tr#bottombox {
    border-color: black;
    border-bottom: solid;
  }
  thead, tbody, tfoot, tr, td, th {
    border-color: #e5e5e5;
    border-style: solid;
    border-width: 0;
  }
  tr#blockbox {
    vertical-align: top;
  }
  th#middlebox {
  text-align: justify;
   width: 100px;
   vertical-align: top;
  }
  button[type=submit] {
    margin-right: 10px;
    margin-bottom: 300px;
    font-size: 14px;
    line-height: 14px;
  }
  </style>
  
</head>

<fieldset>
<div class="all-content">
<form action='add' method='post'>

<input type='hidden' name='studyNo' value='${studyno}'>
<%-- <input type='hidden' name='perno' value='${member.perNo}'> --%>

	<br>
	<table class="table table-responsive text-center">
	<thead>
	
  <tr id="first">
    <th scope="col"></th>
    <th scope="col" id="title">📖 | To-Do List 등록</th>
    <th scope="col">&emsp;&emsp;&emsp;</th>
  </tr>
	
	<tr id="blockbox">
    <th scope="row" id="middlebox"><label for='f-progress_no'>상태</label></th>
    <td><input id='f-progress_no' type='text' name='todocomplete' value="진행 중" readonly></td>
    <td><input id='f-progress_no' type='hidden' name='todoStatus' value="1" readonly></td>
  </tr>
  
  <tr id="blockbox">
    <th scope="row" id="middlebox"><label for='f-content'>내용</label></th>
    <td><textarea id='f-content' type='text' name='todoContent'></textarea></td>
    <td></td>
  </tr>
	
  <tr id="blockbox">
    <th scope="row" id="middlebox"><label for='f-note'>비고</label></th>
    <td><input id='f-note' type='text' name='todoRemark'></td>
    <td></td>
  </tr>
  
  <tr id="bottombox">
    <th scope="row" id="middlebox"><label for='f-writer'>작성자</label></th>
    <td>
    <input id='f-writer' type='text' value='${member.perNickname}' readonly></td>
    <td></td>
  </tr>
	
	</thead>
	</table>
	
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
	<button type="submit" class="btn btn-outline-dark" value="등록" onclick="addnotice();">등록</button>
	</div>

</form>
</div>
</fieldset>

<script type="text/javascript">
/* function addnotice(){
    Swal.fire({
      title: '📖 | To-Do List 등록',
      text: "정말 등록하시겠습니까?",
      icon: 'question',
      showCancelButton: true,
      timer: 50000,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '네',
      cancelButtonText: '아니오'
      }).then((result) => {
        if (result.value) {
          '📖 | To-Do List 등록',
          '등록이 완료되었습니다.',
          'success'
          }
        })
      } */
</script>