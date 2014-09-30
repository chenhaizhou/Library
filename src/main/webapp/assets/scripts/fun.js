function ajaxFileUpload() {
    if(validateImage($('#cover'))){
        $.ajaxFileUpload
        (
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
                $('#successTips').show();
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
        $('#cover_error').text("只能上传jpg、jpeg、png或gif格式的图片！")
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

    $('#ocover').change(function() {
        $('#photocover').val($(this).val());
    });

    $("#addBookForm").validate({
        rules: {
            name: "required",
            author: "required",
            isbn: "required",
            publisher: "required",
            cover: "required"
        },
        messages: {
            name: "书名不能为空",
            author: "作者不能为空",
            isbn: "ISBN不能为空",
            publisher: "出版社不能为空",
            cover: "封面未上传"
        },
        onfocusout: function(element) { $(element).valid(); },
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
