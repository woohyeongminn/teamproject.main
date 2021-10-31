<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 상세</title>
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
   <b> 아이디 비밀번호 찾기</b>
   <hr>
   <dl>
      <dt>아이디 찾기</dt>
      <dd><input id='name' type='text' name='name' placeholder='이름을 입력하세요' size='30px'></dd>
      <p>해당 이름이 존재하지 않습니다.</p>
      <dd><input id='tel' type='tel' name='tel' placeholder='전화번호를 입력하세요' size='30px'></dd>
      <p>회원 이름과 전화번호가 일치하지 않습니다.</p>
      <p>'%s님'의 이메일 >> ", ceoMember.getCeoName()</p>
      <dt>비밀번호 찾기</dt>
      <ul>
        이메일로 발급
        <dd><input id='name' type='text' name='name' placeholder='이름을 입력하세요' size='30px'></dd>
        <dd><input id='email' type='email' name='email' placeholder='이메일를 입력하세요' size='30px'></dd>
      </ul>
      <ul>전화번호로 발급
        <dd><input id='name' type='text' name='name' placeholder='이름을 입력하세요' size='30px'></dd>
        <dd><input id='tel' type='tel' name='tel' placeholder='전화번호를 입력하세요' size='30px'></dd>
      </ul>
   </dl>
</body>
</html>