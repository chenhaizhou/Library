$(function(){
    $.ajaxSetup({
        statusCode: {
            499: function(){
                $(".login-btn").click();
            }
        }
    });

    $("#myBorrowed a").on("click",function(){

        window.location.href = basePath + "/borrowStatus.do";
    })
});

