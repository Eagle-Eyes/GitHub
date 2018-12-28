<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false"
         isErrorPage="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="en" dir="${sessionScope.dir}">
<head>
    <style>
        table td {
            vertical-align: top;
            border: solid 1px #888;
            padding: 10px;
        }
    </style>
    <title>Error Page</title>
</head>
<body>
<h1><fmt:message key="Error_occured"/> Please contact the administrator : </h1>
<table>
    <tr>
        <td>Date</td>
        <td>${timestamp}</td>
    </tr>
    <tr>
        <td>Error</td>
        <td>${error}</td>
    </tr>
    <tr>
        <td>Status</td>
        <td>${status}</td>
    </tr>
    <tr>
        <td>Message</td>
        <td>${message}</td>
    </tr>
    <tr>
        <td>Exception</td>
        <td>${exception}</td>
    </tr>
    <tr>
        <td>Trace</td>
        <td>
            <pre>${trace}</pre>
        </td>
    </tr>
</table>
</body>
</html>