<%@page import="com.ogong.pms.domain.Study"%>
<%@page import="com.ogong.pms.dao.StudyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>📖 스터디 찾기</title>
<style>
label {
	margin-right: 5px;
	text-align: right;
	display: inline-block;
	width: 60px;
}
</style>
</head>
<body>
	<h1>스터디 상세</h1>
	<%
	int no = Integer.parseInt(request.getParameter("no"));
	Study study = studyDao.findByNo(no);

	if (study == null) {
	%>
	해당 번호의 스터디가 없습니다.
	<%
	} else {
	%>
	<form action='StudyUpdate.jsp'>
		<label for='f-studyNo'>번호</label> <input id='f-studyNo' type='text'
			name='studyNo' value='<%=study.getStudyNo()%>' readonly><br>
		<label for='f-countBookMember'>북마크</label> <input
			id='f-countBookMember' type='text' name='countBookMember'
			value='<%=study.getCountBookMember()%>' readonly><br> <label
			for='f-studyTitle'>제목</label> <input id='f-studyTitle' type='text'
			name='studyTitle' value='<%=study.getStudyTitle()%>'><br>
		<label for='f-owner'>조장</label> <input id='f-owner' type='text'
			name='owner' value='<%=study.getOwner().getPerNickname()%>'><br>
		<label for='f-subjectName'>분야</label> <input id='f-subjectName'
			type='text' name='subjectName' value='<%=study.getSubjectName()%>'><br>
		<label for='f-area'>지역</label> <input id='f-area' type='text'
			name='area' value='<%=study.getArea()%>'><br> <label
			for='f-countMember'>인원수</label> <input id='f-countMember' type='text'
			name='countMember' value='<%=study.getCountBookMember()%>' readonly><br>
		<label for='f-numberOfPeple'>최대 인원수</label> <input
			id='f-numberOfPeple' type='text' name='numberOfPeple'
			value='<%=study.getCountBookMember()%>' readonly><br> <label
			for='f-faceName'>대면/비대면</label> <input id='f-faceName' type='text'
			name='faceName' value='<%=study.getFaceName()%>'><br> <label
			for='f-introduction'>소개글</label> <input id='f-introduction'
			type='text' name='introduction' value='<%=study.getIntroduction()%>'><br>
		<button>변경</button>
		<a href='StudyDelete.jsp?no=<%=study.getStudyNo()%>'>[삭제]</a> <a
			href='StudyList.jsp'>[목록]</a><br>
	</form>
	<%
	}
	%>
</body>
</html>
<%!StudyDao studyDao;

  public void jspInit() {
    ServletConfig config = getServletConfig();
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }%>
