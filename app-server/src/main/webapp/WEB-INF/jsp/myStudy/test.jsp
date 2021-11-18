<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://rtcmulticonnection.herokuapp.com/dist/RTCMultiConnection.min.js"></script>
<script src="https://rtcmulticonnection.herokuapp.com/socket.io/socket.io.js"></script>

<style>
video {
  width: 25%;
  border-radius: 14px;
}
</style>
</head>
<body>

<button id="btn-open-or-join-room" class="btn btn-outline-dark btn-sm">
방만들기
</button>

<label>방번호</label>
<input id="txt-roomid" placeholder="Unique Room Id">

<hr>

<div id="videos-container">
</div>



<script>
var connection = new RTCMultiConnection();

connection.socketURL = 'https://rtcmulticonnection.herokuapp.com:443/';

connection.session = {
		audio: true,
		video: true
};

connection.sdpConstraints.mandatory = {
	OfferToReceiveAudio: true,
	OfferToReceiveVideo: true
};

var roomid = document.getElementById('txt-roomid');
roomid.value = connection.token();

var videosContainer = document.getElementById('videos-container');
connection.onstream = function(event) {
	var video = event.mediaElement;
	var text = document.createTextNode("\u00a0");
	
	videosContainer.appendChild(text);
	videosContainer.appendChild(video);
}

document.getElementById('btn-open-or-join-room').onclick = function(){
	this.disabled = true;
	
	connection.openOrJoin(roomid.value || 'predefiend-roomid');
}

</script>
</body>
</html>






