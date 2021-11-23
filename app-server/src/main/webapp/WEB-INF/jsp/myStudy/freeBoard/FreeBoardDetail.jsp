<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<link rel="canonical" href="https://getbootstrap.kr/docs/5.1/examples/sticky-footer/">

<!-- Bootstrap core CSS -->
<link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<!-- Favicons -->
<link rel="apple-touch-icon" href="/docs/5.1/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/5.1/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/5.1/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
<link rel="icon" href="/docs/5.1/assets/img/favicons/favicon.ico">
<meta name="theme-color" content="#7952b3">

<style>
.inner-page {
    height: 1000px;
}

.main-panel {
    height: 800px;
}

 .bd-placeholder-img {
   font-size: 1.125rem;
   text-anchor: middle;
   -webkit-user-select: none;
   -moz-user-select: none;
   user-select: none;
 }

 @media (min-width: 768px) {
   .bd-placeholder-img-lg {
     font-size: 3.5rem;
   }
 }
 
 #empty-comment {
   text-align: center;
 }
 
 .lead {
    height: 300px;
    background-color: #f9f9f9;
    overflow: scroll;
    padding: 15px;
    width: 790px;
 }
 
 .f-btn {
    padding: 5px 7px;
    font-size: 14px;
 }
 
 .py-0 {
    width: 790px;
    text-align: right;
    margin-left: 20px;
 }
 
 .comment-wrap {
    display: flex;
    flex-direction: row;
 }
 
 .comment-top {
     display: inline-block;
    font-size: 13px;
    padding-bottom: 5px;
    padding-left: 2px
 }
 
 .commentList-wrap::-webkit-scrollbar {
    width: 10px;
  }
  .commentList-wrap::-webkit-scrollbar-thumb {
  background-color: rgb(247, 231, 215);
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
  }
  .commentList-wrap::-webkit-scrollbar-track {
  background-color: rgb(250, 250, 234);
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
  }
 
</style>
    
<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper">

      <jsp:include page="../MyStudyDetailNav.jsp"/>

      <%-- main-panel --%>
      <div class="main-panel">
        <div class="content-wrapper">
        
          <%-- row sub-items --%>
          <div class="row">
            
            <div class="d-flex flex-column h-50">
              <!-- Begin page content -->
                <div class="container">
                  <h3 class="mt-3" style="padding: 7px 10px; width: 790px;">${freeBoard.freeBoardTitle} &nbsp;
                  <span style="display: inline-block; font-size: 14px;"> | &nbsp; ${freeBoard.freeBoardWriter.perNickname}</span>
                  <span style="display: inline-block; font-size: 14px;"> | &nbsp; ${freeBoard.freeBoardRegisteredDate}</span> </h3>
                  <p class="lead" style="font-size: 16px;">${freeBoard.freeBoardContent}</p>
                </div>
              
              <!-- 버튼 -->
              <footer class="mt-auto py-0">
                <div class="btn-group">
                  <a href='list?studyno=${freeBoard.studyNo}' class="f-btn">목록</a>
                  <c:if test="${loginUser.perNo eq freeBoard.freeBoardWriter.perNo}">
	                  <a href='updateform?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}' class="f-btn "> |&nbsp; 수정</a>
	                  <a href='delete?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}' class="f-btn"> |&nbsp; 삭제</a>
                  </c:if>
                </div>
              </footer>
              
              <!-- 댓글 등록 -->
              <div class="card">
                <div class="card-body" style=" padding-bottom: 0;">
                <span class="comment-top">댓글작성</span>
                <!-- <form action='${contextPath}/app/freeboard/comment/add' method='post'> -->
                <form action='comment/add' method="post">
                  <!-- <input type='hidden' name='commentWriter' value='${member}'/> -->
                  <input type='hidden' name='studyNo' value='${freeBoard.studyNo}'/>
                  <input type='hidden' name='boardNo' value='${freeBoard.freeBoardNo}'/>
                  <div class="comment-wrap">
	                  <textarea id='f-commentText' type="text" name='commentText' class="form-control" style="width: 90%;"></textarea>
	                  <button class="btn btn-light" style=" padding: 22px 18px; border-radius: 11px; margin-left: 8px;">등록</button>
                  </div>
                  
                </form>
                </div>
              </div>
              
              <br>
              <div id="empty-comment">
                <c:if test="${empty commentList}">등록된 댓글이 없습니다.</c:if>
              </div>
                
                
                <div class="commentList-wrap" style="max-height: 300px; overflow-y: scroll;">
	                <!-- 댓글 목록 -->
	                <c:forEach items="${commentList}" var="comment">
	                <div class="card2">
	                <div class="card-body" style="padding: 5px 23px;">
	                  <span style="font-size: 15px;">${comment.commentText}</span><br>
	                  
	                  <span style="font-size: 12px;">${comment.commentWriter.perNickname} | ${comment.commentRegisteredDate}</span>
	                  <div class="btn-group" role="group" aria-label="Basic outlined example">
	                    <c:if test="${comment.commentWriter.perNo eq member.perNo}">
	                    
	                    <!-- 댓글 수정 -->
	                    <button class="btn btn-link" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo" style="font-size: 12px; padding: 3px 6px;">수정</button>
	                    
	                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	                      <div class="modal-dialog">
	                        <div class="modal-content">
	                            <div class="modal-header">
	                              <h5 class="modal-title" id="exampleModalLabel">댓글 수정</h5>
	                              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                            </div>
	                            <div class="modal-body">
	                              <!-- <form> -->
	                              <form action='comment/update' method="post">
	                                <input type='hidden' name='studyNo' value='${comment.studyNo}'/>
	                                <input type='hidden' name='boardNo' value='${comment.boardNo}'/>
	                                <input type='hidden' name='commentNo' value='${comment.commentNo}'/>
	                                <div class="mb-3">
	                                  <label for="message-text" class="col-form-label">내용</label>
	                                  <!-- <textarea class="form-control" id="message-text"></textarea> -->
	                                  <textarea type="text" class="form-control" id='f-commentText' name='commentText' ></textarea>
	                                </div>
	                                <button type="button" class="btn btn-light" data-bs-dismiss="modal">취소</button>
	                                <button class="btn btn-dark">수정</button>
	                              </form>
	                            </div>
	                            <!-- <div class="modal-footer">
	                            </div> -->
	                          <!-- </form> -->
	                        </div>
	                      </div>
	                    </div>
	                      <a href='comment/delete?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}&commentno=${comment.commentNo}' class="btn btn-link" style="font-size: 12px; padding: 3px 6px;">삭제</a>
	                    </c:if>
	                  </div>
	                  </div>
	                 </div>
	                </c:forEach>
               </div>
             </div>
            
          </div> <%-- end row sub-items --%>
        
        
       </div> <%-- end content-wrapper --%>
     </div> <%-- main-panel --%>
     
     </div>
   </div>


<script type="text/javascript">
  function checkValue() {

  var form = document.freeBoardInfo;

  if (!form.commentText.value) {
    alert("댓글을 입력하세요.");
    return false;
  }
}
</script>
</body>
      