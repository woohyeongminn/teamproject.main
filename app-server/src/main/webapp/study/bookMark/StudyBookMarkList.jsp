<%@page import="com.ogong.pms.domain.Study"%>
<%@page import="java.util.Collection"%>
<%@page import="com.ogong.pms.dao.StudyDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<title>🌟 내 북마크</title>
</head>
<body>
	<h1>▶ 내 북마크 목록</h1>
	<a href=''>북마크 추가</a>
	<br>
	<table border='1'>
		<thead>
			<tr>
				<th>번호</th>
				<th>북마크</th>
				<th>제목</th>
				<th>분야</th>
				<th>인원수</th>
				<th>최대 인원수</th>
				<th>조장</th>
				<th>대면/비대면</th>
			<tr>
				</thread>
		<tbody>

			<%
			Collection<Study> studyList = studyDao.findAll();

			for (Study study : studyList) {
			%>
			<tr>
				<td><%=study.getStudyNo()%></td>
				<td><%=study.getCountBookMember()%></td>
				<td><a href='StudyDetail?no=%1$d'><%=study.getStudyTitle()%></a></td>
				<td><%=study.getSubjectName()%></td>
				<td><%=study.getCountMember()%></td>
				<td><%=study.getNumberOfPeple()%></td>
				<td><%=study.getOwner().getPerNickname()%></td>
				<td><%=study.getFaceName()%></td>
			</tr>
			<%
			}
			%>

		</tbody>
	</table>
</body>
</html>
<%!StudyDao studyDao;

  public void jspInit() throws ServletException {
    ServletConfig config = getServletConfig();
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    studyDao = (StudyDao) 웹애플리케이션공용저장소.getAttribute("studyDao");
  }%>
