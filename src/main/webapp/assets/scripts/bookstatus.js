var bookstatus = {
    username : $('#username').text(),
    columnModel: {
        borrowed : '<tr><th class="w60">#</th><th colspan="2">Book Name</th><th class="w220">Author</th><th class="w160">Borrow time</th></tr>',
        borrowing : '<tr><th class="w60">#</th><th colspan="2">Book Name</th><th class="w220">Author</th><th class="w160">Borrow time</th><th class="w100"></th></tr>',
        returned : '<tr><th class="w60">#</th><th colspan="2">Book Name</th><th class="w220">Author</th><th class="w160">Borrow time</th><th class="w160">Return time</th></tr>'
    },
    loadBorrowBookList : function(status, totalCount, itemCountInEachPage) {
        if(status !== 'borrowing') {
            $('#smart-paginator').empty().smartpaginator({ totalrecords: totalCount, recordsperpage: itemCountInEachPage, initval: 0, next: 'Next', prev: 'Prev', first: 'First', last: 'Last', theme: 'black', onchange: bookstatus.onChange

            });
        } else {
            $('#smart-paginator').empty();
        }

        bookstatus.loadBorrowedBookList(1,status);
    },

    onChange : function(newPageValue) {
        bookstatus.loadBorrowedBookList(newPageValue);
    },

    loadBorrowedBookList : function(pageNumber, status){

        status = status ? status : $('.btn-group button.active').attr('id').replace('Btn','');
        var template = $.templates("#"+ status +"BookListTmpl"),username = $('#username').text();
        $.ajax({
            type: "GET",
            url: basePath + "/borrowedBooksList.do",
            contentType: "application/text; charset=utf-8",
            data:"username=" + username +"&pagenumber=" + pageNumber + "&status=" + status,
            dataType: 'json',
            success: function (result) {

                $.each(result,function(i){
                    result[i]["index"] = (pageNumber-1)*10 + i + 1;
                    result[i]["borrowDate"] = bookstatus.getTime(result[i]["borrowDate"]);
                });

                var htmlOutput = bookstatus.columnModel[status]
                               + template.render(result);
                $("#borrowed-book-list").html(htmlOutput);

            },
            error :function() {
                //alert("show book wrong");
            }
        });

    },
    loadBorrowBookListInfo : function(status){
        var username = $('#username').text();
        $.ajax({
            type: "GET",
            url: basePath + "/borrowedBookListCount.do",
            contentType: "application/json; charset=utf-8",
            data : "username=" + username + "&status=" + status,
            success: function (result) {

                var totalCnt = parseInt(result);
                bookstatus.loadBorrowBookList(status, totalCnt, 10);

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

var btnEvent = {
    borrowed: "borrowed",
    borrowing: "borrowing",
    returned: "returned"
}

$(function(){
    $('.navbar-nav li').removeClass('active').siblings('#myBorrowed').addClass('active');
    $.when(userLogin.checkUserInfo()).done(function(){
        bookstatus.loadBorrowBookListInfo('borrowed');
    });

    $('.btn-group button').click(function() {
        $(this).addClass('active').siblings('button').removeClass('active');
        bookstatus.loadBorrowBookListInfo(btnEvent[$(this).attr('id').replace('Btn','')]);
    });

})