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
		
    .pt-4 {
		  height: auto;
		}
		
		.section-bg {
      background: white;
    }
    </style>

  </head>
  <body>
  
  
  
  <!-- 스터디 목록 -->
    <section id="services" class="services section-bg">
      <div class="container" data-aos="fade-up">

        <header class="section-header">
          <h3>Today Study</h3>
          <p>함께 성장할 스터디를 모집해보세요</p>
        </header>

        <div class="row">
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

      </div>

      </div>
    </section>
    <!-- End 스터디 목록 -->
  
  
  
  </body>
</html>
