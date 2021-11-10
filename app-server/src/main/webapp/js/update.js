 $(document).ready(function () {

        var isKeyUp = false;
        var timeoutId;

        var letterRegExp = new RegExp("[a-z]");
        var capsLockRegExp = new RegExp("[A-Z]");
        var numberRegExp = new RegExp("[0-9]");
        var symbolRegExp = new RegExp("\\W");
        

        $("#change-password-btn").click(function (e) {
            e.preventDefault();
            $("#password-area").css("display","none");
            $("#change-password-area").css("display", "");
        });

        $("#change-password-cancel-btn").click(function (e) {
            e.preventDefault();
            $("#password").val('');
            $("#newPassword").val('');
            $("#confirmPassword").val('');
            $("#password-area").css("display","");
            $("#change-password-area").css("display", "none");
            $("#new-password-invalid").css("display","none");
            $("#valid-newPassword").css("display","none");
            $("#password-invalid").css("display","none");
            $("#valid-password").css("display","none");
            $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent disabled');
            $("#change-password-finish-btn").prop('disabled', true);
            $("#newPassword").attr('class', 'n-input');
        });

        $("#password").keyup(function (e) {
            e.preventDefault();
            var password = $("#password");
            var newPassword = $("#newPassword");
            var confirmPassword = $("#confirmPassword");
            var displayValue = $("#new-password-invalid").css("display");
            var passwordInvalidDisplayValue = $('#password-invalid').css("display");

            if (password.val().length >= 4 &&
                newPassword.val().length >= 8 &&
                confirmPassword.val().length >= 8 &&
                displayValue == 'none' &&
                passwordInvalidDisplayValue == 'none'
            ) {
                $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent');
                $("#change-password-finish-btn").prop('disabled', false) ;
            } else {
                $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent disabled');
                $("#change-password-finish-btn").prop('disabled', true) ;
            }

            value = $(this).val();
            var passwordInvalid = $('#password-invalid');
            var newPasswordInvalid = $("#new-password-invalid");

            if (!value) {
                passwordInvalid.css('display', '');
                passwordInvalid.text('');
                return false;
            }

            if (password.val().length < 4) {
                passwordInvalid.css('display', '');
                $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent disabled');
                $("#change-password-finish-btn").prop('disabled', true) ;
                passwordInvalid.text("4자리 이상 입력해 주십시오.");
                return false;
            }

            passwordInvalid.css('display', 'none');
            if (passwordInvalid.css("display") === 'none' && newPasswordInvalid.css("display") === 'none' && confirmPassword.val().length >= 8) {
                $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent');
                $("#change-password-finish-btn").prop('disabled', false) ;
            }
        });

        $("#newPassword").keyup(function (e) {
            e.preventDefault();
            var newPassword = $("#newPassword");

            if(newPassword.val() == '' || newPassword.val().length < 8) {
                newPassword.attr('class', 'n-input input-danger');
                $("#valid-newPassword").css("display","none");
                $("#new-password-invalid").css("display","");
                $("#new-password-invalid").text("8자리 이상 입력해 주십시오.");
                $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent disabled');
                $("#change-password-finish-btn").prop('disabled', true) ;
                return false;
            } else if(checkFourConsecutiveChar(newPassword.val())) {
                newPassword.attr('class', 'n-input input-danger');
                $("#valid-newPassword").css("display","none");
                $("#new-password-invalid").css("display","");
                $("#new-password-invalid").text("4개 이상 연속으로 동일한 문자는 사용하실 수 없습니다.");
                $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent disabled');
                $("#change-password-finish-btn").prop('disabled', true) ;
                return false;
            } else if(!isValidPassword(newPassword.val())) {
                newPassword.attr('class', 'n-input input-danger');
                $("#valid-newPassword").css("display","none");
                $("#new-password-invalid").css("display","");
                $("#new-password-invalid").text("숫자 ,영문 대소문자, 특수문자 중 두가지 이상으로 조합해 주십시오.");
                $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent disabled');
                $("#change-password-finish-btn").prop('disabled', true) ;
                return false;
            } else {
                var points = getPassordRulePoint(newPassword.val());
                newPassword.attr('class', 'n-input');
                $("#new-password-invalid").css("display","none");
                $("#valid-newPassword").css("display","");
                if( points >= 24 ) {
                    $("#valid-newPassword").text("사용 가능한 비밀번호입니다. 안전도 : 높음");
                } else if( points >= 16 ) {
                    $("#valid-newPassword").text("사용 가능한 비밀번호입니다. 안전도 : 중간");
                } else if( points >= 12 ) {
                    $("#valid-newPassword").text("사용 가능한 비밀번호입니다. 안전도 : 낮음");
                } else {
                    $("#valid-newPassword").text("사용 가능한 비밀번호입니다. 안전도 : 매우낮음");
                }

                var confirmPassword = $("#confirmPassword");
                var password = $("#password");
                if (password.val().length >= 4 && confirmPassword.val().length >= 8) {
                    $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent');
                    $("#change-password-finish-btn").prop('disabled', false) ;
                } else {
                    $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent disabled');
                    $("#change-password-finish-btn").prop('disabled', true) ;
                }
            }
            return true;
        });

        $("#confirmPassword").keyup(function (e) {
            e.preventDefault();
            var password = $("#password");
            var newPassword = $("#newPassword");
            var confirmPassword = $("#confirmPassword");
            var displayValue = $("#new-password-invalid").css("display");
            var passwordInvalidDisplayValue = $('#password-invalid').css("display");

            if (password.val().length >= 4 &&
                newPassword.val().length >= 8 &&
                confirmPassword.val().length >= 8 &&
                displayValue == 'none' &&
                passwordInvalidDisplayValue == 'none'
            ) {
                $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent');
                $("#change-password-finish-btn").prop('disabled', false) ;
            } else {
                $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent disabled');
                $("#change-password-finish-btn").prop('disabled', true) ;
            }
        });

        $("#change-password-finish-btn").click(function (e) {
            e.preventDefault();

            var password = $("#password").val();
            var newPassword = $("#newPassword").val();
            var confirmPassword = $("#confirmPassword").val();

            if(password === '') {
                alert('현재 비밀번호를 입력해주세요.');
                return false;
            }

            if (password.length < 4) {
                alert('비밀번호 4자 이상이여야합니다.');
                return false;
            }

            if(newPassword !== confirmPassword) {
                alert('신규 비밀번호와 재입력 비밀번호가 같지 않습니다.');
                $("#confirmPassword").val('');
                $("#newPassword").val('');
                $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent disabled');
                $("#change-password-finish-btn").prop('disabled', true);
                $("#new-password-invalid").text('');
                $("#valid-newPassword").text('');
                return false;
            }

            if (password === newPassword) {
                alert('현재 비밀번호와 신규 비밀번호가 동일합니다.');
                $("#newPassword").val('');
                $("#confirmPassword").val('');
                $("#change-password-finish-btn").attr('class', 'n-btn btn-sm btn-accent disabled');
                $("#change-password-finish-btn").prop('disabled', true);
                $("#new-password-invalid").text('');
                $("#valid-newPassword").text('');
                return false;
            }

            if(confirm('비밀번호를 변경하시겠습니까?')) {
                mss.my.ajax.call(
                    {
                        method: "PUT",
                        url: "/api/member/v1/change-password",
                        data : {
                            "password" : password,
                            "newPassword" : newPassword,
                            "confirmPassword" : confirmPassword
                        },
                        success : function (responseData) {
                            alert(responseData.message);
                            if (responseData.success) {
                                $("#password-area").css("display","");
                                $("#change-password-area").css("display", "none");
                            }

                            if (responseData.code == 1500) {
                                document.location.href = "/login/v1/login?referer=" + encodeURIComponent(location.pathname);
                                return false;
                            }

                            $("#password").val('');
                            $("#newPassword").val('');
                            $("#confirmPassword").val('');
                            $("#new-password-invalid").css("display","none");
                            $("#valid-newPassword").css("display","none");
                            $("#password-invalid").css("display","none");
                            $("#valid-password").css("display","none");

                        },
                        fail : function (data) {
                            var responseData = data.responseJSON;
                            alert(responseData.message);
                        }
                    },
                    true
                )
            }
        });

        function checkFourConsecutiveChar(password) {
            for (var i = 0; i < password.length - 3; i++) {
                if (password.charAt(i) == password.charAt(i + 1) &&
                    password.charAt(i + 1) == password.charAt(i + 2) &&
                    password.charAt(i + 2) == password.charAt(i + 3)) {
                    return true;
                }
            }
            return false;
        }

        function isValidPassword(password) {
            var violationCnt = 0;
            if(!letterRegExp.test(password)) {
                violationCnt ++;
            }

            if(!capsLockRegExp.test(password)) {
                violationCnt ++;
            }

            if(!numberRegExp.test(password)) {
                violationCnt ++;
            }

            if(!symbolRegExp.test(password)) {
                violationCnt ++;
            }

            if(violationCnt > 2) {
                return false;
            } else {
                return true;
            }
        }

        function getPassordRulePoint(password) {
            var point = 0;
            if(letterRegExp.test(password)) {
                point = point + 4;
            }

            if(capsLockRegExp.test(password)) {
                point = point + 4;
            }

            if(numberRegExp.test(password)) {
                point = point + 4;
            }

            if(symbolRegExp.test(password)) {
                point = point + 4;
            }

            return point;
        }
    
      function loadSnsState() {}  
    });
    function getMemberRefundAccount() {}