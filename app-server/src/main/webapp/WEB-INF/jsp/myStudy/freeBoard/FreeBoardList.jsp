<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style>

  .main-panel {
    font-size: 14px;
    xjustify-content: center;
  }
  
  #empty-freeboard {
    text-align: center;
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
            
            <div id="button" class="d-grid gap-5 d-md-flex justify-content-md-center">
				      <a href="form?studyno=${studyno}" class="btn btn-light">등록</a>
				    </div>
				    <br>
				    <c:if test="${not empty freeBoardList}">
				      <div class="row row-cols-1 row-cols-md-3 g-3">
				        <c:forEach items="${freeBoardList}" var="freeBoard">
				          <div class="col">
				            <div class="card" style="border: 1px solid #e3e3e3;">
				              <div class="card-body">
				                <h5 class="card-title" style="font-weight: bold">
				                  <a
				                    href="detail?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}"
				                    >${freeBoard.freeBoardTitle}</a
				                  >
				                </h5>
				                <p class="card-text">${freeBoard.freeBoardContent}</p>
				                <p class="card-text">
				                  <small class="text-muted"
				                    >${freeBoard.freeBoardWriter.perNickname}</small
				                  >
				                  <small class="text-muted"
				                    >👀 ${freeBoard.freeBoardViewcount}</small
				                  >
				                </p>
				              </div>
				            </div>
				          </div>
				        </c:forEach>
				      </div>
				    </c:if>
				    <div id="empty-freeboard">
				      <c:if test="${empty freeBoardList}"> <br>등록된 게시글이 없습니다. </c:if>
				    </div>
				    <!-- 이미지 -->
				    <!-- <c:if test='${not empty freeBoardList}'>
				<c:forEach items="${freeBoardList}" var="freeBoard">
				<div class="card mb-3" style="max-width: 540px;">
				  <div class="row g-0">
				    <div class="col-md-4">
				      <img src="${contextPath}/upload/upload/freeboard/"${freeboard.freeboardImgs[0].name} class="img-fluid rounded-start" alt="...">
				    </div>
				    <div class="col-md-8">
				      <div class="card-body">
				        <h5 class="card-title">
				          <a href="detail?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}">${freeBoard.freeBoardTitle}</a>
				        </h5>
				        <p class="card-text">${freeBoard.freeBoardContent}</p>
				        <p class="card-text"><small class="text-muted">${freeBoard.freeBoardWriter.perNickname}</small></p>
				      </div>
				    </div>
				  </div>
				</div>
				</c:forEach>
				</c:if>
				<c:if test="${empty freeBoardList}">
				  등록된 게시글이 없습니다.
				</c:if> -->
				    <!-- 테이블 -->
				    <!-- <table class="table table-hover">
				    <thead>
				      <tr>
				        <th>No.</th>
				        <th>제목</th>
				        <th>작성자</th>
				        <th>조회수</th>
				      </tr>
				      </thread>
				    <tbody>
				      <c:forEach items="${freeBoardList}" var="freeBoard">
				        <tr>
				          <td>${freeBoard.freeBoardNo}</td>
				          <td><a
				            href="detail?studyno=${freeBoard.studyNo}&freeboardno=${freeBoard.freeBoardNo}">${freeBoard.freeBoardTitle}</a></td>
				          <td>${freeBoard.freeBoardWriter.perNickname}</td>
				          <td>${freeBoard.freeBoardViewcount}</td>
				        </tr>
				      </c:forEach>
				    </tbody>
				  </table>
				  <c:if test="${empty freeBoardList}">
				    등록된 게시글이 없습니다.
				  </c:if> -->
            
            
          </div> <%-- end row sub-items --%>
        
       </div> <%-- end content-wrapper --%>
     </div> <%-- main-panel --%>
     
     </div>
   </div>

</body>
      