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
        <h2 class="book-name" >Error</h2>
        <div class="operation-buttons">
            <input id = "editBtn" class="btn btn-primary" value="Edit" type="button" data-toggle="modal" data-target="#editBookFrmWrap">
            <input id = "deleteBtn" class="btn btn-danger" value="Delete" type="button">
        </div>
    </div>
</div>

<section class="container container_main">
    <div class="main">
        <h3>${message}</h3>
    </div>

</section>


<script src="${assets}/scripts/lib/jquery-1.11.1.min.js"></script>
<script src="${assets}/scripts/lib/bootstrap.min.js"></script>
<script src="${assets}/scripts/lib/jquery.validate.min.js"></script>
<script src="${assets}/scripts/lib/ajaxfileupload.js"></script>
<script src="${assets}/scripts/lib/jquery.cookie.js"></script>
<script src="${assets}/scripts/public.js"></script>
<script src="${assets}/scripts/login.js"></script>
</body>
</html>