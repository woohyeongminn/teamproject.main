<%@page import="com.ogong.pms.servlet.cafe.CafeHandlerHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
* {
  font-size: 14px;
}

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
<div class="all-content">
      <form action="add" method="post" enctype="multipart/form-data">
    <div id='aside'>
      <span id='c-image'>사진</span>
        <input id="input-roomFile" type="file" multiple="multiple" name='photo'/>
    </div>
    <div id='content'>
      <input type ='hidden' name='cafeno' value='${cafeNo}'>
      
      <label for='f-roomName'>스터디룸 이름</label>
      <input id='f-roomName' type='text' name='name'><br>
      
      <label for='f-roomInfo'>스터디룸 설명</label>
      <input id='f-roomInfo' type='text' name='info'><br>
      
      <label for='f-roomPeople'>스터디룸 최대인원</label>
      <input id='f-roomPeople' type="number" pattern="\d*" name='people'><br>

      <label for='f-roomPrice'>스터디룸 시간당금액</label>
      <input id='f-roomPrice' type='tel' pattern="\d*"  name='roomPrice'><br>
      
      
      <!-- 
      if (people <= 0) {
          System.out.println(" >> 인원을 0보다 작게 설정할 수 없습니다. 다시 입력해주세요.");
        } else if (people > 50) {
          System.out.println(" >> 최대 50명까지 입력할 수 있습니다. 다시 입력해주세요.");
        } else {
        
        
        if (timePrice <= 0) {
          System.out.println(" >> 금액을 0보다 작게 설정할 수 없습니다. 다시 입력해주세요.");
        } else if (timePrice > 500000) {
          System.out.println(" >> 50만원 이상 입력할 수 없습니다. 다시 입력해주세요.");
        } 
         -->
    </div>
    <div id='button'>
     <button id='b-but' type="submit" value="등록" >등록</button>
    </div>
    </form>
  </div> 
</body>
</html>