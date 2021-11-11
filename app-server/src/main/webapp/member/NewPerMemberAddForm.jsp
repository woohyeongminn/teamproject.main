
<!DOCTYPE html>
<html lang="ko">

    <head>
        

        
    <title>회원가입 | 오늘의 공부</title>


        
    <meta charset="utf-8">
   
    <script>
        var THIS_PAGE_GF = "A" ;
        var USE_GLOBAL_FILTER = 'Y';

        function commonHistoryBack() {
            var isApp = false;
            if (isApp) {
                AppInterface.historyBack();
                return false;
            }
            history.back();
            return false;
        }

    </script>
    <script>
     
       mss.ui.config.set(uiConfig);

        // 본인인증 팝업 X 버튼 클릭시
        function selfCertifyBackBtn(pageCode) {
            if(pageCode == '005') {
                if(false) {
                    var appInterfaceCall = _AppInfo.UpperVersion('2.23.0') && _AppInfo.LowerVersion('2.35.0');
                    if (appInterfaceCall) {
                        AppInterface.closeForLogin("");
                        return false;
                    }
                }
                if(opener) {
                    opener.location.href = "https://my.musinsa.com/login/v1/login?referer=" + encodeURIComponent('https://store.musinsa.com');
                    self.close();
                    return false;
                }
                location.href = "https://my.musinsa.com/login/v1/login?referer=" + encodeURIComponent('https://store.musinsa.com');
                return false;
            }

            self.close();
            return false;
        }

        Date.prototype.format = function(f) {
            if (!this.valueOf()) return " ";

            var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
            var d = this;

            return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
                switch ($1) {
                    case "yyyy": return d.getFullYear();
                    case "yy": return (d.getFullYear() % 1000).zf(2);
                    case "MM": return (d.getMonth() + 1).zf(2);
                    case "dd": return d.getDate().zf(2);
                    case "E": return weekName[d.getDay()];
                    case "HH": return d.getHours().zf(2);
                    case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
                    case "mm": return d.getMinutes().zf(2);
                    case "ss": return d.getSeconds().zf(2);
                    case "a/p": return d.getHours() < 12 ? "오전" : "오후";
                    default: return $1;
                }
            });
        };

        String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
        String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
        Number.prototype.zf = function(len){return this.toString().zf(len);};

    </script>

    <script src="https://static.msscdn.net/static/member/js/ajax.js?2021104221413" type="text/javascript"></script>


        

    </head>

    <body>
        
    <div class="musinsa-wrapper wrapper-member devicePC">

        <div id="commonLayoutHeader"></div>
        <!-- Member -->
        <div class="n-member-area form-area">

            <form class="formBox" name="joinForm" id="joinForm" method="post" novalidate>
                <input type="hidden" name="checkNick" value="0">
                <input type="hidden" name="checkPassword" value="0">
                <input type="hidden" name="checkConfirmPassword" value="0">
                <input type="hidden" name="checkEmail" value="0">
                <input type="hidden" name="checkRecommendMemberId" value="0">
                <input type="hidden" name="checkPhoneNumber" value="0">
                <input type="hidden" id="joinToken" name="joinToken" value="bc1af3a7bc8bbba6d8eaa6c5d07260a4c53b130f"/>
                <input type="hidden" id="referer" name="referer" value="http://www.musinsa.com/"/>
                <input type="hidden" name="eventToken" value=""/>
                <input type="hidden" name="snsToken" value=""/>
                <input type="hidden" id="partnerCode" name="partnerCode" value=""/>
                <input type="hidden" name="eventPage" value=""/>
                <input type="hidden" name="eventCode" value=""/>
                <input type="hidden" name="returnUrl" value=""/>

                <!-- Header -->
                <header class="member-header">
                    
                    
                        <div class="logo">
                            <img src="https://image.msscdn.net/ui/musinsa/resources/common/image/logo_mwusinsa.svg" alt="무신사로고">
                        </div>
                    
                    
                    
                        <p class="text-kakao font-mss">회원가입</p>
                    
                </header>
                <!-- //Header -->

                <!-- Join Form -->
                <div class="join-form">
                    <div class="n-form-set">
                        <label for="memberId" class="n-form-label">아이디 <span class="essential">필수 입력</span></label>
                        <input type="text" class="n-input input" id="memberId" name="memberId" placeholder="아이디 입력(5~11자)" minlength="5" maxlength="11">
                        <p class="n-validation" id="hLayerid"></p>
                    </div>
                    <div class="n-form-set">
                        <label for="password" class="n-form-label">비밀번호 <span class="essential">필수 입력</span></label>
                        <div class="n-form-icon">
                            <input type="password" class="n-input input" id="password" name="password" minlength="8" autocomplete="new-password"
                                   placeholder="비밀번호(숫자,영문,특수문자 조합 최소8자)">
                            <p class="n-validation" id="passwordValidMessage"></p>
                            <button type="button" class="btn" onclick="togglePassword('password', this);" >
                                <i class="ic-30-solid-show ic-gray5">비밀번호 보기</i>
                            </button>
                        </div>
                        <div class="n-form-icon">
                            <input type="password" class="n-input input" id="confirmPassword" name="confirmPassword" autocomplete="new-password"
                                   minlength="8" placeholder="비밀번호 확인">
                            <p class="n-validation" id="passwordConfirmValidMessage"></p>
                            <button type="button" class="btn" onclick="togglePassword('confirmPassword', this);">
                                <i class="ic-30-solid-show ic-gray5">비밀번호 보기</i>
                            </button>
                        </div>
                    </div>
                    <div class="n-form-set">
                        <label for="email" class="n-form-label">이메일 <span class="essential">필수 입력</span></label>
                        <div class="n-form-layer" id = "emailFromLayer">
                            <input type="email" class="n-input input" id="email" name="email" maxlength="50"
                                   placeholder="이메일" autocomplete="off" value="">
                            <p class="n-validation" id="hLayeremail"></p>
                            <ul id="emailDomainList" class="layer">
                                <li>
                                    <button type="button"><span></span><em>naver.com</em></button>
                                </li>
                                <li>
                                    <button type="button"><span></span><em>gmail.com</em></button>
                                </li>
                                <li>
                                    <button type="button"><span></span><em>hanmail.net</em></button>
                                </li>
                                <li>
                                    <button type="button"><span></span><em>nate.com</em></button>
                                </li>
                                <li>
                                    <button type="button"><span></span><em>daum.net</em></button>
                                </li>
                            </ul>
                        </div>
                        
                    </div>
                  
                    
                    <div id="agreementDivArea" class="agreement">
                        <div>
                            <input type="checkbox" class="n-check" id="checkAll">
                            <label for="checkAll" class="all">약관 전체동의</label>
                        </div>
                        <div>
                            <input type="checkbox" class="n-check agree-item required-agree-item" id="agreeCheckbox" name="agreeCheckbox">
                            <label for="agreeCheckbox">개인정보 수집 이용동의(필수)</label>
                            <button type="button" class="link" onclick="privacyAgreeUsagePopBtnClickHandler()">약관보기</button>
                        </div>

                        <div>
                            <input type="checkbox" class="n-check agree-item required-agree-item" id="ageAgreeCheckbox" name="ageAgreeCheckbox">
                            <label for="ageAgreeCheckbox">만 14세 미만 가입 제한(필수)</label>
                        </div>
                    </div>
                    
                </div>
                <div id="joinBtnDiv" class="member-btn">
                    
                        <button type="submit" id="joinBtn" class="n-btn btn-primary">본인인증하고 회원가입</button>
                    
                </div>

                
                   
                

            </form>
        </div>

        <!-- ANCHOR Common Layout Start -->
        <div id="commonLayoutFooter"></div>
        <script type="text/javascript" src="https://static.msscdn.net/static/common/2.2.0/cl-mobile.js"></script>
        <script>window.commonLayout.render('#commonLayoutFooter', '#commonLayoutHeader', {
            titleText: '회원가입',
            titleBlind: true,
            useAppbarButtonBack: true,
            useMenubarBottom: false
        });</script>
        <!-- ANCHOR Common Layout End -->
    </div>
    </body>
    <script type="text/javascript">
        var isPassMemberId = false;
        var isPassPassword = false;
        var isConfirmPassPassword = false;
        var isPassEmail = false;
        var isPassAgree = false;
        var existMemberIdResponse,
            memberIdResult = false,
            recommendMemberIdLengthTrigger = false,
            memberIdLengthTrigger = false;

        var snsToken = '';

        var joinBtnActive = function (isActive) {
            $("#joinBtn").attr("class", "n-btn btn-primary" + (!isActive ? " disabled" : ""));
        };

        function checkMemberIdLength() {
            if ($('#memberId').val().length === 11 && memberIdLengthTrigger) {
                existMemberId(existMemberIdResponse);
                return false;
            } else {
                memberIdLengthTrigger = false;
                return true;
            }
        }

        function existMemberId(response) {
            var $memberId = $('#memberId');
            if (response.success) {
                $("input[name='checkNick']").val('1');
                $memberId.attr('class', 'n-input input');
                $('#hLayerid').attr("class", "n-validation validation-passed");
                $("#hLayerid").html(response.message);

                isPassMemberId = true;
                if (isPassJoin()) {
                    joinBtnActive(true);
                }

                return true;
            } else {
                $("input[name='checkNick']").val('0');
                $("#hLayerid")
                    .removeClass('validation-passed')
                    .html(response.message);
                $memberId.attr('class', 'n-input input-danger');

                joinBtnActive(false);
                isPassMemberId = false;
                return false;
            }
        }

        function validateLengthMemberIdWhenKeyup() {
            $('#memberId').val($('#memberId').val().trim());
            var $memberId = $('#memberId');
            if ($memberId.val().length > 11) {
                var limitMemberId = $memberId.val().substring(0, 11);
                $memberId.val(limitMemberId);
            }
        }

        function validateMemberId() {

            validateLengthMemberIdWhenKeyup();

            var $memberId = $('#memberId');
            if ($memberId.val().length === 0) {
                $("#hLayerid")
                    .removeClass('validation-passed')
                    .html('닉네임은 필수정보 입니다.');
                $memberId.attr('class', 'n-input input-danger');
                isPassMemberId = false;
                $("input[name='checkNick']").val('0');
                joinBtnActive(false);
                return false;
            }

            if ($memberId.val().length < 2) {
                $("#hLayerid")
                    .removeClass('validation-passed')
                    .html('닉네임은 2자 이상이어야 합니다.');
                $memberId.attr('class', 'n-input input-danger');
                isPassMemberId = false;
                $("input[name='checkNick']").val('0');
                joinBtnActive(false);
                return false;
            }

            if ($memberId.val().length > 11) {
                var subMemberId = $memberId.val().substring(0, 11);
                $("#memberId").val(subMemberId);
            }


            if ($memberId.val().length < 11) {
                memberIdLengthTrigger = false;
            }

            if (memberIdLengthTrigger) {
                return false;
            }

            ajaxExistMemberId();
        }

        function ajaxExistMemberId() {
            if (!checkMemberIdLength()) {
                return false;
            }

            var $memberId = $('#memberId');
            var joinToken = sessionStorage.getItem('joinTokenSession') ? sessionStorage.getItem('joinTokenSession') : '';
            mss.my.ajax.call(
                {
                    async: false,
                    type: 'POST',
                    contentType: "application/json",
                    url: '/api/member/v2/join/memberId/exist',
                    data: JSON.stringify({
                        'memberId': $memberId.val(),
                        'joinToken': joinToken
                    }),
                    success: function (response) {
                        existMemberIdResponse = response;
                        existMemberId(response);

                        if ($('#memberId').val().length === 11) {
                            memberIdLengthTrigger = true;
                        }
                    }
                }, false
            );
        }

        function validatePassword() {
            var password = $("#password").val().trim();
            $("input[name='password']").val('');
            $("input[name='password']").val(password);

            $("input[name='checkPassword']").val('0');

            if (password === '') {
                $('#passwordValidMessage').html('비밀번호는 필수정보입니다.');
                $("input[name='password']").attr('class', 'n-input input-danger');
                $("input[name='confirmPassword']").attr('class', 'n-input input');
                $("#passwordConfirmValidMessage").html('');
                isPassPassword = false;
                joinBtnActive(false);
                return;
            }

            if (!passwordValidCheck(password)) {
                isPassPassword = false;
                joinBtnActive(false);
                return;
            }

            var isCheckValidPassword = callCheckValidPassword($("#password"));
            if (!isCheckValidPassword) {
                isPassPassword = false;
                joinBtnActive(false);
                return;
            }

            var confirmPassword = $('#confirmPassword').val();

            if (confirmPassword !== '' && password.length > 7) {
                if (password !== confirmPassword) {
                    $("input[name='confirmPassword']").attr('class', 'n-input input-danger');
                    $("#passwordConfirmValidMessage").html('비밀번호가 일치하지 않습니다.');
                    isConfirmPassPassword = false;
                    joinBtnActive(false);
                    return;
                } else {
                    $("input[name='confirmPassword']").attr('class', 'n-input input');
                    $("#passwordConfirmValidMessage").html('');

                    isPassPassword = true;
                    isConfirmPassPassword = true;

                    if (isPassJoin()) {
                        joinBtnActive(true);
                    }
                }
            }


            function passwordValidCheck(pw) {
                var $msgObj = $('#passwordValidMessage');
                var has_letter = new RegExp("[a-z]");
                var has_caps = new RegExp("[A-Z]");
                var has_numbers = new RegExp("[0-9]");
                var has_symbols = new RegExp(/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>₩@\#$%&\\\=\(\'\"]/gi);
                var pob = 0;

                if(has_letter.test(pw)) {
                    pob++;
                }
                if (has_caps.test(pw)) {
                    pob++;
                }
                if (has_numbers.test(pw)) {
                    pob++;
                }
                if (has_symbols.test(pw)) {
                    pob++;
                }

                if(!pw) {
                    $msgObj.html("<font>비밀번호는 필수정보입니다.</font>");
                    $("input[name='password']").attr('class', 'n-input input-danger');
                    return false;
                }

                //8자리 이상
                if (pw.length < 8 || pw.length > 20) {
                    $msgObj.html("<font>8~20자 이내로 입력해 주십시오.<font>");
                    $("input[name='password']").attr('class', 'n-input input-danger');
                    return false;
                }

                var isContainBlank = $("#password").val().indexOf(" ");
                if (isContainBlank != -1) {
                    $msgObj.html("<font>숫자 ,영문 대소문자, 특수문자 중 두가지 이상으로 조합해 주십시오.<font>");
                    $("input[name='password']").attr('class', 'n-input input-danger');
                    return false;
                }

                if(pob <= 1) {
                    $msgObj.html("<font>숫자 ,영문 대소문자, 특수문자 중 두가지 이상으로 조합해 주십시오.<font>");
                    $("input[name='password']").attr('class', 'n-input input-danger');
                    return false;
                }

                if(samePassword(pw)) {
                    $msgObj.html("<font>동일문자를 반복해서 4자 이상 사용할 수 없습니다.<font>");
                    $("input[name='password']").attr('class', 'n-input input-danger');
                    return false;
                }

                $msgObj.html("");
                $("input[name='password']").attr('class', 'n-input input');
                return true;
            }

            function samePassword(password) {
                var wordRegExp = new RegExp("(\\w)\\1\\1\\1");
                var specialWordRegExp = new RegExp("(\\([~!@#$%^&*()_+|<>?:{}])\\1\\1\\1");
                return wordRegExp.test(password) || specialWordRegExp.test(password);
            }

            function callCheckValidPassword(password) {
                var invalidPassword = false;

                mss.my.ajax.call(
                    {
                        async: false,
                        type: 'POST',
                        contentType: "application/json",
                        url: '/api/member/v2/join/validation/password',
                        data: JSON.stringify({
                            password: password.val()
                        }),
                        success: function (response) {
                            var success = response.success;

                            if (success) {
                                $("input[name='checkPassword']").val('1');
                                $("input[name='password']").attr('class', 'n-input input');
                                invalidPassword = true;
                                isPassPassword = true;
                            } else {
                                $("#passwordValidMessage")
                                    .removeClass('validation-passed')
                                    .html(response.message);
                                $("input[name='password']").attr('class', 'n-input input-danger');
                                invalidPassword = false;
                                isPassPassword = false;
                            }
                        }
                    }, false
                );

                return invalidPassword;
            }
        }

        function validateConfirmPassword() {
            $("input[name='checkConfirmPassword']").val('0');
            var password = $('#password').val();
            var confirmPassword = $('#confirmPassword').val().trim();
            $("#confirmPassword").val('');
            $("#confirmPassword").val(confirmPassword);

            if (password !== '' && password.length > 7) {
                if (confirmPassword === '') {
                    $("input[name='confirmPassword']").attr('class', 'n-input input-danger');
                    $("#passwordConfirmValidMessage").html('비밀번호 재확인은 필수정보입니다.');
                    isConfirmPassPassword = false;
                    joinBtnActive(false);
                } else if (password !== confirmPassword) {
                    $("input[name='confirmPassword']").attr('class', 'n-input input-danger');
                    $("#passwordConfirmValidMessage").html('비밀번호가 일치하지 않습니다.');
                    isConfirmPassPassword = false;
                    joinBtnActive(false);
                } else {
                    $("input[name='confirmPassword']").attr('class', 'n-input input');
                    $("#passwordConfirmValidMessage").html('');
                    $("input[name='checkConfirmPassword']").val('1');
                    isConfirmPassPassword = true;

                    if (isPassJoin()) {
                        joinBtnActive(true);
                    }
                }
            }
        }

        function validateEmail () {

            var trimedEmail = $("#email").val().trim();

            $("#emailDomainList").hide();

            if (!$("#email").val()) {
                $("#hLayeremail")
                    .removeClass('validation-passed')
                    .html('이메일은 필수정보입니다.');
                $("input[name='email']").attr('class', 'n-input input-danger');
                isPassEmail = false;
                joinBtnActive(false);
            } else {
                // 이메일 유효성 체크
                var email = $("#email").val();
                var isInvalidEmail = email.indexOf('\@') === -1 || email.indexOf('.') === -1;

                if (isInvalidEmail) {
                    $("input[name='checkEmail']").val("0");
                    $("input[name='email']").attr('class', 'n-input input-danger');
                    $("#hLayeremail")
                        .removeClass('validation-passed')
                        .html("이메일 주소가 올바르지 않습니다.");
                    isPassEmail = false;
                    joinBtnActive(false);
                    return false;
                } else {
                    var joinToken = sessionStorage.getItem('joinTokenSession') ? sessionStorage.getItem('joinTokenSession') : '';
                    mss.my.ajax.call(
                        {
                            async: false,
                            contentType: "application/json",
                            url: '/api/member/v2/join/email/exist',
                            method: 'POST',
                            data: JSON.stringify({
                                email: $("#email").val().trim(),
                                joinToken: joinToken
                            }),
                            success: function (response) {
                                $("#email").removeClass('valid');
                                if (response.success) {
                                    $("input[name='checkEmail']").val("1");
                                    $("input[name='email']").attr('class', 'n-input input');
                                    $("#hLayeremail").html(response.message);
                                    $('#hLayeremail').attr("class", "n-validation validation-passed");
                                    isPassEmail = true;

                                    if (isPassJoin()) {
                                        joinBtnActive(true);
                                    }
                                } else {
                                    $("input[name='checkEmail']").val("0");
                                    $("input[name='email']").attr('class', 'n-input input-danger');
                                    $("#hLayeremail")
                                        .removeClass('validation-passed')
                                        .html(response.message);
                                    isPassEmail = false;
                                    joinBtnActive(false);
                                }
                            }
                        }
                        , false
                    );
                }
            }
            $("#emailFromLayer").attr("class","n-form-layer");
            return false;
        }

        function validationRecommendMemberId() {
            var recommendMemberId = $('#recommendMemberId');
            if (recommendMemberId.val().length === 0) {
                $("#hLayerRecommendMemberId").html('');
                $("#recommendMemberId").attr('class', 'n-input');
                $("input[name='checkRecommendMemberId']").val("1");
                if (isPassJoin()) {
                    joinBtnActive(true);
                }
                return true;
            }
            $("input[name='checkRecommendMemberId']").val('0');
            if (recommendMemberId.val().length < 4) {
                $("#hLayerRecommendMemberId").html('아이디는 4자 이상이어야 합니다.');
                $("#recommendMemberId").attr('class', 'n-input input-danger');
                joinBtnActive(false);
                return false;
            }
            return ajaxValidationRecommendMemberId(recommendMemberId.val());
        }

        function checkRecommendMemberId(memberId) {
            if (memberId.length === 17 && recommendMemberIdLengthTrigger) {
                return false;
            } else {
                recommendMemberIdLengthTrigger = false;
                return true;
            }
        }

        function ajaxValidationRecommendMemberId(memberId) {
            if (!checkRecommendMemberId(memberId)) {
                return false;
            }
            var ajaxResultRecommendMemberId = false;
            mss.my.ajax.call(
                {
                    async: false,
                    type: 'POST',
                    dataType: 'json',
                    contentType: "application/json",
                    url: '/api/member/v2/join/verify/recommend-member-id',
                    data: JSON.stringify({
                        "recommendMemberId": memberId
                    }),
                    success: function (response) {
                        if (memberId.length === 17) {
                            recommendMemberIdLengthTrigger = true;
                        }

                        if (!response.success) {
                            recommendIdFailCallback();
                            return false;
                        }

                        $("input[name='checkRecommendMemberId']").val("1");
                        $("#recommendMemberId").attr('class', 'n-input');
                        $("#hLayerRecommendMemberId").attr('class', 'n-validation validation-passed');
                        $("#hLayerRecommendMemberId").html('추천인 아이디입니다. 가입 후 추천인과 신규회원 모두 적립금 1,000원을 드립니다.');

                        if (isPassJoin()) {
                            joinBtnActive(true);
                        }

                        ajaxResultRecommendMemberId = true;

                    }, error: function() {
                        recommendIdFailCallback();
                    }
                }, false
            );
            return ajaxResultRecommendMemberId;
        }

        function recommendIdFailCallback(resultMessage) {
            $("#hLayerRecommendMemberId").attr('class', 'n-validation');
            $("#hLayerRecommendMemberId").html(resultMessage ? resultMessage : '친구 초대 이벤트에 참여한  추천인 아이디가 아닙니다.<br>(참여방법 : 스토어 > 좌측슬라이드 ‘메뉴’ > 이벤트  )');

            $("#recommendMemberId").attr('class', 'n-input input-danger');

            $("input[name='checkRecommendMemberId']").val("0");
            $("#recommendMemberId").focus();
            var memberId = $('#recommendMemberId');
            if (memberId.length === 17) {
                recommendMemberIdLengthTrigger = true;
            }
            joinBtnActive(false);
        }

        function isPassJoin() {
            if ($("#recommendMemberId").val() && $("input[name='checkRecommendMemberId']").val() !== '1') {
                return false;
            }
            if (snsToken) {
                return isPassMemberId && isPassPassword && isConfirmPassPassword && isPassEmail;
            }
            return isPassMemberId && isPassPassword && isConfirmPassPassword && isPassEmail && isPassAgree;
        }

        function togglePassword(_id, _this) {
            var _password = $("#" + _id);
            if (_password.attr("type") == "password") {
                _password.attr("type", "text");
                _this.childNodes[1].className = 'ic-30-solid-hide ic-gray5';
            } else {
                _password.attr("type", "password");
                _this.childNodes[1].className = 'ic-30-solid-show ic-gray5';
            }
        }

        function moveScrollToTargetElement(elementId) {
            try {
                var offset = $('#' + elementId).offset();
                var winH = $(window).height();
                $('html, body').animate({scrollTop : (offset.top - winH/2) }, 100);
            } catch (e) {
                var bodyOffSet = $('body').offset();
                $('html, body').animate({scrollTop : (bodyOffSet.top - winH/2) }, 100);
            }
        }

        function validateAllDataMemberId() {
            memberIdLengthTrigger = false;
            validateMemberId();
        }

        function setAutoJoinFomDataIfSnsJoin() {
            var email = '';
            if (email) {
                $("#memberId").val(email.substring(0, email.indexOf('@')));
                validateAllDataMemberId();
                $("#email").val(email);
                validateEmail();
            }
        }

        var mouseFlag = true;
        $(document).ready(function () {

            function validateAllData(e) {
                validateAllDataMemberId();
                validatePassword();
                validateConfirmPassword();
                validateEmail();

                if (!isPassMemberId) {
                    moveScrollToTargetElement("memberId");
                    $("#memberId").focus();
                    return false;
                }
                if(!isPassPassword) {
                    moveScrollToTargetElement('password');
                    $("#password").focus();
                    return false;
                }
                if(!isConfirmPassPassword) {
                    moveScrollToTargetElement('confirmPassword');
                    $("#confirmPassword").focus();
                    return false;
                }
                if(!isPassEmail) {
                    moveScrollToTargetElement('email');
                    $("#email").focus();
                    return false;
                }

                if (!snsToken) {
                    if (!$('#agreeCheckbox').prop('checked')) {
                        alert('회원으로 가입을 원하실 경우,\n\n\[개인정보 수집 이용동의(필수)\]에 동의하셔야 합니다.');
                        isPassAgree = false;
                        return false;
                    }

                    if (!$('#useTermsCheckbox').prop('checked')) {
                        alert('회원으로 가입을 원하실 경우,\n\n\[무신사, 무신사스토어 이용약관(필수)\]에 동의하셔야 합니다.');
                        isPassAgree = false;
                        return false;
                    }

                    if (!$('#ageAgreeCheckbox').prop('checked')) {
                        alert('회원으로 가입을 원하실 경우,\n\n[만 14세 미만 가입 제한(필수)]에 동의하셔야 합니다.');
                        isPassAgree = false;
                        return false;
                    }
                }
                return validationRecommendMemberId();
            }

            initSessionStorage();
            setAutoJoinFomDataIfSnsJoin();

            var isSubmit = true;
            $("#joinBtnDiv").on('mousedown', function (e) {
                e.preventDefault();
            }).on('click', '#joinBtn', function (e) {

                e.preventDefault();

                if (isSubmit) {
                    if(!validateAllData()) {
                        return false;
                    }

                    joinBtnActive(false);
                    isSubmit = false;

                    $("#joinToken").val(sessionStorage.getItem('joinTokenSession'));
                    mss.my.ajax.call(
                        {
                            async: false,
                            type: 'POST',
                            url: '/api/member/v2/join/verify',
                            data : $("#joinForm").serialize(),
                            success: function (response) {
                                if (!response.success) {
                                    alert(response.usermessage);
                                    isSubmit = true;
                                    return false;
                                }

                                if (snsToken) {
                                    location.href = "/member/v2/join/complete?joinDataToken=" + response.joinDataToken;
                                    return true;
                                }

                                var connectUrl = "https://my.musinsa.com/member/v1/selfcertify/connect?redirectUrl=" + '/member/v2/join' + "&pageCode=" + '005' + "&selfCertifyFlag=" + 'NICE' + '&joinDataToken=' + response.joinDataToken;
                                window.open(connectUrl, "popupChk", "width=410, height=715");
                                isSubmit = true;
                            }
                        }, true
                    );
                }

                return false;
            });

            $('#emailFromLayer').mousedown(function(){
                mouseFlag = false;
            });
            $('#emailFromLayer').mouseup(function(){
                mouseFlag = true;
            });

            $("#joinHistoryBackBtn").click(function () {
                if ($('#partnerCode').val()) {
                    if(confirm('<신규 회원 15% 할인쿠폰 받으러 가기> 이벤트 링크를 통해 다시 접속하셔야 정상 발급됩니다.')) {
                        commonHistoryBack();
                    }
                    return;
                }
                commonHistoryBack();
            });

            $("#emailFromLayer ul").on("click","li button",function(e) {
                e.preventDefault();
                $("#email").val($(this).text());

                validateEmail();
                return false;
            });

            $('#memberId').on("keyup", validateMemberId);
            $('#memberId').on("blur", validateMemberId);
            $("#password").on("keyup", validatePassword);
            $("#confirmPassword").on("keyup", validateConfirmPassword);
            $("#email").on("keyup", function(e){
                if(!mouseFlag) {
                    return false;
                }
                validateEmail();
            });
            $("#email").on("keyup", function(e) {
                var value = e.currentTarget.value;
                if (value.length > 1 && value[value.length-1] == "@") {
                    $("#emailFromLayer ul > li button span").text(value);
                }

                if(value.indexOf("@") != -1){
                    e.target.parentElement.className = "n-form-layer is-active";
                    $("#emailDomainList").show();
                    $("#emailDomainList li").show();
                    var inputDomain = $("#email").val().split('@')[1];
                    var emailId = $("#email").val().split('@')[0];
                    $("#emailFromLayer ul > li button span").text(emailId + '@');
                    $("#emailDomainList li em").each(function () {
                        if ($(this).text().indexOf(inputDomain) == -1) {
                            $(this).parents('li').hide();
                        }
                    });
                } else {
                    e.target.parentElement.className = "n-form-layer";
                }
            });

            $("#memberId, #password, #confirmPassword, #recommendMemberId").focusin(function () {
                $("#emailFromLayer").removeClass('is-active');
            });

            $("#agreementDivArea label").click(function () {
                $("#emailFromLayer").removeClass('is-active');
            });

            $('#recommendMemberId').on("keyup", validationRecommendMemberId);

            $("#checkAll").on("change", function () {
                if ($('#checkAll').prop("checked")) {
                    $('.n-check').prop('checked', true);
                    isPassAgree = true;

                    if (isPassJoin()) {
                        joinBtnActive(true);
                    }
                } else {
                    $('.n-check').prop('checked', false);
                    isPassAgree = false;
                    joinBtnActive(false);
                }
            });


            $(".agree-item").click(function () {

                var isCheckAll = $("#checkAll").prop("checked");
                var requiredSelectedCnt = $('.required-agree-item:checked').length;
                var optionalSelectedCnt = $('.optional-agree-item:checked').length;
                var selectedCnt = requiredSelectedCnt + optionalSelectedCnt;
                var allAgreeCnt = 4;

                if (isCheckAll && selectedCnt != allAgreeCnt) {
                    $("#checkAll").prop("checked", false);
                }


                if (selectedCnt === allAgreeCnt) {
                    $("#checkAll").prop("checked", true);
                    isPassAgree = true;
                    if (isPassJoin()) {
                        joinBtnActive(true);
                    }
                }

                if (requiredSelectedCnt == 3) {
                    isPassAgree = true;
                    if (isPassJoin()) {
                        joinBtnActive(true);
                    }
                }

                if (requiredSelectedCnt != 3) {
                    joinBtnActive(false);
                    isPassAgree = false;
                }

            });

        });
        //document.ready end #######

        function initSessionStorage() {
            $("#memberId").val(sessionStorage.getItem("memberId"));
            $("#password").val(sessionStorage.getItem("password"));
            $("#confirmPassword").val(sessionStorage.getItem("confirmPassword"));
            $("#email").val(sessionStorage.getItem("email"));

            if (!$("#recommendMemberId").val()) {
                $("#recommendMemberId").val(sessionStorage.getItem("recommendMemberId"));
            }


            if (sessionStorage.getItem("checkRecommendMemberId") === '1') {
                $("input[name='recommendMemberId']").attr('class', 'n-input input');
                $("input[name='checkRecommendMemberId']").val('1')

            }

            var invalidMsg = sessionStorage.getItem("memberIdInvalidMsg");
            if (invalidMsg) {
                $("#hLayerid").html(invalidMsg);
                $("#memberId").attr('class', 'n-input input-danger');
            }

            invalidMsg = sessionStorage.getItem("passwordInvalidMsg");
            if (invalidMsg) {
                $("#passwordValidMessage").html(invalidMsg);
                $("input[name='password']").attr('class', 'n-input input-danger');
            }

            invalidMsg = sessionStorage.getItem("confirmPasswordInvalidMsg");
            if (sessionStorage.getItem("confirmPasswordInvalidMsg")) {
                $("#passwordConfirmValidMessage").html(invalidMsg);
                $("input[name='confirmPassword']").attr('class', 'n-input input');
            }

            invalidMsg = sessionStorage.getItem("emailInvalidMsg");
            if (sessionStorage.getItem("emailInvalidMsg")) {
                $("#hLayeremail")
                    .removeClass('validation-passed')
                    .html(invalidMsg);
                $("input[name='email']").attr('class', 'n-input input-danger');
            }

            invalidMsg = sessionStorage.getItem("recommendMemberIdInvalidMsg");
            if (sessionStorage.getItem("recommendMemberIdInvalidMsg")) {
                $("#hLayerRecommendMemberId").html(invalidMsg);
                $("#hLayerRecommendMemberId").css('display','block');
                $("input[name='recommendMemberId']").attr('class', 'n-input input-danger');
            }

            $("#checkAll").prop('checked', sessionStorage.getItem("checkAll") === 'true' ? true : false);
            $("#agreeCheckbox").prop('checked', sessionStorage.getItem("agreeCheckbox") === 'true' ? true : false);
            $("#useTermsCheckbox").prop('checked', sessionStorage.getItem("useTermsCheckbox") === 'true' ? true : false);
            $("#marketingReceiveAgreeYn").prop('checked', sessionStorage.getItem("marketingReceiveAgreeYn") === 'true' ? true : false);
            $("#ageAgreeCheckbox").prop('checked', sessionStorage.getItem("ageAgreeCheckbox") === 'true' ? true : false);

            isPassMemberId = sessionStorage.getItem("isPassMemberId") === 'true' ? true : false;
            memberIdResult = sessionStorage.getItem("memberIdResult") === 'true' ? true : false;
            isPassPassword = sessionStorage.getItem("isPassPassword") === 'true' ? true : false;
            isConfirmPassPassword = sessionStorage.getItem("isConfirmPassPassword") === 'true' ? true : false;
            isPassEmail = sessionStorage.getItem("isPassEmail") === 'true' ? true : false;
            isPassAgree = sessionStorage.getItem("isPassAgree") === 'true' ? true : false;

            if (isPassJoin()) {
                joinBtnActive(true);
            } else {
                joinBtnActive(false);
            }

            clearJoinFormSessionStorage();

            var joinTokenSession = sessionStorage.getItem('joinTokenSession');
            if (!joinTokenSession) {
                joinTokenSession = $('#joinToken').val();
            }
            sessionStorage.setItem('joinTokenSession', joinTokenSession);
        }

        /**
         * joinTokenSession을 제외하고 clear를 한다. 안그러면 새로고침 해도 값이 입력 값들이 계속 남아 있음
         */
        function clearJoinFormSessionStorage() {
            sessionStorage.removeItem('memberId');
            sessionStorage.removeItem('password');
            sessionStorage.removeItem('confirmPassword');
            sessionStorage.removeItem('email');
            sessionStorage.removeItem('recommendMemberId');
            sessionStorage.removeItem('memberIdInvalidMsg');
            sessionStorage.removeItem('passwordInvalidMsg');
            sessionStorage.removeItem('confirmPasswordInvalidMsg');
            sessionStorage.removeItem('emailInvalidMsg');
            sessionStorage.removeItem('recommendMemberIdInvalidMsg');
            sessionStorage.removeItem('checkRecommendMemberId');
            sessionStorage.removeItem('checkPhoneNumber');
            sessionStorage.removeItem('checkAll');
            sessionStorage.removeItem('agreeCheckbox');
            sessionStorage.removeItem('useTermsCheckbox');
            sessionStorage.removeItem('marketingReceiveAgreeYn');
            sessionStorage.removeItem('ageAgreeCheckbox');
            sessionStorage.removeItem('isPassMemberId');
            sessionStorage.removeItem('memberIdResult');
            sessionStorage.removeItem('isPassPassword');
            sessionStorage.removeItem('isConfirmPassPassword');
            sessionStorage.removeItem('isPassEmail');
            sessionStorage.removeItem('isPassAgree');
            sessionStorage.removeItem('joinBtn');
        }

        function setSessionStorage() {
            sessionStorage.setItem("memberId", $("#memberId").val());
            sessionStorage.setItem("password", $("#password").val());
            sessionStorage.setItem("confirmPassword", $("#confirmPassword").val());
            sessionStorage.setItem("email", $("#email").val());
            sessionStorage.setItem("recommendMemberId", $("#recommendMemberId").val());

            if ($("input[name='checkId']").val() === '0') {
                sessionStorage.setItem("memberIdInvalidMsg", $("#hLayerid").html());
            }

            if ($("input[name='checkPassword']").val() === '0') {
                sessionStorage.setItem("passwordInvalidMsg", $("#passwordValidMessage").html());
            }

            if ($("input[name='checkConfirmPassword']").val() === '0') {
                sessionStorage.setItem("confirmPasswordInvalidMsg", $("#passwordConfirmValidMessage").html());
            }

            if ($("input[name='checkEmail']").val() === '0') {
                sessionStorage.setItem("emailInvalidMsg", $("#hLayeremail").html());
            }

            if ($("input[name='checkRecommendMemberId']").val() === '0') {
                sessionStorage.setItem("recommendMemberIdInvalidMsg", $("#hLayerRecommendMemberId").html());
            }

            sessionStorage.setItem("checkRecommendMemberId", $("input[name='checkRecommendMemberId']").val());
            sessionStorage.setItem("checkPhoneNumber", $("input[name='checkPhoneNumber']").val());

            sessionStorage.setItem("checkAll", $("#checkAll").prop('checked'));
            sessionStorage.setItem("agreeCheckbox", $("#agreeCheckbox").prop('checked'));
            sessionStorage.setItem("useTermsCheckbox", $("#useTermsCheckbox").prop('checked'));
            sessionStorage.setItem("marketingReceiveAgreeYn", $("#marketingReceiveAgreeYn").prop('checked'));
            sessionStorage.setItem("ageAgreeCheckbox", $("#ageAgreeCheckbox").prop('checked'));

            sessionStorage.setItem("isPassMemberId", isPassMemberId);
            sessionStorage.setItem("memberIdResult", memberIdResult);
            sessionStorage.setItem("isPassPassword", isPassPassword);
            sessionStorage.setItem("isConfirmPassPassword", isConfirmPassPassword);
            sessionStorage.setItem("isPassEmail", isPassEmail);
            sessionStorage.setItem("isPassAgree", isPassAgree);

            sessionStorage.setItem("joinBtn", $("#joinBtn").prop('disabled'));

        }

        function checkIdValue(id) {
            if ("" === id) {
                return false;
            }
            return getTypeCheck(id, "abcdefghijklmnopqrstuvwxyz1234567890_");

            /*타입비교 (비교문자 , 비교형식 ; ex: getTypeCheck(string , "1234567890") ) */
            function getTypeCheck(s, spc) {
                for (var i = 0; i < s.length; i++) {
                    if (spc.indexOf(s.substring(i, i + 1)) < 0) {
                        return false;
                    }
                }
                return true;
            }
        }

        function privacyAgreeUsagePopBtnClickHandler() {
            var popSizeOnMobile = '';

            if(false) {
                popSizeOnMobile = 'width=100%,height=100%,';
            }

            setSessionStorage();
            window.open('/member/v1/join/agreement/privacy-usage', '_self', popSizeOnMobile + 'statusbar=no,scrollbars=yes,toolbar=no');
        }

        function serviceAgreementPopBtnClickHandler() {
            var popSizeOnMobile = '';

            if(false) {
                popSizeOnMobile = 'width=100%,height=100%,';
            }

            setSessionStorage();
            window.open('/member/v1/join/agreement/service', '_self', popSizeOnMobile + 'statusbar=no,scrollbars=yes,toolbar=no');
        }

        function marketingAgreementPopBtnClickHandler() {
            var popSizeOnMobile = '';

            if(false) {
                popSizeOnMobile = 'width=100%,height=100%,';
            }

            setSessionStorage();
            window.open('/member/v1/join/agreement/marketing', '_self', popSizeOnMobile + 'statusbar=no,scrollbars=yes,toolbar=no');
        }

        function moveBuyNonMember() {
            var referer = "https://store.musinsa.com/app/order/order_form";
            location.href = '/login/v1/login?referer=' + encodeURIComponent(referer);
        }
    </script>


</html>