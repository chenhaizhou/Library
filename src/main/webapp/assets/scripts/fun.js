$(function () {

    $("#addBookForm").validate({
        rules: {
            name: "required",
            author: "required",
            isbn: "required",
            publisher: "required",
            cover: "required"
        },
        messages: {
            name: "必填项",
            author: "必填项",
            isbn: "必填项",
            publisher: "必填项",
            cover: "必填项"
        },
        onfocusout: function(element) { $(element).valid(); },
        success: function(e){
            $(e).parent().removeClass('has-error').addClass('has-success');
        },
        highlight: function(e){
            $(e).parent().removeClass('has-success').addClass('has-error');
        },
        submitHandler:function() {
            ajaxSubmitForm();
        }
    });

    function ajaxSubmitForm(){
        var formData = {
            id: 1,
            name: $('#name').val(),
            author: $('#author').val(),
            isbn: $('#isbn').val(),
            publisher:$('#publisher').val(),
            introduction: $('#introduction').val()
        };

        $.ajax({
            type: "post",
            url: "/Library/addBook.do",
            contentType: "application/json; charset=utf-8",
            dataType:'json',
            data: JSON.stringify(formData),
            success: function (result) {
                console.log(result);
                console.log(result.a)
            }
        });
    }
});
