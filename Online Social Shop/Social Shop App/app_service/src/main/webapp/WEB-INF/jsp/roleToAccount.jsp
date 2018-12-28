<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}" dir="${sessionScope.dir}">
<head>
    <title><fmt:message key="Add_Role_to_User"/></title>
    <script type="text/javascript" src="/resources/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="/resources/scripts.js"></script>
    <link href="/resources/dist/css/bootstrap.css" rel="stylesheet">
</head>
<body class="container">
<jsp:include page="elements/header.jsp"/>

<div class="body-content">
    <a class="btn btn-default btn-sm" href="/admin/accounts"><fmt:message key="back"/></a>
    <br/>
    <br/>
    <form action="/admin/accounts/roles" method="post">
        <p><fmt:message key="Select_User"/>
            <select class="form-control" required id="selectbox" name="id"
                    onchange="window.location =('/admin/accounts/accountRoles?id='+document.getElementById('selectbox').value);viewOptions();"
                    onload="viewOptions()">>
                <option value="0"></option>
                <c:forEach items="${accountRepo}" var="account">
                    <option value="${account.id}"
                            <c:if test="${selectedaccount.id==account.id}">selected</c:if>>${account.name}</option>
                </c:forEach>
            </select>
        </p>
        <br/>
        <br/>
        <br/>
        <div id="optionsFieldset">
            <fieldset style="display: inline-block">
                <legend><fmt:message key="Choose_Roles"/></legend>

                <input type="checkbox" id="allActions" onChange="handle(this)"/>
                <label for="allActions"><fmt:message key="All"/></label>

                <c:forEach var="role" items="${roleRepo}">
                    <div>
                        <c:set var="contains" value="false"/>
                        <c:forEach var="item" items="${accountroleids}">
                            <c:if test="${item eq role.id}">
                                <c:set var="contains" value="true"/>
                            </c:if>
                        </c:forEach>
                        <input type="checkbox" id="rolename${role.id}" name="roles"
                               value="${role.id}" <c:if test="${contains}">checked</c:if>/>
                        <label for="rolename${role.id}">${role.displayName}</label>
                    </div>
                </c:forEach>
                <input type="checkbox" id="none" name="roles" value="0" checked hidden/>
                <br/>
                <input class="btn btn-primary" type="submit" value="<fmt:message key="Save"/>"/>
            </fieldset>
        </div>
    </form>
</div>

</body>
</html>