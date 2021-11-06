<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<style>
/*footer 시작*/

footer {
  font-size: 14px;
  background-color: whitesmoke;
  position: inherit;
  z-index:-99;
  margin-top:60px;
  width: 100%;
  height:260px;
  bottom: 0;
  left: 0;
}

.footer_company {
  display: flex;
  margin: 10px 0 0 20px;
  padding: 15px 0 0 0;
  justify-content: center;
}

.footer_company .footer_li {
list-style-type: none;
}

.footer_company .footer_li .footer_a{
  padding: 2px 10px 2px 0;
  
  text-decoration: none;
  color: black;
}

.footer_address {
  margin-left: 20px;
}

.footer_address > p {
    margin: 0;
    padding: 4px;
    text-align: center;
}
.footer_copyright {
  margin:0px;
  margin-left: 20px;
  padding-bottom: 10px;
  text-align: center;
}

</style>
</head>

<body>
    <footer>
      <ul class="footer_company">
        <li class="footer_li"><a href="#" class="footer_a">오늘의 공부 소개</a></li>
        <li class="footer_li"><a href="#" class="footer_a">이용약관</a></li>
        <li class="footer_li"><a href="#" class="footer_a">개인정보처리방침</a></li>
        <li class="footer_li"><a href="#" class="footer_a">1:1문의</a></li>
        <li class="footer_li"><a href="#" class="footer_a">법적고지</a></li>
        <li class="footer_li"><a href="#" class="footer_a">사이트맵</a></li>
      </ul>
      <hr>
      <div class="footer_address">
        <p>상호명 : 오늘의 공부&emsp;ㅣ&emsp;주소 : (우)1111 서울특별시 강남구 역삼동 819-3 삼오빌딩</p>
        <p>FAX : 0505-111-1111&emsp;ㅣ&emsp;Email: bit.study2@gmail.com</p>
        <p>고객센터 : 1577-1111 (평일 09:00 ~ 18:00 / 점심 12:00 ~ 13:00)</p>
        <p>사업자등록번호 : 391-11-1111&emsp;ㅣ&emsp;통신판매업신고번호: 제 2021-서울강남-1111 호 ㅣ 대표: 엄땡땡</p>
      </div>
      <hr>
      <p class="footer_copyright">COPYRIGHTⓒ2021 TODAYSTUDY. ALL RIGHTS RESERVED.</p>
    </footer>
</body>
</html>
