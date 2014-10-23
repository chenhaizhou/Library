<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="${basePath}/index.do" class="navbar-brand">Library</a>
        </div>
        <nav class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${basePath}/index.do">List</a></li>
                <li id="myBorrowed"><a href="###">Borrow Status</a></li>
                <li id="addBook-li" ><a id="addBook-btn" href="###" data-toggle="modal" data-target="#addBookFrmWrap">Add</a></li>
            </ul>
            <div class="navbar-right">
                <div id="login-area">
                    <strong class="login-user"></strong>
                    <a class="login-btn" href="###" data-toggle="modal" data-target=".login-modal">Login</a>
                    <a class="logout-btn" href="###" >Log out</a>
                </div>
            </div>
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
                            <input type="text" class="form-control" id="inputUsername" placeholder="Enter user name" tabindex="1">
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPassword" placeholder="Password" tabindex="2">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label>
                                    <input id="remember" type="checkbox" name="checkbox"> Remember me
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="error-msg col-sm-offset-2 col-sm-10">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="button" class="login-submit btn btn-success" tabindex="4" >Login</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal" tabindex="5" >Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>