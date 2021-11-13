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

body {
  height: auto;
}

a {
  text-decoration: none;
}

legend {
  text-align: center;
}

.all-content {
   max-width: 900px;
   margin: 0 auto;
  height: 700px;
}

button {
  border: 0;
  background: transparent;
}
 
.btn {
  margin: 0 7px;
  padding: 5px 10px;
  height: auto;
  line-height: inherit;
}
 
</style>

<body>
  <br><br><br>
   <div class="all-content">
		   <P>&emsp;&emsp;등록된 스터디카페 삭제 시 복구가 불가능합니다.</P>
		   <a href='delete?cafeno=${cafe.no}' class='btn btn-outline-dark'>삭제</a>
   </div>
</body>
     
<script>
  location.href = "#tab2";
</script>
