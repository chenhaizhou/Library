
// login
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

                showLoginInfo(loginData.username);
                rememberMe(loginData.username,loginData.password);

            }
        }

    })
});

// log out
$(".logout-btn").click(function () {
    showLogoutInfo();
});

// showLoginInfo
function showLoginInfo(username){
    $("#addBook-btn").css("display","block");
    $(".logout-btn").css("display","block");
    $(".login-btn").hide();
    $(".login-user").show();
    $(".login-user").text("Welcome: "+username);
}

// showLogoutInfo
function showLogoutInfo(){
    $("#addBook-btn").css("display","none");
    $(".logout-btn").css("display","none");
    $(".login-btn").show();
    $(".login-user").hide();
}

// rememberMe
function rememberMe(username,password){
    if(document.getElementById("remember").checked == true ){
        $.cookie('username', username);
        $.cookie('password', password);
    }else{
        $.removeCookie('username');
        $.removeCookie('password');
        $("#inputUsername").val("");
        $("#inputPassword").val("");
    }
}

// checkCookie
function checkCookie(){
    if($.cookie('username') != null ){
        $("#inputUsername").val($.cookie('username'));
        $("#inputPassword").val($.cookie('password'));
    }
}

// init
checkCookie();
