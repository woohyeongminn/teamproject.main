<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href='${contextPath}/css/calendar/core/main.css' rel='stylesheet' />
<link href='${contextPath}/css/calendar/daygrid/main.css' rel='stylesheet' />
<link href='${contextPath}/css/calendar/timegrid/main.css' rel='stylesheet' />
<script src='${contextPath}/css/calendar/core/main.js'></script>
<script src='${contextPath}/css/calendar/interaction/main.js'></script>
<script src='${contextPath}/css/calendar/daygrid/main.js'></script>
<script src='${contextPath}/css/calendar/timegrid/main.js'></script>
<div style="text-align: center;">
  <b style="font-size:18px;">${myStudy.studyTitle}📆</b>
</div>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'interaction', 'dayGrid', 'timeGrid' ],
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      defaultDate: '2021-11-23',
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
        var title = prompt('Event Title:');
        if (title) {
          calendar.addEvent({
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: arg.allDay
          })
        }
        calendar.unselect()
      },
      editable: true,
      eventLimit: true, // allow "more" link when too many events
      
    });

    calendar.render();
  });

</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }

</style>
<body>

  <div id='calendar'></div>

</body>
