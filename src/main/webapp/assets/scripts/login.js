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
        $(".logout-btn").show();
        $(".login-btn").hide();
        $(".login-user").show();
        $(".login-user").text("Welcome: " + username);
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

                    // borrow book
                    var redoUrl = $.cookie('redo_url');
                    if(redoUrl.indexOf('borrowBook') > 0){
                        $('#borrowBtn').click();
                    }
                    $.cookie('redo_url', '');
                }
            })
        }

    },

    remember: function (username, password) {
        if ($("#remember").prop("checked") == true) {
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
        $(".login-btn").show();

        $(".login-user").hide();
        $("#deleteBtn").hide();

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
            $("#remember").attr("checked", true);
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
        $.ajax(
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

userLogin.init();