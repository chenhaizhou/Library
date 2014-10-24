var userLogin = {

    operateBtn: function(username) {
        if (username === "admin") {
            $("#borrowBtn").hide();
            $("#deleteBtn").show();
            $("#editBtn").show();
        } else {
            var remainNumber = $("#edit-totalnumber").val() - $("#edit-borrowednumber").val();
            if (remainNumber > 0) {
                $("#borrowBtn").show();
            } else {
                $('#borrowBtn').attr('disabled', 'true');
            }
            $("#deleteBtn").hide();
            $("#editBtn").hide();
        }
    },

    login: function (username) {
        if(username === 'admin'){
            $("#addBook-li").show();
        }else{
            $('#myBorrowed').show();
        }
        $(".signUp-btn").hide();
        $(".logout-btn").show();
        $(".login-btn").hide();
        $(".login-user").show();
        $(".login-user").html('Welcome: <em id="username">' + username + '</em>');
        $("#inputUsername").val(username);
        userLogin.operateBtn(username);
    },

    validate : function(username,password){
        if ( username == "" ){
            $(".error-msg").text("Please enter the username.");
            return false;
        }else if ( password == "" ){
            $(".error-msg").text("Please enter the password.");
            return false;
        }else{
            return true;
        }
    },

    submit: function () {

        var loginData = {
            username: $("#inputUsername").val(),
            password: $("#inputPassword").val()
        };

        if(userLogin.validate(loginData.username,loginData.password)){
            $.ajax({
                type: "post",
                url: basePath + "/user/loginSubmit.do",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(loginData),
                dataType: "json",
                success: function (result) {
                    if (result.result === 'UserNameError') {
                        $(".error-msg").text("The username is not exist.");
                    } else if (result.result === 'UserLoginError') {
                        $(".error-msg").text("The password is wrong.");
                    } else if (result.result === 'UserLoginSuccess') {
                        $(".login-modal").modal("hide");

                        userLogin.login(loginData.username);
                        userLogin.remember(loginData.username, loginData.password);

                    }

                    var redoUrl = $.cookie('redo_url') || '';
                    if(redoUrl.indexOf('borrowBook') > 0){
                        $('#borrowBtn').click();
                    }
                    $.cookie('redo_url', '');

                    window.location.reload();
                }
            })
        }

    },

    remember: function (username, password) {
        if ($("#remember").prop("checked")) {
            $.cookie('username', username, { expires: 7 });
        } else {
            $.removeCookie('username');
        }
    },

    setBorrowStatus:function(){
        var remainNumber = $("#edit-totalnumber").val() - $("#edit-borrowednumber").val();

        if (remainNumber > 0) {
            $("#borrowBtn").show();
        } else {
            $("#borrowBtn").hide();
        }
    },

    logout: function () {
        $("#addBook-li,#myBorrowed").hide();
        $(".logout-btn").hide();
        $(".login-user").hide();
        $("#deleteBtn").hide();
        $(".login-btn").show();
        $(".signUp-btn").show();

        $("#editBtn").hide();
        userLogin.setBorrowStatus();

        $.ajax(
            {
                type: "GET",
                url: basePath + "/user/logout.do",
                contentType: "application/json; charset=utf-8",
                success: function () {

                },
                error: function () {
                    alert("logout error");
                }
            }
        );

        $("#inputUsername").val('');
    },

    checkCookie: function () {
        if (typeof $.cookie('username') == "string") {
            $("#inputUsername").val($.cookie('username'));
            $("#remember").prop("checked", true);
        } else {
            $("#inputUsername").val("");
        }
    },

    checkLoginStatus: function (loginStatus) {
        if (loginStatus) {
            userLogin.login();
        } else {
            userLogin.logout();
        }
    },

    addEvent: function () {

        $(".login-submit").click(function () {
            userLogin.submit();
        });

        $(".login-btn").click(function () {
            userLogin.checkCookie();
            $('.login-modal').on('shown.bs.modal', function () {
                $("#inputUsername").focus();
            });
            $("#inputPassword").val("");
            $(".error-msg").text("");
        });

        $("#inputUsername,#inputPassword").keypress(function (event) {
            if (event.keyCode == 13) {
                $(".login-submit").click();
            }
        });

        $(".logout-btn").click(function () {
            userLogin.logout();
        });

    },


    checkUserInfo: function () {
        return $.ajax(
            {
                type: "POST",
                url: basePath + "/user/getUserInfo.do",
                contentType: "application/json; charset=utf-8",
                data: "",
                dataType: 'json',
                success: function (result) {
                    var name = result.userName;
                    if(name === ''){
                        userLogin.logout();
                    }else{

                        userLogin.login(name);
                    }

                },
                error: function () {
                    alert("check userInfo error");
                }
            }
        );
    },

    init: function () {
        userLogin.addEvent();
        userLogin.checkCookie();

        userLogin.checkUserInfo();
    }
}

var userSignUp = {

    addEvent: function () {

        $('#signUp-modal').on('show.bs.modal', function () {
            $("#signUp-form").find("input").val("");
            $('#sign-successTips').addClass('hide');
            $('#signUp-form div').removeClass('has-error has-success').find('em.error').remove();
            userSignUp.validateForm("signUp-form");
        });

    },

    validateForm: function (formId) {

        $("#" + formId).validate({
            rules: {
                'sign-username': "required",
                'sign-password': {
                    required: true,
                    minlength: 3
                },
                'sign-confirm-password': {
                    required: true,
                    equalTo: "#sign-inputPassword"
                }
            },
            messages: {
                'sign-username': "This username is required.",
                'sign-password': {
                    required: "This password is required",
                    minlength: "At least 3 characters required!"
                },
                'sign-confirm-password': {
                    required: "This confirm password is required.",
                    equalTo: "The password should be the same with the first one you entered."
                }
            },
            errorElement: "em",
            onfocusout: function (element) {
                $(element).valid();
            },
            success: function (element) {
                $(element).parent().removeClass('has-error').addClass('has-success');
            },
            highlight: function (element) {
                $(element).parent().removeClass('has-success').addClass('has-error');
            },
            submitHandler: function () {
                userSignUp.submit();
            }
        })

    },

    submit : function () {

        var signUpData = {
            username : $("#sign-inputUsername").val(),
            name : $("#sign-inputName").val() != "" ? $("#sign-inputName").val() : $("#sign-inputUsername").val(),
            password : $("#sign-inputPassword").val()
        };

        $.ajax({
            type : "post",
            url : basePath + "/user/signUpSubmit.do",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(signUpData),
            dataType : "text",
            success : function(result){
                if (result == 'SignUpError') {
                    $(".error-msg").text("The username is exist, please try another one.");
                } else if (result === 'SignUpSuccess') {
                    $(".error-msg").empty();
                    $('#sign-successTips').removeClass('hide').text('sign up successfully.');
                    setTimeout(function(){
                        window.location.reload();
                    }, 1000);
                }
            }
        })

    },

    init: function () {

        userSignUp.addEvent();

    }

};

$(function(){
    userLogin.init();
    userSignUp.init();
});