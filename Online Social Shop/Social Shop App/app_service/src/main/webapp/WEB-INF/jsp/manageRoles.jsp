<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}" dir="${sessionScope.dir}">
<head>
    <title><fmt:message key="Role_management"/></title>
    <script type="text/javascript" src="/resources/scripts.js"></script>
    <script type="text/javascript" src="/resources/dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="/resources/jquery.min.js"></script>
    <link href="/resources/dist/css/bootstrap.css" rel="stylesheet">
</head>
<body class="container">
<jsp:include page="elements/header.jsp"/>

<a class="btn btn-default btn-sm" href="/admin"><fmt:message key="back"/></a>
<br/>
<a class="btn btn-default" href="/admin/addRole"><fmt:message key="Add_Role"/></a>
<a class="btn btn-default" href="/admin/roles/actions"><fmt:message key="Add_Action_to_Role"/></a>
<br/>
<table class="table table-sm table-responsive" border="1">
    <tr>

        <th><fmt:message key="name"/></th>
        <th><fmt:message key="deletedDate"/></th>
        <th><fmt:message key="registeredDate"/></th>
        <th><fmt:message key="updatedDate"/></th>
        <th></th>
    </tr>
    <c:forEach var="role" items="${roleRepo}">
        <tr>
            <td>${role.displayName}</td>
            <td>${role.deletedDate}</td>
            <td>${role.registeredDate}</td>
            <td>${role.updatedDate}</td>
            <td id="deleterole${role.id}"><input class="btn btn-danger" type="button"
                                                 value="<fmt:message key="Delete" />"
                                                 onclick="window.location =('/admin/roles/${role.id}/delete')"/></td>
        </tr>
    </c:forEach>
</table>
<br/>
<br/>
</body>
</html>