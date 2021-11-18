<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
* {
	font-size: 14px;
}

.all-content {
	width: 100%;
	max-width: 900px;
	margin: 0 auto;
	font-size: 14px;
}

tr#bottombox {
	border-color: black;
}

thead, tbody, tfoot, tr, td, th {
	border-style: solid;
	border-width: 0;
}

tr#middlebox {
	border-color: #d1d0d0;
}

tr#blockbox {
	vertical-align: top;
}

th#countbox {
	font: small-caption;
	color: #9ba8b3;
}

tr#first {
	border-bottom: solid;
}

th#title {
	font-size: 18px;
	letter-spacing: 5px;
	line-height: 50px;
}

#f-introduction {
	text-align: justify;
	margin: 0;
	word-wrap: break-word;
	width: 500px;
	height: 50px;
	font-size: 14px;
	min-height: 200px;
	letter-spacing: 0;
	border: 0px solid white;
	outline-color: lightgray;
	border-radius: 1px;
}

label {
	text-align: justify;
	width: 80px;
}

input {
	width: 500px;
	font-size: 14px;
	text-align: justify;
	border: white;
	outline-color: white;
	font-weight: 400;
}

button[type=submit] {
	margin-right: 3px;
	font-size: 14px;
	line-height: 14px;
}

a {
	color: black;
	text-decoration: blink;
}

a:hover {
	color: white;
}
</style>

<!-- 내 스터디 삭제 & 탈퇴 -->
<script>
	function delBtn_click(waitingGuilder) {
		if (waitingGuilder > 0) {
			alert('스터디 삭제 시 승인 대기 중인 구성원도 모두 거절됩니다.<br/>정말 삭제하시겠습니까?');
		}
	}
	
	function exitBtn_click(waitingGuilder) {
	    if (waitingGuilder > 0) {
	      alert('스터디 탈퇴 시 승인 대기 중인 구성원도 모두 거절됩니다.<br/>정말 탈퇴하시겠습니까?');
	    }
	  }
</script>

<br>
<br>
<br>

<div class="all-content">

	<input type='hidden' name='subjectNo' value='${study.subjectNo}'>

	<br>
	<table class="table table-hover text-center">
		<thead>

			<tr id="first">
				<th scope="col"></th>
				<th scope="col" id="title">| 📰 ${study.studyTitle} ✏ |</th>
				<th scope="col"><c:choose>
						<c:when test="${study.countMember < study.numberOfPeple}">
	    [모집 중]
	    </c:when>
						<c:otherwise>
	    [모집 완료]
	    </c:otherwise>
					</c:choose></th>
			</tr>

			<tr id="middlebox">
				<th scope="row"><label for='f-studyNo'>번호</label></th>
				<td><input id='f-studyNo' type='text' name='studyNo'
					value="${study.studyNo}" readonly></td>
				<td></td>
			</tr>

			<tr id="middlebox">
				<th scope="row"><label for='f-bookMember'>북마크</label></th>
				<td><input id='f-bookMember' type='text' name='bookMember'
					value="${study.countBookMember}" readonly></td>
				<td></td>
			</tr>

			<tr id="middlebox">
				<th scope="row"><label for='f-owner'>조장</label></th>
				<td><input id='f-owner' type='text' name='owner'
					value="${study.owner.perNickname}" readonly></td>
				<td></td>
			</tr>

			<tr id="middlebox">
				<th scope="row"><label for='f-subjectName'>분야</label></th>
				<td><input id='f-subjectName' type='text' name='subjectName'
					value="${study.subjectName}" readonly></td>
				<td></td>
			</tr>

			<tr id="middlebox">
				<th scope="row"><label for='f-area'>지역</label></th>
				<td><input id='f-area' type='text' name='area'
					value="${study.area}" readonly></td>
				<td></td>
			</tr>

			<tr id="middlebox">
				<th scope="row"><label for='f-countMember'>인원수</label></th>
				<td><input id='f-countMember' type='text' name='countMember'
					value="${study.countMember}" readonly></td>
				<td></td>
			</tr>

			<tr id="middlebox">
				<th scope="row"><label for='f-numberOfPeple'>최대 인원수</label></th>
				<td><input id='f-numberOfPeple' type='text'
					name='numberOfPeple' value="${study.numberOfPeple}" readonly></td>
				<td></td>
			</tr>

			<tr id="middlebox">
				<th scope="row"><label for='f-faceName'>대면 상태</label></th>
				<td><input id='f-faceName' type='text' name='faceName'
					value="${study.faceName}" readonly></td>
				<td></td>
			</tr>

			<tr id="blockbox">
				<th scope="row"><label for='f-introduction'>소개글</label></th>
				<td><textarea id='f-introduction' type='text'
						name='introduction' rows="3" wrap="virtual" readonly>${study.introduction}</textarea></td>
				<td></td>
			</tr>

			<tr id="bottombox">
				<th scope="row"><label for='f-point'>활동 점수</label></th>
				<td><input id='f-point' type='text' name='point'
					value="${study.point}" readonly></td>
				<td></td>
			</tr>

		</thead>
	</table>

	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
		<c:choose>
			<c:when test="${status == 'waiting'}">
				<button type="submit" class="btn btn-outline-dark" value="목록">
					<a href='list'>목록</a>
				</button>
			</c:when>
			<c:otherwise>
				<button type="submit" class="btn btn-outline-dark" value="목록">
					<a href='list'>목록</a>
				</button>
				<c:if test='${study.owner.perNo == loginUser.perNo}'>
					<a
						href='${contextPath}/app/mystudy/updateform?studyno=${study.studyNo}'
						class="btn btn-outline-dark" formaction="updateform">수정</a>
					<c:if test="${!(study.countMember > '1')}">
						<a
							href='${contextPath}/app/mystudy/delete?studyno=${study.studyNo}'
							class="btn btn-outline-dark" onclick="delBtn_click(${study.waitingCountMember}); return false;">삭제</a>
					</c:if>
				</c:if>
				<a href='${contextPath}/app/mystudy/exit?studyno=${study.studyNo}'
					class="btn btn-outline-dark" onclick="exitBtn_click(${study.waitingCountMember}); return false;">탈퇴</a>
				<button type="submit" class="btn btn-outline-dark" value="구성원">
					<a
						href='${contextPath}/app/mystudy/guilder/list?studyNo=${study.studyNo}'>구성원</a>
				</button>
				<button type="submit" class="btn btn-outline-dark" value="캘린더">
					<a href='${contextPath}/app/mystudy/calendar/list'>캘린더</a>
				</button>
				<button type="submit" class="btn btn-outline-dark" value="자유 게시판">
					<a
						href="${contextPath}/app/mystudy/freeboard/list?studyno=${study.studyNo}">자유
						게시판</a>
				</button>
				<button type="submit" class="btn btn-outline-dark" value="화상미팅">
					<a href='list'>화상미팅</a>
				</button>
				<button id="popup_open_btn" class="btn btn-outline-dark">To-Do</button>
				<!-- todo 모달 -->
				<jsp:include page="todo/ToDoPopup.jsp" />
			</c:otherwise>
		</c:choose>

	</div>