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
                    <a class="signUp-btn" href="###" data-toggle="modal" data-target="#signUp-modal" >Sign Up</a>
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

<div id="signUp-modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel2">Sign up in Library</h4>
            </div>
            <div class="modal-body">
                <form id="signUp-form" class="form-horizontal" >
                    <div class="form-group">
                        <label for="sign-inputUsername" class="col-sm-3 control-label">Username</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="sign-inputUsername" name="sign-username" placeholder="Enter username for login." tabindex="1" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sign-inputName" class="col-sm-3 control-label">Nickname</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="sign-inputName" name="sign-name" placeholder="Enter nickname for display." tabindex="2" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sign-inputPassword" class="col-sm-3 control-label">Password</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="sign-inputPassword" name="sign-password" placeholder="Enter password" tabindex="3" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sign-confirm-inputPassword" class="col-sm-3 control-label">Confirm Password</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="sign-confirm-inputPassword" name="sign-confirm-password" placeholder="Enter the password again" tabindex="4" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div id="sign-error-msg" class="error-msg col-sm-offset-3 col-sm-8">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-4">
                            <button type="submit" class="signUp-submit btn btn-success" tabindex="5" >Submit</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal" tabindex="6" >Cancel</button>
                        </div>
                        <div class="col-sm-4">
                            <div id="sign-successTips" class="alert alert-success hide" role="alert"></div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>