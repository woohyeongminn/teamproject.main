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
  <div class="all-content">
   <br>
    <div id="top">
      <b style="font-size: 20px">🔍이메일 찾기</b><br> 
    </div>
   <hr>
    <form id="member-form" action='findemail' name='perInfo' method='post' >

      <div id="mn">
        <label id='f-name' for='f-name' class="col-sm-2 col-form-label">이름</label>
        <input id='i-name' type='text' name='name' placeholder="*필수"/><br>
      </div>

      <div id="mt">
        <label id='f-tel'for='f-tel' class="col-sm-2 col-form-label">전화번호</label>
        <input id='i-tel' type='text' name='tel' pattern="[0-9]+" minlength='3' maxlength='3'  style="width:50px;"/> -
        <input type='text' name='tel' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:50px;"/> -
        <input type='text' name='tel' pattern="[0-9]+" minlength='4' maxlength='4'  style="width:50px;"/> <br>
      </div><hr>
      
       <div class="d-grid gap-2 d-md-flex justify-content-md-end">
         <button class="btn btn-outline-dark" type="submit" >🔍이메일 찾기</button> 
         <a type="button" class="btn btn-outline-dark" href="${contextPath}/app/index">❌취소하기</a>
       </div> 
   </form>
   </div>
</body>

 



