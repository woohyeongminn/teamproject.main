<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 프로필</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
   
   <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script> <!-- 의존하는 것 우선 -->
   <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
<style>
  label {
    margin-left: 10px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  legend {
  text-align: center;
  }
  input {
  border : white;
  outline-color : lightgray;
  }
  p {
  text-align-last: center;
  }
  div {
  margin-right: 10px;
  display: flex;
  align-items: center;
  flex-direction: row;
  justify-content: center;
  }
  a {
  color : black;
  text-decoration : blink;
  }
  a:hover {
  color : white;
  }
  .c-top {
  width: 100%;
  padding: 20px 0 20px 0px;
  text-align: center;
  font-weight: bold;
  background-color: rgb(247, 231, 215);
}
</style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<fieldset>
<section>
<div class="c-top">
        🙂 마이페이지
      </div>
<table class="table table-responsive">
<td><form action='update'></td>
<br>
<p><label for='f-nickName' class='form-label' size='100px'>닉네임</label>
<input id='f-nickName' type='nickName' name='nickName' placeholder='닉네임' size='20'></p>
<p><label for='f-email' class='form-label' size='100px'>이메일</label>
<input id='f-email' type='email' name='email' placeholder='아이디' size='20'></p>
<p><label for='f-password' class='form-label' size='100px'>비밀번호</label>
<input id='f-password' type='password' name='password' placeholder='비밀번호' size='20'></p>
<br>
</table>
</fieldset>
<%-- <input type='hidden' name='no' value='${loginAdmin.masterNo}'> --%>
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
<button type="submit" class="btn btn-outline-dark" value="변경" >변경</button> 
</div>
</form>
</section>
</body>
</html>