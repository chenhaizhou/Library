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
        var borrowed = parseInt($('#book-info-totalnumber').text()) - parseInt($('#book-info-leftnumber').text());

        if(borrowed > 0){
            alert("This book has been borrowed, can't delete.");
        } else {
            if(confirm('Are you sure to delete this book?')){
                delBook($('#bookISBN').text());
            }
        }
    });
});
