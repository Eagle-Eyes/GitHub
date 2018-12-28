<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}" dir="${sessionScope.dir}">
<head>
    <title><fmt:message key="Action_management"/></title>
    <script type="text/javascript" src="/resources/scripts.js"></script>
    <script type="text/javascript" src="/resources/dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="/resources/jquery.min.js"></script>
    <link href="/resources/dist/css/bootstrap.css" rel="stylesheet">
</head>
<body class="container">
<jsp:include page="elements/header.jsp"/>

<a class="btn btn-default btn-sm" href="/admin"><fmt:message key="back"/></a>
<br/>
<br/>
<table class="table table-sm table-responsive" border="1">
    <tr>

        <th><fmt:message key="name"/></th>
        <th><fmt:message key="deletedDate"/></th>
        <th><fmt:message key="registeredDate"/></th>
        <th><fmt:message key="updatedDate"/></th>
        <th><fmt:message key="accessibility"/></th>
        <th><fmt:message key="url"/></th>
        <th><fmt:message key="tag"/></th>
        <th></th>
    </tr>
    <c:forEach var="action" items="${actionRepo}">
        <tr>
            <td>${action.displayName}</td>
            <td>${action.deletedDate}</td>
            <td>${action.registeredDate}</td>
            <td>${action.updatedDate}</td>
            <td>${action.accessibility}</td>
            <td>${action.url}</td>
            <td>${action.tag}</td>
            <td id="deleteaction${action.id}"><input class="btn btn-danger" type="button"
                                                     value="<fmt:message key="Delete" />"
                                                     onclick="getMethod('/removeaction',${action.id})"/>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<br/>
</body>
</html>


