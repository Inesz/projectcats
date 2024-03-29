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
        <title>Cats list</title>
    </head>
    <body>
        <%@ include file="authPanel.jspf" %>

        <h1>Cats list</h1>

        <c:if test="${not empty msg}">
            <div class="alert alert-${css} alert-dismissible" role="alert">
        		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
        			<span aria-hidden="true">×</span>
        		</button>
        		<spring:message code="${msg}"/>
            </div>
        </c:if>

        <table style="width:100%">
            <tr>
                <th>Name</th>
                <th>Owner</th>
                <th>Date of birth</th>
                <th>Weight</th>
            </tr>

            <c:forEach var="cat" items="${catsList}">

                <tr>
                    <td>
                       <a href="<c:url value="cats/${cat.id}"/>">
                        ${cat.getName()}
                    </a>
                </td>
                <td>${cat.getOwner()}</td>
                <td>${cat.getBirth()}</td>
                <td>${cat.getWeight()}</td>
                <td><input type="button"  onclick="location.href='remove-${cat.id}'" value="remove"></td>
                </tr>
            </c:forEach>

            <form:form method="POST" modelAttribute="catDTO">
                <tr>
                    <td>
                        <form:input path="name"/>
                        <form:errors path="name"/>
                    </td>
                    <td>
                        <form:input path="owner"/>
                        <form:errors path="owner"/>
                    </td>
                    <td>
                        <form:input path="birth"/>
                        <form:errors path="birth"/>
                    </td>
                    <td>
                        <form:input path="weight"/>
                        <form:errors path="weight"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="addCat">
                    </td>
                </tr>
            </form:form>
        </table>
        <p>

<a class="weatherwidget-io" href="https://forecast7.com/pl/54d3518d65/gdansk/" data-label_1="GDAŃSK" data-label_2="WEATHER" data-theme="retro-sky" >GDAŃSK WEATHER</a>
    <script src="${pageContext.request.contextPath}/resources/script/weather.js"></script>
    <script src="${pageContext.request.contextPath}/resources/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/popper.js/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>
<!--
usefull?
https://javainterviewpoint.com/spring-mvc-multiple-submit-buttons-in-a-single-form/
-->