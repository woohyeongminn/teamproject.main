<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>등록 | 자유 게시판</title>
<link rel="stylesheet"
	href="../node_modules/bootstrap/dist/css/bootstrap.css">
<script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
<script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
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

input {
	border: white;
	outline-color: lightgray;
}
</style>
</head>
<body>
	<jsp:include page="../../header.jsp" />
	<fieldset>
		<br>
		<legend>
			<b>🪧 자유게시판 등록</b>
		</legend>
		<br>
		<hr>
		<table class="table table-responsive">
			<thead>
				<tr>
					<th><label for='f-title'>제목</label></th>
					<th><label for='f-content'>내용</label></th>
					<th><label for='f-filepath'>파일</label></th>
				</tr>
			</thead>
			<form action='add'>
				<input type='hidden' name='studyno' value='${studyno}'>
				<td></label><input id='f-title' type='text' name='title'></td>
				<td><input id='f-content' type='text' name='content'></td>
				<td><input id='f-filepath' type='file' name='filepath'></td>
		</table>
	</fieldset>
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
		<button type="submit" class="btn btn-outline-dark" value="등록">등록</button>
		</form>
	</div>
</body>
</html>
