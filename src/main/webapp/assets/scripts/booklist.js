function loadBookList(totalCount, itemCountInEachPage) {
    $('#smart-paginator').smartpaginator({ totalrecords: totalCount, recordsperpage: itemCountInEachPage, initval: 0, theme: '', onchange: onChange

    });
    var fliter = {
        pageNumber:1,
        keyword:$('[name=searchKey]').val()
    };
    loadBooksByPage(fliter);
}

function onChange(newPageValue) {
    var fliter = {
        pageNumber:newPageValue,
        keyword:$('[name=searchKey]').val()
    };
    loadBooksByPage(fliter);
}

function loadBooksByPage(fliter) {
    var template = $.templates("#bookTmpl");
    $.ajax({
        type: "GET",
        url: basePath + "/listBooks.do",
        contentType: "application/text; charset=utf-8",
        data:"keyword=" + fliter.keyword +"&pageNumber="+ fliter.pageNumber,
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

function loadBookInfos() {
    var fliterx = {
        keyword:$('[name=searchKey]').val()
    };
    $.ajax({
        type: "POST",
        url: basePath + "/booksCount.do",
        contentType: "application/json; charset=utf-8",
        data:JSON.stringify(fliterx),
        success: function (result) {
            var totalCnt = parseInt(result);
            loadBookList(totalCnt, 20);
        },
        error: function () {
            alert("wrong");
        }
    });
}

$(document).ready(function() {

    loadBookInfos();

    $('#searchBtn').click(function(){
        loadBookInfos();
    });
});