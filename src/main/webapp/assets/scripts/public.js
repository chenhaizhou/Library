$(function(){
    $.ajaxSetup({
        statusCode: {
            499: function(){
                window.location.reload();
            }
        }
    });
});
