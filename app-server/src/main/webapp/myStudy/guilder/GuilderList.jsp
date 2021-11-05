<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>목록 | 조장인 스터디</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<style>
*{
  margin:0; padding:0;
  font-size:15px; 
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
.tabmenu ul li a{
  display:block;
  line-height:40px;
  height:40px;
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
</style>
</head>
<body>
<jsp:include page="../../header.jsp"/>

<br><br><br>
<h3>‍👩‍👧‍👧 ‍[${study.studyTitle}] 구성원 목록</h3>
<div class="tabmenu">
  <ul>
    <li id="tab1" class="btnCon"><a class="btn first" href="#tab1">참여중인 구성원</a>
      <div class="tabCon" >
      <br>
      <br><br>
      <table class="table table-hover">
        <tbody>
          <c:forEach items="${guildersList}" var="guilderMember">
            <div class="photo">${guilderMember.perPhoto}</div>
            <h5><a class="profile" href="detail?guilderNo=${guilderMember.perNo}">${guilderMember.perNickname}</a></h5>
          </c:forEach>
        </tbody>
      </table>
      <c:if test="${empty guildersList}">
        스터디에 참여 중인 구성원이 없습니다.
      </c:if>
      
      </div>
      
    </li>
    <li id="tab2" class="btnCon"><a class="btn" href="#tab2">승인대기중인 구성원</a>
      <div class="tabCon" >
      <br>
      <br><br>
      <table class="table table-hover">
        <tbody>
          <c:forEach items="${waitingGuilderList}" var="waitingMember">
            <div class="card-body">
            <div class="photo">${waitingMember.perPhoto}</div>
            <h5><a class="profile" href="agree?watingNo=${waitingMember.perNo}">${waitingMember.perNickname}</a></h5>
          </div>
          </c:forEach>
        </tbody>
      </table>
      <c:if test="${empty waitingGuilderList}">
      승인 대기 중인 구성원이 없습니다.
      </c:if>
      </div>
      
    </li>    
    
  </ul>
</div>

<script>
location.href = "#tab1";
</script>
  
</body>
</html>