<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/includes.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="uft-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Library</title>
    <link rel="stylesheet" href="${assets}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${assets}/css/smartpaginator.css">
    <link rel="stylesheet" href="${assets}/css/layout.css">
    <link rel="stylesheet" href="${assets}/css/login.css">
    <link rel="stylesheet" href="${assets}/css/bookstatus.css">
    <script>
        var basePath = '${basePath}';
    </script>
<script id="borrowedBookListTmpl" type="text/x-jsrender">
    <tr>
        <td>{{:index}}</td>
        <td><a href="${basePath}/bookDetail.do?bookId={{:bookId}}" class="img"><img src="${basePath}/{{:coverImageUrl}}"/></a></td>
        <td><a href="${basePath}/bookDetail.do?bookId={{:bookId}}">{{:bookName}}</a></td>
        <td>{{:author}}</td>
        <td>{{:borrowDate}}</td>
    </tr>
</script>
</head>
<body>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="banner">
    <h2>Borrow Status</h2>
</div>
<section class="container container_main">
    <div class="main" style="width: 980px; margin: 0 auto;">
        <div id="smart-paginator" > </div>
        <table id="borrowed-book-list" class="table table-bordered">
        </table>
    </div>
</section>
<%@ include file="/WEB-INF/pages/add_book.jsp" %>
<script src="${assets}/scripts/lib/jquery-1.11.1.min.js"></script>
<script src="${assets}/scripts/lib/bootstrap.min.js"></script>
<script src="${assets}/scripts/lib/jquery.validate.min.js"></script>
<script src="${assets}/scripts/lib/smartpaginator.js" ></script>
<script src="${assets}/scripts/lib/jsrender.js"></script>
<script src="${assets}/scripts/lib/jquery.cookie.js"></script>
<script src="${assets}/scripts/public.js"></script>
<script src="${assets}/scripts/login.js"></script>
<script src="${assets}/scripts/bookstatus.js"></script>
</body>
</html>