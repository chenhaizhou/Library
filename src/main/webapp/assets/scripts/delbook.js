
function sayHello(name){
    return "Hello " + name;
}
function delBook(bookISBN){
    $.ajax({
        type:"post",
        url: basePath + '/delbook.do',
        contentType: "application/json; charset=utf-8",
        data:  bookISBN,
        success:function(){
            window.location.href = basePath + '/index.do';
        }
    });
}

$(function(){
    $('#deleteBtn').click(function(){
        if(confirm('Are you sure to delete this book?')){
            delBook($('#bookISBN').text());
        }
    });
});
