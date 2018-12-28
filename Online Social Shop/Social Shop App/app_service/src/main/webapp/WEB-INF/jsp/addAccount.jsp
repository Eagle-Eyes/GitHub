<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}" dir="${sessionScope.dir}">
<head>
    <title><fmt:message key="Add_User"/></title>
    <script type="text/javascript" src="/resources/scripts.js"></script>
    <script type="text/javascript" src="/resources/dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="/resources/jquery.min.js"></script>
    <link href="/resources/dist/css/bootstrap.css" rel="stylesheet">
</head>
<body class="container">
<jsp:include page="elements/header.jsp"/>
<div class="body-content">
    <a class="btn btn-default btn-sm" href="/admin/accounts"><fmt:message key="back"/></a>
    <br/>
    <br/>
    <form id="add" action="/admin/accounts" method="post">
        <table>
            <tr>
                <td><fmt:message key="Username"/></td>
                <td><input class="form-control" required name="name" value="${enteredValue}" type="text"/></td>
            </tr>
            <tr>
                <td><fmt:message key="Password"/></td>
                <td><input class="form-control" id="password" name="password" type="password" onkeyup='check_pass();'/>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="RepeatPassword"/></td>
                <td><input class="form-control" id="password2" name="password2" type="password"
                           onkeyup='check_pass();'/></td>
            </tr>
        </table>
        <br/>
        <br/>
        <input class="btn btn-sm btn-primary" id="saveNew" value="<fmt:message key="Save" />" type="submit" disabled/>
    </form>
</div>

</body>
</html>