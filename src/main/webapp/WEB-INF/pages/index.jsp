<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="uft-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Library</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <style>
        body {
            padding-top:50px;
        }
    </style>
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
<section class="container">
    <ul class="row">
        <li class="col-sm-6 col-md-4">1</li>
        <li class="col-sm-6 col-md-4">2</li>
        <li class="col-sm-6 col-md-4">3</li>
    </ul>

</section>


<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">新建</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">书名</label>
                        <div class="col-sm-10">
                            <input type="text" id="name" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="author" class="col-sm-2 control-label">作者</label>
                        <div class="col-sm-10">
                            <input type="text" id="author" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isbn" class="col-sm-2 control-label">ISBN</label>
                        <div class="col-sm-10">
                            <input type="text" id="isbn" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="publisher" class="col-sm-2 control-label">出版社</label>
                        <div class="col-sm-10">
                            <input type="text" id="publisher" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cover" class="col-sm-2 control-label">封面</label>
                        <div class="col-sm-10">
                            <input type="file" id="cover" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="introduction" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10">
                            <textarea id="introduction" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" class="btn btn-success" id="submitAdd">确定</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="/Library/assets/scripts/fun.js"></script>
</html>