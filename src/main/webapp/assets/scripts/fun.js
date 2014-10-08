function ajaxFileUpload() {
    if(validateImage($('#photocover'))){
        $.ajaxFileUpload(
            {
                url: '/Library/upload.do',
                secureuri: false,
                fileElementId: 'cover',
                dataType: 'json',
                success: function (result){
                    if(result.resultCode === 'success') {
                        $('#coverImageId').val(result.coverImageId);
                        $("#cover").parent().removeClass('has-error').addClass('has-success');
                        ajaxSubmitForm();
                    }
                }
            }
        )
    }
}

function ajaxSubmitForm(){
    var formData = {
        name: $('#name').val(),
        author: $('#author').val(),
        isbn: $('#isbn').val(),
        publisher:$('#publisher').val(),
        coverImageId:$('#coverImageId').val(),
        introduction: $('#introduction').val()
    };

    $.ajax({
        type: "post",
        url: "/Library/addBook.do",
        contentType: "application/json; charset=utf-8",
        dataType:'json',
        data: JSON.stringify(formData),
        success: function (result) {
            if(result.resultCode === 'success'){
                $('#successTips').removeClass('hide').text('Add a success.');
                setTimeout(closeModal,2000);
            }
        }
    });
}

function validateImage(obj) {
    var file = obj;
    var tmpFileValue = file.val();

    //校验图片格式
    if (/^.*?\.(gif|png|jpg|jpeg)$/.test(tmpFileValue.toLowerCase())) {
        return true;
    } else {
        var errTipObj = $('#photocover-error');
        if(errTipObj.length <= 0){
            file.after('<div id="photocover-error"></div>');
        }
        errTipObj.text("Upload file format can be a JPG, jpeg, PNG or GIF")
            .parent().removeClass('has-success').addClass('has-error');
        return false;
    }
}

function closeModal(){
    $('.modal').modal('hide');
    window.location.reload();
}

$(function () {

    $('#browse').click(function(){
        $('#cover').click();
    });

    $('#cover').change(function() {
        $('#photocover').val($(this).val());
    });

    $("#addBookForm").validate({
        rules: {
            name: "required",
            author: "required",
            isbn: "required",
            publisher: "required",
            photocover: "required"
        },
        messages: {
            name: "This field is required.",
            author: "This field is required.",
            isbn: "This field is required.",
            publisher: "This field is required.",
            photocover: "This field is required."
        },
        onfocusout: function (element) {
            $(element).valid();
        },
        success: function(e){
            $(e).parent().removeClass('has-error').addClass('has-success');
        },
        highlight: function(e){
            $(e).parent().removeClass('has-success').addClass('has-error');
        },
        submitHandler:function() {
            ajaxFileUpload();
        }
    });
});
