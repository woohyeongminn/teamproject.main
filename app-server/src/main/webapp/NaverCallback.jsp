<%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>네이버 로그인</title>
    <script type="text/javascript" src="http://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"></script>
    <script type="text/javascript" src="http://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
    <script type="text/javascript">
			  var naver_id_login = new naver_id_login("wh8zH3W5STAuoCbw48Fz", "http://localhost:8080/ogong/app/index");
			  // 접근 토큰 값 출력
			  alert(naver_id_login.oauthParams.access_token);
			  // 네이버 사용자 프로필 조회
			  naver_id_login.get_naver_userprofile("naverSignInCallback()");
			  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
			   var name, email, nickname, photo, tel;
			  function naverSignInCallback() {
				  name = naver_id_login.getProfileData('name');
				  email = naver_id_login.getProfileData('email');
				  nickname = naver_id_login.getProfileData('nickname');
				  photo = naver_id_login.getProfileData('profileImage');
				  tel = naver_id_login.getProfileData('tel');
				  
				  // 정보 저장할 url
				  var url = "http://" + window.location.hostname + 
				            ((location.port==""||location.port==undefined)?"":":" + location.port) + "localhost:8080/ogong/app/member/form";
				  // 함수 호출
				  post_to_url(url, {'name': name, 'email': email, 'nickname': nickname, 'photo': photo, 'tel': tel})
			  }
	  </script>
</body>

<script type="text/javascript">
// url로 넘기면서 정보도 같이 넘기기
function post_to_url(path, params, method='post') {
	  // body에 form 태그 추가
	  const form = document.createElement('form');
	  form.method = method;
	  form.action = path;
	  
	  // 정보 hidden으로 form 태그 안에 추가
	  for(const key in params) {
		  if(params.hasOwnProperty(key)) {
			  const hiddenField = document.createElement('input');
			  hiddenField.type = 'hidden';
			  hiddenField.name = key;
			  hiddenField.value = params[key];
			  form.appendChild(hiddenField);
		  }
	  }
	  // form 태그 닫기
	  document.body.appendChild(form);
	  
	  // submit으로 전송
	  form.submit();
}
</script>
</html>


<%-- <%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>네이버로그인</title>
  </head>
  <body>
  <%
    String clientId = "wh8zH3W5STAuoCbw48Fz";//애플리케이션 클라이언트 아이디값";
    String clientSecret = "gwBpzepQnp";//애플리케이션 클라이언트 시크릿값";
    String code = request.getParameter("code");
    String state = request.getParameter("state");
    String redirectURI = URLEncoder.encode("http://localhost:8080/ogong/app/index", "UTF-8");
    String apiURL;
    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
    apiURL += "client_id=" + clientId;
    apiURL += "&client_secret=" + clientSecret;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&code=" + code;
    apiURL += "&state=" + state;
    String access_token = "";
    String refresh_token = "";
    System.out.println("apiURL="+apiURL);
    try {
      String apiurl = "https://openapi.naver.com/v1/nid/me";
      URL url = new URL(apiURL);
      HttpURLConnection con = (HttpURLConnection)url.openConnection();
      con.setRequestMethod("GET");
      int responseCode = con.getResponseCode();
      BufferedReader br;
      System.out.print("responseCode="+responseCode);
      if(responseCode==200) { // 정상 호출
        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
      } else {  // 에러 발생
        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
      }
      String inputLine;
      StringBuffer res = new StringBuffer();
      while ((inputLine = br.readLine()) != null) {
        res.append(inputLine);
      }
      br.close();
      if(responseCode==200) {
        out.println(res.toString());
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  %>
  </body>
</html>
 --%>