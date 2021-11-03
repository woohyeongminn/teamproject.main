<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 스터디카페 삭제</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
a {
 text-decoration:none;
}
label {
  display: inline-block;
  margin-right: 5px;
  width: 130px;
}
#button {
  margin-left: 130px;
  margin-top: 30px;
}
#b-but {
  width: 140px;
  padding: 5px;
  margin-right: 10px;
  background-color: dimgray(209, 209, 209);
  border-radius: 4px;
  color: black;
  font-size: smaller;
  cursor: pointer;
  text-align: center;
}
</style>
</head>

<body>
  <b>&emsp;&emsp;👩‍🏫 내 스터디카페 삭제</b><br>
  <hr>
  <P>&emsp;&emsp;등록된 스터디카페 삭제 시 복구가 불가능합니다.</P>
  <div id='button'>
     <a href='delete?no=${cafe.no}&ceoNo=${ceoNo}'>삭제</a>
  </div>
</body>
</html>