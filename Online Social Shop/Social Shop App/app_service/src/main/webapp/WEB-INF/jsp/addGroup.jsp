<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}" dir="${sessionScope.dir}">
<head>
    <title><fmt:message key="Add_Role"/></title>
    <script type="text/javascript" src="/resources/dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="/resources/jquery.min.js"></script>
    <link href="/resources/dist/css/bootstrap.css" rel="stylesheet">
</head>
<body class="container">
<jsp:include page="elements/header.jsp"/>
<div class="body-content">
    <a class="btn btn-default btn-sm" href="/admin/groups"><fmt:message key="back"/></a>
    <br/>
    <br/>
    <form id="add" action="/admin/groups" method="post">
        <fmt:message key="Role_Name"/><input class="form-control" required type="text" value="${enteredValue}"
                                             name="displayName"/>
        <br/>
        <input class="btn btn-sm btn-primary" value="<fmt:message key="Save" />" type="submit"/>
    </form>
</div>

</body>
</html>