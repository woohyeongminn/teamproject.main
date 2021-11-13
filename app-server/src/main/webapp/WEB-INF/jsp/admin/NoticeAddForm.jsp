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
    text-align: center;
  }
  label {
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
  thead, tbody, tfoot, tr, td, th {
    border-color: black;
    border-style: solid;
    border-width: 0;
  }
  tr#blockbox {
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

<section>
<fieldset>
<div class="all-content">

<form id="notice-add" action='add' method='post' enctype="multipart/form-data">

  <br>
  <table class="table table-responsive text-center">
  <thead>
  
  <tr>
    <th scope="col"></th>
    <th scope="col">🔔 공지게시판 게시글 등록</th>
    <th scope="col">&emsp;&emsp;&emsp;</th>
  </tr>
  
  <tr id="blockbox">
    <th scope="row"><label for='f-title'>제목</label></th>
    <td><input id='f-title' type='text' name='adminNotiTitle' placeholder="제목을 입력해 주세요." autocomplete='off'></td>
    <td></td>
  </tr>
  
  <tr id="blockbox">
    <th scope="row"><label for='f-content' id="content">내용</label></th>
    <td><textarea id='f-content' name='adminNotiContent' rows="20" wrap="virtual" autocomplete='off' placeholder="내용을 입력해 주세요."></textarea></td>
    <td></td>
  </tr>
  
  <tr id="blockbox">
    <th scope="row"><label for='f-filepath'>파일</label></th>
    <td><img style="width: 200px;" id="preview-image" src="https://dummyimage.com/200x200/ffffff/000000.png&text=preview">
    <div class="button">
              <label for="chooseFile" id="click"><b>👉 CLICK HERE! 👈</b></label>
          </div>
          <input type="file" id="chooseFile" name="filepath" accept="image/*" onchange="loadFile(this)"></td>
    <!-- <input style="display: block;" type="file" name="filepath" id="input-image"> -->
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
</section>

<script>
function readImage(input) {
    // 인풋 태그에 파일이 있는 경우
    if(input.files && input.files[0]) {
        // 이미지 파일인지 검사 (생략)
        // FileReader 인스턴스 생성
        const reader = new FileReader()
        // 이미지가 로드가 된 경우
        reader.onload = e => {
            const previewImage = document.getElementById("preview-image")
            previewImage.src = e.target.result
        }
        
        // reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0])
    }
}
// input file에 change 이벤트 부여
const inputImage = document.getElementById("chooseFile")
inputImage.addEventListener("change", e => {
    readImage(e.target)
})
</script>

<script>
document.querySelector("#notice-add").onsubmit = () => {
  if (document.querySelector("#f-title").value == "" ||
        document.querySelector("#f-content").value == "") {
    /* window.alert("필수 입력 항목이 비어 있습니다.") */
    Swal.fire('제목이나 내용을 입력해 주세요.')
    return false; // 일단 서버에 보내지 마
  }
};
</script>

<script type="text/javascript">

function addnotice(){
	  Swal.fire({
	    title: '🔔 공지게시글 등록',
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
	    	  '🔔 공지게시글 등록',
	        '공지게시글 등록이 완료되었습니다.',
	        'success'
	        }
	      })
	    }
	    
/*   if (document.querySelector("#f-title").value != "" &&
	        document.querySelector("#f-content").value != "") {
    Swal.fire({
        title: '🔔 공지게시글 등록',
        text: "정말 등록하시겠습니까?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '네',
        cancelButtonText: '아니오'
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire(
            '🔔 공지게시글 등록',
            '공지게시글 등록이 완료되었습니다.',
            'success'
            return true;
          )
        }
      })
  } */

</script>

