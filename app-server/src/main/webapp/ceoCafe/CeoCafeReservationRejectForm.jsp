<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 예약 거절</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
* {
  font-size: 14px;
}

a {
 text-decoration:none;
}

label {
  display: inline-block;
  margin-right: 5px;
  width: 130px;
}

.all-content {
  width: 100%;
  margin: 0 auto;
}
</style>
</head>

<body>
  <div class="all-content">
  <P>&emsp;&emsp;예약 내역을 거절 시 복구가 불가능합니다.</P>
  <a href='reject?resno=${reserNo}' class='btn btn-outline-dark'>거절</a>
  <a href='detail?resno=${reserNo}' class='btn btn-outline-dark'>취소</a>
  </div>
</body>
</html>