<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../resources/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="../resources/font-awesome/css/all.css" type="text/css" rel="stylesheet"/>
    <link href="../resources/flag-icon-css/css/flag-icon.min.css" type="text/css" rel="stylesheet"/>
    <title>Cats list</title>
</head>
<body>
<%@ include file="authPanel.jspf" %>

<h1>Cat ${cat.getName()}</h1>

<%@ include file="imgUpload.jspf" %>

<script src="../resources/jquery/jquery.min.js"></script>
<script src="../resources/popper.js/umd/popper.min.js"></script>
<script src="../resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>