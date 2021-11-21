<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <meta name="viewport" content="width=device-width, initial-scale=1.0">

 <link href="${contextPath}/css/calstyle.css" rel="stylesheet" type="text/css">

<c:forEach items="${calendarList}" var="calendar">
 <p>${calendar.importance}</p>
</c:forEach>

<div class="all-content"> 
  <div class="c-top">
    </div>
    <div class="calendarwrap">
	    <div class="calendar">
	        <div class="header">
	        <div><b style="font-size:18px;">📝${myStudy.studyTitle}</b></div>
	            <div class="year-month" style="font-weight: 700;"></div>
	              <div class="nav">
	                <button class="nav-btn go-prev" onclick="prevMonth()">◀</button>
	                <button class="nav-btn go-today" onclick="goToday()">now</button>
	                <button class="nav-btn go-next" onclick="nextMonth()">▶</button>
	              </div>
	        </div><hr>
	        
					<!-- Button trigger modal -->
					<div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a  type="button" data-bs-toggle="modal"  data-bs-target="#addCalendarModal">➕등록</a> |   					    
            <a  type="button" data-bs-toggle="modal"  data-bs-target="#searchCalendarModal">🔎검색</a> |
            <a href="../detail?studyNo=${myStudy.studyNo}">뒤로가기</a>                
          </div> 
					<!-- Modal -->
					<form action="add" method="post">
						<div class="modal fade" id="addCalendarModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						     
						      <div class="modal-header">
						        <h5 class="modal-title" id="staticBackdropLabel">일정 등록</h5>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      
						      <div class="modal-body">
							      <div class="addcalendar">
							        <label>📅</label>
							        <input type="date" name="calendarDate"/>
									    <label for='f-status'>⭐</label>
									    <select id="f-status" name='importanceNo' >
									      <option value='1' name='importanceNo'>⭐⭐⭐⭐⭐</option>
									      <option value='2' name='importanceNo'>⭐⭐⭐⭐☆</option>
									      <option value='2' name='importanceNo'>⭐⭐⭐☆☆</option>
									      <option value='2' name='importanceNo'>⭐⭐☆☆☆</option>
									      <option value='2' name='importanceNo'>⭐☆☆☆☆</option>
									    </select><br><hr>					        
							        
							        <label>📝</label>
							        <input type="text" name="calendarContent" placeholder="*내용을 입력하세요." /><br>
							        <label >💡</label>
							        <input id="alarm" type="checkbox"/><br>
							        <input type="hidden" name=studyNo value="${myStudy.studyNo}"/>
							      </div>
						      </div>
						      
						      <div class="modal-footer">
						        <button type="submit" class="btn btn-outline-dark" onclick="addCalendar(this)" data-bs-dismiss="modal">등록</button>
						        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">취소</button>
						      </div>
						      
						    </div>
						  </div>
						</div> <!-- d-grid gap-2 d-md-flex justify-content-md-end  -->    
          </form>
          
          <!-- Modal -->
          <div class="modal fade" id="searchCalendarModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="staticBackdropLabel">일정 검색</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                
							    <div id="searching">
	                  <label>📅</label>
	                  <input type="date" name='date'></input> |
	                  <label>📝</label>
	                  <input type="text" name="search" placeholder="*검색어를 입력하세요." /><br>
							    </div>           
               
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-outline-dark" onclick="searchCalendar(this)" data-bs-dismiss="modal">검색</button>
                  <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">취소</button>
                </div>
              </div>
            </div>
          </div> <!-- d-grid gap-2 d-md-flex justify-content-md-end  -->    
          <!-- Button trigger modal -->            	        

	        <div class="main">
	            <div class="days">
	                <div class="day" style="font-size:18px;">Sun</div>
	                <div class="day" style="font-size:18px;">Mon</div>
	                <div class="day" style="font-size:18px;">Thu</div>
	                <div class="day" style="font-size:18px;">Wed</div>
	                <div class="day" style="font-size:18px;">Thr</div>
	                <div class="day" style="font-size:18px;">Fri</div>
	                <div class="day" style="font-size:18px;">Sat</div>
	            </div>
	            <div class="dates"></div>
	        </div>
       
	    </div>
   </div>  
</div> 

<script type="text/javascript" src="${contextPath}/js/calendar.js"></script>
<script>
function addCalendar(obj) { 
    alert("일정이 등록 되었습니다.")
    }
function searchCalendar(obj) { 
    alert("검색 결과!!")
    } 
</script>
