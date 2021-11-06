<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 프로필 수정</title>
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
  <jsp:include page="../header.jsp"/>
  <h5>기업 회원 프로필 수정</h5>

  <form action="update">
  <span>(${loginCeoUser.ceoNo})</span><br>

  <label for='f-name' class='form-label' size='100px'>이름</label>
  <input id='f-name' type='text' name='name' value='${ceoMember.ceoName}'><br>
  
  <label for='f-nickname' class='form-label' size='100px'>닉네임</label>
  <input id='f-nickname' type='text' name='nickname' value='${ceoMember.ceoNickname}'><br>
  
  <label for='f-photo' class='form-label' size='100px'>사진</label>
  <input id='f-photo' type='text' name='photo' value='${ceoMember.ceoPhoto}'><br>
  
  <label for='f-tel' class='form-label' size='100px'>전화번호</label>
  <input id='f-tel' type='tel' name='tel' value='${ceoMember.ceoTel}'><br>
  
  <label for='f-email' class='form-label' size='100px'>이메일</label>
  <input id='f-email' type='email' name='email' value='${ceoMember.ceoEmail}'><br>
  
  <label for='f-password' class='form-label' size='100px'>암호</label>
  <input id='f-password' type='password' name='password'><br>
  
  <span>가입일ㅣ</span> <span>${ceoMember.ceoRegisteredDate}</span><br>
  
  <button type="submit" value="수정">수정</button>

  </form>
  
 </body>
</html>