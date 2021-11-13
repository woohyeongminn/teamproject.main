<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta charset="UTF-8">
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
  <form action='delete' method='post'>
	  <input type='hidden' name='ceoNo' value="${ceoMember.ceoNo}"><br>

	  <label for='f-email' class='form-label' size='100px'>이메일</label>
	  <input id='f-email' type='email' name='ceoEmail' placeholder='아이디' size='20'><br>
	
	  <p>비밀번호를 입력하세요.</p>
	  <label for='f-password' class='form-label' size='100px'>암호</label>
	  <input id='f-password' type='password' name='ceoPassword' placeholder='암호' size='20'><br>
	
	  <button type="submit" value="삭제" class="btn btn-outline-dark">
	     탈퇴하기
	  </button>
  </form>
 </body>