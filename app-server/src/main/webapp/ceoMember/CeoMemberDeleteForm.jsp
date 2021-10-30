<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 탈퇴</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  legend {
  text-align: center;
  }
</style>
</head>

<body>
  <h5>기업 회원 탈퇴</h5>

  <form action='delete'>
  <label for='f-email' class='form-label' size='100px'>이메일</label> <input id='f-email' type='email' name='email' placeholder='아이디' size='20'><br>
 
  <c:set var='email' value='${ceoMember.ceoEmail}' }/>
  <c:if test='${ceoMember.ceoEmail != f-email} }'>
  <c:out value='<p>이메일이 일치하지 않습니다.</p>'/>
  </c:if>
 
  <p>비밀번호를 입력하세요.</p>
  <label for='f-password' class='form-label' size='100px'>암호</label> <input id='f-password' type='password' name='password' placeholder='암호' size='20'><br>
  

  <c:set var='email' value='${ceoMember.ceoPassword}' }/>
  <c:if test='${ceoMember.ceoPassword != f-password} }'>
  <c:out value='<p>비밀번호가 일치하지 않습니다.</p>'/>
  </c:if>

  <button type="submit" value="삭제" formaction="delete" class="btn btn-primary">
     <a href='delete?no=${ceoMember.ceoNo}'>탈퇴하기</a>
  </button>
      
  </form>
 </body>
</html>