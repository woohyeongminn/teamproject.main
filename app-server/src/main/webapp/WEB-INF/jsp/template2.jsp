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

  <title>Today Study</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="${contextPath}/css/assets/img/favicon.png" rel="icon">
  <link href="${contextPath}/css/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Montserrat:300,400,500,600,700" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${contextPath}/css/assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="${contextPath}/css/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${contextPath}/css/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${contextPath}/css/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="${contextPath}/css/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link rel="stylesheet" href="${contextPath}/css/assets/css/style.css">

</head>
<body>
<jsp:include page="header2.jsp"/>

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
		
		  <jsp:include page="footer2.jsp"/>
	</body>
</html>
