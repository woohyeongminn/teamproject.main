<%@page import="com.ogong.pms.web.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta charset="UTF-8">
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