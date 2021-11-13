<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
* {
  font-size:14px;
}
label {
	margin-right: 5px;
	text-align: right;
	display: inline-block;
	width: 60px;
}
</style>
</head>
<body>
	<c:if test="${study.owner.perNo eq loginUser.perNo}">
		<c:choose>
			<c:when test="${study.countMember > 0}">
        구성원에게 조장 권한을 위임하고 탈퇴를 진행해 주세요.
      </c:when>
			<c:when test="${study.watingCountMember > 0}">
        승인 대기 중인 구성원이 없어야 스터디 탈퇴가 가능합니다.
      </c:when>
		</c:choose>
	</c:if>
	<span>스터디 탈퇴가 완료되었습니다.</span>
	<br>
	<button>
		<a href='${contextPath}/app/mystudy/list'>목록</a>
	</button>
</body>
</html>
