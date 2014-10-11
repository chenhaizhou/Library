function loadBookList(totalCount, itemCountInEachPage) {
    $('#smart-paginator').smartpaginator({ totalrecords: totalCount, recordsperpage: itemCountInEachPage, initval: 0, next: 'Next', prev: 'Prev', first: 'First', last: 'Last', theme: 'black', onchange: onChange
    });

    var fliter = {
        pageValue:1,
        searchKey:''
    };
    loadBooksByPage(fliter);
}

function onChange(newPageValue) {
    var fliter = {
        pageValue:newPageValue,
        searchKey:$('#searchKey').val()
    };
    loadBooksByPage(fliter);
}

function loadBooksByPage(fliter) {
    var template = $.templates("#bookTmpl");
    $.ajax({
        type: "POST",
        url: basePath + "/listBooks.do",
        contentType: "application/json; charset=utf-8",
        data:"" + fliter,
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
        url: basePath + "/booksCount.do",
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

    $('#searchBtn').click(function(){
        var fliter = {
            pageValue:1,
            searchKey:$('#searchKey').val()
        };
        loadBooksByPage(fliter)
    });
});