function loadBookList(totalCount, itemCountInEachPage) {
    $('#smart-paginator').smartpaginator({ totalrecords: totalCount, recordsperpage: itemCountInEachPage, initval: 0, next: 'Next', prev: 'Prev', first: 'First', last: 'Last', theme: 'black', onchange: onChange
    });
}

function onChange(newPageValue) {
    loadBooksByPage(newPageValue);
}

function loadBooksByPage(pageValue) {
    alert(pageValue);
}