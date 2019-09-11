<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cats list</title>
    </head>
    <body>
        <h1>Cats list</h1>

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
                        <a href="<c:url value=" cats/${cat.name}"/>">
                        ${cat.getName()}
                    </a>
                </td>
                <td>${cat.getOwner()}</td>
                <td>${cat.getBirth()}</td>
                <td>${cat.getWeight()}</td>
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
    </body>
</html>