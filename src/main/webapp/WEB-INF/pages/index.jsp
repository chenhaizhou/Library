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
    <link rel="stylesheet" href="${assets}/css/booklist.css">
    <link rel="stylesheet" href="${assets}/css/login.css">
    <script>
        var basePath = '${basePath}';
    </script>
<script id="bookTmpl" type="text/x-jsrender">
   <li>
        <a href="${basePath}/bookDetail.do?bookId={{:id}}" ><img src="${basePath}/{{:coverImageUrl}}"/></a>
        <br/><a href="${basePath}/bookDetail.do?bookId={{:id}}">{{:name}}</a><br/>
        <span>{{:author}}</span>
    </li>
</script>
</head>
<body>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<div class="banner">
    <h2>Book List</h2>
    <div class="row search-box">
        <div class="col-md-6 col-md-offset-3">
            <div class="input-group">
                <input type="text" class="form-control" name="searchKey" placeholder="keyword">
            <span class="input-group-btn">
                <input type="button" class="btn btn-success" value="Search" id="searchBtn">
            </span>
            </div>
        </div>
    </div>
</div>
<section class="container container_main">
    <div class="main" style="width: 980px; margin: 0 auto;">
        <div id="smart-paginator" > </div>
        <ul id="booklist" class="clear" style="list-style: none;">

        </ul>
    </div>
</section>

</body>
<script src="${assets}/scripts/lib/jquery-1.11.1.min.js"></script>
<script src="${assets}/scripts/lib/bootstrap.min.js"></script>
<script src="${assets}/scripts/lib/jquery.validate.min.js"></script>
<script src="${assets}/scripts/lib/smartpaginator.js" ></script>
<script src="${assets}/scripts/lib/jsrender.js"></script>
<script src="${assets}/scripts/lib/ajaxfileupload.js"></script>
<script src="${assets}/scripts/lib/jquery.cookie.js"></script>
<script src="${assets}/scripts/public.js"></script>
<script src="${assets}/scripts/addbook.js"></script>
<script src="${assets}/scripts/booklist.js" ></script>
<script src="${assets}/scripts/login.js"></script>
</html>