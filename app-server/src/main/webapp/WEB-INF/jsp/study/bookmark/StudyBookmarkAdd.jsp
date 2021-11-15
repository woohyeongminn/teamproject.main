<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<table border='1'>
		<tbody>
			<c:forEach items="${study.members}" var="joinMember">
				<c:if test="${loginUser.perNo eq joinMember.perNo}">이미 참여 중인 스터디입니다.</c:if>
			</c:forEach>
			<c:forEach items="${study.watingMember}" var="watingMember">
				<c:if test="${loginUser.perNo eq watingMember.perNo}">이미 승인 대기 중인 스터디입니다.</c:if>
			</c:forEach>
			<c:forEach items="${study.bookMarkMember}" var="bookMarkMember">
				<c:if test="${loginUser.perNo eq bookMarkMember.perNo}">이미 북마크 중인 스터디입니다.</c:if>
			</c:forEach>
			<p>북마크가 완료되었습니다.</p>
		</tbody>
	</table>
</body>
</html>
