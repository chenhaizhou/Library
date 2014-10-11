function loadBookList(totalCount, itemCountInEachPage) {
    $('#smart-paginator').smartpaginator({ totalrecords: totalCount, recordsperpage: itemCountInEachPage, initval: 0, next: 'Next', prev: 'Prev', first: 'First', last: 'Last', theme: 'black', onchange: onChange
    });

    var fliter = {
        pageNumber:1,
        keyword:$('#searchKey').val()
    };
    loadBooksByPage(fliter);
}

function onChange(newPageValue) {
    var fliter = {
        pageNumber:newPageValue,
        keyword:$('#searchKey').val()
    };
    loadBooksByPage(fliter);
}

function loadBooksByPage(fliter) {
    var template = $.templates("#bookTmpl");
    $.ajax({
        type: "POST",
        url: basePath + "/listBooks.do",
        contentType: "application/json; charset=utf-8",
        data:JSON.stringify(fliter),
        dataType: 'json',
        success: function (result) {
            var htmlOutput = template.render(result);
            $("#booklist").html(htmlOutput);
            console.log(result);
        },
        error :function() {
            alert("show book wrong");
        }
    });
}

$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: basePath + "/booksCount.do",
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            var totalCnt = parseInt(result);

            loadBookList(totalCnt, 20);
        },
        error :function() {
            alert("wrong");
        }
    });

    $('#searchBtn').click(function(){
        $('#1').click();
    });
});