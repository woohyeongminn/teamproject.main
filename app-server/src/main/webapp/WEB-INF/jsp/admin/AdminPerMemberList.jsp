<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<style>
* {
  font-size: 14px;
}

  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
  }
  legend {
  text-align: center;
  }
  div {
  margin-right: 10px;
  }
</style>
</head>

<br>
    <table class="table">

        <thead>
          <tr style="margin-left: auto;" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
            <th>번호</th>
            <th>이름</th>
		       <th>닉네임</th>
            <th>이메일</th>
            <th>가입일</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${perMemberList}" var="perMember">
	             <tr>
	              <td>${perMember.perNo}</td>
	              <td><a href='permemberdetail?no=${perMember.perNo}'>${perMember.perName}</a></td> 
	              <td>${perMember.perNickname}</td> 
	              <td>${perMember.perEmail}</td> 
	              <td>${perMember.perRegisteredDate}</td>
	             </tr>
         </c:forEach>
        </tbody>
  </table>
  
<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">

  <jsp:include page="AdminMenu.jsp"/>
    
</div>

<script>
document.querySelectorAll("tbody a").forEach((aTag) => {
  aTag.onclick = () => false;
});

var trList = document.querySelectorAll("tbody tr"); // 리턴 객체는 HTMLCollection 타입 객체이다.
trList.forEach(function(trTag) {
  trTag.onclick = (e) => {
    //console.log(e.currentTarget.querySelector("a").href);
    //e.currentTarget.querySelector("a").click();
    window.location.href = e.currentTarget.querySelector("a").href;
    //window.location.href = "detail?no=" + e.currentTarget.getAttribute("data-no");
  };
});
</script>
