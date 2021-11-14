<%@page import="com.ogong.pms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<style>
.btn {
font-size: 14px;
 line-height: 14px;
}
.all {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    text-align: center;
}
#createDt {
  text-align:right;
  font-size: 14px;
}
#name, #email, #tel {
text-align:left;
}
label {

}
</style>
  
 
 
    <hr><br><div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <input type="submit" value="수정하기" formaction="update" class ="btn btn-outline-dark"/>
      <input type="submit" value="취소하기" formaction="${contextPath}/app/member/detail" class ="btn btn-outline-dark"/>
    </div>
  </div>
</form>
     