<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 스터디카페 룸 등록</title>
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
  #aside {
     width: 120px;
     height: 171px;
     float: left;
  }
  #content {
     margin-left: 130px;
  } 

  #input-file-button {
  display: inline-table;
  width: 120px;
  padding: 2px;
  background-color: gray;
  border-radius: 5px;
  color: white;
  font-size: smaller;
  cursor: pointer;
  text-align: center;
  margin-top: 5px;
 }

 #c-image {
  width: 120px;
  height: 150px;
  background-color: darkgray;
  display: table-cell;
  vertical-align: middle;
  text-align: center;
}
#c-grade {
  margin-left: 41px;
  vertical-align: 4px;
}
#c-review {
  width: 427px;
  background-color: whitesmoke;
  height: 80px;
  margin-bottom: 10px;
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
  <b>👩‍🏫 내 스터디카페 룸 등록</b><br>
  <p>등록된 스터디룸이 없습니다.</p>
  <hr>
    <div id='aside'>
      <span id='c-image'>사진</span>
      <form action="upload.php" method="post" enctype="multipart/form-data">
        <input id="input-roomFile" type="file" multiple="multiple" style='display:none' name='filename[]'/>
        <label id="input-file-button" for="input-file">
          파일 첨부
        </label>
      </form>
    </div>
    <form action='add'>
    <div id='content'>
      <input type ='hidden' name='no' value='${cafeNo}'>
      
      <label for='f-roomName'>스터디룸 이름</label>
      <input id='f-roomName' type='text' name='name'><br>
      
      <label for='f-roomInfo'>스터디룸 설명</label>
      <input id='f-roomInfo' type='text' name='info'><br>
      
      <label for='f-roomPeople'>스터디룸 최대인원</label>
      <input id='f-roomPeople' type="number" pattern="\d*" name='people'><br>

      <label for='f-roomPrice'>스터디룸 시간당금액</label>
      <input id='f-roomPrice' type='tel' pattern="\d*"  name='roomPrice'><br>
      
    </div>
    <div id='button'>
     <button id='b-but' type="submit" value="등록" formaction="add">등록</button>
    </div>
  </form> 
</body>
</html>