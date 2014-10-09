
var userLogin = {

    login : function(username){
        $("#addBook-btn").show();
        $(".logout-btn").show();
        $(".login-btn").hide();
        $(".login-user").show();
        $(".login-user").text("Welcome: "+username);
    },

    submit : function(){
        var loginData = {
            username: $("#inputUsername").val(),
            password: $("#inputPassword").val()
        };

        $.ajax({
            type: "post",
            url: "/Library/user/loginSubmit.do",
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(loginData),
            dataType:"json",
            success: function (result) {
                if (result.result === 'UserNameError') {
                    $(".error-msg").text("The username is wrong.");
                } else if (result.result ==='UserLoginError') {
                    $(".error-msg").text("The password is wrong.");
                } else if(result.result ==='UserLoginSuccess') {
                    $(".login-modal").modal("hide");

                    userLogin.login(loginData.username);
                    userLogin.remember(loginData.username,loginData.password);

                }
            }
        })
    },

    remember : function(username,password){
        if($("#remember").prop("checked") == true ){
            $.cookie('username', username, { expires: 7 });
        }else{
            $.removeCookie('username');
        }
    },

    logout : function(){
        $("#addBook-btn").hide();
        $(".logout-btn").hide();
        $(".login-btn").show();
        $(".login-user").hide();
    },

    checkCookie : function(){
        if(typeof $.cookie('username') == "string" ){
            $("#inputUsername").val($.cookie('username'));
            $("#remember").attr("checked",true);
        }else{
            $("#inputUsername").val("");
        }
    },

    checkLoginStatus : function(loginStatus){
        if(loginStatus){
            userLogin.login();
        }else{
            userLogin.logout();
        }
    },

    addEvent : function(){

        $(".login-submit").click(function () {
            userLogin.submit();
        });

        $(".login-btn").click(function(){
            userLogin.checkCookie();
            $("#inputPassword").val("");
            $(".error-msg").text("");
        });

        $("#inputUsername,#inputPassword").keypress(function(event){
            if(event.keyCode == 13){
                $(".login-submit").click();
            }
        });

        $(".logout-btn").click(function () {
            userLogin.logout();
        });

    },

    init : function(){
        userLogin.addEvent();
        userLogin.checkCookie();
        userLogin.checkLoginStatus(false);
    }

}

userLogin.init();


