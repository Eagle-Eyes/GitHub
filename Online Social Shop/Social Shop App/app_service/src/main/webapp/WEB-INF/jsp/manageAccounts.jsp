<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}" dir="${sessionScope.dir}">
<head>
    <title><fmt:message key="User_management"/></title>
    <script type="text/javascript" src="/resources/scripts.js"/>
    <script type="text/javascript" src="/resources/dist/js/bootstrap.js"/>
    <script type="text/javascript" src="/resources/jquery.min.js"></script>
    <link href="/resources/dist/css/bootstrap.css" rel="stylesheet">
</head>
<body class="container">
<jsp:include page="elements/header.jsp"/>

<a class="btn btn-default btn-sm" href="/admin"><fmt:message key="back"/></a>
<br/>
<a class="btn btn-default" href="/admin/addAccount"><fmt:message key="Add_User"/></a>
<a class="btn btn-default" href="/admin/accounts/roles"><fmt:message key="Add_Role_to_User"/></a>
<a class="btn btn-default" href="/admin/accounts/groups"><fmt:message key="Add_Group_to_User"/></a>
<a class="btn btn-default" href="/admin/accounts/actions"><fmt:message key="Add_Action_to_User"/></a>

<table class="table table-sm table-responsive" border="1">
    <tr>
        <th><fmt:message key="name"/></th>
        <th><fmt:message key="registeredDate"/></th>
        <th><fmt:message key="updatedDate"/></th>
        <th><fmt:message key="deletedDate"/></th>
        <th><fmt:message key="email"/></th>
        <th><fmt:message key="naturalPerson"/></th>
        <th colspan="2"></th>
    </tr>
    <c:forEach var="account" items="${accountRepo}">
        <tr>
            <td>${account.name}</td>
            <td>${account.registeredDate}</td>
            <td>${account.updatedDate}</td>
            <td>${account.deletedDate}</td>
            <td>${account.email}</td>
            <td>${account.naturalPerson}</td>
            <td id="resetPassword${account.id}"><input type="button" class="btn btn-warning"
                                                       value="<fmt:message key="Reset_Password" />"
                                                       onclick="window.location =('/admin/accounts/${account.id}/resetPass')"/>
            </td>
            <td id="delete${account.id}"><input class="btn btn-danger" type="button"
                                                value="<fmt:message key="Delete" />"
                                                onclick="window.location =('/admin/accounts/${account.id}/delete')"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>