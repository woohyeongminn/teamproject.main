<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<style>
* {
	font-size: 14px;
}

.all-content {
	width: 100%;
	margin: 0 auto;
	display: flex;
	justify-content: center;
}

#button {
	text-align: center;
}
</style>

</head>
<body>
	<div class="all-content">
		<form action='update' method='post'>
			<input type="hidden" name="studyNo" value="${study.studyNo}">
			<div id='content'>
				<div class="mb-3 row">
					<label for='f-studyTitle'>제목</label> <input id='f-studyTitle'
						type='text' name='studyTitle' class="form-control" required
						oninvalid="this.setCustomValidity('제목을 입력하세요.')"
						oninput="this.setCustomValidity('')">
				</div>

				<div class="mb-3 row">
					<label for='f-numberOfPeple'>최대 인원수</label> <select
						name="numberOfPeple">
						<option value="2" name="numberOfPeple" selected>2</option>
						<option value="3" selected>3</option>
						<option value="4" selected>4</option>
						<option value="5" selected>5</option>
						<option value="6" selected>6</option>
						<option value="7" selected>7</option>
						<option value="8" selected>8</option>
						<option value="9" selected>9</option>
						<option value="10" selected>10</option>
						<option value="11" selected>11</option>
						<option value="12" selected>12</option>
						<option value="13" selected>13</option>
						<option value="14" selected>14</option>
						<option value="15" selected>15</option>
						<option value="16" selected>16</option>
						<option value="17" selected>17</option>
						<option value="18" selected>18</option>
						<option value="19" selected>19</option>
						<option value="20" selected>20</option>
						<option value="21" selected>21</option>
						<option value="22" selected>22</option>
						<option value="23" selected>23</option>
						<option value="24" selected>24</option>
						<option value="25" selected>25</option>
						<option value="26" selected>26</option>
						<option value="27" selected>27</option>
						<option value="28" selected>28</option>
						<option value="29" selected>29</option>
						<option value="30" selected>30</option>
					</select>
				</div>

				<div class="mb-3 row">
					<label for='f-faceNo'>대면 상태</label> <select name="faceNo">
						<option value="1" name="faceNo" selected>대면</option>
						<option value="2" selected>비대면</option>
						<option value="3" selected>대면/비대면</option>
					</select>
				</div>

				<div class="mb-3 row">
					<label for='f-introduction'>소개글</label>
					<textarea id='f-introduction' type='text' name='introduction'
						class="form-control" rows="3" required
						oninvalid="this.setCustomValidity('소개글을 입력하세요.')"
						oninput="this.setCustomValidity('')"></textarea>
				</div>

				<div class="mb-3 row">
					<label for='f-studyStatus'>진행 상태</label> <select name="studyStatus">
						<option value="1" name="studyStatus" selected>진행</option>
						<option value="2" selected>종료</option>
					</select>
				</div>
				<br>
			</div>
			<div id='button'>
				<button class="btn btn-dark">수정</button>
			</div>
		</form>
	</div>
</body>
</html>