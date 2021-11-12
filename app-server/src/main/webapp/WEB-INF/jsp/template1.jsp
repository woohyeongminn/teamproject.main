<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<c:if test="${not empty refresh}">
  <meta http-equiv="Refresh" content="${refresh}">   
</c:if>
  <title>오늘의 공부</title>
  <link rel="stylesheet" href="${contextPath}/node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="${contextPath}/node_modules/sweetalert2/dist/sweetalert2.css">
  <link rel="stylesheet" href="${contextPath}/css/common.css">
  
  <script src="${contextPath}/node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="${contextPath}/node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="${contextPath}/node_modules/sweetalert2/dist/sweetalert2.js"></script>
  
</head>
	<body>
		<jsp:include page="header.jsp"/>
		
		
		<!-- template-wrap 은 100% 짜리 배경-->
		
		<div class="template-wrap" style="background-color: rgb(247, 231, 215);">
		  <div class="template-top">
        ${pageTitle}
      </div>
    </div>

		<div class="template-wrap">
			<div class="template-content">
			  <jsp:include page="${contentUrl}"/>
			</div>  
		</div>
		
		<div class="template-footer" style="bottom: 0; font-size: 10px;">
		  <jsp:include page="footer.jsp"/>
		</div>
		
	</body>
</html>
