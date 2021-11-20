<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <meta name="viewport" content="width=device-width, initial-scale=1.0">

 <link href="${contextPath}/css/calstyle.css" rel="stylesheet" type="text/css">
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
            <a  type="button" data-bs-toggle="modal"  data-bs-target="#addCalendarModal">➕</a> |   					    
            <a  type="button" data-bs-toggle="modal"  data-bs-target="#searchCalendarModal">🔎</a>                
          </div> 
					<!-- Modal -->
					<div class="modal fade" id="addCalendarModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="staticBackdropLabel">일정 등록</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					        <label>📅</label>
					        <input type="date"/>
							    <label for='f-status'>⭐</label>
							    <select id="f-status" name='important' >
							      <option value='1' name='important'>⭐☆☆☆☆</option>
							      <option value='2' name='important'>⭐⭐☆☆☆</option>
							      <option value='2' name='important'>⭐⭐⭐☆☆</option>
							      <option value='2' name='important'>⭐⭐⭐⭐☆</option>
							      <option value='2' name='important'>⭐⭐⭐⭐⭐</option>
							    </select><br><hr>					        
					        <label>📝</label>
					        <input type="text" name="content" placeholder="*내용을 입력하세요." /><br>
					        <label >💡</label>
					        <input id="alarm" type="checkbox"/><br>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-outline-dark" onclick="addCalendar(this)">등록</button>
					        <button type="button" class="btn btn-outline-dark" data-bs-dismiss="modal">취소</button>
					      </div>
					    </div>
					  </div>
					</div> <!-- d-grid gap-2 d-md-flex justify-content-md-end  -->    
         
          <!-- Button trigger modal -->
          <!-- Modal -->
          <div class="modal fade" id="searchCalendarModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="staticBackdropLabel">일정 검색</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
							    <label for='f-status'>검색</label>
							    <select id="f-status" name='status' >
							      <option value='1' name='status'>날짜</option>
							      <option value='2' name='status'>검색어</option>
							    </select><br>              
                
							    <div id="dateRow">
	                  <label>📅</label>
	                  <input type="date"/>
							    </div>             
							   
							    <div id="stringRow">
	                  <label>📝</label>
	                  <input type="text" name="search" placeholder="*검색어를 입력하세요." /><br>
							    </div>             
               
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-outline-dark" onclick="searchCalendar(this)">검색</button>
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

var fStatus = document.querySelector("#f-status");
var dateRow = document.querySelector("#dateRow");
var stringRow = document.querySelector("#stringRow");

dateRow.style["display"] = "none";
stringRow.style["display"] = "none";

fStatus.addEventListener("input", function() {
  if (fStatus.value == "2") {
	  stringRow.style["display"] = "";
  } else {
	  dateRow.style["display"] = "";
  }
});

</script>