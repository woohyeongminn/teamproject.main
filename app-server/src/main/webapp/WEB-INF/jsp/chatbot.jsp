<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 공부</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<!-- 아이콘 -->
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<style>
.tmHead {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 50;
  width: 100%;
  height: 36px;
  background-color: rgb(155 136 131);
}

#tmPopWrap h2 {
  padding: 13px 24px;
  min-height: 7px;
  color: #ffffff;
  font-size: 14px;
  background: rgb(191 179 176);
}

.tmBtnArea.tmBtnLogin button {
  color: #fff;
  border: 1px solid rgb(191 179 176);
  background-color: rgb(191 179 176);
}

#tmPopWrap {
  display: none;
  z-index: 30;
  position: absolute;
  background: #fff;
  width: 80%;
  max-width: 320px;
  height: auto;
  border: 1px solid rgb(191 179 176);
}

#chat img {
    position: fixed;
    right: 50px;
    bottom: 50px;
    width: 110px;
    height: 100px;
}

button[type=submit] {
    margin-right: 3px;
    font-size: 9px;
    line-height: 9px;
  }
a {
    margin-right: 3px;
    font-size: 9px;
    line-height: 9px;
  }
</style>

</head>
<body>

<!-- <i id="icon" class="far fa-envelope"></i> -->

	<div class="chat" id="chat">
	  <h4 style="
    text-align: center;
    font-size: 14px;
    font-weight: bolder;
    margin-top: 10px;"></h4>
	  
	  <iframe src="https://service.dongledongle.com/TodayStudy" frameborder="0" width="100%" height="470px;"></iframe>
	  
	  <div class="choice" style="display: block; text-align: right; margin-right: 20px;">
		  <%-- <input type="submit" id="btnBack" value="돌아가기" class="btn btn-outline-dark"
		         onClick="location.href='${contextPath}/app/index'" style="font-size: 12px; line-height: 12px; margin-right:5px;"> --%>
		         
		  <a href="javascript:close()" id="btnJoin" class="btn btn-outline-dark" style="font-size: 12px; line-height: 12px;">회원가입</a>
	  </div>
	       
	</div>
	
<script type="text/JavaScript">

function close() {
	if (window.opener && !window.opener.closed) {
		window.opener.location = "${contextPath}/app/login";
		window.close();
	}
};
</script>

</body>
</html>