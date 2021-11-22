<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
  .day {
	margin: 0 30px 0 0;
	float: left;
	font-size:18px;
}
  .all {
  
  }
</style>

<div class="all">
 
<div class="head">
<b style="font-size:18px;">📝${myStudy.studyTitle}</b>
<b class="year-month" ></b>
<a  type="button">등록</a> |
<a  type="button">검색</a> |
<a  type="button">뒤로가기</a> |
</div>
<hr>

<div class="days">
    <div class="day" style="color:red;">Sun</div>
    <div class="day">Mon</div>
    <div class="day">Thu</div>
    <div class="day">Wed</div>
    <div class="day">Thr</div>
    <div class="day">Fri</div>
    <div class="day">Sat</div>
</div>
       
</div>



<script type="text/javascript">
const date = new Date();
const renderCalendar = () => {
	const viewYear = date.getFullYear();
	const viewMonth = date.getMonth();

if (viewMonth  == 0) {
    var month = "Jan.";
  }
  else if (viewMonth  == 1) {
    var month = "Feb.";
  }
  else if (viewMonth  == 2) {
    var month = "Mar.";
  }
  else if (viewMonth  == 3) {
    var month = "Apr.";
  }
  else if (viewMonth  == 4) {
    var month = "May.";
  }
  else if (viewMonth  == 5) {
   var month = "Jun.";
  }  
  else if (viewMonth  == 6) {
   var month = "Jul.";
  }
  else if (viewMonth  == 7) {
   var month = "Aug.";
  }
  else if (viewMonth  == 8) {
   var month = "Sep.";
  }
  else if (viewMonth  == 9) {
   var month = "Oct.";
  }
  else if (viewMonth  == 10) {
   var month = "Nov.";
  }
  else if (viewMonth  == 11) {
   var month = "Dec.";
  }

document.querySelector('.year-month').textContent = "${month} ${viewYear}"

};
</script>

<script>
function addCalendar(obj) { 
    alert("일정이 등록 되었습니다.")
    }
function searchCalendar(obj) { 
    alert("검색 결과!!")
    } 
</script>
