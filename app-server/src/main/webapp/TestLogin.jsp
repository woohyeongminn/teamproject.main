<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no,viewport-fit=cover">
    <meta name="referrer" content="no-referrer-when-downgrade" />

        
        <!-- Google Tag Manager -->
        <script>
            var dataLayer = dataLayer || [];
            window.addEventListener("load", function() {
                var memberGA = null;
                if (memberGA != null && memberGA.dimension1 != null) {
                    dataLayer.push(memberGA);
                }

                (function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
                        new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
                    j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
                    'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
                })(window,document,'script','dataLayer','GTM-TXDSFSF');
            }, false);
        </script>
        <!-- End Google Tag Manager -->

    
        
        <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
        <script>
            (function (){
                Kakao.init("a432f563a20ec43eb463878d3c627079");
            })();
        </script>
    <link rel="stylesheet" href="https://static.msscdn.net/ui/build/m/css/common.css?2021104221413">
    <link rel="stylesheet" href="https://static.msscdn.net/ui/build/m/css/login.css?2021104221413">


        

    <script src="https://static.msscdn.net/static/member/js/jquery-3.4.1.min.js?2021104221413" type="text/javascript"></script>
    

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
    <script src="https://static.msscdn.net/skin/m_musinsa/js/appinterface.js?2021104221413" type="text/javascript"></script>
    <script src="https://static.msscdn.net/static/member/js/appinterface-member.js?2021104221413" type="text/javascript"></script>

    <script src="https://static.msscdn.net/static/member/js/constant.js?2021104221413" type="text/javascript"></script>
    <script src="https://static.msscdn.net/static/member/js/ui/config.js?2021104221413" type="text/javascript"></script>
    <script src="https://static.msscdn.net/static/member/js/ui.js?2021104221413" type="text/javascript"></script>
    <script src="https://static.msscdn.net/ui/musinsa/resources/mw/js/join.js" type="text/javascript"></script>
    <script>
        // mss.ui.config 내의 기본 속성값을 대치하기 위한 Object
        // 서버쪽에서 출력해 줘야 합니다.

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


        String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
        String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
        Number.prototype.zf = function(len){return this.toString().zf(len);};

    </script>

    <script src="https://static.msscdn.net/static/member/js/ajax.js?2021104221413" type="text/javascript"></script>

    </head>

    <body>

        <div id="commonLayoutHeader"></div>
        <!-- Member -->
        <div class="n-member-area form-area">

            <form class="formBox" name="joinForm" id="joinForm" method="post" novalidate>
                <input type="hidden" name="checkId" value="0">
                <input type="hidden" name="checkPassword" value="0">
                <input type="hidden" name="checkConfirmPassword" value="0">
                <input type="hidden" name="checkEmail" value="0">
                <input type="hidden" name="checkRecommendMemberId" value="0">
                <input type="hidden" name="checkPhoneNumber" value="0">
                <input type="hidden" id="joinToken" name="joinToken" value="27c56cdb0ec836a01edb4ada34bcef7b9d65391a"/>
                <input type="hidden" id="referer" name="referer" value="http://my.musinsa.com/"/>
                <input type="hidden" name="eventToken" value=""/>
                <input type="hidden" name="snsToken" value=""/>
                <input type="hidden" id="partnerCode" name="partnerCode" value=""/>
                <input type="hidden" name="eventPage" value=""/>
                <input type="hidden" name="eventCode" value=""/>
                <input type="hidden" name="returnUrl" value=""/>

                <!-- Header -->
                <header class="member-header">
                        <p class="text-kakao font-mss">회원가입</p>
                </header>
                <!-- //Header -->

                <!-- Join Form -->
                <div class="join-form">
                    <div class="n-form-set">
                        <label for="memberId" class="n-form-label">닉네임 <span class="essential">필수 입력</span></label>
                        <input type="text" class="n-input input" id="memberId" name="memberId" placeholder="아이디 입력(2~10자)" minlength="5" maxlength="11">
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
                $("input[name='checkId']").val('1');
                $memberId.attr('class', 'n-input input');
                $('#hLayerid').attr("class", "n-validation validation-passed");
                $("#hLayerid").html(response.message);

                isPassMemberId = true;
                if (isPassJoin()) {
                    joinBtnActive(true);
                }

                return true;
            } else {
                $("input[name='checkId']").val('0');
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

    </script>
</html>