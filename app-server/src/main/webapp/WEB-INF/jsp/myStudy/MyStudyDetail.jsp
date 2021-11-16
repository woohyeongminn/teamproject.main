<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
  }

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
    
    button#buttonsave {
    background-color: white;
    border-color: lightgray;
		}
		button#buttonsave:hover {
		    color: lightgray;
		}
</style>
  
  <br><br><br>
  
<div class="all-content">
  
  <input type='hidden' name='subjectNo' value='${study.subjectNo}'>
  
  <br>
  <table class="table table-hover text-center">
  <thead>
  
  <tr id="first">
    <th scope="col"></th>
    <th scope="col" id="title">| 📰 ${study.studyTitle} ✏ |</th>
    <th scope="col">
		<c:choose>
			<c:when test="${study.countMember < study.numberOfPeple}">
	    [모집 중]
	    </c:when>
			<c:otherwise>
	    [모집 완료]
	    </c:otherwise>
		</c:choose>
		</th>
	</tr>
	
	<tr id="middlebox">
    <th scope="row"><label for='f-studyNo'>번호</label></th>
    <td><input id='f-studyNo' type='text' name='studyNo' value="${study.studyNo}" readonly></td>
    <td></td>
  </tr>
  
  <tr id="middlebox">
    <th scope="row"><label for='f-bookMember'>북마크</label></th>
    <td><input id='f-bookMember' type='text' name='bookMember' value="${study.countBookMember}" readonly></td>
    <td></td>
  </tr>
  
  <tr id="middlebox">
    <th scope="row"><label for='f-owner'>조장</label></th>
    <td><input id='f-owner' type='text' name='owner' value="${study.owner.perNickname}" readonly></td>
    <td></td>
  </tr>
  
  <tr id="middlebox">
    <th scope="row"><label for='f-subjectName'>분야</label></th>
    <td><input id='f-subjectName' type='text' name='subjectName' value="${study.subjectName}" readonly></td>
    <td></td>
  </tr>
  
  <tr id="middlebox">
    <th scope="row"><label for='f-area'>지역</label></th>
    <td><input id='f-area' type='text' name='area' value="${study.area}" readonly></td>
    <td></td>
  </tr>
  
  <tr id="middlebox">
    <th scope="row"><label for='f-countMember'>인원수</label></th>
    <td><input id='f-countMember' type='text' name='countMember' value="${study.countMember}" readonly></td>
    <td></td>
  </tr>
  
  <tr id="middlebox">
    <th scope="row"><label for='f-numberOfPeple'>최대 인원수</label></th>
    <td><input id='f-numberOfPeple' type='text' name='numberOfPeple' value="${study.numberOfPeple}" readonly></td>
    <td></td>
  </tr>
  
  <tr id="middlebox">
    <th scope="row"><label for='f-faceName'>대면 상태</label></th>
    <td><input id='f-faceName' type='text' name='faceName' value="${study.faceName}" readonly></td>
    <td></td>
  </tr>
  
  <tr id="blockbox">
    <th scope="row"><label for='f-introduction'>소개글</label></th>
    <td><textarea id='f-introduction' type='text' name='introduction' rows="3" wrap="virtual" readonly>${study.introduction}</textarea></td>
    <td></td>
  </tr>
  
  <tr id="bottombox">
    <th scope="row"><label for='f-point'>활동 점수</label></th>
    <td><input id='f-point' type='text' name='point' value="${study.point}" readonly></td>
    <td></td>
  </tr>
	
	</thead>
	</table>
	
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
    <button type="submit" class="btn btn-outline-dark" value="목록"><a href='list'>목록</a></button>
	    <c:if test='${study.owner.perNo == loginUser.perNo}'>
		    <button type="submit" class="btn btn-outline-dark" value="변경" formaction="updateform">
		       <a href='${contextPath}/app/mystudy/updateform?studyno=${study.studyNo}'>변경</a></button>
		    <button type="submit" class="btn btn-outline-dark" value="삭제">
		       <a href='${contextPath}/app/mystudy/delete?studyno=${study.studyNo}'>삭제</a></button>
		  </c:if>
	  <button type="submit" class="btn btn-outline-dark" value="탈퇴">
      <a href='${contextPath}/app/mystudy/exit?studyno=${study.studyNo}'>탈퇴</a></button>
    <button type="submit" class="btn btn-outline-dark" value="구성원">
      <a href='${contextPath}/app/mystudy/guilder/list?studyNo=${study.studyNo}'>구성원</a></button>
    <button type="submit" class="btn btn-outline-dark" value="캘린더">
      <a href='${contextPath}/app/mystudy/calendar/list'>캘린더</a></button>
    <button type="submit" class="btn btn-outline-dark" value="자유 게시판">
      <a href="${contextPath}/app/mystudy/freeboard/list?studyno=${study.studyNo}">자유 게시판</a></button>
<%--     <button type="submit" class="btn btn-outline-dark" value="To-Do">
      <a href='${contextPath}/app/mystudy/todo/list?studyno=${study.studyNo}&perno=${loginUser.perNo}'>To-Do</a></button>
 --%>    <button type="submit" class="btn btn-outline-dark" value="화상미팅">
      <a href='list'>화상미팅</a></button>
  
  
  
  <!--  모달 시작 -->
  <button id="popup_open_btn" class="btn btn-outline-dark">To-Do</button>
  <div id="my_modal">
   <h4>📋 오늘의 공부</h4>
   
  <div class="input-group mb-3" style="width: 50%;">
    <form action='todo/add' method='post' id="todobox">
    <input type='hidden' name='studyNo' value='${study.studyNo}'>
	    <input type="text" class="form-control" id="input" name='todoContent'>
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
        <li class="list-group-item">${todo.todoContent}
          <form action='todo/delete' id="tododelete">
          <input type='hidden' name='studyno' value='${study.studyNo}'>
          <input type='hidden' name='todono' value='${todo.todoNo}'>
        <button style='float: right;' class='btn btn-outline-dark' type='submit' onclick='remove("+cnt+")'>삭제</button>
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
/* document.querySelector("#todobox").onsubmit = () => {
	  if (document.querySelector("#input").value == "") {
	    Swal.fire('내용을 입력해 주세요.')
	    return false;
	  }
	}; */


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
