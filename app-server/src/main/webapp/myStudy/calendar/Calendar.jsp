<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

 <meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>myStudy | calendar</title>
 <link href="../../calstyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../../header.jsp"/>
    <div class="c-top">
        🗓️ 캘린더
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
	    </div>
    </div>
    
     <script type="text/javascript" src="../../calendar.js"></script>
</body>
</html>