<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>오늘의 공부-로그인</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<!-- 아이콘 -->
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<!-- 네이버 -->
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>

<!-- JQUERY -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<!-- 구글 -->
<meta name = "google-signin-client_id"content = "866898854522-6sla16re13s21boo6nf9u8116n62aafv.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>



<style>
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p,
	blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn,
	em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var,
	b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend,
	table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas,
	details, embed, figure, figcaption, footer, header, hgroup, menu, nav,
	output, ruby, section, summary, time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: top;
}

body {
	line-height: 1.5;
}

ul li {
	list-style-type: none;
}

a {
	text-decoration: none;
	color: black;
}

/*body 시작-------------------------------------*/
* {
	font-size: 14px;
}

section {
	width: 100%;
	z-index: 500;
}

.contents {
	display: flex;
	flex-direction: column;
	flex-wrap: nowrap;
	position: relative;
	width: 1200px;
	margin: 0 auto;
}

.contents .c1 {
	width: 1000px;
	margin: 0 auto;
	padding: 20px;
}

.contents .c1 #box {
	margin: 40px;
	margin-top: 100px;
}

.contents .c1 .card {
	border: none;
	border: 1px solid darkgray;
	border-radius: 10px;
	padding: 25px;
	height: 230px;
}
/* 
.contents .c1 .card span .btn {
  background-color: rgb(247, 231, 215);
  border  :1px solid rgb(247, 231, 215);
  color: #616161;
}

.contents .c1 .card .btn:hover {
  background-color:  rgb(219, 211, 209);
  border  :1px solid rgb(219, 211, 209);
  color:  white;
} */
.contents .c1 .card .card-text {
	margin-bottom: 16px;
	font-size: 13px;
}

#naver_id_login {
	margin: 10px 0;
}

.btn {
	font-size: 12px;
	line-height: 17px;
	width: 124px;
}
</style>
</head>

<body>

	<section>
		<div class="contents">

			<div class="c1 row">
				<div class="col" id="box">
					<div class="card">
						<h4 class="card-title">
							관리자&ensp;<i class="fas fa-child"></i>
						</h4>
						<span class="card-text">관리자 로그인<br> 페이지로 이동합니다.
						</span> <span class="but"><a href="admin/form"
							class="btn btn-outline-dark">LOGIN</a></span>
					</div>
				</div>

				<div class="col" id="box">
					<div class="card">
						<h4 class="card-title">개인 회원</h4>
						<span class="card-text">개인 회원 로그인<br> 페이지로 이동합니다.
						</span> <span><a href="member/form" class="btn btn-outline-dark">LOGIN</a>&emsp;</span>

						<!-- 네이버 로그인 버튼 노출 영역 -->
						<div id="naver_id_login"></div>
						<script type="text/javascript">
                var naver_id_login = new naver_id_login("wh8zH3W5STAuoCbw48Fz", "http://localhost:8080/ogong/app/index");
                var state = naver_id_login.getUniqState();
                naver_id_login.setButton("green", 1, 40);
                naver_id_login.setDomain("http://localhost:8080/ogong/app/login");
                naver_id_login.setState(state);
                naver_id_login.setPopup();
                naver_id_login.init_naver_id_login();
              </script>

						<!-- 카카오 로그인 -->
						<div id="kakao_id_login">
						<a href="https://kauth.kakao.com/oauth/authorize?client_id=9e2ad8ce072bca1d727914f05e243cbb&redirect_uri=http://localhost:8080/ogong/app/login&response_type=code">
							<img src="${contextPath}/img/kakao_login.png"></img>
						</a>

						<script type="text/javascript">
	              const CLIENT_ID = "9e2ad8ce072bca1d727914f05e243cbb";
	              const REDIRECT_URI =  "http://localhost:8080/ogong/app/login";
	
	              export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;
	
	              /* OAuth2RedirectHandler.js */
	              import React from "react";
								import { useDispatch } from "react-redux";
								import { actionCreators as userActions } from "../redux/modules/user";
								import Spinner from "./Spinner";
								
								const OAuth2RedirectHandler = (props) => {
								  const dispatch = useDispatch();
								
								  // 인가 코드
								  let code = new URL(window.location.href).searchParams.get("code");
								
								  React.useEffect(async () => {
								    await dispatch(userActions.kakaoLogin(code));
								  }, []);
								
								  return <Spinner />;
								};
								
								export default OAuth2RedirectHandler;
								
								/* App.js */
								function App() {

								    return (
								      <React.Fragment>
								        <ConnectedRouter history={history}>
								          <Route path="/signup" exact component={Signup} />
								          <Route path="/login" exact component={Login} />
								          
								          <Route path="/ogong/app/login" component={OAuth2RedirectHandler}></Route>
								          
								        </ConnectedRouter>
								      </React.Fragment>
								    );
								  }
								
								/* user.js */
								const kakaoLogin = (code) => {
								    return function (dispatch, getState, { history }) {
								      axios({
								        method: "GET",
								        url: `http://localhost:8080/ogong/app/login?code=${code}`,
								      })
								        .then((res) => {
								          console.log(res); // 토큰이 넘어옴
								          
								          const ACCESS_TOKEN = res.data.accessToken;
								          
								          localStorage.setItem("token", ACCESS_TOKEN); // 예시로 로컬에 저장
								          
								          history.replace("/index") // 토큰 받은 후 로그인 -> 메인으로 화면 전환
								          
								          }.catch((err) => {
								          console.log("소셜 로그인 에러", err);
								          window.alert("로그인에 실패하였습니다.");
								          history.replace("/login"); // 로그인 실패시 로그인 화면으로
								          }
								      }
								  };
              </script>
              
            </div> 
             
            <div id="google_id_login">
            <div class="g-signin2" data-onsuccess="onSignIn"></div>
            </div>
            <a href="#" onclick="signOut();">Sign out</a>
             
             
             
             
					</div>
				</div>

				<div class="col" id="box">
					<div class="card">
						<h4 class="card-title">기업 회원</h4>
						<span class="card-text">기업 회원 로그인<br> 페이지로 이동합니다.
						</span> <span><a href="ceomember/form"
							class="btn btn-outline-dark">LOGIN</a></span>
					</div>
				</div>
			</div>

		</div>
	</section>


<script type="text/javascript">
function onSignIn(googleUser) {
	
	/*  var auth2 = gapi.auth2.getAuthInstande();
  
  if (auth2.isSigendIn.get()){ */
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
  /* }  */
 
 $.ajax({
	 url:"member/google",
	 type:"POST",
	 data: {
		 name: profile.getName(),
		 email: profile.getEmail()
	 },
	 success: function(data) {
		 if (data){
			 
			 alert(data);
		 
			 
		 } else {
			 alert("잠시 후에 시도해주세요."); }
		 },
			 
	  error: function() {
		  alert("에러 발생");
		 }

   })

}

var id_token = roorleUser.getAuthResponse().id_token;
console.log("ID Token: " + id_token);
</script>


<script>
  function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }
</script>

</body>
</html>
