<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/font-awesome/css/all.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/flag-icon-css/css/flag-icon.min.css" type="text/css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet">
    <title>Hello kittens</title>
</head>
<body class="bg-image">

<div class="d-flex flex-column min-100">
<div>
<%@ include file="authPanel.jspf" %>
  </div>
    <div class="flex-fill d-flex flex-wrap flex-column">

        <div class="d-flex flex-fill justify-content-center align-items-center">
            <h1 class="hi-message"><spring:message code="index.message.welcome"/></h1>
        </div>
        <div class="d-flex flex-fill justify-content-center align-items-start">
            <a href="<c:url value=" cats"/>" class="btn btn-primary btn-lg active" role="button" aria-pressed="true"><spring:message code="index.button.enter" /></a>
        </div></div>
    <div class="mt-auto"><%@ include file="footer.jspf" %></div>
</div>
</div>


<!-- https://getbootstrap.com/docs/4.1/utilities/flex/ -->
<script src="${pageContext.request.contextPath}/resources/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/popper.js/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>