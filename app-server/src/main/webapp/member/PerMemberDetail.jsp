<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인회원 상세</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
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
   <b> 📖 개인회원 상세</b>
   <hr>
   <form action='updateform'>
     <span id='no' name='no'>(${perMember.perNo})</span><br>
     <span>이름ㅣ</span> <span>${perMember.perName}</span><br>
     <span>닉네임ㅣ</span> <span>${perMember.perNickname}</span><br>
     <span>이메일ㅣ</span> <span>${perMember.perEmail}</span><br>
     <span>사진ㅣ</span> <img src="..." alt="..." class="img-circle"><br>
     <span>전화번호ㅣ</span> <span>${perMember.perTel}</span><br>
     <span>가입일ㅣ</span> <span>${perMember.perRegisteredDate}</span><br>
     <button type="submit">
        <a href='updateform?no=${perMember.perNo}'>프로필 수정하기</a>
     </button>
     <button type="submit">
        <a href='delete?no=${perMember.perNo}'>탈퇴하기</a>
      </button>
    </form>
</body>
</html>
