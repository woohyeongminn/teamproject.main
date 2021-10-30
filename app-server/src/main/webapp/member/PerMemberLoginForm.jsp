<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인회원 로그인</title>
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
  <h5>개인 회원 로그인</h5>

  <form action='login'>
  <label for='f-email' class='form-label' size='100px'>이메일</label> <input id='f-email' type='email' name='email' placeholder='아이디' size='20'><br>
  <label for='f-password' class='form-label' size='100px'>암호</label> <input id='f-password' type='password' name='password' placeholder='암호' size='20'><br>
  <div class="form-check">
    <input type="checkbox" class="form-check-input" id="dropdownCheck">
    <label class="form-check-label" for="dropdownCheck">
      Remembr me
    </label>
  </div>

  <div class="mb-3">
    <div class="dropdown-divider"></div>
    <a class="dropdown-item" href="#">회원가입</a>
    <a class="dropdown-item" href="#">비밀번호찾기</a>
  </div>
  </div>
  
  <button type="submit" class="btn btn-primary">로그인</button>
  </form>
 </body>
</html>
