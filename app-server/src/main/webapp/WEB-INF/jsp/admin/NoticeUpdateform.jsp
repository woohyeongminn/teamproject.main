<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

   <!-- 아이콘 -->
  <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
  
  <style>
  .all-content {
    width: 100%;
    max-width: 900px;
    margin: 0 auto;
    font-size: 14px;
  }
  thead, tbody, tfoot, tr, td, th {
    border-color: black;
    border-style: solid;
    border-width: 0;
  }
  th {
  text-align: center;
  }
  label {
    margin-right: 5px;
    text-align: center;
    width: 60px;
  }
  label#click {
    width: 150px;
  }
  label#click:hover {
    color: silver;
  }
  input {
  width: 700px;
  font-size: 14px;
  text-align: center;
  border : white;
  outline-color : lightgray;
  font-weight: 400;
  }
  input#chooseFile {
   font-size: 12px;
   color: white;
   line-height: 12px;
   /*mix-blend-mode: color;*/
  }
  input::file-selector-button {
  display: none;
  }
  tr#blockbox {
    vertical-align: top;
  }
  #f-content {
  text-align: justify;
  margin: 0;
  word-wrap: break-word;
  width: 700px;
  height: 150px;
  font-size: 14px;
  min-height: 200px;
  letter-spacing: 0;
  border: 0px solid white;
  outline-color: lightgray;
  border-radius: 1px;
  }
  .img_button {
  text-align: center;
  }
  #f-photo-image {
  height: 300px;
  }
  .click_button {
  text-align: center;
  }
  button[type=submit] {
    font-size: 14px;
    line-height: 14px;
  }
  </style>
  
</head>
<body>

<section>
<fieldset>

<div class="all-content">

<form id="notice-update" action='update' method='post' enctype="multipart/form-data">
  
  <br>
  <table class="table table-responsive">
  <thead>
  
  <tr>
    <th scope="col"></th>
    <th scope="col">🔔 공지게시판 게시글 변경</th>
    <th scope="col">&emsp;&emsp;&emsp;</th>
  </tr>
  
  <tr id="blockbox">
    <th scope="row"><label for='f-title'>제목</label></th>
    <td><input id='f-title' type='text' name='title' placeholder='${notice.adminNotiTitle}' autocomplete='off'></td>
    <td></td>
  </tr>
  
  <tr id="blockbox">
    <th scope="row"><label for='f-content' id="content">내용</label></th>
    <td><textarea id='f-content' type='text' name='content' rows="20" wrap="virtual" autocomplete='off' placeholder='${notice.adminNotiContent}'></textarea></td>
    <td></td>
  </tr>
  
  <tr id="blockbox">
    <th scope="row"><label for='f-filepath'>파일</label></th>
    <td><div class="img_button">
    <img id="f-photo-image" src="../upload/notice/${notice.adminNotiFile}"></div>
    <div class="click_button">
              <br>
              <label for="chooseFile" id="click"><b>👉 CLICK HERE! 👈</b></label>
          </div>
          <input type="file" id="chooseFile" name="filepath" accept="image/*" onchange="loadFile(this)"></td>
    <td></td>
  </tr>
  
  </thead>
  </table>
  
  <input type='hidden' name='no' value='${notice.adminNotiNo}'>
  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
  <button type="submit" class="btn btn-outline-dark" value="변경">변경</button>
  </div>
  
</form>

</div>

</fieldset>
</section>

<script>
document.querySelector("#notice-update").onsubmit = () => {
  if (document.querySelector("#f-title").value == "" ||
        document.querySelector("#f-content").value == "") {
    /* window.alert("필수 입력 항목이 비어 있습니다.") */
    Swal.fire('제목이나 내용을 입력해 주세요.')
    return false; // 일단 서버에 보내지 마
  }
};
</script>
