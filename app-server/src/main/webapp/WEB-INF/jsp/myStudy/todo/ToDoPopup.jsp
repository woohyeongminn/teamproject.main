<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
/* 	* {
	font-size: 14px;
	}
  .all-content {
    width: 100%;
    max-width: 900px;
    margin: 0 auto;
    font-size: 14px;
  }
  tr#bottombox {
    border-color: black;
  }
  thead, tbody, tfoot, tr, td, th {
    border-style: solid;
    border-width: 0;
  }
  tr#middlebox {
  border-color: #d1d0d0;
  }
  tr#blockbox {
    vertical-align: top;
  }
	th#countbox {
	    font: small-caption;
	    color: #9ba8b3;
	}
	tr#first {
    border-bottom: solid;
  }
  th#title {
    font-size: 18px;
    letter-spacing: 5px;
    line-height: 50px;
  }
  #f-introduction {
  text-align: justify;
  margin: 0;
  word-wrap: break-word;
  width: 500px;
  height: 50px;
  font-size: 14px;
  min-height: 200px;
  letter-spacing: 0;
  border: 0px solid white;
  outline-color: lightgray;
  border-radius: 1px;
  }
  label {
    text-align: justify;
    width: 80px;
  }
  input {
    width: 500px;
    font-size: 14px;
    text-align: justify;
    border: white;
    outline-color: white;
    font-weight: 400;
  }
  button[type=submit] {
    margin-right: 3px;
    font-size: 14px;
    line-height: 14px;
  }
  a {
  color : black;
  text-decoration : blink;
  }
  a:hover {
  color : white;
  } */

/* 모달 */
#my_modal {
  text-align: -webkit-center;
  display: none;
  width: 700px;
  padding: 20px 60px;
  background-color: rgb(251 246 240);
  border: 1px solid #888;
  border-radius: 15px;
}

#my_modal .modal_close_btn {
  position: absolute;
  top: 10px;
  right: 10px;
}
  
img#logo {
  width: 200px;
  margin-bottom: 10px;
}
  
button#buttonsave {
  margin-top: 5px;
  margin-right: 18px;
  background-color: white;
  float: right;
  color: #212529;
  border-color: #757c83;
}
button#buttonsave:hover {
	background-color: black;
	color: white;
}
</style>

  <!--  모달 시작 -->
  <div id="my_modal">
  
   <div class="top">
      <img src="${contextPath}/img/logo.png" id="logo">
   </div>
   
  <div class="input-group mb-3" style="width: 50%;">
    <form action='todo/add' method='post' id="todobox">
    <input type='hidden' name='studyNo' value='${study.studyNo}'>
	    <input type="text" class="form-control" id="input" name='todoContent' style="width: 289px;">
	      <input id='f-progress_no' type='hidden' class="form-control" name='todocomplete' value="진행 중" readonly>
	      <input id='f-progress_no' type='hidden' class="form-control" name='todoStatus' value="1" readonly>
	      <input id='f-writer' type='hidden' class="form-control" value='${member.perNickname}' readonly>
      <button class="btn btn-outline-dark" type="submit" id="buttonsave">입력</button>
    </form>
  </div>
  
  <ul class="list-group" style="width: 50%;" id="list">
    <li class="list-group-item">📋 TO DO LIST</li>
	    <c:if test="${empty todoList}">
	      등록된 To-Do List가 없습니다.
	    </c:if>
    <c:forEach items="${todoList}" var="todo">
      <c:if test="${not empty todoList}">
        <li class="list-group-item" style="text-align: justify;">${todo.todoContent}
          <form action='todo/delete' id="tododelete">
          <input type='hidden' name='studyno' value='${study.studyNo}'>
          <input type='hidden' name='todono' value='${todo.todoNo}'>
        <button style='float: right; border-color: #757c83;' class='btn btn-outline-dark' type='submit' onclick='remove("+cnt+")'>삭제</button>
          </form>
        </li>
      </c:if>
    </c:forEach>
  </ul>
    <a class="modal_close_btn">닫기</a>
</div>
  </div>
  <!-- // 모달 -->

</div>

<script>
function modal(id) {
    var zIndex = 9999;
    var modal = document.getElementById(id);

    // 모달 div 뒤에 희끄무레한 레이어
    var bg = document.createElement('div');
    bg.setStyle({
        position: 'fixed',
        zIndex: zIndex,
        left: '0px',
        top: '0px',
        width: '100%',
        height: '100%',
        overflow: 'auto',
        // 레이어 색갈은 여기서 바꾸면 됨
        backgroundColor: 'rgba(0,0,0,0.4)'
    });
    document.body.append(bg);

    // 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
    modal.querySelector('.modal_close_btn').addEventListener('click', function() {
        bg.remove();
        modal.style.display = 'none';
    });

    modal.setStyle({
        position: 'fixed',
        display: 'block',
        boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',

        // 시꺼먼 레이어 보다 한칸 위에 보이기
        zIndex: zIndex + 1,

        // div center 정렬
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        msTransform: 'translate(-50%, -50%)',
        webkitTransform: 'translate(-50%, -50%)'
    });
}

// style 한번에 오브젝트로 설정(위에 함수로 준 스타일)
Element.prototype.setStyle = function(styles) {
    for (var k in styles) this.style[k] = styles[k];
    return this;
};

document.getElementById('popup_open_btn').addEventListener('click', function() {
    // 모달창 띄우기
    modal('my_modal');
});

var button = document.getElementById('buttonsave');
var input = document.getElementById('input');
var list = document.getElementById('list');
var cnt = 1;

button.addEventListener('click', clickButton);

function clickButton() {
  var temp = document.createElement('li');
  temp.setAttribute("class", "list-group-item");
  temp.setAttribute("id", "li"+cnt);
  temp.innerHTML = input.value;
  temp.innerHTML += "<button style='float: right;' class='btn btn-outline-dark' type='button' onclick='remove("+cnt+")'>삭제</button>";
  list.appendChild(temp);
  cnt++;
}

function remove(cnt) {
  //window.alert(cnt);
  var li = document.getElementById('li'+cnt);
  list.removeChild(li);
}
</script>
