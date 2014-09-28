function login(){
    $.ajax({
        type : "post",
        url : "/Library/user/login.do",
        data : {
            'username' : $("#inputUsername").text(),
            'password' : $("#inputPassword").text()
        },
        async: false,
        success : function(data){
            if(data == false){
                $("#error-msg").text("The username or password is wrong, please input again.");
            }else{
                $('#login-modal').modal('hide');
            }
        },
        error : function (){
            alert("Oops, something is wrong, we are working on it");
        }

    })
}
