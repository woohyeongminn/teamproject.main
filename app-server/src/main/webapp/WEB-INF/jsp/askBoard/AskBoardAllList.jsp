<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
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
  a {
  color: black;
  }
</style>

<fieldset>
<br>
<c:choose>
  <c:when test="${not empty loginUser}">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
       <a href='permylist' >내 글</a> |
       <a href='alllist' >전체 글</a>
    </div>
  </c:when>
  
  <c:when test="${not empty loginCeoUser}">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
       <a href='ceomylist' >내 글</a>
       <a href='alllist' >전체 글</a>
    </div>
  </c:when>
  
  <c:when test="${empty loginUser && empty loginCeoUser}">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
       <a href="javascript:logout(this);" attr-a="onclick : attr-a">내 글</a> |
       <a href='alllist' >전체 글</a>
    </div>  
  </c:when>
</c:choose>
<hr>
  <table class="table table-responsive text-center">
	  <thead>
	    <tr id="head">
			   <th>번호</th>
			   <th>제목</th>
			   <th>작성자</th>
			   <th>조회수</th>
			   <th>등록일</th>
			   <th>답변</th>
	    </tr>   
    </thead>
    
    <tbody>
       <c:forEach items="${askBoardList}" var="askBoard">
          <c:choose>
            <c:when test="${askBoard.askStatus == 1}">
              <tr>
                <c:choose>
                  <c:when test="${askBoard.askMemberWriter.perStatus == 1}">
					           <td>${askBoard.askNo}.</td>
					           <td><a href='alldetail?askNo=${askBoard.askNo}'>${askBoard.askTitle}</a></td>
								     <td>[개인]${askBoard.askMemberWriter.perNickname}</td>
								     <td>${askBoard.askVeiwCount}</td>
								     <td>${askBoard.askRegisteredDate}</td>
                     <c:choose>
                        <c:when test="${empty askBoard.reply}">
                           <td> 🗨 </td>
                        </c:when>
                        <c:otherwise>
                           <td> 💬 </td>
                        </c:otherwise>
                     </c:choose>
                  </c:when>
                  <c:when test="${askBoard.askCeoWriter.ceoStatus == 2}">
                     <td>${askBoard.askNo}.</td>
								     <td><a href='alldetail?askNo=${askBoard.askNo}'>${askBoard.askTitle}</a></td>
								     <td>[사장]${askBoard.askCeoWriter.ceoNickname}</td>
								     <td>${askBoard.askVeiwCount}</td>
								     <td>${askBoard.askRegisteredDate}</td>
			               <c:choose>
                        <c:when test="${empty askBoard.reply}">
                           <td> 🗨 </td>
                        </c:when>
			                  <c:otherwise>
			                     <td> 💬 </td>
			                  </c:otherwise>
			               </c:choose>
                  </c:when>
                </c:choose>
              </tr>
            </c:when>
            <c:otherwise>
              <tr>
                <td>${askBoard.askNo}.</td>
				        <td></td>
					        <td><a href="javascript:lockAskBaord(this);" attr-a="onclick : attr-a">🔐 비공개</a></td>
					        <td></td><td></td><td></td>
			        </tr>
            </c:otherwise>
          </c:choose>
       </c:forEach>
    </tbody>
  </table>
</fieldset>

<c:if test="${empty askBoardList}">
 <div style="text-align: center"> 
  <b style="font-size:14; text-align: center">❕❔ 등록된 게시글이 없습니다.</b>
 </div> 
</c:if>

<c:choose>
	<c:when test="${not empty loginUser}">
	  <div class="d-grid gap-2 d-md-flex justify-content-md-end">
		   <a href='peraddform' type="button" class ="btn btn-outline-dark">등록하기</a>
	  </div>
	</c:when>
	
	<c:when test="${not empty loginCeoUser}">
		<div class="d-grid gap-2 d-md-flex justify-content-md-end">
	     <a href='ceoaddform' type="button" class = "btn btn-outline-dark">등록하기</a>
		</div>
	</c:when>
	
	<c:when test="${empty loginUser && empty loginCeoUser}">
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
       <a id="notlogin" type="button" class = "btn btn-outline-dark" onclick="logout();">등록하기</a>
    </div>	
	</c:when>
</c:choose>


<script>
document.querySelectorAll("tbody a").forEach((aTag) => {
  aTag.onclick = () => false;
});

var trList = document.querySelectorAll("tbody tr"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    //console.log(e.currentTarget.querySelector("a").href);
    //e.currentTarget.querySelector("a").click();
    window.location.href = e.currentTarget.querySelector("a").href;
    //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
  };
});

function lockAskBaord(obj) {
	alert("비공개 처리된 문의글입니다.")
	}

function logout(obj) { 
	  alert("로그인 해주세요.")
	  }
</script>







