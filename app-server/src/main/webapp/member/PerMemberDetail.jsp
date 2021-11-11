<%@page import="com.ogong.pms.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	 #info {
  text-align-last: center;
  }
</style>


     <!-- <span id='no' name='no'>(${loginUser.perNo})</span><br> -->
     <div id="photo">
     <span>사진ㅣ</span> <img src="..." alt="..." class="img-circle">
     </div>
     <div id="info">
     <span>이름ㅣ</span> <span>${loginUser.perName}</span><br>
     <span>닉네임ㅣ</span> <span>${loginUser.perNickname}</span><br>
     <span>이메일ㅣ</span> <span>${loginUser.perEmail}</span><br>
     <span>전화번호ㅣ</span> <span>${loginUser.perTel}</span><br>
     <span>가입일ㅣ</span> <span>${loginUser.perRegisteredDate}</span><br>
     </div>
     <br>
     <button class = "btn btn-outline-dark" ><a href='updateform'>프로필 수정</a></button>
     <button class = "btn btn-outline-dark" ><a href='delete'>회원 탈퇴</a></button>
     <button class = "btn btn-outline-dark" ><a href='logout'>로그아웃</a></button>
     <button class = "btn btn-outline-dark" ><a href='../bookmark/list?perno=${loginUser.perNo}'>내 북마크</a></button>
		 <button class = "btn btn-outline-dark" ><a href='../study/list?perno=${loginUser.perNo}'>스터디 찾기</a></button>
		 <button class = "btn btn-outline-dark" ><a href='../cafe/list'>스터디카페 예약하기</a></button>
		 <button class = "btn btn-outline-dark" ><a href='../cafe/reservationList'>내 예약 목록</a></button>
		 <button class = "btn btn-outline-dark" ><a href='../cafe/reviewList'>내 리뷰 목록</a></button>
		 <button class = "btn btn-outline-dark" ><a href='../askboard/mylist'>내 문의게시판</a></button>


