<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <style>
   label {
    margin-right: 10px;
    text-align: center;
    display: inline;
    width: 60px;
  }
  
  legend {
  text-align: center;
  font-size: 16px;
  }
  
  form {
  max-width: 500px;
  }
  
  .input {
  display: inline-block;
  padding:10px;
  width:100%;
  margin-top: 10px;
  border-color : lightgray;
  border-radius: 6px;
  }
  
  .input[type=checkbox]:checked {
  background-color: black;
  }
  
  p {
  text-align-last: center;
  }
  
  div {
  margin-right: 10px;
  align-items: end;
  flex-direction: row;
  justify-content: center;
  }
  
  a {
  color : black;
  text-decoration : blink;
  }
  
  a:hover {
  color : darkgray;
  }
  
  .btn {
   font-size: 14px;
   line-height: 24px;
  }
  .all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    padding: 50px;
    margin-top:40px;
    text-align: center;
  }
   input {
  border : white;
  outline-color : lightgray;
  }

</style>
<div class="all-content">
  <b style="font-size:20px"> 🙋개인 회원 가입 </b>
    <fieldset>
     <table class="table table-responsive">
      <form action='add' method="post"><br>
        <p><label for='f-photo' class='form-label' size='100px'>사진</label>
          <input id='f-photo' type='file' name='photo' placeholder="사진"><br>
        </p>
        <p><label for='f-name' class='form-label' size='100px'>이름</label>
          <input id='f-name' type='text' name='name' placeholder="이름"><br>
        </p>
        <p><label for='f-email' class='form-label' size='100px'>이메일</label>
          <input id='f-email' type='email' name='email' placeholder="이메일"><br>
        </p>
        <p><label for='f-password' class='form-label' size='100px'>암호</label>
          <input id='f-password' type='password' name='password' placeholder="암호"><br>
        </p>
        <p><label for='f-nickname' class='form-label' size='100px'>닉네임</label>
          <input id='f-nickname' type='name' name='nickname' placeholder="닉네임"><br>
        </p>
        <p><label for='f-tel' class='form-label' size='100px'>전화번호</label>
          <input id='f-tel' type='tel' name='tel' placeholder="전화번호"><br>
        </p>
    </table>
   </fieldset> 
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-outline-dark" type="submit" value="등록" formaction="add">가입하기</button>
    </div>
   </form>
</div>





