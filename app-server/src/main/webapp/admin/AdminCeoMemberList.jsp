<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
* {
  font-size: 14px;
}

.all-content {
  width: 100%;
  margin: 0 auto;
  padding: 30px;
}

.t-top {
  text-align: center;
}

.t-content {
  text-align: center;
}
</style>
</head>

<body>
<div class="all-content">
  <c:if test='${not empty ceoMemberList}'>
    <table class="table table-responsive text-cente">
        <thead class="t-top">
          <tr>
            <th>번호</th>
            <th>이름</th>
            <th>닉네임</th>
            <th>이메일</th>
            <th>가입일</th>
          </tr>
          </thead>
          <tbody class="t-content">
          <c:forEach items="${ceoMemberList}" var="ceoMember">
             <tr>
              <td>(${ceoMember.ceoNo})</td>
              <td><a href='detail?no=${ceoMember.ceoNo}'>${ceoMember.ceoName}</a></td> 
              <td>${ceoMember.ceoNickname}</td> 
              <td>${ceoMember.ceoEmail}</td> 
              <td>${ceoMember.ceoRegisteredDate}</td>
             </tr>
         </c:forEach>
        </tbody>
    </table>
    </c:if>
    <c:if test="${empty ceoMemberList}">
        <p>등록된 기업회원이 없습니다.</p>
    </c:if>
  </div>
</body>
</html>