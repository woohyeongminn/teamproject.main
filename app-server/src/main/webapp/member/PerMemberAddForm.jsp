<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ✏회원가입</title>
  <style>
  * {
  font-size: 14px;
  }
  
  label {
    margin-right: 5px;
    text-align: center;
    display: inline;
    width: 60px;
    size:100px;
  }
  
  .btn {
    line-height: 14px;
  }
  </style>
</head>

<body><br>
   <form action='add' method="post">
        <label for='f-name' class='form-label' size='100px'>이름</label>
        <input id='f-name' type='text' name='name' placeholder="이름"><br>
        
        <label for='f-email' class='form-label' size='100px'>이메일</label>
        <input id='f-email' type='email' name='email' placeholder="이메일"><br>
        
        <label for='f-password' class='form-label' size='100px'>암호</label>
        <input id='f-password' type='password' name='password' placeholder="암호"><br>
      
        <div id="nickname">
        <label for='f-nickname' class='form-label' size='100px'>닉네임</label>
        <input id='f-nickname' type='name' name='nickname' placeholder='닉네임' size='20'><br>
        </div>
        
        <label for='f-photo' class='form-label' size='100px'>사진</label>
        <input id='f-photo' type='file' name='photo' placeholder="사진"><br>
        
        <label for='f-tel' class='form-label' size='100px'>전화번호</label>
        <input id='f-tel' type='tel' name='tel' placeholder="전화번호"><br><br>
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-outline-dark" type="submit" value="등록" formaction="add">등록하기</button>
   </div>
    </div>
   </form>
   
   <script type="text/javascript">
   var fNickname = document.querySelector()
   
   </script>
   
   
 </body>
</html>





