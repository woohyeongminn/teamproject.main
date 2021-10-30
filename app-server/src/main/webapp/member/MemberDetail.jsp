<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <title>마이 페이지</title>
</head>
<body>
<h1> ▶내 프로필</h1>
<style>
 label {
    margin-right: 5px;
    text-align: right;
    display: inline-block;
    width: 60px;
   }
</style>
</head>
<body>
	  <td>${myPageMember.perNickname}</td><br>
	  <td>이름:${myPageMember.perName}</td><br>
	  <td>사진:${myPageMember.perPhoto}</td><br>
	  <td>번호:${myPageMember.perTel}</td><br>
	  <td>이메일:${myPageMember.perEmail}</td><br> 
	  <td>가입일:${myPageMember.perRegisteredDate}</td><br>
	  <button><a href='update/form?no={myPageMember.perNo}'><p>개인정보수정</p></a></button>
	  <button><a href='delete?no={myPageMember.perNo}'><p>회원탈퇴</p></a></button></tbody>
</body>
</html>
