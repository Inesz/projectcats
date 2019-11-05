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
    <link href="${pageContext.request.contextPath}/resources/css/cat.css" rel="stylesheet">
    <title>Cats list</title>
</head>

<body class="bg-image">

<div class="d-flex flex-column min-100">
    <div>
        <%@ include file="authPanel.jspf" %>
  </div>

  <div id="catFoto" class="d-flex flex-fill">
    <div id="foto" class="align-items-stretch col-6">

        <div id="img">
            <img src="${catImg}"/>
        </div>
        <%@ include file="imgUpload.jspf" %>

    </div>
    <div id="info" class="d-flex align-items-stretch col-6 flex-column">
        <h1>Cat ${cat.getName()}</h1>
          <span>${cat.getBirth()}</span>
         <span>${cat.getWeight()}</span>
        <span>${cat.getOwner()}</span>
    </div>
  </div>



    <div class="mt-auto"><%@ include file="footer.jspf" %></div>
</div>

<script src="${pageContext.request.contextPath}/resources/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/popper.js/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>