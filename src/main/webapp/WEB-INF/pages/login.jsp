<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="uft-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Library</title>
    <link rel="stylesheet" href="/Library/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="/Library/assets/css/layout.css">
    <link rel="stylesheet" href="/Library/assets/css/login.css">

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
                <li><a id="addBook-btn" href="###" data-toggle="modal" data-target=".bs-example-modal-lg">Add</a></li>
            </ul>

                <a class="login-btn" href="###" data-toggle="modal" data-target=".login-modal">Login</a>

            <div class="login-user">username</div>

        </nav>

    </div>
</header>

<div class="modal fade login-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">User login</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" >
                    <div class="form-group">
                        <label for="inputUsername" class="col-sm-2 control-label">User name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputUsername" placeholder="Enter user name">
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPassword" placeholder="Password">
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="error-msg col-sm-offset-2 col-sm-10">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" class="login-submit btn btn-success" >Login</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
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
<script src="/Library/assets/scripts/login.js"></script>
</html>