<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<title>등록 | 스터디 찾기</title>
<style>
* {
    font-size:16px;
}
.all-content {
	display: flex;
	justify-content: center;
}
.form-control {
  height:23px;
}
</style>
<script type="text/javascript">
  function checkValue() {

  var form = document.studyInfo;

  if (!form.studytitle.value) {
    alert("제목을 입력하세요.");
    return false;
  }

  if (!form.subjectno.value) {
    alert("분야를 선택하세요.");
    return false;
  }

  if (!form.area.value) {
    alert("지역을 입력하세요.");
    return false;
  }

  if (!form.area.value.contains("시") || !form.area.value.contains("구") || !form.area.value.contains("도")) {
    alert("@@시 / @@도 / @@구 등을 입력하세요.");
    return false;
  }

  if (!form.numberofpeple.value) {
    alert("최대 인원수를 입력하세요.");
    return false;
  }

  if (form.numberofpeple.value == 0 || form.numberofpeple.value > 30) {
    alert("인원수는 1명 이상 30명 이하로만 입력 가능합니다.");
    return false;
  }

  if (!form.faceno.value) {
    alert("대면 상태를 선택하세요.");
    return false;
  }

  if (!form.introduction.value) {
    alert("소개글을 입력하세요.");
    return false;
  }
}
</script>
</head>
<body>
<div class="all-content">
  <form action='add' method='post' name='studyInfo' onsubmit="return checkValue()">
    <div id='content'>
      <br>
    <div class="mb-3 row">
    <label for='f-studytitle' class="col-sm-2 col-form-label">제목</label>
    <div class="col-sm-10">
    <input id='f-studytitle' type='text' name='studytitle' class="form-control">
    </div>
  </div>

  <label for='f-subjectno'>분야</label><br>
  <select name="subjectno" form-select-border-width:"300">
  <option value="1" name="faceno" selected>어학</option>
        <option value="2" selected>자격증</option>
        <option value="3" selected>취업</option>
        <option value="4" selected>IT</option>
        <option value="5" selected>예체능</option>
        <option value="6" selected>기타</option>
  </select>

  <div class="mb-3 row">
  <label for='f-area' class="col-sm-2 col-form-label">지역</label>
  <div class="col-sm-10">
    <input id='f-area' type='text' name='area' class="form-control">
  </div>
  </div>

  <label for='f-numberofpeple'>최대 인원수</label><br>
  <select name="numberofpeple">
  <option value="2" name="numberofpeple" selected>2</option>
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
  </select><br>

  <label for='f-viewCount'>대면 상태</label><br>
  <select name="faceno">
  <option value="1" name="faceno" selected>대면</option>
        <option value="2" selected>비대면</option>
        <option value="3" selected>대면/비대면</option>
  </select>

    <div class="mb-3 row">
    <label for='f-introduction' class="col-sm-2 col-form-label">소개글</label>
    <div class="col-sm-10">
      <input id='f-introduction' type='text' name='introduction' class="form-control">
    </div>
    </div>
    </div>
    <br>
	<button class="btn btn-primary btn-sm">등록</button><br>
</form>
  </div>
</body>
</html>
