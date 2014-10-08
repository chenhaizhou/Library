
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
                $(".error-msg").text("The password is wrong, please input again.");
            } else if(result.result ==='UserLoginSuccess') {
                $(".login-modal").modal("hide");

                showLoginInfo(loginData.username);
                rememberMe(loginData.username,loginData.password);

            }
        }

    })
});

$("#inputUsername,#inputPassword").keypress(function(event){
    if(event.keyCode == 13){
        $(".login-submit").click();
    }
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
    if($("#remember").prop("checked") == true ){
        $.cookie('username', username, { expires: 7 });
        $.cookie('password', password, { expires: 7 });
    }else{
        $.removeCookie('username');
        $.removeCookie('password');
        $("#inputUsername").val("");
        $("#inputPassword").val("");
    }
}

// checkCookie
function checkCookie(){
    if($.cookie('username') != undefined ){
        $("#inputUsername").val($.cookie('username'));
        $("#inputPassword").val($.cookie('password'));
        $("#remember").attr("checked",true);
    }
}

// init
checkCookie();


