<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset='utf-8' />
<link href='${contextPath}/css/calendar/core/main.css' rel='stylesheet' />
<link href='${contextPath}/css/calendar/daygrid/main.css'
	rel='stylesheet' />
<link href='${contextPath}/css/calendar/timegrid/main.css'
	rel='stylesheet' />

<script src='${contextPath}/css/calendar/core/main.js'></script>
<script src='${contextPath}/css/calendar/interaction/main.js'></script>
<script src='${contextPath}/css/calendar/daygrid/main.js'></script>
<script src='${contextPath}/css/calendar/timegrid/main.js'></script>

<style>
.breadcrumbs {
	padding: 15px 0 0 0;
}

.fc-sun {
	color: red;
}

.fc-day.fc-widget-content.fc-mon.fc-today {
	background-color: #EFE6E1;
}

.fc-prev-button.fc-button.fc-button-primary {
	background-color: #EFE6E1;
	color: black;
}

.fc-today-button.fc-button.fc-button-primary {
	background-color: #EFE6E1;
	color: black;
}

.fc-next-button.fc-button.fc-button-primary {
	background-color: #EFE6E1;
	color: black;
}

.fc-dayGridMonth-button.fc-button.fc-button-primary.fc-button-active {
	background-color: #EFE6E1;
	color: black;
}

.fc-timeGridWeek-button.fc-button.fc-button-primary {
	background-color: #EFE6E1;
	color: black;
}

.container {
	margin-bottom: 20px;
}
</style>

<div style="text-align: center;">
	<p style="font-size: 18px;">📝 ${myStudy.studyTitle}</p>
</div>

<script>
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');

		var calendar = new FullCalendar.Calendar(calendarEl, {
			plugins : [ 'interaction', 'dayGrid', 'timeGrid' ],
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'dayGridMonth,timeGridWeek'
			},
			defaultDate : '2021-11-23',
			navLinks : true, // can click day/week names to navigate views
			selectable : true,
			selectMirror : true,
			select : function(arg) {
				var title = prompt('Event Title:');
				if (title) {
					calendar.addEvent({
						title : title,
						start : arg.start,
						end : arg.end,
						allDay : arg.allDay
					})
				}
				calendar.unselect()
			},
			editable : true,
			eventLimit : true, // allow "more" link when too many events
			locale : 'ko',
			events : []
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

.all-content {
	width: 100%;
	max-width: 900px;
	margin: 0 auto;
	font-size: 14px;
}
</style>
<div class="all-content">
	<body>
		<div id='calendar'></div>
	</body>
</div>
