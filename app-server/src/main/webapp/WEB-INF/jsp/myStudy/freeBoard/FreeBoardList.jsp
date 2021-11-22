<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style>

  .main-panel {
    font-size: 14px;
    xjustify-content: center;
  }
  
  .content-wrapper {
    background-color: white;
    border-radius: 20px;
    max-height: 700px;
    padding: 30px;
    overflow-y: scroll;
  }
  
  .content-wrapper::-webkit-scrollbar {
    width: 10px;
  }
  .content-wrapper::-webkit-scrollbar-thumb {
  background-color: rgb(247, 231, 215);
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
  }
  .content-wrapper::-webkit-scrollbar-track {
  background-color: rgb(250, 250, 234);
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
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
            
          </div> <%-- end row sub-items --%>
        
       </div> <%-- end content-wrapper --%>
     </div> <%-- main-panel --%>
     
     </div>
   </div>

</body>
      