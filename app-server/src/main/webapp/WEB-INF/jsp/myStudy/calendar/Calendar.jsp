<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <meta name="viewport" content="width=device-width, initial-scale=1.0">

 <link href="${contextPath}/css/calstyle.css" rel="stylesheet" type="text/css">
 <style>
   .all-content {
  width: 100%;
  max-width: 900px;
  margin: 0 auto;
  font-size: 14px;
  } 
  .btn {
    line-height: 10px;
  }  
 </style>
<div class="all-content"> 
  <div class="c-top">
    </div>
    <div class="calendarwrap">
	    <div class="calendar">
	        <div class="header">
	            <div class="year-month"></div>
	              <div class="nav">
	                <button class="nav-btn go-prev" onclick="prevMonth()">&lt;</button>
	                <button class="nav-btn go-today" onclick="goToday()">Today</button>
	                <button class="nav-btn go-next" onclick="nextMonth()">&gt;</button>
	              </div>
	        </div>
	        <div class="main">
	            <div class="days">
	                <div class="day">일</div>
	                <div class="day">월</div>
	                <div class="day">화</div>
	                <div class="day">수</div>
	                <div class="day">목</div>
	                <div class="day">금</div>
	                <div class="day">토</div>
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