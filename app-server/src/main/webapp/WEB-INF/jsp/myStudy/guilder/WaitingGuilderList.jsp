<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
*{
  margin:0; padding:0;
  line-height:1.3;
}
ul{list-style:none;}

.tabmenu{ 
  max-width:750px; 
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
  left:0;
  box-sizing: border-box; 
  border : 5px solid #f9f9f9;
  width: 800px;
  height: 500px;
  /* overflow-y: scroll; */
}

  .tabCon::-webkit-scrollbar {
    width: 10px;
  }
  .tabCon::-webkit-scrollbar-thumb {
  background-color: rgb(247, 231, 215);
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
  }
  .tabCon::-webkit-scrollbar-track {
  background-color: rgb(250, 250, 234);
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
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

.ownerBtn {
font-size: 12px;
    padding: 10px;
    border-radius: 13px;
}

.content-wrapper {
  background-color: white;
  border-radius: 20px;
  height: 800px;
  padding: 30px;
}
</style>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper">

      <jsp:include page="../MyStudyDetailNav.jsp"/>

      <%-- main-panel --%>
      <div class="main-panel">
        <div class="content-wrapper">
        
          <%-- row sub-items --%>
          <div class="row">
           <div class="tabmenu">
						  <ul>
						    
						    <c:if test="${loginUser.perNo eq study.owner.perNo}">
						    <li id="tab2" class="btnCon">
						      <div class="tabCon" >
						      <br>
						      <table class="table table-hover text-center align-middle">
						      <thead>
						        <tr>
						          <th>닉네임</th>
						          <th>승인</th>
						          <th>거절</th>
						        </tr>
						       </thead>
						        <tbody>
						          <c:forEach items="${waitingGuilderList}" var="waitingMember">
						            <tr>
						              <td>
						                 <img src="${contextPath}/img/perProfile.jpg" width="50px">
						                 <span> ${waitingMember.perNickname} </span>
						              </td>
						              
						              <td><button class="btn btn-outline-dark" style="font-size: 12px; padding: 10px; border-radius: 13px;" onclick='return submitBtn("agree","watingMemberNo",${waitingMember.perNo},"studyNo",${study.studyNo});'>승인</button></td>
						              <td><button class="btn btn-outline-dark" style="font-size: 12px; padding: 10px; border-radius: 13px;" onclick='return submitBtn("disagree","watingMemberNo",${waitingMember.perNo},"studyNo",${study.studyNo});'>거절</button></td>
						            </tr>
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
          </div> <%-- end row sub-items --%>
        
        
       </div> <%-- end content-wrapper --%>
     </div> <%-- main-panel --%>
     
     </div>
   </div>

<script>
location.href = "#tab2";

function submitBtn(str, name1, value1, name2, value2) {
  console.log(str, name1, value1, name2, value2);
  var form = document.createElement('form');
  form.setAttribute('method', 'post');

  var hiddenField = document.createElement('input');
  hiddenField.setAttribute('type', 'hidden');
  hiddenField.setAttribute('name', name1);
  hiddenField.setAttribute('value', value1);
  form.appendChild(hiddenField);

  var hiddenField = document.createElement('input');
  hiddenField.setAttribute('type', 'hidden');
  hiddenField.setAttribute('name', name2);
  hiddenField.setAttribute('value', value2);
  form.appendChild(hiddenField);

  document.body.appendChild(form);
  
  if (str == 'agree') {
    Swal.fire({
            title: '정말 승인하시겠습니까?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '네',
            cancelButtonText: '아니오'
        }).then((result) => {
            if (result.isConfirmed) {
               form.setAttribute('action', 'agree');
               form.submit();
               return true;
            }
        })  
  } else if (str == 'disagree') {
    Swal.fire({
            title: '정말 거절하시겠습니까?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '네',
            cancelButtonText: '아니오'
        }).then((result) => {
            if (result.isConfirmed) {
               form.setAttribute('action', 'disagree');
               form.submit();
               return true;
            }
        })  
  }
  
};

</script>
</body>
      