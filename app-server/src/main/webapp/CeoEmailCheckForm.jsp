<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 이메일 중복체크</title>
</head>
<body onload="pValue()">
<div id="wrap">
  <br>
  <b><font size=4> 아이디 중복체크 </font></b>
  <hr size=1>
  <br>
  <div id = "chk">
    <form id="check">
      <input type="text" name="emial" id="ceoEmail"/>
      <input type="button" value="중복확인" onclick="emialCheck()"/>
    </form>
    <div id="meseege">
    
    </div>
  </div>
</div>

</body>
</html>