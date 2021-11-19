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
	            <div class="year-month"></div>
	              <div class="nav">
	                <button class="nav-btn go-prev" onclick="prevMonth()">◀</button>
	                <button class="nav-btn go-today" onclick="goToday()">today</button>
	                <button class="nav-btn go-next" onclick="nextMonth()">▶</button>
	              </div>
	        </div><hr>
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
            <br><div class="d-grid gap-2 d-md-flex justify-content-md-end">
               <a type="button" class ="btn btn-outline-dark" onclick="add(this)">✔일정등록</a>
             </div> 
	    </div>
   </div>  
</div>    
     <script type="text/javascript" src="${contextPath}/js/calendar.js"></script>
<script>
function add(obj) { 
    alert("일정 등록.")
    }
</script>