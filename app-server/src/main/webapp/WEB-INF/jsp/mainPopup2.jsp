<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
trimDirectiveWhitespaces="true"%>

<!-- 아이콘 -->
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<!DOCTYPE html>
<html>
<head>
<style>
.layerPopup img{
margin-bottom : 20px;
}
.layerPopup:before {
display:block;
content:"";
position:fixed;
left:0;
top:0;
width:100%;
height:100%;
background:rgba(0,0,0,.5);
z-index:9000
}
.layerPopup .layerBox {
z-index:10000;
position:fixed;
left:50%;
top:48%;
width: 800px;
height: 300px;
transform:translate(-50%, -50%);
padding:30px;
background:rgb(255 248 241);
border-radius:6px;
display: flex;
flex-direction: row;
}
.layerPopup .layerBox .title {
margin-bottom:10px;
padding-bottom:10px;
font-weight:600;
text-align: center;
border-bottom:1px solid #d9d9d9;
}
.layerPopup .layerBox .btnTodayHide {
font-size:14px;
font-weight:600;
color:black; 
float: left;
text-decoration:none;
width: 150px;
height : 30px;
line-height:30px;
border:black solid 1px;
text-align : center;
text-decoration:none;
}
.layerPopup .layerBox .leftbox {
  background-color: rgb(255 248 241);
  width: 70%;
  display: flex;
  flex-direction: column;
}
.leftbox .lefttop {
  position: absolute;
  width: 515px;
  height: 240px;
  z-index: 99999;
  top: -45px;
  background-color: rgb(255 248 241);
  border-radius: 10px;
  box-shadow: 10px 10px 10px 0px rgba(0, 0, 0, 0.418);
}
.leftbox .tempbox {
  margin-top: 30%;
  height: 35%;
  text-align: right;
  background-color: rgb(255 248 241);
  padding: 40px 20px 0 20px;
}
.leftbox .leftbottom {
  bottom: 0;
  width: 100%;
  height: 10%;
  background-color: rgb(255 248 241);
  color: rgb(123 111 109);
}
.layerPopup .layerBox .rightbox {
  background-color: rgb(255 248 241);
  width: 35%;
  display: flex;
  flex-direction: column;
}
.rightbox .righttop {
  position: absolute;
  width: 241px;
  height: 20%;
  z-index: 33333;
  background-color: rgb(255 248 241);
  padding: 0 10px;
}
.rightbox .rightmiddle {
  margin-top: 28%;
  margin-left: 10px;
  width: 100%;
  height: 80%;
  z-index: 55555;
  border-top: 1px solid;
  border-top-color: rgb(159 146 143);
  background-color: rgb(255 248 241);
  padding: 0 20px;
  text-align: center;
  -webkit-text-stroke: thin;
}
.rightbox .rightbottom {
  bottom: 0;
  width: 100%;
  height: 3%;
  z-index: 99999;
  background-color: rgb(255 248 241);
}
.layerPopup div{
display : inline;
}
.layerPopup form{
font-size:14px;
font-weight:500;
line-height:30px
}
.layerPopup #close {
font-size:16px;
font-weight:600;
width: 40px;
height : 30px;
color:black;
float: right;
line-height:30px;
text-align : center;
text-decoration:underline;
color: rgb(123 111 109);
}
.layerPopup a{
text-decoration : none;
color : rgb(163 150 148);
width: 50px;
height : 40px;
}
input[type=checkbox]:checked {
  background-color: black;
}
img#logo {
  width: 250px;
  margin-bottom: 0;
}
svg#icon {
  width: 40px;
  height: 30px;
  border: 3px solid;
  border-radius: 5px;
  border-inline: none;
  color: rgb(159 146 143);
}
</style>
<!-- layer popup content -->
</head>
<body>
<div class="layerPopup" id="layer_popup" style="visibility: visible;">    
  <form name="pop_form">
  <div class="layerBox">
  
    <div class="leftbox">

      <div class="lefttop">
        <div class="hellopopup">
          <img src="${contextPath}/img/hello.gif" width=515 height=240 usemap="#popup" alt="event page" id="hello" style="border-radius: 10px;">
          </div>
      </div>

      <div class="tempbox">
        <div class="middlecontent">
        
        <p>
        <!-- <iframe width="20" height="20" src="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/131362507&amp;color=%2397cbff&amp;auto_play=true&amp;hide_related=false&amp;show_comments=true&amp;show_user=true&amp;show_reposts=false&amp;show_teaser=true"
        frameborder="no" scrolling="no" allow="autoplay" style="padding-top: 8px;"></iframe>&emsp; -->  
        
        <a href="javascript:closePop();"><i id="icon" class="fas fa-user-graduate"></i>&emsp;</a>
           <a href="javascript:openchatbot()"><i id="icon" class="far fa-envelope"></i>&emsp;</a>
           <a href="${contextPath}/app/askboard/alllist"><i id="icon" class="fas fa-comments"></i></a></p>
        </div>
      </div>

      <div class="leftbottom">
        <div id="check" >
          <input type="checkbox" class="form-check-input" name="chkbox" value="checkbox" id='chkbox' style="margin-top: 8px;">
          <label for="chkbox">&nbsp&nbsp오늘 하루 보지 않기</label></div>
      </div>

    </div>
    <div class="rightbox">
      <div class="righttop">
        <div class="content">
          <img src="${contextPath}/img/logo.png" usemap="#popup" alt="event page" id="logo">
          </div>
      </div>

      <div class="rightmiddle">
        <div class="middlecontent">
        <br>
          <p>환영합니다!</p>
          <p>오늘의 공부 홈페이지입니다!</p>
        </div>
      </div>
      <div class="rightbottom">
        <div id="close" ><a href="javascript:closePop();">닫기</a></div>
      </div>
    </div>
    
  </div>
  
  </form>
</div>
</body>
</html>
<script type="text/JavaScript">
function openchatbot() {  
  window.open("todaystudy", "오늘의 공부 챗봇", "width=400px; height=550px;")
}
</script>
<script language="JavaScript">
//head 태그 안에 스크립트 선언
function setCookie( name, value, expiredays ) {
    var todayDate = new Date();
    todayDate.setDate( todayDate.getDate() + expiredays ); 
    document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}
function closePop() {
    if ( document.pop_form.chkbox.checked ){
        setCookie( "maindiv", "done" , 1 );
    }
    document.all['layer_popup'].style.visibility = "hidden";
}
</script>
<script language="Javascript">
cookiedata = document.cookie;   
if ( cookiedata.indexOf("maindiv=done") < 0 ){     
    document.all['layer_popup'].style.visibility = "visible";
}
else {
    document.all['layer_popup'].style.visibility = "hidden";
}
</script>
