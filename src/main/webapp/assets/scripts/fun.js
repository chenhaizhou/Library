/**
 * Created by hzchen on 14-9-28.
 */
$(function(){
    $("#submitAdd").click(function(){
        var formData = {
            name: $('#name').val(),
            author: $('#author').val(),
            isbn: $('#isbn').val(),
            publisher:$('#publisher').val(),
            cover:"/asd/asd.png",
            introduction: $('#introduction').val()
        };
        $.post('http://10.29.3.128:8080/Library/addBook.do',formData,function(data){

        });
    });
});
