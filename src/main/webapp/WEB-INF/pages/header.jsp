<header class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="${basePath}/index.do" class="navbar-brand">Library</a>
        </div>
        <nav class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${basePath}/index.do">List</a></li>
                <li id="addBook-li" ><a id="addBook-btn" href="###" data-toggle="modal" data-target="#addBookFrmWrap">Add</a></li>
            </ul>
            <div class="navbar-right">
                <div id="login-area">
                    <strong class="login-user">username</strong>
                    <a class="login-btn" href="###" data-toggle="modal" data-target=".login-modal">Login</a>
                    <a class="logout-btn" href="###" >Log out</a>
                </div>
            </div>
        </nav>
    </div>
</header>

<div class="modal fade bs-example-modal-lg" id="addBookFrmWrap" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="addModalLabel">Create</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="addBookForm">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label"><em>*</em>Book Name</label>
                        <div class="col-sm-10">
                            <input type="text" id="name" name="name" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="author" class="col-sm-2 control-label"><em>*</em>Author</label>
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
                        <label for="publisher" class="col-sm-2 control-label"><em>*</em>Publisher</label>
                        <div class="col-sm-10">
                            <input type="text" id="publisher" name="publisher" class="form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="totalnumber" class="col-sm-2 control-label">Total number</label>
                        <div class="col-sm-10">
                            <input type="number" id="totalnumber" name="totalnumber" class="form-control" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cover" class="col-sm-2 control-label"><em>*</em>Cover</label>
                        <div class="col-sm-10">
                            <%--<input type="file" id="cover" name="cover" class="form-control" required>--%>
                            <input id="cover" type="file" name="cover" class="hide">
                            <div class="input-group ver-top">
                                <input id="photocover" name="photocover" class="form-control" type="text" readonly>
                                <input type="hidden" id="coverImageId" name="coverImageId">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" id="browse">Browse</button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="introduction" class="col-sm-2 control-label">Introduction</label>
                        <div class="col-sm-10">
                            <textarea id="introduction" name="introduction" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-2">
                            <button type="submit" class="btn btn-success" id="submitAdd">OK</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                        <div class="col-sm-6">
                            <div id="successTips" class="alert alert-success hide" role="alert"></div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>



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