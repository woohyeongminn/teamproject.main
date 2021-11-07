<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title> 캘린더</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   <link rel="stylesheet" href="../node_modules/@fullcalendar/core/main.css">
   <link rel="stylesheet" href="../node_modules/@fullcalendar/daygrid/main.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
   
   <script src="../node_modules/@fullcalendar/interaction/main.js"></script>
   <script src="../node_modules/@fullcalendar/core/main.js"></script>
   <script src="../node_modules/@fullcalendar/daygrid/main.js"></script>
   
   <script>
   var calendar = new Fullcalendar.Calendar(calendarEl, {
	   calendar.render();
   });
   </script>
   
   <body>
    <div id='calendar'></div>
   </body>
   
   <script>
   var calendar = new FullCalendar.Calendar(calendarEl, {
	   plugins: ['interaction', 'dayGrid']
   });
   </script>
   
   <script>
   var calendar = new FullCAlendar.Calendar(calendarEl, {
	   editable: true,
	   events: [
		   {
			   title: 'All Day Event',
			   start: '2020-02-07',
			   end: '2020-02-10'
		   },
		   {
			   title: 'Long Event',
			   start: '2020-02-07',
			   end: '2020-02-10'
		   },
		   {
			   groupId: 999,
			   title: 'Repeating Event',
			   start: '2020-02-16T16:00:00'
		   },
   </script>
   
   <script >
   var calendar = new FullCalendar.Calendar(calendarEl, {
	   header: {
		   left: 'prevYear,prev,next,nextYear today',}
       center: 'title',
       right: 'dayGridMonth,dayGridWeek, dayGriDay'
     },
   });
   </script>
   
   <script>
    var calendar = new FullCalendar.Calendar(calendarEl, {
      editable: true,
      events: [
    	  {
    		  title: 'All Day Event',
    		  start: '2020-02-01'
    	  },
    	  {
    		  title: 'Long Event',
    		  start: '2020-02-07',
    		  end: '2020-02-10'
    	  },
    		{
    		  groupId: 999,
    		  title: 'Repeating Event'
    		  start: '2020-02-09T16:00:00'
    		},
    		{
    			groupId: 999,
    			title: 'Repeating Event'
    			start: '2020-02-16T16:00:00'
    		},
   </script>
   
   
   
   
   