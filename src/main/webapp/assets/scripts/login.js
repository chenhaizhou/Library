$(function() {
    $(".login-submit").click(function () {
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
                    $(".error-msg").text("The username is not exist.");
                } else if (result.result ==='UserLoginError') {
                    $(".error-msg").text("The username or password is wrong, please input again.");
                } else if(result.result ==='UserLoginSuccess') {
                    $(".login-modal").modal("hide");
                    $("#addBook-btn").css("display","block");
                    $(".login-btn").hide();
                    $(".login-user").show();
                    $(".login-user").text(loginData.username);

                    //remember me check
                    if(document.getElementById("remember").checked == true ){
                        $.cookie('username', loginData.username);
                        $.cookie('password', loginData.password);
                    }else{
                        $.removeCookie('username');
                        $.removeCookie('password');
                        $("#inputUsername").val("");
                        $("#inputPassword").val("");
                    }
                }
            }

        })
    });

    //checkCookie
    function checkCookie(){
        if($.cookie('username') != null ){
            $("#inputUsername").val($.cookie('username'));
            $("#inputPassword").val($.cookie('password'));
        }
    }

    //init
    checkCookie();

})
