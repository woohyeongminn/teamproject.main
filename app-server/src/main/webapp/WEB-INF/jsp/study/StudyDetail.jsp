<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
  <head>

    <style>
    .inner-page {
		  height: 81vmin;
		}
		
		.mb-3 select {
		  height: 38px;
		  width: 466px;
		  xwidth: 100px;
		}
		
		.modal-backdrop {
      position: relative;
    }

		.modal-backdrop.show {
      opacity: 0;
		}
		
		.modal-header {
		  background-color: white;
		}
    </style>

  </head>
  <body>

<main class="container">
  <div class="row g-5">
    <div class="col-md-8">
      <h3 class="pb-4 mb-4 fst-italic border-bottom">
        ${study.subjectName}
      </h3>

      <article class="blog-post">
        <h2 class="blog-post-title">${study.studyTitle}</h2>
        <p class="blog-post-meta">
        <td><fmt:formatDate value="${study.registeredDate}" pattern="yyyy-MM-dd" /></td> by ${study.owner.perNickname}</p>
        <p>${study.introduction}</p>
        <hr>
      </article>

      <article class="blog-post">
        <h3>About</h3>
        <table class="table">
          <thead>
            <tr>
              <th>대면 상태</th>
              <c:if test="${study.faceNo ne '2'}">
                <th>지역</th>
              </c:if>
              <th>인원수</th>
              <th>비고</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>${study.faceName}</td>
              <c:if test="${study.faceNo ne '2'}">
                <td>${study.area}</td>
              </c:if>
              <td>${study.numberOfPeple}</td>
              <td>
              <c:if test="${study.studyStatus eq '1'}">진행</c:if>
              <c:if test="${study.studyStatus eq '2'}">종료</c:if>
              </td>
            </tr>
          </tbody>
        </table>
      </article>
    </div>

    <div class="col-md-4">
      <div class="position-sticky" style="top: 2rem;">
        <div class="p-4 mb-3 bg-light rounded">
          <h4 class="fst-italic">
          <c:choose>
		        <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus ne '2'}">모집중</c:when>
		        <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus ne '2'}">모집중</c:when>
		        <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus eq '2'}">모집완료</c:when>
		        <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus eq '2'}">모집완료</c:when>
		      </c:choose>
          </h4>
          <p class="mb-0">
          참여 <ins>${study.countMember}</ins><sub>명</sub><br>
          승인 대기 <ins>${study.waitingCountMember}</ins><sub>명</sub><br>
          북마크 <ins>${study.countBookMember}</ins><sub>명</sub>
          </p>
        </div>

        <div class="p-4">
		      <h4 class="fst-italic">메뉴</h4>
		      <div class="btn-group-vertical">
		      <ol class="list-unstyled mb-0">

		        <c:if test="${study.owner.perNo eq loginUser.perNo}">
		        <!-- 스터디 수정 -->
		        <!-- <li><a href="updateform?studyno=${study.studyNo}" class="btn btn-link">수정</a></li> -->

		        <li><button type="button" class="btn btn-light" data-bs-toggle="modal"
              data-bs-target="#exampleModal" data-bs-whatever="@mdo">수정</button></li>

		      <div class="modal fade" id="exampleModal" tabindex="-1"
            aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">

                <!-- 상단 헤더 -->
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">스터디 수정</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal"
                    aria-label="Close"></button>
                </div>

                <div class="modal-body">
                  <form action='update' method='post'>
                    <input type="hidden" name="studyNo" value="${study.studyNo}">

                    <!-- 제목 -->
                    <div class="mb-3">
						          <label for='f-studyTitle'>제목</label>
						          <input id='f-studyTitle' type='text' name='studyTitle'
						            value="${study.studyTitle}" class="form-control" required
						            oninvalid="this.setCustomValidity('1자 이상 50자 이하로 입력하세요.')"
						            oninput="this.setCustomValidity('')">
						        </div>

                    <!-- 최대 인원수 -->
                    <div class="mb-3">
						          <label for='f-numberOfPeple'>최대 인원수</label>
						          <select name="numberOfPeple" value="${study.numberOfPeple}">
						            <option value="2" selected>2</option>
						            <option value="3" selected>3</option>
						            <option value="4" selected>4</option>
						            <option value="5" selected>5</option>
						            <option value="6" selected>6</option>
						            <option value="7" selected>7</option>
						            <option value="8" selected>8</option>
						            <option value="9" selected>9</option>
						            <option value="10" selected>10</option>
						            <option value="11" selected>11</option>
						            <option value="12" selected>12</option>
						            <option value="13" selected>13</option>
						            <option value="14" selected>14</option>
						            <option value="15" selected>15</option>
						            <option value="16" selected>16</option>
						            <option value="17" selected>17</option>
						            <option value="18" selected>18</option>
						            <option value="19" selected>19</option>
						            <option value="20" selected>20</option>
						            <option value="21" selected>21</option>
						            <option value="22" selected>22</option>
						            <option value="23" selected>23</option>
						            <option value="24" selected>24</option>
						            <option value="25" selected>25</option>
						            <option value="26" selected>26</option>
						            <option value="27" selected>27</option>
						            <option value="28" selected>28</option>
						            <option value="29" selected>29</option>
						            <option value="30" selected>30</option>
						          </select>
						        </div>

                    <!-- 대면 상태 -->
                    <div class="mb-3">
                      <label for='f-faceNo'>대면 상태</label>
						          <select name="faceNo" value="${study.faceNo}">
						            <option value="1" selected>대면</option>
						            <option value="2" selected>비대면</option>
						            <option value="3" selected>대면/비대면</option>
						          </select>
                    </div>

                    <!-- 소개글 -->
                    <div class="mb-3">
                      <label for='f-introduction'>소개글</label>
						          <textarea id='f-introduction' type='text' name='introduction'
						            value="${study.introduction}" class="form-control" rows="3"
						            required oninvalid="this.setCustomValidity('소개글을 입력하세요.')"
						            oninput="this.setCustomValidity('')">
						          </textarea>
                    </div>

                    <!-- 진행 상태 -->
                    <div class="mb-3">
                      <label for='f-studyStatus'>진행 상태</label>
						          <select name="studyStatus" value="${study.studyStatus}">
						            <option value="1" selected>진행</option>
						            <option value="2" selected>종료</option>
						          </select>
                    </div>

                    <!-- 하단 버튼 -->
                    <div class="modal-footer">
                      <button type="button" class="btn btn-light"
                        data-bs-dismiss="modal">취소</button>
                      <button class="btn btn-dark">수정</button>
                    </div>

                  </form>
                </div>

              </div>
            </div>
          </div>
		
		        <!-- 스터디 삭제 -->
		          <li><a href='delete?studyno=${study.studyNo}' class="btn btn-light">삭제</a><li>
		        </c:if>
		        
		        <c:if test="${loginUser ne null}">
		          <!-- 스터디 참여 -->
		          <c:if test="${study.owner.perNo ne loginUser.perNo}">
	              <c:if test="${guilder == 'false'}">
	                <li><a href='join?studyno=${study.studyNo}' class="btn btn-light">참여 신청</a></li>
	              </c:if>
		          </c:if>
		          
		          <!-- 스터디 북마크 -->
		          <c:if test="${myBookmark eq '0'}">
		           <li><a href='bookmark/add?studyno=${study.studyNo}' class="btn btn-light">북마크 추가</a></li>
		          </c:if>
		          <c:if test="${myBookmark eq '1'}">
		           <li><a href='bookmark/delete?studyno=${study.studyNo}' class="btn btn-light">북마크 삭제</a></li>
		          </c:if>
		         </c:if>
		
		        <!-- 스터디 목록 -->
		        <li><a href="list" class="btn btn-light">목록</a></li>
		      </ol>
		     </div>
		    </div>
      </div>
    </div>

  </div>
</main>

  </body>
</html>
