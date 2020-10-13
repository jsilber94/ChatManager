<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chat Box</title>
</head>
<%-- Bootstrap CSS/JS --%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
      integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="style.scss">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>

<body>

<%--DISPLAY ERROR MESSAGES--%>
<div class="alert alert-danger" role="alert">
    ${errorMessage}
</div>


<div class="container">
    <div class="jumbotron">
        <%--GET CHAT LOGS--%>
        <form action="ChatServlet" method="get" class="main-form">
            <div class="row">
                <div class="col-5">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">From</span>
                        </div>
                        <input type="text" class="form-control" placeholder="----/--/--" name="from">
                    </div>
                </div>
                <div class="col-5">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">To</span>
                        </div>
                        <input type="text" class="form-control" placeholder="----/--/--" name="to">
                    </div>
                </div>
                <div class="col-2">
                    <input class="control-label" type="hidden" type="text" name="action" value="get"/>
                    <input type='submit' value='Make a get request' class="btn btn-dark"/>
                </div>
            </div>
        </form>

        <br/>

        <%--SEND A MESSAGE--%>
        <form action="ChatServlet" method="post" class="main-form">
            <div class="row">
                <div class="col-5">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">@</span>
                        </div>
                        <input type="text" class="form-control" placeholder="Username" name="user">
                    </div>
                </div>
                <div class="col-5">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Message</span>
                        </div>
                        <input type="text" class="form-control" placeholder="Your Message" name="message">
                    </div>
                </div>
                <div class="col-2">
                    <input type='submit' value='Make a post request' class="btn btn-dark"/>
                </div>
            </div>
        </form>
    </div>
</div>

<div class="container">
    <div class="jumbotron">
        <%--DISPLAY CHAT LOGS--%>
        <div class="chat-panel">
            <div class="row no-gutters">
                <div class="col-6">
                    <div class="chat-bubble chat-bubble--left">
                        <c:forEach var="message" items="${requestScope.messages}">
                            <c:out value="${message}"/>
                            <br/>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <%--REFRESH THE CHAT LOGS--%>
        <div class="row">
            <div class="col">
                <a href="ChatServlet?from=1990-01-01&to=2021-01-01&format=text">
                    <label>Refresh chat</label>
                    <i class="material-icons">refresh</i>
                </a>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="jumbotron">
        <%--CLEAR THE CHAT--%>
        <form action="ChatServlet" method="get" class="main-form">
            <div class="row">
                <div class="col-5">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">From</span>
                        </div>
                        <input type="text" class="form-control" placeholder="----/--/--" name="from">
                    </div>
                </div>
                <div class="col-5">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">To</span>
                        </div>
                        <input type="text" class="form-control" placeholder="----/--/--" name="to">
                    </div>
                </div>
                <div class="col-2">
                    <input class="control-label" type="hidden" type="text" name="action" value="delete"/>
                    <input type='submit' value='Clear Chat' class="btn btn-dark"/>
                </div>
            </div>
        </form>

        <br/>

        <div class="row">
            <div class="input-group">
                <div class="input-group-prepend">
                    <%--DOWNLOAD CHATLOGS AS TEXT--%>
                    <form action="ChatServlet" method="get">
                        <input class="control-label" type="hidden" name="action" value="download"/>
                        <input class="control-label" type="hidden" name="type" value="txt"/>
                        <input type='submit' value='Download .TXT' class="btn btn-dark"/>
                    </form>
                </div>
                <div class="input-group-append">
                    <%--DOWNLOAD CHATLOGS AS XML--%>
                    <form action="ChatServlet" method="get">
                        <input class="control-label" type="hidden" name="action" value="download"/>
                        <input class="control-label" type="hidden" name="type" value="xml"/>
                        <input type='submit' value='Download .XML' class="btn btn-dark"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>