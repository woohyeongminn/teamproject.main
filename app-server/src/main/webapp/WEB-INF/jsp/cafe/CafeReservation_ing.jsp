<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
	<style>
	html {
	 cursor: default;
	}
	*{
	 font-size: 14px;
	}
	.c-content h3 {
    font-weight: bolder;
  }
  a {
   text-decoration:none;
  }
  h3, p {
    margin: 0px;
  }
  p {
    font-size: 13.5px !important;
  }
  .template-content {
    height: auto;
  }
  .pt-4 {
    height: auto !important;
  }
  .c-content {
    width: 100%;
    max-width: 720px;
    margin: 0 auto;
    box-sizing: border-box;
    xborder: 1px solid black;
  }
  .c-photo {
    height: 264px;
    background-size: cover;
    background-position: 50% 50%;
  }
  .c-body {
    padding: 0 20px 28px;
    xtext-align: center;
    xborder: 1px solid gray;
  }
  .c-room-name {
    position: absolute;
    left: 0;
    bottom: 13px;
    right: 0;
    padding-left: 25px;
    padding-right: 25px;
    text-align: center;
  }
  .c-room-name-title {
    margin-bottom: 6px;
    font-weight: 500;
    font-size: 24px;
    line-height: 26px;
    color: #fff;
    text-shadow: 0 0 1px rgb(0 0 0 / 20%);
  }
  .c-room-section {
    max-width: 720px;
    margin: 0 auto;
  }
  .c-room-section-content {
    margin: 10px 10px;
    color: #000;
  }
  .c-room-booking-section {
    border-top: 12px solid #9ba4a914;
    margin-top: 10px;
  }
  .c-room-booking-select {
    padding: 11px 0 11px 10px;
    box-sizing: border-box;
  }
  .c-room-booking-select > h3 {
    display: inline-block;
    margin-right: 7px;
    font-weight: 600;
    vertical-align: middle;
    font-size: 14px;
    line-height: 14px;
  }
  .form-check-input:disabled {
    background-color: lightgray;
  }
  #time-value > p {
    margin-bottom: 18px;
  }
	</style>
</head>
<body>
<div class="c-content">
<br><br>

  <div class="c-body">
    <div class="c-photo" style="background-image: url(${contextPath}/img/studyroom1.jpg);"></div>
    <div class="c-room-info-section">
      <div class="c-room-section">
        <div>
          <p class="c-room-section-content">
            ${cafeRoom.roomInfo}
          </p>
        </div>
      </div>
    </div>
    
    <div class="c-room-booking-section">
      <div class="c-room-booking-select date">
        <h3>📅 날짜 선택</h3>
      </div>
      
      <div class="c-room-booking-select date-value">
          <input class="form-control" type="text" id="calendar" placeholder="ex) 2021-11-23" name="date" style="background-color: white;">
      </div>
      
      <div class="c-room-booking-select time">
        <h3>🕑 시간 선택</h3>
      </div>
      
      <div class="c-room-booking-select time-value" id="time-value">
        <p> 날짜를 선택해주세요. </p>
			    <ul class="list-group">
          <c:forEach items="${statusOfNumber}" var="i">
			      <li class="list-group-item">
	            <input class="form-check-input me-1" type="checkbox" value="${i.key},${i.value}" aria-label="${i.value}" name="selectedTime" disabled>
	            ${fn:split(i.value, ',')[0]} ~ ${fn:split(i.value, ',')[1]} : ${fn:split(i.value, ',')[2]}
			      </li>
			  </c:forEach>
			  </ul>
      </div>
      
      <div class="c-room-booking-select people">
        <h3>👪 인원수</h3>
      </div>
      
      <div class="c-room-booking-select people">
          <input class="form-control" type="number" min="1" max="${cafeRoom.people}" value="1" id="people">
      </div>
      
      <div class="c-room-booking-select price" id="price">

      </div>
      
      <div class="c-room-booking-select payment-type">
        <h3>💳 결제 방법</h3>
      </div>
      
      <div class="c-room-booking-select payment-type-value">
        <div class="form-check form-check-inline">
				  <input class="form-check-input" type="radio" name="payment-type" id="payment-type-1" value="reservation">
				  <label class="form-check-label" for="inlineRadio1">현장결제</label>
				</div>
				<div class="form-check form-check-inline">
				  <input class="form-check-input" type="radio" name="payment-type" id="payment-type-2" value="payment">
				  <label class="form-check-label" for="inlineRadio2">실시간결제</label>
				</div>
      </div>
      
      <div class="c-room-booking-select payment">
        <button type="button" class="btn btn-outline-dark" id="pay_btn" style="width: 100%;" disabled>예약하기</button>
      </div>
      
    </div> <!-- c-room-booking-section -->
  </div>
</div>

<br><br><br>

<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://npmcdn.com/flatpickr/dist/flatpickr.min.js"></script>
<script src="https://npmcdn.com/flatpickr/dist/l10n/ko.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js" type="text/javascript"></script> <!-- jQuery -->
<script src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js" type="text/javascript"></script> <!-- iamport  -->

<script>
flatpickr.localize(flatpickr.l10ns.ko);
flatpickr("#calendar");
var dateInput = document.querySelector("#calendar");
dateInput.flatpickr({
	// 최소 날짜
	minDate:new Date().fp_incr(1),
});

var time_section = document.getElementById("time-value");

document.querySelector("#calendar").onchange = () => {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                 // 서버에서 받은 JSON 문자열을 자바스크립트 객체로 변환한다.
                 var arr = JSON.parse(xhr.responseText);
                 // console.log(arr);
                 
                 if(document.querySelector('#time-value p') != null) 
                  document.querySelector('#time-value p').remove();
  
                 if(document.querySelector('#time-value ul') != null)                 
                  document.querySelector('#time-value ul').remove();

                 var value;
                 var str = document.createElement("ul");
                 str.setAttribute("class","list-group");
                	 
                 var strTag = "";
                 for (let i in arr) {
                	 value = arr[i].split(",");
                	 
                	 if (value[2] == '예약 불가') {
                		 strTag += "<li class='list-group-item'>"
                		 strTag += "<input class='form-check-input me-1' value='"+i+","+value+"' id='"+i+"' type='checkbox' aria-label='"+value+"' name='selectedTime' disabled> " 
                		 strTag += "<span style='color:lightgray;'>"+value[0] + " ~  " + value[1] + " : " + value[2]+"</span>";
                	 } else {
                		 strTag += "<li class='list-group-item'>"
                		 strTag += "<input class='form-check-input me-1' value='"+i+","+value+"' id='"+i+"' type='checkbox' aria-label='"+value+"' name='selectedTime'> " + value[0] + " ~  " + value[1] + " : " + value[2] + "</li>";
                	 }
                	  
                 }
                 strTag += "</ul>";
                 str.innerHTML = strTag;
                 time_section.appendChild(str);
                 
                 
                 // 체크박스 값 제어
                 const checkBoxes = document.querySelectorAll('input[name=selectedTime]');
                 checkBoxes.forEach((item) => item.addEventListener('input', checkTime));
                 let checkedValue = new Array();
                 var min;
                 var max;
                 
                 function checkTime(e) {
                   const { checked, id } = e.target;
                   if (checked) {
                		   
                	   if (checkedValue.length > 0) {
                       //console.log(checkedValue);
                       
                       min = Math.min.apply(null, checkedValue);
                       max = Math.max.apply(null, checkedValue);
                		   
                       if (id != min-1 && id != max+1){
                			   alert("연속된 시간만 선택 가능합니다. 다시 선택해주세요.");
                         e.target.checked = false;
                		   } else {
                			   checkedValue.push(id);
                			   totalPrice(checkedValue);
                		   }
                		   
                	   } else if (checkedValue.length == 0) {
                		   checkedValue.push(id);
                		   totalPrice(checkedValue);
                	   }
                	
                   } else {
                	   min = Math.min.apply(null, checkedValue);
                     max = Math.max.apply(null, checkedValue);
                     
                	   if (id == min || id == max){
                        e.target.checked = false;
                        for(let i = 0; i < checkedValue.length; i++) {
                        	  if(checkedValue[i] === id)  {
                        		  checkedValue.splice(i, 1);
                              totalPrice(checkedValue);
                        	    i--;
                        	  }
                        	}
                      } else {
                    	  e.target.checked = true;
                    	  totalPrice(checkedValue);
                      }
                   }
                 } // 체크박스 함수 끝
                 
                 
                 // 가격 계산
                 function totalPrice(checkedValue, peopleNumber){
                   if(checkedValue.length > 0){
                	 var price_section = document.getElementById("price");
                	 
                	 let min = Math.min.apply(null, checkedValue);
                   let max = Math.max.apply(null, checkedValue);
                   
                	 selectedDate = document.getElementById('calendar').value;
                	 selectedTime = document.getElementById(min).value;
                   startTime = selectedTime.split(",")[1];
                   usingTime = max-min+1;
                   
                   if (peopleNumber != null){
                	   price = usingTime * ${cafeRoom.roomPrice} * peopleNumber;
                   } else {
                	   price = usingTime * ${cafeRoom.roomPrice};
                	   
                   }
                   
                   if(document.querySelector('#price h3') != null) 
                    document.querySelector('#price h3').remove();
                   
	                 let dateAndPrice = document.createElement("h3");
	                 let dateAndPriceText = document.createTextNode(selectedDate+" "+startTime+" ~ ("+usingTime+"시간) "+price+"원");
	                   
	                 dateAndPrice.appendChild(dateAndPriceText);
	                 price_section.appendChild(dateAndPrice);
	                   
	                 const payBtn = document.getElementById('pay_btn');
	                 payBtn.disabled = false;
	                   
                   } else {
                	   
                	 if(document.querySelector('#price h3') != null) 
                     document.querySelector('#price h3').remove();
                	   
                	 const payBtn = document.getElementById('pay_btn');
                   payBtn.disabled = true;
                   
                   }
                   
                 } // 가격 계산 함수 끝
                 
                 document.querySelector("#people").onclick = (e) => {
                	 //console.log(e.currentTarget.value);
                	 totalPrice(checkedValue, e.currentTarget.value);
                 } // 가격 계산 * 인원수
            }
        }
    };
    var selectDate = document.getElementById('calendar').value;

    xhr.open("GET", "reservationTime?cafeNo="+${cafe.no}+"&roomNo="+${cafeRoom.roomNo}+"&date="+selectDate, true);
    xhr.send();
};



var selectedDate;
var selectedTime;
var startTime;
var usingTime;
var price;

const { IMP } = window;
IMP.init('imp83497236');

document.querySelector("#pay_btn").onclick = () => {
	  if (checkLogin(${loginUser.perNo}) == null){
		  swal.fire("로그인 한 회원만 예약 가능합니다.");
		  return false;
	  }
	  
	  Swal.fire({
	      title: '정말 예약하시겠습니까?',
	      showCancelButton: true,
	      confirmButtonText: '네',
	      cancelButtonText: '아니오'
	   }).then((result) => {
	     if (result.value) { 
          
    var selectedPeople = document.querySelector("#people").value;
	  var payment_type = $('input[name="payment-type"]:checked').val();
    console.log(selectedDate, startTime , usingTime, selectedPeople, price);
	  var reservation = {
	          useDate : selectedDate,
	          roomNo : ${cafeRoom.roomNo},
	          useTime : usingTime,
	          startTimeStr : startTime,
	          useMemberNumber : selectedPeople,
	          totalPrice : price
	  };
    
    createForm = function(reservation){
        var form = document.createElement("form");
        
        for(key in reservation){
            var param = document.createElement('input');
            param.setAttribute('type','hidden');
            param.setAttribute('name',key);
            param.setAttribute('value',reservation[key]);
            form.appendChild(param);
      }
        return form;
    }
    
    if (payment_type == 'payment') {
    
    	// 결제	
	    IMP.request_pay({
	        pg : 'html5_inicis',
	        merchant_uid : 'merchant_' + new Date().getTime(),
	        name : '오늘의공부_결제테스트',
	        amount : price, // 결제할 금액
	        buyer_email : '${sessionScope.loginUser.perEmail}',
	        buyer_tel : '${sessionScope.loginUser.perTel}' // 일부 누락or공백일때 일부 PG사에서 오류 발생
	    }, function(rsp) {
	        if ( rsp.success ) {
	        	
	        	reservation.paymentUid=rsp.merchant_uid;
	        	reservation.paymentType='카카오페이';
	        	var form = createForm(reservation);
	        	
	        	$.ajax({
	                url : "paymentReservation",
	                type : "post",
	                dataType : "json",
	                data : $(form).serialize(),
	                success:function(data){
	                	  let reservationNo = data['reservationNo'];
	                    
	                    let endForm = document.createElement('form');
	                    endForm.setAttribute('method', 'post');
	                    endForm.setAttribute('action', 'reservationEndPage');

	                    let hiddenField = document.createElement('input');
	                    hiddenField.setAttribute('type', 'hidden');
	                    hiddenField.setAttribute('name', "reservationNo");
	                    hiddenField.setAttribute('value', reservationNo);
	                    endForm.appendChild(hiddenField);

	                    document.body.appendChild(endForm);
	                    endForm.submit();
	                }
	            });
	
	        //var msg = '결제가 완료되었습니다.';
	        
	        } else {
	            var msg = '결제 실패<br>';
	            msg += rsp.error_msg;
	            swal.fire(msg);
	        }
	
	    });

    } else {
    	
    	// 예약
    	var form = createForm(reservation);
    	
	    $.ajax({
	        url : "completeReservation",
	        type : "post",
	        dataType : "json",
	        data : $(form).serialize(),
	        success:function(data){
	        	let reservationNo = data['reservationNo'];
	        	
	        	let endForm = document.createElement('form');
	        	endForm.setAttribute('method', 'post');
	        	endForm.setAttribute('action', 'reservationEndPage');

	        	let hiddenField = document.createElement('input');
            hiddenField.setAttribute('type', 'hidden');
            hiddenField.setAttribute('name', "reservationNo");
            hiddenField.setAttribute('value', reservationNo);
            endForm.appendChild(hiddenField);

            document.body.appendChild(endForm);
            endForm.submit();
	        }
	    });
    }
  }})
};

function checkLogin(user) {
	  if (user == null) {
	    return null;
	  }
	  return user;
}

</script>







