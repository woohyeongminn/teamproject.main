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
  #all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    font-size:14px;
  } 
  #top {
  text-align: center;
  }
  a {
  color: black;
  }
</style>

<body>
  <div id="all-content">
   <br>
    <div id="top">
      <b style="font-size: 20px">🔍이메일 찾기</b><br> 
    </div>
    <hr>
    <form id="member-form" action='findemail' name='perInfo' method='post' >
      <div id="mn">
        <label id='f-name' for='f-name' class="col-sm-2 col-form-label">이름</label>
        <input id='i-name' type='text' name='perName' placeholder="*필수"/><br>
      </div>

      <div id="mt">
        <label id='f-tel'for='f-tel' class="col-sm-2 col-form-label">전화번호</label>
        <input id='i-tel' type='text' name='tel' pattern="[0-9]+" minlength='3' maxlength='3'  style="width:50px;"/> -
        <input id='i-tel1' type='text' name='tel' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:50px;"/> -
        <input id='i-tel2' type='text' name='tel' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:50px;"/> <br>
      </div>
      
       <div class="d-grid gap-2 d-md-flex justify-content-md-end">
         <button class="btn btn-outline-dark" type="submit" >🔍이메일 찾기</button> 
       </div><hr> 
       <div class="d-grid gap-2 d-md-flex justify-content-md-end">
         <a href="getpwbyemailform">비밀번호 찾기</a> |
         <a href="${contextPath}/app/index">취소하기</a>
       </div> 
   </form>
  </div>
</body>

<script>  
  document.querySelector("#all-content").onsubmit = () => {
  if (document.querySelector("#i-name").value == "") {
    alert("**이름을 입력해주세요.")
    return false;
  } else if (document.querySelector("#i-tel").value == "" ||
		  document.querySelector("#i-tel1").value == "" ||
		  document.querySelector("#i-tel2").value == "") {
    alert("**전화번호를 입력해주세요.")
    return false;
  }
};
</script>
 



