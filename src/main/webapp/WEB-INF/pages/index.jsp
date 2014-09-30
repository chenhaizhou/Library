<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="uft-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Library</title>
    <link rel="stylesheet" href="/Library/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/Library/assets/css/layout.css">
    <link rel="stylesheet" href="/Library/assets/css/smartpaginator.css" type="text/css" />
    <link rel="stylesheet" href="/Library/assets/css/booklist.css"/>

<script id="bookTmpl" type="text/x-jsrender">
   <li>
        <a><img src="{{:coverImageUrl}}"/></a>
        <br/><a>{{:name}}</a><br/>
        <span>{{:author}}</span>
    </li>
</script>
</head>
<body>
<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="" class="navbar-brand">Library</a>
        </div>
        <nav class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="">List</a></li>
                <li><a href="###" data-toggle="modal" data-target=".bs-example-modal-lg">Add</a></li>
            </ul>
        </nav>
    </div>
</header>
<div class="banner">
    <h2>Book List</h2>
</div>
<section class="container container_main">
    <div class="main" style="width: 980px; margin: 0 auto;">
        <div id="smart-paginator" > </div>
        <ul id="booklist" class="clear" style="list-style: none;">

        </ul>
    </div>
</section>


<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">新建</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="addBookForm">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label"><em>*</em>书名</label>
                        <div class="col-sm-10">
                            <input type="text" id="name" name="name" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="author" class="col-sm-2 control-label"><em>*</em>作者</label>
                        <div class="col-sm-10">
                            <input type="text" id="author" name="author" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isbn" class="col-sm-2 control-label"><em>*</em>ISBN</label>
                        <div class="col-sm-10">
                            <input type="text" id="isbn" name="isbn" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="publisher" class="col-sm-2 control-label"><em>*</em>出版社</label>
                        <div class="col-sm-10">
                            <input type="text" id="publisher" name="publisher" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cover" class="col-sm-2 control-label"><em>*</em>封面</label>
                        <div class="col-sm-10">
                            <input type="file" id="cover" name="cover" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="introduction" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10">
                            <textarea id="introduction" name="introduction" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-success" id="submitAdd">确定</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script src="/Library/assets/scripts/lib/jquery-1.11.1.min.js"></script>
<script src="/Library/assets/scripts/lib/bootstrap.min.js"></script>
<script src="/Library/assets/scripts/lib/jquery.validate.min.js"></script>
<script src="/Library/assets/scripts/lib/smartpaginator.js" type="text/javascript"></script>
<script src="/Library/assets/scripts/lib/jsrender.js"></script>
<script src="/Library/assets/scripts/fun.js"></script>
<script src="/Library/assets/scripts/booklist.js" type="text/javascript"></script>
</html>