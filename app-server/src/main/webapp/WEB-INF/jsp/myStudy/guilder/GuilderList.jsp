<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
*{
  margin:0; padding:0;
  font-size:14px; 
  line-height:1.3;
}
ul{list-style:none;}

.tabmenu{ 
  max-width:900px; 
  margin: 0 auto; 
  position:relative; 
}
.tabmenu ul li{
  display:  inline-block;
  width:33.33%; 
  float:left;  
  text-align:center; 
  background :#f9f9f9;
}
.tabmenu ul li .btn{
  display:block;
  text-decoration:none; 
  color: #000;
}

.profile{
 display:block;
}

.tabCon{
  display:none; 
  padding: 20px;
  position:absolute; 
  left:0; top:40px; 
  box-sizing: border-box; 
  border : 5px solid #f9f9f9;
  width: 900px;
}
.btnCon:target  {
  background : #ccc;
}
.btnCon:target .tabCon{
  display: block;
}

.agreebtn {
  display: block;
  float: left;
}

table tr {
  height: 50px;
}

table td button {
  margin: 0 auto;
}

.swal2-radio {
  display: grid !important;
}
</style>
</head>
<body>

<div class="tabmenu">
  <ul>
    <li id="tab1" class="btnCon"><a class="btn first" href="#tab1">참여중인 구성원</a>
      <div class="tabCon" >
      <br>
      <table class="table table-hover text-center align-middle">
      <thead>
        <tr>
          <th>닉네임</th>
          <c:if test="${loginUser.perNo eq study.owner.perNo}">
	          <th>조장 권한 위임</th>
	          <th>탈퇴시키기</th>
          </c:if>
        </tr>
        </thead>
        <tbody>
          <c:forEach items="${guildersList}" var="guilderMember">
          <tr>
           <c:choose>
              <c:when test="${study.owner.perNo == guilderMember.perNo}">
		          <td colspan="3">
		            <img src="${contextPath}/img/KakaoTalk_20211113_014317871.jpg" width="50px">
		            <a class="profile" href="detail?guilderNo=${guilderMember.perNo}">
		               👑 ${guilderMember.perNickname} [조장]</a></td>
			        </c:when>
	            <c:otherwise> 
	              <td>
	               <img src="${contextPath}/img/KakaoTalk_20211113_014317871.jpg" width="50px">
	               <a class="profile" href="detail?guilderNo=${guilderMember.perNo}">${guilderMember.perNickname}</a></td>
	            </c:otherwise>
		        </c:choose>
		        <c:choose>
              <c:when test="${loginUser.perNo eq study.owner.perNo}">
                <c:if test="${study.owner.perNo != guilderMember.perNo}">
                  <td><button class="btn btn-outline-dark" onclick='return submitBtn(this.form,${guilderMember.perNo},${study.studyNo});'>조장권한위임</button></td>
				          <td><button class="btn btn-outline-dark" onclick='return submitBtn2(this.form,${guilderMember.perNo},${study.studyNo});'>탈퇴시키기</button></td>
				        </c:if>
              </c:when>
              </c:choose>
	          </tr>
	       </c:forEach>
        </tbody>
      </table>
		                
      <c:if test="${empty guildersList}">
        스터디에 참여 중인 구성원이 없습니다.
      </c:if>
      
      </div>
      
    </li>
    
    <c:if test="${loginUser.perNo eq study.owner.perNo}">
    <li id="tab2" class="btnCon"><a class="btn" href="#tab2">승인대기중인 구성원</a>
      <div class="tabCon" >
      <br>
      <br><br>
      <table class="table table-hover">
        <tbody>
          <c:forEach items="${waitingGuilderList}" var="waitingMember">
            <div class="card">
	            <div class="body-photo">${waitingMember.perPhoto} 프로필사진</div>
	            <div class="body-left"><a class="profile" href="detail?watingNo=${waitingMember.perNo}">${waitingMember.perNickname}</a></div>
	            <div class="body-right">
	            <a type="button" class="ownerbtn" href="agree?watingMemberNo=${waitingMember.perNo}&studyNo=${study.studyNo}">승인</a>
	            <a type="button" class="ownerbtn" href="disagree?watingMemberNo=${waitingMember.perNo}&studyNo=${study.studyNo}">거절</a>
              </div>
            </div>
          </c:forEach>
        </tbody>
      </table>
      <c:if test="${empty waitingGuilderList}">
      승인 대기 중인 구성원이 없습니다.
      </c:if>
      </div>
      
    </li> 
    </c:if>   
    
  </ul>
</div>

<script>
location.href = "#tab1";

function submitBtn(frm,guilderMemberNo,studyNo) {
	
	var form = document.createElement('form');
	form.setAttribute('method', 'post');

	var hiddenField = document.createElement('input');
	hiddenField.setAttribute('type', 'hidden');
	hiddenField.setAttribute('name', 'guilderMemberNo');
	hiddenField.setAttribute('value', guilderMemberNo);
	form.appendChild(hiddenField);

	var hiddenField = document.createElement('input');
	hiddenField.setAttribute('type', 'hidden');
	hiddenField.setAttribute('name', 'studyNo');
	hiddenField.setAttribute('value', studyNo);
	form.appendChild(hiddenField);

	document.body.appendChild(form);
	
	(async () => {
	
	const inputOptions = new Promise((resolve) => {
		  setTimeout(() => {
		    resolve({
		      'entrust': '조장 권한 넘겨주고 스터디 구성원 되기',
		      'entrustexit': '조장 권한 넘겨주고 스터디 탈퇴'
		    })
		  }, 1000)
		})

		const { value: color } = await Swal.fire({
		  title: '조장 권한 넘겨주기',
		  input: 'radio',
		  inputOptions: inputOptions,
		  inputValidator: (value) => {
		    if (!value) {
		      return '둘 중 하나를 선택해주세요!'
		    }
		  }
		})

		if(color) {
      if (color == 'entrust'){
          form.setAttribute('action', 'entrust');
          form.submit();
          return true;
        } else if (color == 'entrustexit'){
				form.setAttribute('action', 'entrustexit');
	      form.submit();
				return true;
			}
		}
	})()
};

function submitBtn2(frm,guilderMemberNo,studyNo) {
	
	var form = document.createElement('form');
	form.setAttribute('method', 'post');

	var hiddenField = document.createElement('input');
	hiddenField.setAttribute('type', 'hidden');
	hiddenField.setAttribute('name', 'guilderMemberNo');
	hiddenField.setAttribute('value', guilderMemberNo);
	form.appendChild(hiddenField);

	var hiddenField = document.createElement('input');
	hiddenField.setAttribute('type', 'hidden');
	hiddenField.setAttribute('name', 'studyNo');
	hiddenField.setAttribute('value', studyNo);
	form.appendChild(hiddenField);

	document.body.appendChild(form);
	
  Swal.fire({
      title: '정말 탈퇴시키겠습니까?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: '네',
      cancelButtonText: '아니오'
  }).then((result) => {
      if (result.isConfirmed) {
     		 form.setAttribute('action', 'delete');
         form.submit();
         return true;
      }
  })
};

</script>
