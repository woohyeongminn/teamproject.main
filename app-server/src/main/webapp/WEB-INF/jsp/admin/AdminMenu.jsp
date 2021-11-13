<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
.offcanvas-start {
  width: 350px;
  }
  button[type=button2] {
  margin-left: 70px;
    color: black;
  }
  button[type=button2]:hover {
    color: black;
  }
  .btn-group {
  margin-top: 10px;
  display: block;
  }
  .btn-secondary:focus {
  background-color: beige;
  color: black;
  }
  button[type=button] {
    margin-block: 10px;
    border: 1.5px solid;
    border-radius: 10px;
    background-color: white;
    color: black;
    font-size: 14px;
    line-height: 14px;
  }
  button[type=button]:hover {
    background-color: beige;
    color: black;
  }
  .dropdown-menu {
  background-color: rgba(211, 211, 211, 0);
  border: rgba(211, 211, 211, 0);
  }
  button[type=button1] {
      margin-left: 15px;
      margin-bottom: 5px;
      border-radius: 5px;
      border: 1px solid;
      background-color: white;
      color: black;
      font-size: 14px;
  }
  button[type=button1]:hover {
    background-color: beige;
    color: black;
  }
</style>

 <div class="offcanvas-header">
   <h4 class="offcanvas-title" id="offcanvasExampleLabel">👑 관리자 👑</h4>
   <button type="button2" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
   <hr>
 </div>

 <div class="offcanvas-body">
   <div>
     <b>이동하고 싶은 탭을 선택해 주세요!</b>
   </div>

   <div class="btn-group dropend">
     <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
       👑 관리자 페이지
     </button>
     <div class="dropdown-menu" role="menu" style="border-color: white;">
       <button class="dromdown-item" type="button1">
         <a href='logout' style="color: black; text-decoration: none;">🖐 로그아웃</a></button><br>
       <button class="dromdown-item" type="button1">
         <a href='/ogong/admin/detail' style="color: black; text-decoration: none;">🙂 마이페이지</a></button>
     </div>
   </div>
   
   <div class="btn-group dropend">
     <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
       📁 회원 관리
     </button>
     <div class="dropdown-menu" role="menu" style="border-color: white;">
       <button class="dromdown-item" type="button1">
         <a href="/ogong/admin/permemberlist" style="color: black; text-decoration: none;">🎓 개인 회원</a></button><br>
       <button class="dromdown-item" type="button1">
         <a href="/ogong/admin/ceomember/list" style="color: black; text-decoration: none;">👔 기업 회원</a></button>
     </div>
   </div>
     
   <div class="btn-group dropend">
     <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
       📖 스터디 관리
     </button>
     <div class="dropdown-menu" role="menu" style="border-color: white;">
       <button class="dromdown-item" type="button1">
         <a href="study/list" style="color: black; text-decoration: none;">📚 스터디 목록</a></button><br>
     </div>
   </div>
   
   <div class="btn-group dropend">
     <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
       🏘 장소 관리
     </button>
     <div class="dropdown-menu" role="menu" style="border-color: white;">
       <button class="dromdown-item" type="button1">
         <a href="/ogong/admin/cafeList" style="color: black; text-decoration: none;">📝 장소 목록</a></button><br>
       <button class="dromdown-item" type="button1">
         <a href="/ogong/admin/reviewList" style="color: black; text-decoration: none;">🔖 장소 리뷰</a></button>
     </div>
   </div>
   
   <div class="btn-group dropend">
     <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
       💌 고객센터 관리
     </button>
     <div class="dropdown-menu" role="menu" style="border-color: white;">
       <button class="dromdown-item" type="button1">
         <a href="/ogong/adminNotice/list" style="color: black; text-decoration: none;">📢 공지사항</a></button><br>
       <button class="dromdown-item" type="button1">
         <a href="/ogong/admin/askboardlist" style="color: black; text-decoration: none;">💬 문의사항</a></button>
     </div>
   </div>
     
  </div>