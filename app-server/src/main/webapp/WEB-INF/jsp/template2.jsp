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
  <meta content="" name="description">
  <meta content="" name="keywords">
  
  
  <!-- 우리꺼 -->
  <link rel="stylesheet" href="${contextPath}/node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="${contextPath}/node_modules/sweetalert2/dist/sweetalert2.css">
  <link rel="stylesheet" href="${contextPath}/css/common.css">
  
  <script src="${contextPath}/node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="${contextPath}/node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script src="${contextPath}/node_modules/sweetalert2/dist/sweetalert2.js"></script>

  <link rel="icon" href="${contextPath}/img/favicon.ico" type="image/x-icon" sizes="16x16" style="background-color: rgba(255, 255, 255, 0);">
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

<style>
#header {
  position: absolute;
  background-color: white;
}

.template-wrap {
    width:100%;
    height: 800px;
    background-color: red;
}
    
.template-top {
    display: inline-block;
    width: 1200px;
    text-align: center;
    margin: 0 auto;
    vertical-align: middle;
    font-weight: 600;
    margin-top: 36px;
    margin-left: 105px;
}

.top-template-wrap {
    width:100%;
    position: absolute;
    top:100px;
}

#footer {
  position: absolute;
  bottom: 0;
}
</style>
</head>

<body>
  <!-- 헤더 -->
	<jsp:include page="header2.jsp"/>
	
	
	
	
	<div class="template-wrap">
	
	<!-- template-wrap 은 100% 짜리 배경-->
   <div class="top-template-wrap" style="background-color: #fbf2ee75;">
     <div class="template-top">
       ${pageTitle}
     </div>
   </div>

   
     <div class="template-content">
       <jsp:include page="${contentUrl}"/>
     </div>
      
   </div>
	
	
	
	
	
	
	
	<!-- 푸터 -->
   <jsp:include page="footer.jsp"/>
   

	 <!-- Vendor JS Files -->
	 <script src="${contextPath}/css/assets/vendor/aos/aos.js"></script>
	 <script src="${contextPath}/css/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	 <script src="${contextPath}/css/assets/vendor/glightbox/js/glightbox.min.js"></script>
	 <script src="${contextPath}/css/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	 <script src="${contextPath}/css/assets/vendor/php-email-form/validate.js"></script>
	 <script src="${contextPath}/css/assets/vendor/purecounter/purecounter.js"></script>
	 <script src="${contextPath}/css/assets/vendor/swiper/swiper-bundle.min.js"></script>
	
	 <!-- Template Main JS File -->
	 <script src="${contextPath}/css/assets/js/main.js"></script>
	 
</body>
</html>
