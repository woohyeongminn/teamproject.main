<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>댓글 등록 | 자유 게시판</title>
<style>
*{
  font-size:14px;
}
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
	<fieldset>
		<br>
		<legend>
			<b>🪧 댓글 등록</b>
		</legend>
		<br>
		<hr>
		<table class="table table-responsive">
			<thead>
				<tr>
					<th><label for='f-commentText'>내용</label></th>
				</tr>
			</thead>
			<form action='add'>
				<input type='hidden' name='studyno' value='${study.studyNo}'>
				<input type='hidden' name='freeboardno' value='${freeBoard.freeBoardNo}'>
				<td><input id='f-commentText' type='text' name='commenttext'></td>
		</table>
	</fieldset>
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
		<button type="submit" class="btn btn-outline-dark" value="등록">등록</button>
		</form>
	</div>
</body>
</html>
