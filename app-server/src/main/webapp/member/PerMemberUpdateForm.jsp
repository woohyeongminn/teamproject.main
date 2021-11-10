<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인회원 프로필 수정</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://static.msscdn.net/mfile_outsrc/js/vendor/jquery-1.11.1.min.js?20160201"></script>
 <style>
    label {
      margin-right: 5px;
      text-align: center;
      display: inline;
      width: 60px;
    }
    legend {
    text-align: center;
    font-family
    }
  .n-btn {
    border-radius: 10px;
    background-color: blanchedalmond;
    color: black;
    font-size: 18px;
  }
  .n-btn:hover {
    background-color: lightyellow;
    color: black;
  }

 </style>
</head>

<body>
<jsp:include page="../header.jsp"/>
  <legend>✏ 개인 회원 프로필 수정</legend><br>

  
  <form  action="update">  
    <span>(${loginUser.perNo})</span><br>
    
    <label for='f-nickname'>닉네임</label>
    <input id='f-nickname' type='text' name='nickname' value='${loginUser.perNickname}'><br>
    
    <label for='f-name'>이름</label>
    <input id='f-name' type='text' name='name' value='${loginUser.perName}'><br>
    
    <label for='f-email'>이메일</label> 
    <input id='f-email' type='email' name='email' value='${loginUser.perEmail}'><br>
    <br>
     
    <label for='f-photo'>사진</label> 
    <input id='f-photo' type='text' name='photo' value='${loginUser.perPhoto}'><br>
    
    <label for='f-tel'>전화</label> 
    <input id='f-tel' type='tel' name='tel' value='${loginUser.perTel}'><br>
    
    <span>가입일ㅣ</span> <span>${loginUser.perRegisteredDate}</span><br>
    <input type ='hidden' name='no' value='${loginUser.perNo}'>
    
		 <section class="n-section-block">
		  <table class="n-table table-row my-info-modify">
		   <tr id="password-area">
		     <th scope="row">비밀번호</th>
		     <td><button  class="n-btn w100 btn-sm btn-default cert-hidden" id="change-password-btn">비밀번호 변경</button></td>
		   </tr>
		                 <!--비밀번호 변경-->
		   <tr id="change-password-area" style="display: none">
		     <th scope="row">비밀번호</th>
		       <td colspan="2">
		         <div class="my-info-modify">
		             <div class="my-info-modify">
		               <div class="input">
		                   <label for="password">현재 비밀번호</label>
		                   <input type="password" class="n-input" id="password">
		                   <span id="password-invalid" class="validate danger"></span>
		               </div>
		               
		               <div class="input">
		                   <label for="newPassword">신규 비밀번호</label>
		                   <input type="password" class="n-input" id="newPassword" maxlength="20">
		                   <span id = "new-password-invalid" class="validate danger"></span>
		                   <span id="valid-newPassword" class="validate" style="display: none">사용 가능한 비밀번호입니다.</span>
		               </div>
		               
		               <div class="input">
		                   <label for="confirmPassword">신규 비밀번호 재 입력</label>
		                   <input type="password" class="n-input" id="confirmPassword" maxlength="20">
		                   <span id="confirm-password-invalid" class="validate danger"></span>
		                   <span  id="valid-confirmPassword" class="validate" style="display: none">사용 가능한 비밀번호입니다.</span>
		               </div>
		               <div class="btn-group">
		                   <button type="button" class="n-btn btn-sm btn-lighter" id="change-password-cancel-btn">취소</button>
		                   <button type="button" class="n-btn btn-sm btn-accent disabled" id="change-password-finish-btn" disabled >완료</button>
		               </div>
		         </div>
		       </td>
		   </tr>
		  </table>
		 </section>
  
  <br><button class="n-btn w100 btn-sm btn-default cert-hidden" type="submit" value="수정">수정하기</button>
  </form> 

  <script type="text/javascript" src="update.js"></script>
  
 </body>
</html>






