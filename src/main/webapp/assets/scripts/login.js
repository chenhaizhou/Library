
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
                $(".error-msg").text("The username is wrong.");
            } else if (result.result ==='UserLoginError') {
                $(".error-msg").text("The password is wrong.");
            } else if(result.result ==='UserLoginSuccess') {
                $(".login-modal").modal("hide");

                login(loginData.username);
                rememberMe(loginData.username,loginData.password);

            }
        }
    })
});

$(".login-btn").click(function(){
    checkCookie();
    $("#inputPassword").val("");
    $(".error-msg").text("");
});

$("#inputUsername,#inputPassword").keypress(function(event){
    if(event.keyCode == 13){
        $(".login-submit").click();
    }
});

$(".logout-btn").click(function () {
    logout();
});

function login(username){
    $("#addBook-btn").show();
    $(".logout-btn").show();
    $(".login-btn").hide();
    $(".login-user").show();
    $(".login-user").text("Welcome: "+username);
}

function logout(){
    $("#addBook-btn").hide();
    $(".logout-btn").hide();
    $(".login-btn").show();
    $(".login-user").hide();
}

function rememberMe(username,password){
    if($("#remember").prop("checked") == true ){
        $.cookie('username', username, { expires: 7 });
    }else{
        $.removeCookie('username');
    }
}

function checkCookie(){
    if($.cookie('username') != undefined ){
        $("#inputUsername").val($.cookie('username'));
        $("#remember").attr("checked",true);
    }else{
        $("#inputUsername").val("");
    }
}

function checkLoginStatus(loginStatus){
    if(loginStatus){
        login();
    }else{
        logout();
    }
}

checkCookie();
checkLoginStatus(false);



