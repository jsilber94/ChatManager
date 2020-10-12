<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<%-- Bootstrap CSS/JS --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="style.scss">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<body>
<div class="container">
    <div class="jumbotron">
        <div class="form-group">
            <form action="ChatServlet" method="get">
                <label>From</label>
                <input class="control-label" type="text" name="from"/>
                <label>to</label>
                <input class="control-label" type="text" name="to"/>
                <label>format</label>
                <input class="control-label" type="text" name="format"/>
                <input type='submit' value='Make a get request'/>
            </form>
        </div>
    </div>
</div>

<div>${result}</div>


<div class="container">
    <div class="row no-gutters">
        <div class="form-group">
            <form action="ChatServlet" method="post">
                <label>User</label>
                <input class="control-label" type="text" name="user"/>
                <label>Message</label>
                <input class="control-label" type="text" name="message"/>
                <input type='submit' value='Make a post request'/>
            </form>
        </div>
    </div>
</div>

<div class="chat-panel">
    <div class="row no-gutters">
        <div class="col-md-3">
            <div class="chat-bubble chat-bubble--left">
<%--                @<%= name %>: <%= message %>--%>
                @mask: Yo yo yo yo yo this is a message
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <a href="">
                <i class="material-icons">delete</i>
            </a>
        </div>
    </div>
</div>

</body>
</html>