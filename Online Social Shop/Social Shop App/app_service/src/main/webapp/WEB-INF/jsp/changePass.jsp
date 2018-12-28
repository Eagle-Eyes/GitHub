<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}" dir="${sessionScope.dir}">
<head>
    <title><fmt:message key="Change_Password"/></title>
    <script type="text/javascript" src="/resources/scripts.js"></script>
    <script type="text/javascript" src="/resources/dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="/resources/jquery.min.js"></script>
    <link href="/resources/dist/css/bootstrap.css" rel="stylesheet">
</head>
<body class="container">
<jsp:include page="elements/header.jsp"/>

<div class="body-content">
    <a class="btn btn-default btn-sm" href="/admin"><fmt:message key="back"/></a>
    <br/>
    <form action="/admin/editPassword" method="post">
        <h2><p><fmt:message key="Change_Password"/></p></h2>
        <input hidden name="accountname" value="${accountname}" type="text"/>
        <table>
            <tr>
                <td><fmt:message key="Old_Password"/></td>
                <td><input class="form-control" id="oldPassword" name="oldPassword" type="password"/></td>
            </tr>

            <tr>
                <td><fmt:message key="New_Password"/></td>
                <td><input class="form-control" id="password" name="password" type="password" onkeyup='check_pass();'/>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="Repeat_New_Password"/></td>
                <td><input class="form-control" id="password2" name="password2" type="password"
                           onkeyup='check_pass();'/></td>
            </tr>
        </table>
        <br/>
        <input id="saveNew" class="btn btn-sm btn-primary" value="<fmt:message key="Save" />" type="submit" disabled/>
    </form>
</div>

</body>
</html>