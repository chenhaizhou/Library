var bookstatus = {
    username : $('#username').text(),
    loadBorrowBookList : function(totalCount, itemCountInEachPage) {
        $('#smart-paginator').smartpaginator({ totalrecords: totalCount, recordsperpage: itemCountInEachPage, initval: 0, next: 'Next', prev: 'Prev', first: 'First', last: 'Last', theme: 'black', onchange: bookstatus.onChange,

        });
        bookstatus.loadBorrowedBookList(1);
    },

    onChange : function(newPageValue) {
        bookstatus.loadBorrowedBookList(newPageValue);
    },

    loadBorrowedBookList : function(pageNumber){

        var template = $.templates("#borrowedBookListTmpl"),username = $('#username').text();
        $.ajax({
            type: "GET",
            url: basePath + "/borrowedBooksList.do",
            contentType: "application/text; charset=utf-8",
            data:"username=" + username +"&pagenumber=" + pageNumber,
            dataType: 'json',
            success: function (result) {

                $.each(result,function(i){
                    result[i]["index"] = (pageNumber-1)*10 + i + 1;
                    result[i]["borrowDate"] = bookstatus.getTime(result[i]["borrowDate"]);
                });

                var htmlOutput = '<tr><th class="w60">#</th><th colspan="2">Book Name</th><th class="w220">Author</th><th class="w160">Borrow time</th></tr>'
                               + template.render(result);
                $("#borrowed-book-list").html(htmlOutput);

            },
            error :function() {
                //alert("show book wrong");
            }
        });

    },
    loadBorrowBookListInfo : function(){
        var username = $('#username').text();
        $.ajax({
            type: "GET",
            url: basePath + "/borrowedBookListCount.do",
            contentType: "application/json; charset=utf-8",
            data : "username=" + username,
            success: function (result) {

                var totalCnt = parseInt(result);
                bookstatus.loadBorrowBookList(totalCnt, 10);

            },
            error: function () {
                //alert("wrong");
            }
        });
    },
    getTime: function(){
        var ts = arguments[0] || 0;
        var t,y,m,d,h,i,s;
        t = ts ? new Date(ts) : new Date();
        y = t.getFullYear();
        m = t.getMonth()+1;
        d = t.getDate();
        h = t.getHours();
        i = t.getMinutes();
        s = t.getSeconds();
        return y+'-'+(m<10?'0'+m:m)+'-'+(d<10?'0'+d:d)+' '+(h<10?'0'+h:h)+':'+(i<10?'0'+i:i)+':'+(s<10?'0'+s:s);
    }
}


$(function(){
    $('.navbar-nav li').removeClass('active').siblings('#myBorrowed').addClass('active');
    $.when(userLogin.checkUserInfo()).done(function(){
        bookstatus.loadBorrowBookListInfo();

    });

})