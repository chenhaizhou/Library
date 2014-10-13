<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/includes.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="uft-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Library</title>
    <link rel="stylesheet" href="${assets}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${assets}/css/layout.css">
    <link rel="stylesheet" href="${assets}/css/login.css">
    <link rel="stylesheet" href="${assets}/css/bookdetail.css">
    <script>
        var basePath = '${basePath}';
    </script>
</head>
<body>
<%@ include file="/WEB-INF/pages/header.jsp" %>

<div class="banner" >
    <div class="container">
        <h2 class="book-name" >${book.name}</h2>
        <div class="operation-buttons">
            <input id = "deleteBtn" class="btn btn-danger" value="Delete" type="button">
        </div>
    </div>
</div>

<section class="container container_main">
    <div class="main">
        <div id="book-detail">
            <div id="book-image">
                <img src="${basePath}/${book.coverImageUrl}" width="100%"/>
            </div>
            <div id="book-info">
                <ul>
                    <li class="clear">
                        <div class="book-info-label">Author:</div>
                        <div class="book-info-content">${book.author}</div>
                    </li>
                    <li class="clear">
                        <div class="book-info-label">ISBN:</div>
                        <div class="book-info-content" id="bookISBN">${book.isbn}</div>
                    </li>
                    <li class="clear">
                        <div class="book-info-label">Publisher:</div>
                        <div class="book-info-content">${book.publisher}</div>
                    </li>
                    <li class="clear">
                        <div class="book-info-label">Introduction:</div>
                        <div class="book-info-content">${book.introduction}</div>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</section>

<script src="${assets}/scripts/lib/jquery-1.11.1.min.js"></script>
<script src="${assets}/scripts/lib/bootstrap.min.js"></script>
<script src="${assets}/scripts/lib/jquery.validate.min.js"></script>
<script src="${assets}/scripts/lib/ajaxfileupload.js"></script>
<script src="${assets}/scripts/lib/jquery.cookie.js"></script>
<script src="${assets}/scripts/public.js"></script>
<script src="${assets}/scripts/addbook.js"></script>
<script src="${assets}/scripts/login.js"></script>
<script src="${assets}/scripts/delbook.js"></script>
</body>
</html>