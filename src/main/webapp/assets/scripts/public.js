$(function(){
    $.ajaxSetup({
        statusCode: {
            499: function(){
                $(".login-btn").click();
            }
        }
    });
});
