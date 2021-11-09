<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>파일 업로드</title>
</head>
<body>
  <form action="../upload/uploadCheck.jsp" method="post" enctype="multipart/form-data">
    <input type="file" name="file"><br>
    <input type="submit" value="Upload">
  </form>
</body>
</html>