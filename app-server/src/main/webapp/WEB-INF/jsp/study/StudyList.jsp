<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>

    <style>
		* {
		  margin: 0;
		  padding: 0;
		  font-size: 14px;
		  line-height: 1.5;
		}
		
		ul {
		  xlist-style: none;
		}
		
		.tabmenu {
		  xmax-width: 1000px;
		  xmargin: 0 auto;
		  xposition: relative;
		  xmargin-top: 50px;
		}
		
		.tabmenu ul li {
		  xdisplay: inline-block;
		  xwidth: 33.33%;
		  xfloat: left;
		}
		
		.tabmenu ul li a {
		  xdisplay: block;
		  xline-height: 40px;
		  xtext-decoration: none;
		}
		
		.tabCon {
		  xdisplay: none;
		  xpadding: 20px 0px;
		  xposition: absolute;
		  xleft: 0;
		  xtop: 40px;
		  xbox-sizing: border-box;
		  xwidth: 1000px;
		}
		
		.btnCon:target {
		  xbackground: rgb(247, 231, 215);
		}
		
		.btnCon:target .tabCon {
		  xdisplay: block;
		}
		
		.section-header p {
		  padding-bottom: 50px;
		}
		
    .pt-4 {
		  height: auto;
		}
		
		.section-bg {
      background: white;
    }
    
    #search {
		  text-align: center;
		}
		
		form {
      padding-bottom: 50px;
    }

		.modal-backdrop {
      position: relative;
    }

    .modal-backdrop.show {
      opacity: 0;
    }

		.mb-3 select {
		  height: 33.5px;
		  width: 470px;
		  xwidth: 100px;
		}
		
		#empty-study {
		  text-align: center;
		}
		
		#services .box {
		  height: 309.6px;
		}
    </style>

    <script>
	    location.href = "#tab1";
	  </script>

  </head>
  <body>
  
  <div class="tabmenu">
    <ul>



    <!--===== 전체 스터디 목록 =====-->
    <li id="tab1" class="btnCon"><a class="btn first" href="#tab1">전체</a>
      <div class="tabCon">
  
    <!-- 스터디 목록 -->
    <section id="services" class="services section-bg">
      <div class="container" data-aos="fade-up">

        <header class="section-header">
          <h3>Today Study</h3>
          <p>함께 성장할 스터디를 모집해보세요</p>
        </header>
        
        <!-- 검색 -->
          <div id="search">
            <form action="search" method='get'>
              <select name="where">
                <option value="1">분야</option>
                <option value="2">제목</option>
                <option value="3">지역</option>
              </select> <input type="text" name="keyword">
              <button class="btn btn-outline-dark btn-sm">검색</button>
            </form>
          </div>
          <br>
          
          <c:if test="${loginUser ne null}">
          <!-- 스터디 등록 -->
            <button type="button" class="btn btn-light" data-bs-toggle="modal"
              data-bs-target="#exampleModal" data-bs-whatever="@mdo" >글쓰기</button>

            <div class="modal fade" id="exampleModal" tabindex="-1"
              aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">

                  <!-- 상단 헤더 -->
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">스터디 등록</h5>
                    <button type="button" class="btn-close"
                      data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>

                  <div class="modal-body">
                    <form action='add' method='post'>
                      <!-- 제목 -->
                      <div class="mb-3">
                        <label for='f-studyTitle'>제목</label> <input
                          id='f-studyTitle' type='text' name='studyTitle'
                          class="form-control" required
                          oninvalid="this.setCustomValidity('제목을 입력하세요.')"
                          oninput="this.setCustomValidity('')">
                      </div>

                      <!-- 분야 -->
                      <div class="mb-3">
                        <label for='f-subjectNo'>분야</label> <select name="subjectNo">
                          <option value="1" name="subjectNo" selected>어학</option>
                          <option value="2" selected>자격증</option>
                          <option value="3" selected>취업</option>
                          <option value="4" selected>IT</option>
                          <option value="5" selected>예체능</option>
                          <option value="6" selected>기타</option>
                        </select>
                      </div>
                      
                      <!-- 지역 -->
                      <div class="mb-3">
                        <label for='f-area'>지역</label> <input id='f-area'
                          type='text' name='area' class="form-control" required
                          oninvalid="this.setCustomValidity('시 / 도 / 구를 입력하세요.')"
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
                        <textarea id='f-introduction' type='text'
                          name='introduction' class="form-control" rows="3" required
                          oninvalid="this.setCustomValidity('소개글을 입력하세요.')"
                          oninput="this.setCustomValidity('')"></textarea>
                      </div>

                      <!-- 진행 상태 -->
                      <div class="mb-3">
                        <label for='f-studyStatus'>진행 상태</label> <select
                          name="studyStatus">
                          <option value="1" name="studyStatus" selected>진행</option>
                          <option value="2" disabled>종료</option>
                        </select>
                      </div>

                      <!-- 하단 버튼 -->
                      <div class="modal-footer">
                        <button type="button" class="btn btn-light"
                          data-bs-dismiss="modal">취소</button>
                        <button class="btn btn-dark">등록</button>
                      </div>

                    </form>
                  </div>

                </div>
              </div>
            </div>

          </c:if>

        <div class="row">
        <c:if test='${not empty studyList}'>
	        <c:forEach items="${studyList}" var="study">
	          <div class="col-md-6 col-lg-4" data-aos="zoom-in" data-aos-delay="100">
	            <div class="box">
	              
	              <c:choose>
	              <c:when test="${study.subjectNo eq '1'}">
	                <div class="icon" style="background:#fff0da;"><i class="bi bi-globe2" style="color:#e98e06;"></i></div>
	              </c:when>
	              <c:when test="${study.subjectNo eq '2'}">
	                <div class="icon" style="background:#fff0da;"><i class="bi bi-book" style="color:#e98e06;"></i></div>
	              </c:when>
	              <c:when test="${study.subjectNo eq '3'}">
	                <div class="icon" style="background:#fff0da;"><i class="bi bi-briefcase" style="color:#e98e06;"></i></div>
	              </c:when>
	              <c:when test="${study.subjectNo eq '4'}">
	                <div class="icon" style="background:#fff0da;"><i class="bi bi-display" style="color:#e98e06;"></i></div>
	              </c:when>
	              <c:when test="${study.subjectNo eq '5'}">
	                <div class="icon" style="background:#fff0da;"><i class="bi bi-lightbulb" style="color:#e98e06;"></i></div>
	              </c:when>
	              <c:when test="${study.subjectNo eq '6'}">
	                <div class="icon" style="background:#fff0da;"><i class="bi bi-collection" style="color:#e98e06;"></i></div>
	              </c:when>
	              </c:choose>
	
	              <h4 class="title"><a href="detail?studyno=${study.studyNo}">${study.studyTitle}</a></h4>
	
	              <c:choose>
	                <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus ne '2'}">
	                  <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집중</p>
	                </c:when>
	                <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus ne '2'}">
	                  <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집중</p>
	                </c:when>
	                <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus eq '2'}">
	                  <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집완료</p>
	                </c:when>
	                <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus eq '2'}">
	                  <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집완료</p>
	                </c:when>
	              </c:choose>
	
	              <p style="text-align:justify; -webkit-text-stroke-width:thin;">${study.introduction}</p>
	              <p class="description">${study.faceName}</p>
	              <p class="description">인원 ${study.countMember}/${study.numberOfPeple}</p>
	              <p class="description">${study.owner.perNickname} ⭐${study.countBookMember}</p>
	            
	            </div>
	          </div>
	        </c:forEach>
        </c:if>
        
        <!-- 검색 결과 -->
          <div id="empty-study">
            <c:if test='${empty studyList}'>
              검색 결과가 존재하지 않습니다.
              <br>
            </c:if>
          </div>
        </div>

      </div>
    </section>
    <!-- End 스터디 목록 -->

     </div>
   </li>
   <!--===== End 전체 스터디 목록 =====-->



   <!--===== 진행 스터디 목록 =====-->
      <li id="tab2" class="btnCon"><a class="btn" href="#tab2">진행</a>
        <div class="tabCon">

          <!-- 스터디 목록 -->
    <section id="services" class="services section-bg">
      <div class="container" data-aos="fade-up">

        <header class="section-header">
          <h3>Today Study</h3>
          <p>함께 성장할 스터디를 모집해보세요</p>
        </header>
        
        <!-- 검색 -->
          <div id="search">
            <form action="search" method='get'>
              <select name="where">
                <option value="1">분야</option>
                <option value="2">제목</option>
                <option value="3">지역</option>
              </select> <input type="text" name="keyword">
              <button class="btn btn-outline-dark btn-sm">검색</button>
            </form>
          </div>
          <br>

          <c:if test="${loginUser ne null}">
          <!-- 스터디 등록 -->
            <button type="button" class="btn btn-light" data-bs-toggle="modal"
              data-bs-target="#exampleModal" data-bs-whatever="@mdo">글쓰기</button>

            <div class="modal fade" id="exampleModal" tabindex="-1"
              aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">

                  <!-- 상단 헤더 -->
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">스터디 등록</h5>
                    <button type="button" class="btn-close"
                      data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>

                  <div class="modal-body">
                    <form action='add' method='post'>
                      <!-- 제목 -->
                      <div class="mb-3">
                        <label for='f-studyTitle'>제목</label> <input
                          id='f-studyTitle' type='text' name='studyTitle'
                          class="form-control" required
                          oninvalid="this.setCustomValidity('제목을 입력하세요.')"
                          oninput="this.setCustomValidity('')">
                      </div>

                      <!-- 분야 -->
                      <div class="mb-3">
                        <label for='f-subjectNo'>분야</label> <select name="subjectNo">
                          <option value="1" name="subjectNo" selected>어학</option>
                          <option value="2" selected>자격증</option>
                          <option value="3" selected>취업</option>
                          <option value="4" selected>IT</option>
                          <option value="5" selected>예체능</option>
                          <option value="6" selected>기타</option>
                        </select>
                      </div>
                      
                      <!-- 지역 -->
                      <div class="mb-3">
                        <label for='f-area'>지역</label> <input id='f-area'
                          type='text' name='area' class="form-control" required
                          oninvalid="this.setCustomValidity('시 / 도 / 구를 입력하세요.')"
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
                        <textarea id='f-introduction' type='text'
                          name='introduction' class="form-control" rows="3" required
                          oninvalid="this.setCustomValidity('소개글을 입력하세요.')"
                          oninput="this.setCustomValidity('')"></textarea>
                      </div>

                      <!-- 진행 상태 -->
                      <div class="mb-3">
                        <label for='f-studyStatus'>진행 상태</label> <select
                          name="studyStatus">
                          <option value="1" name="studyStatus" selected>진행</option>
                          <option value="2" disabled>종료</option>
                        </select>
                      </div>

                      <!-- 하단 버튼 -->
                      <div class="modal-footer">
                        <button type="button" class="btn btn-light"
                          data-bs-dismiss="modal">취소</button>
                        <button class="btn btn-dark">등록</button>
                      </div>

                    </form>
                  </div>

                </div>
              </div>
            </div>

          </c:if>

        <div class="row">
        <c:if test='${not empty studyIngList}'>
          <c:forEach items="${studyIngList}" var="study">
            <div class="col-md-6 col-lg-4" data-aos="zoom-in" data-aos-delay="100">
              <div class="box">
                
                <c:choose>
                <c:when test="${study.subjectNo eq '1'}">
                  <div class="icon" style="background:#fff0da;"><i class="bi bi-globe2" style="color:#e98e06;"></i></div>
                </c:when>
                <c:when test="${study.subjectNo eq '2'}">
                  <div class="icon" style="background:#fff0da;"><i class="bi bi-book" style="color:#e98e06;"></i></div>
                </c:when>
                <c:when test="${study.subjectNo eq '3'}">
                  <div class="icon" style="background:#fff0da;"><i class="bi bi-briefcase" style="color:#e98e06;"></i></div>
                </c:when>
                <c:when test="${study.subjectNo eq '4'}">
                  <div class="icon" style="background:#fff0da;"><i class="bi bi-display" style="color:#e98e06;"></i></div>
                </c:when>
                <c:when test="${study.subjectNo eq '5'}">
                  <div class="icon" style="background:#fff0da;"><i class="bi bi-lightbulb" style="color:#e98e06;"></i></div>
                </c:when>
                <c:when test="${study.subjectNo eq '6'}">
                  <div class="icon" style="background:#fff0da;"><i class="bi bi-collection" style="color:#e98e06;"></i></div>
                </c:when>
                </c:choose>
  
                <h4 class="title"><a href="detail?studyno=${study.studyNo}">${study.studyTitle}</a></h4>
  
                <c:choose>
                  <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus ne '2'}">
                    <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집중</p>
                  </c:when>
                  <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus ne '2'}">
                    <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집중</p>
                  </c:when>
                  <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus eq '2'}">
                    <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집완료</p>
                  </c:when>
                  <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus eq '2'}">
                    <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집완료</p>
                  </c:when>
                </c:choose>
  
                <p style="text-align:justify; -webkit-text-stroke-width:thin;">${study.introduction}</p>
                <p class="description">${study.faceName}</p>
                <p class="description">인원 ${study.countMember}/${study.numberOfPeple}</p>
                <p class="description">${study.owner.perNickname} ⭐${study.countBookMember}</p>
              
              </div>
            </div>
          </c:forEach>
        </c:if>
        
        <!-- 검색 결과 -->
          <div id="empty-study">
            <c:if test='${empty studyIngList}'>
              검색 결과가 존재하지 않습니다.
              <br>
            </c:if>
          </div>
        </div>

      </div>
    </section>

    </div>
   </li>
   <!--===== End 진행 스터디 목록 =====-->



<!--===== 종료 스터디 목록 =====-->
      <li id="tab3" class="btnCon"><a class="btn" href="#tab3">종료</a>
        <div class="tabCon">

          <!-- 스터디 목록 -->
    <section id="services" class="services section-bg">
      <div class="container" data-aos="fade-up">

        <header class="section-header">
          <h3>Today Study</h3>
          <p>함께 성장할 스터디를 모집해보세요</p>
        </header>
        
        <!-- 검색 -->
          <div id="search">
            <form action="search" method='get'>
              <select name="where">
                <option value="1">분야</option>
                <option value="2">제목</option>
                <option value="3">지역</option>
              </select> <input type="text" name="keyword">
              <button class="btn btn-outline-dark btn-sm">검색</button>
            </form>
          </div>
          <br>

          <c:if test="${loginUser ne null}">
          <!-- 스터디 등록 -->
            <button type="button" class="btn btn-light" data-bs-toggle="modal"
              data-bs-target="#exampleModal" data-bs-whatever="@mdo">글쓰기</button>

            <div class="modal fade" id="exampleModal" tabindex="-1"
              aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">

                  <!-- 상단 헤더 -->
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">스터디 등록</h5>
                    <button type="button" class="btn-close"
                      data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>

                  <div class="modal-body">
                    <form action='add' method='post'>
                      <!-- 제목 -->
                      <div class="mb-3">
                        <label for='f-studyTitle'>제목</label> <input
                          id='f-studyTitle' type='text' name='studyTitle'
                          class="form-control" required
                          oninvalid="this.setCustomValidity('제목을 입력하세요.')"
                          oninput="this.setCustomValidity('')">
                      </div>

                      <!-- 분야 -->
                      <div class="mb-3">
                        <label for='f-subjectNo'>분야</label> <select name="subjectNo">
                          <option value="1" name="subjectNo" selected>어학</option>
                          <option value="2" selected>자격증</option>
                          <option value="3" selected>취업</option>
                          <option value="4" selected>IT</option>
                          <option value="5" selected>예체능</option>
                          <option value="6" selected>기타</option>
                        </select>
                      </div>
                      
                      <!-- 지역 -->
                      <div class="mb-3">
                        <label for='f-area'>지역</label> <input id='f-area'
                          type='text' name='area' class="form-control" required
                          oninvalid="this.setCustomValidity('시 / 도 / 구를 입력하세요.')"
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
                        <textarea id='f-introduction' type='text'
                          name='introduction' class="form-control" rows="3" required
                          oninvalid="this.setCustomValidity('소개글을 입력하세요.')"
                          oninput="this.setCustomValidity('')"></textarea>
                      </div>

                      <!-- 진행 상태 -->
                      <div class="mb-3">
                        <label for='f-studyStatus'>진행 상태</label> <select
                          name="studyStatus">
                          <option value="1" name="studyStatus" selected>진행</option>
                          <option value="2" disabled>종료</option>
                        </select>
                      </div>

                      <!-- 하단 버튼 -->
                      <div class="modal-footer">
                        <button type="button" class="btn btn-light"
                          data-bs-dismiss="modal">취소</button>
                        <button class="btn btn-dark">등록</button>
                      </div>

                    </form>
                  </div>

                </div>
              </div>
            </div>

          </c:if>

        <div class="row">
        <c:if test='${not empty studyEndList}'>
          <c:forEach items="${studyEndList}" var="study">
            <div class="col-md-6 col-lg-4" data-aos="zoom-in" data-aos-delay="100">
              <div class="box">
                
                <c:choose>
                <c:when test="${study.subjectNo eq '1'}">
                  <div class="icon" style="background:#fff0da;"><i class="bi bi-globe2" style="color:#e98e06;"></i></div>
                </c:when>
                <c:when test="${study.subjectNo eq '2'}">
                  <div class="icon" style="background:#fff0da;"><i class="bi bi-book" style="color:#e98e06;"></i></div>
                </c:when>
                <c:when test="${study.subjectNo eq '3'}">
                  <div class="icon" style="background:#fff0da;"><i class="bi bi-briefcase" style="color:#e98e06;"></i></div>
                </c:when>
                <c:when test="${study.subjectNo eq '4'}">
                  <div class="icon" style="background:#fff0da;"><i class="bi bi-display" style="color:#e98e06;"></i></div>
                </c:when>
                <c:when test="${study.subjectNo eq '5'}">
                  <div class="icon" style="background:#fff0da;"><i class="bi bi-lightbulb" style="color:#e98e06;"></i></div>
                </c:when>
                <c:when test="${study.subjectNo eq '6'}">
                  <div class="icon" style="background:#fff0da;"><i class="bi bi-collection" style="color:#e98e06;"></i></div>
                </c:when>
                </c:choose>
  
                <h4 class="title"><a href="detail?studyno=${study.studyNo}">${study.studyTitle}</a></h4>
  
                <c:choose>
                  <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus ne '2'}">
                    <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집중</p>
                  </c:when>
                  <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus ne '2'}">
                    <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집중</p>
                  </c:when>
                  <c:when test="${study.countMember ne study.numberOfPeple && study.studyStatus eq '2'}">
                    <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집완료</p>
                  </c:when>
                  <c:when test="${study.countMember eq study.numberOfPeple && study.studyStatus eq '2'}">
                    <p id="wanted" style="text-align:right; font-size:12px; font-family:fantasy; margin:0;">모집완료</p>
                  </c:when>
                </c:choose>
  
                <p style="text-align:justify; -webkit-text-stroke-width:thin;">${study.introduction}</p>
                <p class="description">${study.faceName}</p>
                <p class="description">인원 ${study.countMember}/${study.numberOfPeple}</p>
                <p class="description">${study.owner.perNickname} ⭐${study.countBookMember}</p>
              
              </div>
            </div>
          </c:forEach>
        </c:if>
        
        <!-- 검색 결과 -->
          <div id="empty-study">
            <c:if test='${empty studyEndList}'>
              검색 결과가 존재하지 않습니다.
              <br>
            </c:if>
          </div>
        </div>

      </div>
    </section>

    </div>
   </li>
   <!--===== End 종료 스터디 목록 =====-->



    </ul>
  </div>

  </body>
</html>
