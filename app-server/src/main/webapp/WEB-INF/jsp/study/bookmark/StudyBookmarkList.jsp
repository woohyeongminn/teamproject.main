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
    
    .nav-scroller py-1 mb-2 {
      text-align: -webkit-center;
    }
    
    .justify-content-between {
      width: 300px;
      align-items: center;
      /* text-align: center; */
      /* justify-content: center; */
    }
    
    .pt-4 {
      height: auto;
      padding-top: 0;
    }
    
    .link-secondary:hover {
      text-decoration: underline;
    }
    
    /* .btn {
      justify-content: center;
    } */
    
    /* ul {
      list-style: none;
    } */
    
    /* .tabmenu {
      xmax-width: 1000px;
      xmargin: 0 auto;
      xposition: relative;
      xmargin-top: 50px;
    } */
    
    /* .tabmenu ul li {
      display: inline-block;
      width: 20%;
      float: left;
    } */
    
    /* .tabmenu ul li a {
      display: block;
      xline-height: 40px;
      xtext-decoration: none;
    } */
    
    /* .tabCon {
      display: none;
      position: absolute;
      xpadding: 20px 0px;
      xleft: 0;
      xtop: 40px;
      xbox-sizing: border-box;
      xwidth: 1000px;
    } */
    
    /* .btnCon:target {
      xbackground: rgb(247, 231, 215);
    } */
    
    /* .btnCon:target .tabCon {
      display: block;
    } */
    
    .modal-backdrop {
      position: relative;
    }

    .modal-backdrop.show {
      opacity: 0;
    }
    
    .modal-title {
      font-weight: bold;
    }
    
    .modal-body {
      padding: 2rem;
    }

    .mb-3 select {
      height: 33.5px;
      width: 442px;
      /* width: 100px; */
    }
    
    #services {
      width: 100%;
    }
    
    .section-header p {
      padding-bottom: 60px;
    }
    
    /* form {
      padding-bottom: 50px;
    } */
    
    .section-bg {
      background: white;
    }
    
    #search {
      text-align: center;
      padding-bottom: 30px;
    }
    
    h3 p form .write {
      width: 1299px;
    }
    
    .row {
      padding-top: 30px;
    }
    
    #empty-bookmark {
      text-align: center;
    }
    
    /* #services .box {
      height: 309.6px;
    } */
    
    .intro {
      /* 한 줄 자르기 */
      display: inline-block;
      width: 331.99px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;

      /* 여러 줄 자르기 추가 스타일 */
      white-space: normal;
      line-height: 1.2;
      height: 1.5em;
    }
    </style>

    <!-- <script>
      location.href = "#tab1";
    </script> -->

  </head>
  <body>
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
  
  <!-- <div class="tabmenu">
    <ul> -->

  <!-- <div class="nav-scroller py-1 mb-2" style="text-align:-webkit-center;">
    <nav class="nav d-flex justify-content-between">
      <a class="p-2 link-secondary" href="${contextPath}/app/study/list">전체</a>
      <a class="p-2 link-secondary" href="${contextPath}/app/study/list/ing">진행</a>
      <a class="p-2 link-secondary" href="${contextPath}/app/study/list/end">종료</a>
    </nav>
  </div> -->

    <!--===== 내 북마크 목록 =====-->
    <!-- <li id="tab1" class="btnCon"><a class="btn" href="#tab1">전체</a>
      <div class="tabCon"> -->

    <section id="services" class="services section-bg">
      <div class="container" data-aos="fade-up">



        <div class="row">
        <input type="hidden" name="loginUser" value="${loginUser.perNo}">
        <c:if test='${not empty studyList}'>
          <c:forEach items="${studyList}" var="study">
            <div class="col-md-6 col-lg-4" data-aos="zoom-in" data-aos-delay="100">
              <div class="box">
                
                <c:choose>
                  <c:when test="${study.subjectNo eq '1'}">
                    <div class="icon" style="background:#fceef3;"><i class="bi bi-globe2" style="color:#ff689b;"></i></div>
                  </c:when>
                  <c:when test="${study.subjectNo eq '2'}">
                    <div class="icon" style="background:#fff0da;"><i class="bi bi-book" style="color:#e98e06;"></i></div>
                  </c:when>
                  <c:when test="${study.subjectNo eq '3'}">
                    <div class="icon" style="background:#e6fdfc;"><i class="bi bi-briefcase" style="color:#3fcdc7;"></i></div>
                  </c:when>
                  <c:when test="${study.subjectNo eq '4'}">
                    <div class="icon" style="background:#eafde7;"><i class="bi bi-display" style="color:#41cf2e;"></i></div>
                  </c:when>
                  <c:when test="${study.subjectNo eq '5'}">
                    <div class="icon" style="background:#e1eeff;"><i class="bi bi-lightbulb" style="color:#2282ff;"></i></div>
                  </c:when>
                  <c:when test="${study.subjectNo eq '6'}">
                    <div class="icon" style="background:#ecebff;"><i class="bi bi-collection" style="color:#8660fe;"></i></div>
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
  
                <div class="intro">
                  <p style="text-align:justify; -webkit-text-stroke-width:thin;">${study.introduction}</p>
                </div>
                <p class="description">${study.faceName}</p>
                <p class="description">인원 ${study.countMember}/${study.numberOfPeple}</p>
                <p class="description">${study.owner.perNickname} ⭐${study.countBookMember}</p>
              
              </div>
            </div>
          </c:forEach>
        </c:if>
        
        <!-- 내 북마크 X -->
          <div id="empty-bookmark">
            <c:if test='${empty studyList}'>
              북마크한 스터디가 없습니다.
              <br>
            </c:if>
          </div>
        </div>

      </div>
    </section>

     <!-- </div>
   </li> -->
   <!--===== End 내 북마크 목록 =====-->



    <!-- </ul>
  </div> -->

  </body>
</html>
