<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="resources/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="resources/css/style.css" rel="stylesheet">
    <title>Hello kittens</title>
</head>
<body>
<%@ include file="logout.jspf" %>
<h1>Powitanie</h1>
<p><% out.println("Hello world! -- From JSP"); %></p>
<a href="<c:url value=" cats"/>">Przejdź do listy kotów.</a>

<div class="cat-border">
    <img src="resources/img/cat_upload.jpg"/>
    <img src="<c:url value="/resources/img/cat_upload.jpg"></c:url>" />
</div>

<div class="container"><br/>
    <div class="alert alert-success">
        <strong>Success!</strong> It is working as we expected.
    </div>
</div>

<script src="resources/jquery/jquery.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>