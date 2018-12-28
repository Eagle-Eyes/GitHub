<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<c:if test="${sessionScope.lang eq null}">
    <c:set var="lang" value="fa" scope="session"/>
    <c:set var="dir" value="rtl" scope="session"/>
</c:if>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}" dir="${sessionScope.dir}">
<head>
    <title><fmt:message key="Welcome"/></title>
    <script type="text/javascript" src="/resources/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="/resources/scripts.js"></script>
    <link href="/resources/dist/css/bootstrap.css" rel="stylesheet">
</head>
<body class="container">
<jsp:include page="../elements/header.jsp"/>

<h2><fmt:message key="Welcome_to_user_management,_please_select_the_component"/></h2>
<br/>
<a class="btn btn-lg btn-default" href="/admin/accounts"><fmt:message key="User"/></a>
<a class="btn btn-lg btn-default" href="/admin/groups"><fmt:message key="Group"/></a>
<a class="btn btn-lg btn-default" href="/admin/roles"><fmt:message key="Role"/></a>
<a class="btn btn-lg btn-default" href="/admin/actions"><fmt:message key="Action"/></a>
<br/>
<br/>
</body>
</html>