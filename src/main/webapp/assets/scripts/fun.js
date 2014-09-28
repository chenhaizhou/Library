/**
 * Created by hzchen on 14-9-28.
 */
$(function () {
    $("#submitAdd").click(function () {
        var formData = {
            id: 1,
            name: $('#name').val(),
            author: $('#author').val(),
            isbn: $('#isbn').val(),
            publisher:$('#publisher').val(),
            introduction: $('#introduction').val()
        };
//        $.post('http://localhost:8080/library/addBook.do',JSON.stringify(formData),function(data){
//
//        });

        $.ajax({
            type: "post",
            url: "/Library/addBook.do",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formData),
            success: function (result) {
                console.log("test");
            }
        });
    });
});
