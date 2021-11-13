<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<div class="all-content">
  <c:if test='${not empty ceoMemberList}'>
    <table class="table table-responsive text-cente">
        <thead class="t-top">
          <tr style="margin-left: auto;" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
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
  
<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">

  <jsp:include page="AdminMenu.jsp"/>
    
</div>
