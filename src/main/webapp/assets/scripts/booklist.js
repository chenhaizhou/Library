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
    var pageNum = fliter.pageNumber;
    $.ajax({
        type: "GET",
        url: basePath + "/listBooks.do",
        contentType: "application/text; charset=utf-8",
        data:"keyword=" + fliter.keyword +"&pageNumber="+ fliter.pageNumber,
        dataType: 'json',
        success: function (result) {
            var totalCount = result.totalCount;
            var itemCountInEachPage = 20;
            $('#smart-paginator').smartpaginator({ totalrecords: totalCount, recordsperpage: itemCountInEachPage, initval: pageNum, next: 'Next', prev: 'Prev', first: 'First', last: 'Last', theme: 'black', onchange: onChange,

            });

            var htmlOutput = template.render(result.resultData);
            $("#booklist").html(htmlOutput);
            console.log(result);
        },
        error :function() {
            alert("show book wrong");
        }
    });
}

function loadBookInfos() {
    var fliter = {
        pageNumber:1,
        keyword:$('[name=searchKey]').val()
    };

    loadBooksByPage(fliter);
}

$(document).ready(function() {

    loadBookInfos();

    $('#searchBtn').click(function(){
        loadBookInfos();
    });
});