<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
</head>
<style>

.all-content {
  width: 100%;
  margin: 0 auto;
}

/* 버튼  class = "btn btn-outline-dark"  이걸로 통일 */

/* 테이블  class = "table table-responsive text-center"  이걸로 통일 */
/* 번호, 제목, 작성자, 등록일, 조회수 */
/* 각자 페이지에 맞게 목록형은 알아서~ */

</style>
</head>






<!-- 아래 부분 이제 안씀 템플릿에 적용되어 있어서 -->
<body>
<jsp:include page="../header.jsp"/>
    <!-- 이건 복붙해도되고 안해도됨 -->
    <div class="all-content"> 
    </div>
<jsp:include page="../footer.jsp"/>
</body>
</html>