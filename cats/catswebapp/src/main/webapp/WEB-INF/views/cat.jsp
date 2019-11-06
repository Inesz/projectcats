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

<body class="bg-image" onload="cat.replaceByInfo()">

<div class="d-flex flex-column min-100">
    <div>
        <%@ include file="authPanel.jspf" %>
    </div>

    <div id="catFoto" class="d-flex flex-fill">
        <div id="foto" class="align-items-stretch col-6">

            <div id="img">
                <img src="${catImg}"/>
            </div>
            <div>
                ${catImgComment}
            </div>
            <%@ include file="imgUpload.jspf" %>

        </div>
        <div id="right" class="d-flex align-items-stretch col-6 flex-column">
            <div id="info" class="d-flex align-items-stretch flex-column">
                <h1>${cat.getName()}</h1>
                <button type="button" onclick="cat.replaceByForm()" class="btn btn-dark">
                    <i class="fas fa-pencil-alt" style="color:white"></i>
                </button>
                <span><spring:message code="cat.label.catName"/> ${cat.getName()}</span>
                <span><spring:message code="cat.label.catBirth"/> ${cat.getBirth()}</span>
                <span><spring:message code="cat.label.catWeight"/> ${cat.getWeight()}</span>
                <span><spring:message code="cat.label.catOwner"/> ${cat.getOwner()}</span>
            </div>

            <div id="form">
                <form:form method="POST" modelAttribute="catDTO" class="d-flex align-items-stretch flex-column">
                    <h1>${cat.getName()}</h1>
                    <button type="submit" class="btn btn-dark">
                       <i class="far fa-save" style="color:white"></i>
                    </button>
                    <button type="button" onclick="cat.replaceByInfo(); reset();" class="btn btn-dark">
                        <i class="fas fa-backward" style="color:white"></i>
                    </button>

                    <span><spring:message code="cat.label.catName"/>
                          <form:input path="name" value="${cat.getName()}"/>
                          <form:errors path="name"/>
                  </span>
                    <span><spring:message code="cat.label.catOwner"/>
                          <form:input path="owner" value="${cat.getOwner()}"/>
                          <form:errors path="owner"/></span>
                    <span><spring:message code="cat.label.catBirth"/>
                          <form:input path="birth" value="${cat.getBirth()}"/>
                          <form:errors path="birth"/></span>
                    <span><spring:message code="cat.label.catWeight"/>
                          <form:input path="weight" value="${cat.getWeight()}"/>
                          <form:errors path="weight"/></span>
                </form:form>
            </div>
        </div>
    </div>
    <div class="mt-auto"><%@ include file="footer.jspf" %></div>

    <script src="${pageContext.request.contextPath}/resources/script/cat_css.js"></script>
    <script src="${pageContext.request.contextPath}/resources/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/popper.js/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>