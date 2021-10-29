<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기업회원 목록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>

<body>
	<h1>기업회원 목록</h1>
	
	<table>
	
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>닉네임</th>
				<th>이메일</th>
				<th>가입일</th>
			<tr>
		</thead>
		
		<tbody>
			<c:forEach items="${ceoMemberList}" var="ceoMember">
				<tr>
				    <td>${ceoMember.ceoNo}</td>
				    <td><a href='detail?no=${ceoMember.ceoNo}'>${ceoMember.ceoName}</a></td> 
				    <td>${ceoMember.ceoNickname}</td> 
				    <td>${ceoMember.ceoEmail}</td> 
				    <td>${ceoMember.ceoRegisteredDate}</td>
				</tr>
			</c:forEach>
		</tbody>
		
	</table>
	
</body>
</html>