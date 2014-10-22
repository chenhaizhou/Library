var editBook = {

    ajaxFileUpload: function(){
        function shouldNotUpload() {
            var newFileName = $('#edit-photocover').val();
            return (newFileName == "" || newFileName == editBook.image.filename);
        }

        if(shouldNotUpload()) {
            editBook.ajaxSubmitForm();
        } else if(addBookFun.validateImage($('#edit-photocover'))){
            addBookFun.addButtonDisabled(true);
            $.ajaxFileUpload(
                {
                    url: basePath + '/upload.do',
                    secureuri: false,
                    fileElementId: 'edit-cover',
                    dataType: 'json',
                    success: function (result){
                        if(result.resultCode === 'success') {
                            $('#edit-coverImageId').val(result.coverImageId);
                            $("#edit-cover").parent().removeClass('has-error').addClass('has-success');
                            editBook.ajaxSubmitForm();
                        }
                    }
                }
            )
        }
    },
    ajaxSubmitForm: function(){

        var formData = {
            name: $('#edit-name').val(),
            author: $('#edit-author').val(),
            isbn: $('#edit-isbn').val(),
            publisher:$('#edit-publisher').val(),
            totalNumber:$('#edit-totalnumber').val() || 0,
            coverImageId:$('#edit-coverImageId').val(),
            introduction: $('#edit-introduction').val()
        };

        var changedData = {};
        var changedDataNum = 0;
        for(var bookitem in formData){
            if(formData[bookitem] != editBook.bookData[bookitem] ){
                changedData[bookitem] = formData[bookitem];
                changedDataNum++;
            }
        }

        if(changedDataNum > 0) {

            changedData.id = $("#book-id").val();

            $.ajax({
                type: "post",
                url: basePath + "/editBook.do",
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                data: JSON.stringify(changedData),
                success: function (result) {
                    if (result.resultCode === 'success') {
                        $('#edit-successTips').removeClass('hide').text('Update successfully.');
                        setTimeout(editBook.closeModal, 2000);
                    } else {
                        editBook.addButtonDisabled(false);
                    }
                },
                error: function () {
                    editBook.addButtonDisabled(false);
                }
            });

        }else{
            $('#edit-successTips').removeClass('hide').text('Nothing changed.');
        }
    },
    closeModal: function(){
        $('#editBookForm').modal('hide');
        editBook.addButtonDisabled(false);
        window.location.reload();
    },
    addButtonDisabled: function(Boolean){
        $('#submitEdit').prop('disabled',Boolean)
    }
}

var borrowBook = {
    borrowBook : function() {
        var borrowData = {
            bookId: $('#book-id').val(),
            userName: $('#inputUsername').val()
        };
        $.ajax({
            type: "post",
            url: basePath + "/borrowBook.do",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(borrowData),
            dataType: 'json',
            success: function (result) {
                var resultCode = result.resultCode;
                if (resultCode === 'success') {
                    alert("Borrow Success!");
                } else if (resultCode === 'fail') {
                    alert("Borrow failure!");
                } else {
                    alert("Book not available!")
                }
                setTimeout(function () {
                    window.location.reload();
                }, 1000);
            }
        });
    }

}

$(function(){

    $('#editBtn').click(function(){
        editBook.bookData = {
            name: $('#edit-name').val(),
            author: $('#edit-author').val(),
            isbn: $('#edit-isbn').val(),
            publisher:$('#edit-publisher').val(),
            coverImageId:$('#edit-coverImageId').val(),
            introduction: $('#edit-introduction').val()
        };
        $('#edit-successTips').addClass('hide');

        var originalFileName = $('#edit-photocover').val();

        originalFileName = originalFileName.substring(originalFileName.lastIndexOf('/')+1);
        editBook.image = {filename: originalFileName};
        $('#edit-photocover').val(originalFileName);
    });

    $('#edit-browse').click(function(){
        $('#edit-cover').click();
    });

    $('#borrowBtn').click(function(){

        var userName = $('#inputUsername').val();

            $.ajax({
            type: "GET",
            url: basePath + "/borrowedBookListCount.do",
            contentType: "application/json; charset=utf-8",
            data : "username=" + userName,
            success: function (result) {
                if(result >= 5){
                    alert('You can borrow 5 books at most.');
                } else {
                    borrowBook.borrowBook();
                }
            },
            error: function () {
                alert("borrow book wrong");
            }
        });

    });

    $('#edit-cover').change(function() {
        var filename = $(this).val();
        filename = filename.substring(filename.lastIndexOf('\\')+1);
        $('#edit-photocover').val(filename);
        addBookFun.validateImage($('#edit-photocover'));
    });

    addBookFun.validateForm("editBookForm",$("#book-id").val());

})
