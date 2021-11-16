<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="https://static.msscdn.net/mfile_outsrc/js/vendor/jquery-1.11.1.min.js?20160201"></script>
 <style>
  form {
  max-width: 500px;
  }
  .btn {
   font-size: 14px;
   line-height: 12px;
  }
  b {
  text-align: center;
  font-size:20px
  }  
 .all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    font-size:14px;
  } 
  #top {
  text-align: center;
  }

</style>
<body>
  <div class="all-content"><br>
    <div id="top">
      <b style="font-size: 20px">🔍임시 비밀번호 발급</b><br> 
    </div><hr>
    <form id="member-form" action='getpwbyemail' name='perInfo' method='post' >

      <div id="mn">
        <label id='f-name' for='f-name' class="col-sm-2 col-form-label">이름</label>
        <input id='i-name' type='text' name='perName' placeholder="*필수"/><br>
      </div>

      <div id="me">
        <label id='f-email' for='f-email' class="col-sm-2 col-form-label">이메일</label>
        <input id='i-email' type='text' name='perEmail' pattern="^[a-zA-Z0-9]+$" placeholder="*필수" onkeydown="inputEmail()"/>@
        <select name="site">
          <option>naver.com</option>
          <option>daum.net</option>
          <option>gmail.com</option>
          <option>kakao.com</option>
        </select>
      </div>
       <div class="d-grid gap-2 d-md-flex justify-content-md-end">
         <button class="btn btn-outline-dark" type="submit" >📧비밀번호 발급</button> 
       </div><hr> 
       <div class="d-grid gap-2 d-md-flex justify-content-md-end">
         <a  href="findemailform">이메일 찾기</a> |
         <a  href="${contextPath}/app/index">취소하기</a>
       </div>
   </form>
  </div>
</body>

<script>  
  document.querySelector(".all-content").onsubmit = () => {
  if (document.querySelector("#i-name").value == "") {
    alert("**이름을 입력해주세요.")
    return false;
  } else if (document.querySelector("#i-email").value == "") {
    alert("**이메일을 입력해주세요.")
    return false;
  }
};
</script>

 



