var addBookFun = {
    ajaxFileUpload: function(){
        if(addBookFun.validateImage($('#photocover'))){
            addBookFun.addButtonDisabled(true);
            $.ajaxFileUpload(
                {
                    url: basePath + '/upload.do',
                    secureuri: false,
                    fileElementId: 'cover',
                    dataType: 'json',
                    success: function (result){
                        if(result.resultCode === 'success') {
                            $('#coverImageId').val(result.coverImageId);
                            $("#cover").parent().removeClass('has-error').addClass('has-success');
                            addBookFun.ajaxSubmitForm();
                        }
                    }
                }
            )
        }
    },
    ajaxSubmitForm: function(){
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
            url:  basePath + "/addBook.do",
            contentType: "application/json; charset=utf-8",
            dataType:'json',
            data: JSON.stringify(formData),
            success: function (result) {
                if(result.resultCode === 'success'){
                    $('#successTips').removeClass('hide').text('Add a success.');
                    setTimeout(addBookFun.closeModal,2000);
                }else{
                    addBookFun.addButtonDisabled(false);
                }
            },
            error: function(){
                addBookFun.addButtonDisabled(false);
            }
        });
    },
    validateImage: function(obj) {
        var file = obj;
        var tmpFileValue = file.val();

        if (/^.*?\.(gif|png|jpg|jpeg)$/.test(tmpFileValue.toLowerCase())) {
            obj.parent().removeClass('has-error').addClass('has-success');
            $('#photocover-error').empty();
            return true;
        } else {
            var errTipObj = $('#photocover-error');

            if(errTipObj.length <= 0){
                file.after('<em id="photocover-error" class="error"></em>');
                errTipObj = $('#photocover-error');
            }
            errTipObj.text("Upload file format can be a JPG, jpeg, PNG or GIF")
                .parent().removeClass('has-success').addClass('has-error');
            return false;
        }
    },
    closeModal: function(){
        $('#addBookFrmWrap').modal('hide');
        addBookFun.addButtonDisabled(false);
        window.location.reload();
    },
    addButtonDisabled: function(Boolean){
        $('#submitAdd').prop('disabled',Boolean)
    }
};





$(function () {

    $('#browse').click(function(){
        $('#cover').click();
    });

    $('#cover').change(function() {
        var filename = $(this).val();
        filename = filename.substring(filename.lastIndexOf('\\')+1);
        $('#photocover').val(filename);
        addBookFun.validateImage($('#photocover'));
    });

    $('#addBook-btn').click(function(){
        $('#addBookForm').find('input,textarea').val('');
    });

    $("#addBookForm").validate({
        rules: {
            name: "required",
            author: "required",
            isbn: {
                required: true,
                remote:  basePath + "/checkISBN.do"
            },
            publisher: "required",
            photocover: "required"
        },
        messages: {
            name: "This field is required.",
            author: "This field is required.",
            isbn: {
                required: "This field is required.",
                remote: "This ISBN exists, please fix this field."
            },
            publisher: "This field is required.",
            photocover: "This field is required."
        },
        errorElement: "em",
        onfocusout: function (element) {
            $(element).valid();
        },
        success: function(element){
            $(element).parent().removeClass('has-error').addClass('has-success');
        },
        highlight: function(element){
            $(element).parent().removeClass('has-success').addClass('has-error');
        },
        submitHandler:function() {
            addBookFun.ajaxFileUpload();
        }
    });
});
