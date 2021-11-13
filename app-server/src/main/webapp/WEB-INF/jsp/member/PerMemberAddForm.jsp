<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  form {
  max-width: 500px;
  }
  .btn {
   font-size: 14px;
   line-height: 24px;
  }
  b {
  text-align: center;
  font-size:20px
  padding
  }  
 .all-content {
    width: 100%;
    max-width: 500px;
    margin: 0 auto;
    padding: 30px;
    margin-top:40px;
    text-align: center;
  }  
</style>
 <div class="all-content">
	 <b>🙋개인 회원 가입</b><br> 
	  <form id="member-form" action='add' method='post' enctype="multipart/form-data">
		  <div class="mb-3 row">
			  <label for='f-photo' class="col-sm-2 col-form-label">사진</label> 
				  <div class="col-sm-10">
				    <input id='f-photo' type='file' name='photoFile' class="form-control">
				  </div>
		  </div>
		  
		  <div class="mb-3 row">
			  <label for='f-name' class="col-sm-2 col-form-label">이름</label>
				  <div class="col-sm-6">
				    <input id='f-name' type='text' name='perName' class="form-control"
				    placeholder="*필수 입력 항목">
				  </div>
		  </div>
		   
		  <div class="mb-3 row">
			  <label for='f-nickname' class="col-sm-2 col-form-label">닉네임</label>
				  <div class="col-sm-6">
				    <input id='f-nickname' type='text' name='perNickname' class="form-control"
				    placeholder="*필수 입력 항목">
				  </div>
		  </div> 
		  
		  <div class="mb-3 row">
			  <label for='f-emial' class="col-sm-2 col-form-label">이메일</label>
				  <div class="col-sm-6">
				    <input id='f-email' type='text' name='perEmail' class="form-control"
				    placeholder="*필수 입력 항목">
				  </div>
		  </div> 
		
			<div class="mb-3 row">
			  <label for='f-password' class="col-sm-2 col-form-label">암호</label>
			  <div class="col-sm-6">
			    <input id='f-password' type='password' name='perPassword' class="form-control"
			    placeholder="*필수 입력 항목">
			  </div>
			</div>  
		
			<div class="mb-3 row">
			  <label for='f-tel' class="col-sm-2 col-form-label">전화번호</label> 
			  <div class="col-sm-6">
			    <input id='f-tel' type='tel' name='perTel' class="form-control"
			    placeholder="*필수 입력 항목">
			  </div>
			</div> 
			<input id='f-tel' type='hidden' name='perStatus' value="1"><br>  
		 <button class="btn btn-outline-dark">가입하기</button><br>
		</form>  
 </div>
 
 
 <!-- 
 <div class="all-content">
  <b style="font-size:20px"> 🙋개인 회원 가입 </b>
    <fieldset>
     <table class="table table-responsive">
      <form action='add' method="post"><br>
        <p><label for='f-name' class='form-label' size='100px'>이름</label>
          <input id='f-name' type='text' name='perName' placeholder="이름"><br>
        </p>
        <p><label for='f-email' class='form-label' size='100px'>이메일</label>
          <input id='f-email' type='email' name='perEmail' placeholder="이메일"><br>
        </p>
        <p><label for='f-password' class='form-label' size='100px'>암호</label>
          <input id='f-password' type='password' name='perPassword' placeholder="암호"><br>
        </p>
        <p><label for='f-nickname' class='form-label' size='100px'>닉네임</label>
          <input id='f-nickname' type='name' name='perNickname' placeholder="닉네임"><br>
        </p>
        <p><label for='f-tel' class='form-label' size='100px'>전화번호</label>
          <input id='f-tel' type='tel' name='perTel' placeholder="전화번호"><br>
        </p>
        <input id='f-tel' type='hidden' name='perStatus' value="1"><br>
    </table>
   </fieldset> 
    </div>
   </form>
    
<div class="d-grid gap-2 d-md-flex justify-content-md-end">
     <button class="btn btn-outline-dark" type="submit" value="등록" formaction="add">가입하기</button>
</div>
  --> 
<script>
document.querySelector("#member-form").onsubmit = () => {
  if (document.querySelector("#f-name").value == "" ||
      document.querySelector("#f-email").value == "" ||
      document.querySelector("#f-nickname").value == "" ||
      document.querySelector("#f-tel").value == "" ||
      document.querySelector("#f-password").value == "") {
    //window.alert("필수 입력 항목이 비어 있습니다.")
    Swal.fire("필수 입력 항목이 비어 있습니다.")
    return false;
  }
};
</script>




