$(function() {
    $(".login-submit").click(function () {
        var loginData = {
            username: $("#inputUsername").val(),
            password: $("#inputPassword").val()
        };
        $.ajax({
            type: "post",
            url: "/Library/loginSubmit.do",
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(loginData),
            dataType : 'json',
            success : function(result) {
                if (result == '1') {
                    $(".error-msg").text("The username is not exist.");
                } else if (result =='2') {
                    $(".error-msg").text("The username or password is wrong, please input again.");
                } else {
                    $(".login-modal").modal("hide");
                    $(".addBook-btn").show();
                }
            },
            error: function () {
                alert("Oops, something is wrong, we are working on it");
            }
        })
    })
})
