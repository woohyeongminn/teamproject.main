<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <link rel="canonical" href="https://getbootstrap.kr/docs/5.1/examples/blog/">

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
		  height: 81vmin;
		}
		
		.mb-3 select {
		  height: 38px;
		  width: 466px;
		  xwidth: 100px;
		}
    </style>

    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair&#43;Display:700,900&amp;display=swap" rel="stylesheet">
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
              <th>지역</th>
              <th>인원수</th>
              <th>비고</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>${study.faceName}</td>
              <td>${study.area}</td>
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
          참여 <ins>${study.waitingCountMember}</ins><sub>명</sub><br>
          승인 대기 <ins>${study.countMember}</ins><sub>명</sub><br>
          북마크 <ins>${study.countBookMember}</ins><sub>명</sub>
          </p>
        </div>
        
        <div class="p-4">
		      <h4 class="fst-italic">메뉴</h4>
		      <ol class="list-unstyled mb-0">
		        
		        <c:if test="${study.owner.perNo eq loginUser.perNo}">
		        <!-- 스터디 수정 -->
		          <li><button data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">수정</button></li>
		
		          <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
		                      <label for='f-studyTitle'>제목</label> <input id='f-studyTitle'
		                        type='text' name='studyTitle' class="form-control" required
		                        oninvalid="this.setCustomValidity('제목을 입력하세요.')"
		                        oninput="this.setCustomValidity('')">
		                    </div>
		
		                    <!-- 최대 인원수 -->
		                    <div class="mb-3">
		                      <label for='f-numberOfPeple'>최대 인원수</label> <select
		                        name="numberOfPeple">
		                        <option value="2" name="numberOfPeple" selected>2</option>
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
		                      <label for='f-faceNo'>대면 상태</label> <select name="faceNo">
		                        <option value="1" name="faceNo" selected>대면</option>
		                        <option value="2" selected>비대면</option>
		                        <option value="3" selected>대면/비대면</option>
		                      </select>
		                    </div>
		
		                    <!-- 소개글 -->
		                    <div class="mb-3">
		                      <label for='f-introduction'>소개글</label>
		                      <textarea id='f-introduction' type='text' name='introduction'
		                        class="form-control" rows="3" required
		                        oninvalid="this.setCustomValidity('소개글을 입력하세요.')"
		                        oninput="this.setCustomValidity('')"></textarea>
		                    </div>
		
		                    <!-- 진행 상태 -->
		                    <div class="mb-3">
		                      <label for='f-studyStatus'>진행 상태</label> <select
		                        name="studyStatus">
		                        <option value="1" name="studyStatus" selected>진행</option>
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
		          <li><a href='delete?studyno=${study.studyNo}'>삭제</a><li>
		        </c:if>
		        
		        <c:if test="${loginUser ne null}">
		          <!-- 스터디 참여 -->
		          <c:if test="${study.owner.perNo ne loginUser.perNo}">
		            <c:choose>
		              <c:when test="${guilder == 'false'}">
		                <li><a href='join?studyno=${study.studyNo}'>참여 신청</a></li>
		              </c:when>
		              <c:when test="${guilder == 'waitingGuilder'}">
		                <li>승인 대기중</li>
		              </c:when>
		              <c:when test="${guilder == 'guilder'}">
		                <li>참여중</li>
		              </c:when>
		            </c:choose>
		          </c:if>
		          
		          <!-- 스터디 북마크 -->
		          <c:if test="${myBookmark eq '0'}">
		           <li><a href='bookmark/add?studyno=${study.studyNo}'>북마크 추가</a></li>
		          </c:if>
		          <c:if test="${myBookmark eq '1'}">
		           <li><a href='bookmark/delete?studyno=${study.studyNo}'>북마크 삭제</a></li>
		          </c:if>
		         </c:if>
		
		        <!-- 스터디 목록 -->
		        <li><a href="list">목록</a></li>
		      </ol>
		    </div>
      </div>
    </div>

  </div>
</main>

  </body>
</html>
