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
            <input id = "editBtn" class="btn btn-primary" value="Edit" type="button" data-toggle="modal" data-target="#editBookFrmWrap">
            <input id = "deleteBtn" class="btn btn-danger" value="Delete" type="button">
            <input id = "borrowBtn" class="btn btn-primary" value="Borrow" type="button">
        </div>
        <input id="book-id" type="hidden" value="${book.id}" >

    </div>
</div>

<section class="container container_main">
    <div class="main">
        <div id="book-detail">
            <div id="book-image">
                <img src="${basePath}/${book.coverImageUrl}">
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
                        <div class="book-info-label">Left number:</div>
                        <div id="book-info-leftnumber" class="book-info-content">${book.totalNumber - book.borrowedNumber}</div>
                    </li>
                    <li class="clear">
                        <div class="book-info-label">Total number:</div>
                        <div id="book-info-totalnumber" class="book-info-content">${book.totalNumber}</div>
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


<div class="modal fade bs-example-modal-lg" id="editBookFrmWrap" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="editModalLabel">Edit</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="editBookForm">
                    <div class="form-group">
                        <label for="edit-name" class="col-sm-2 control-label"><em>*</em>Book Name</label>
                        <div class="col-sm-10">
                            <input type="text" id="edit-name" name="name" class="form-control" required value="${book.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-author" class="col-sm-2 control-label"><em>*</em>Author</label>
                        <div class="col-sm-10">
                            <input type="text" id="edit-author" name="author" class="form-control" required value="${book.author}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-isbn" class="col-sm-2 control-label"><em>*</em>ISBN</label>
                        <div class="col-sm-10">
                            <input type="text" id="edit-isbn" name="isbn" class="form-control" required value="${book.isbn}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-publisher" class="col-sm-2 control-label"><em>*</em>Publisher</label>
                        <div class="col-sm-10">
                            <input type="text" id="edit-publisher" name="publisher" class="form-control" required value="${book.publisher}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-totalnumber" class="col-sm-2 control-label">Total number</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="edit-borrowednumber" name="edit-borrowednumber" value="${book.borrowedNumber}">
                            <input type="number" id="edit-totalnumber" name="edit-totalnumber" class="form-control" value="${book.totalNumber}" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-cover" class="col-sm-2 control-label"><em>*</em>Cover</label>
                        <div class="col-sm-10">
                            <input id="edit-cover" type="file" name="cover" class="hide">
                            <div class="input-group ver-top">
                                <input id="edit-photocover" name="photocover" class="form-control" type="text" value="${book.coverImageUrl}" readonly >
                                <input type="hidden" id="edit-coverImageId" name="coverImageId" value="${book.coverImageId}">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" id="edit-browse">Browse</button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-introduction" class="col-sm-2 control-label">Introduction</label>
                        <div class="col-sm-10">
                            <textarea id="edit-introduction" name="introduction" class="form-control">${book.introduction}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-3">
                            <button type="submit" class="btn btn-success" id="submitEdit">Save</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                        <div class="col-sm-6">
                            <div id="edit-successTips" class="alert alert-success hide" role="alert"></div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/pages/add_book.jsp" %>
<script src="${assets}/scripts/lib/jquery-1.11.1.min.js"></script>
<script src="${assets}/scripts/lib/bootstrap.min.js"></script>
<script src="${assets}/scripts/lib/jquery.validate.min.js"></script>
<script src="${assets}/scripts/lib/ajaxfileupload.js"></script>
<script src="${assets}/scripts/lib/jquery.cookie.js"></script>
<script src="${assets}/scripts/public.js"></script>
<script src="${assets}/scripts/addbook.js"></script>
<script src="${assets}/scripts/login.js"></script>
<script src="${assets}/scripts/delbook.js"></script>
<script src="${assets}/scripts/bookdetail.js"></script>
</body>
</html>