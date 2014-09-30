function loadBookList(totalCount, itemCountInEachPage) {
    $('#smart-paginator').smartpaginator({ totalrecords: totalCount, recordsperpage: itemCountInEachPage, initval: 0, next: 'Next', prev: 'Prev', first: 'First', last: 'Last', theme: 'black', onchange: onChange
    });
    loadBooksByPage(1);
}

function onChange(newPageValue) {
    loadBooksByPage(newPageValue);
}

function loadBooksByPage(pageValue) {
    var template = $.templates("#bookTmpl");
    $.ajax({
        type: "POST",
        url: "/Library/listBooks.do",
        contentType: "application/json; charset=utf-8",
        data:"" + pageValue,
        dataType: 'json',
        success: function (result) {
            var htmlOutput = template.render(result);
            $("#booklist").html(htmlOutput);
            console.log(result);
        },
        error :function() {
            alert("wrong");
        }
    });
}

$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: "/Library/booksCount.do",
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            var totalCnt = parseInt(result);
            console.log(totalCnt);
            loadBookList(totalCnt, 20);
        },
        error :function() {
            alert("wrong");
        }
    });
});