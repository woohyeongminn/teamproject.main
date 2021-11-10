<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지 | 수정</title>
  <script type="text/javascript" src="https://static.msscdn.net/mfile_outsrc/js/vendor/jquery-1.11.1.min.js?20160201"></script>
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
    b {
    text-align: center;
    }
      .btn {
    border-radius: 10px;
    background-color: tan;
    color: black;
    font-size: 18px;
  }
  .btn:hover {
    background-color: blanchedalmond;
    color: black;
  }
  .n-table.table-row th, .n-table.table-row td {
    height: auto;
    padding: 15px 0;
    box-sizing: border-box;
    border-top: 1px solid #f1f1f1;
    border-bottom: none;
    font-size: 14px;
    text-align: left;
}
 </style>
 </head> 
 
  <body>
    <jsp:include page="../header.jsp"/>
      <legend><b>✏ 개인 회원 프로필 수정</b></legend><br>
        
         
      <section class="n-section-block">
        <table class="n-table table-row my-info-modify">
            <tr id="password-area">
                <th scope="row">비밀번호</th>
                <td><button type="button" class="n-btn w100 btn-sm btn-default cert-hidden" id="change-password-btn">비밀번호 변경</button></td>
            </tr>
                  <!--비밀번호 변경-->
            <tr id="change-password-area" style="display: none">
              <th scope="row">비밀번호</th>
                <td colspan="2">
                  <div class="my-info-modify">
                      <div class="my-info-modify">
                        <div class="input">
                            <label for="password">현재 비밀번호</label>
                            <input type="password" class="n-input" id="password">
                            <span id="password-invalid" class="validate danger"></span>
                        </div>
                        
                        <div class="input">
                            <label for="newPassword">신규 비밀번호</label>
                            <input type="password" class="n-input" id="newPassword" maxlength="20">
                            <span id = "new-password-invalid" class="validate danger"></span>
                            <span id="valid-newPassword" class="validate"
                                  style="display: none">사용 가능한 비밀번호입니다.</span>
                        </div>
                        
                        <div class="input">
                            <label for="confirmPassword">신규 비밀번호 재 입력</label>
                            <input type="password" class="n-input" id="confirmPassword" maxlength="20">
                            <span id="confirm-password-invalid" class="validate danger"></span>
                            <span  id="valid-confirmPassword" class="validate" style="display: none">사용 가능한 비밀번호입니다.</span>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="n-btn btn-sm btn-lighter" id="change-password-cancel-btn">취소</button>
                            <button type="button" class="n-btn btn-sm btn-accent disabled" id="change-password-finish-btn" disabled >완료</button>
                        </div>
                  </div>
                </td>
              </tr>
             </table>
          </section>
           
<script type="text/javascript" src="update.js"></script>
</body>
</html>