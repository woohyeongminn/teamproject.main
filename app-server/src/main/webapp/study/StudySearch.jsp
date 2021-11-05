<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
request.setCharacterEncoding("utf-8");
%>

<form
	action="<%=request.getContextPath()%>/study/StudyList.jsp"
	method="post">
	<select name="sk">
		<option value="area">지역</option>
		<option value="name">분야</option>
		<option value="name">제목</option>
	</select> <input type="text" name="sv"> <input type="submit"
		value="검색버튼">
</form>
