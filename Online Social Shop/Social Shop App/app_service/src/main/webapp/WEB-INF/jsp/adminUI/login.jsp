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
    <title><fmt:message key="Users_Login"/></title>
    <script type="text/javascript" src="/resources/scripts.js"/>
    <script type="text/javascript" src="/resources/dist/js/bootstrap.js"/>
    <script type="text/javascript" src="/resources/jquery.min.js"></script>
    <link href="/resources/dist/css/bootstrap.css" rel="stylesheet">
</head>
<body class="container">

<jsp:include page="../elements/header.jsp"/>

<div class="body-content">
    <div align="center" style="border:2px solid red; align-items: center">
        <table>
            <h2><p><fmt:message key="Please_log_in_to_system"/></p></h2>
            <form action="${pageContext.request.contextPath}/loginAction" method="post">
                <tr>
                    <td><fmt:message key="Username"/> :</td>
                    <td><input class="form-control" type="text" name="username"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="Password"/> :</td>
                    <td><input class="form-control" type="password" name="password"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><br/><input class="btn btn-primary" type="submit" value="<fmt:message key="Login" />"></td>
                </tr>
            </form>
        </table>
        <br/>
    </div>
    <c:if test="${param.error ne null}">
        <br/>
        <span style="color:red; margin:auto; display:table;"><fmt:message
                key="Username_or_Password_is_not_correct"/></span>
    </c:if>
    <c:if test="${param.logout ne null}">
        <br/>
        <span style="color:red; margin:auto; display:table;"><fmt:message
                key="You_were_logged_out_successfully"/></span>
    </c:if>
</div>
</body>
</html>