<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>

<c:if test="${sessionScope.lang eq null}">
    <c:set var="lang" value="fa" scope="session"/>
    <c:set var="dir" value="rtl" scope="session"/>
</c:if>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<div class="jumbotron">
    <div dir="<c:choose><c:when test="${sessionScope.dir =='ltr'}">rtl</c:when><c:when test="${sessionScope.dir =='rtl'}">ltr</c:when></c:choose>">
        <a class="btn btn-info"
                <c:choose>
                    <c:when test="${sessionScope.lang =='en'}">
                        href="/admin/changeLanguage?lang=fa"><b>فارسی</b>
                    </c:when>
                    <c:when test="${sessionScope.lang =='fa'}">
                        href="/admin/changeLanguage?lang=en"><b>English</b>
                    </c:when>
                </c:choose>
        </a>
        <br/>
    </div>
    <sec:authorize access="isAuthenticated()">
        <fmt:message key="Welcome"/> <b>${sessionScope.name}</b>
        <br/>
        <br/>
        <a class="btn btn-sm btn-primary" href="/admin/editPasswordLoad?name=${sessionScope.name}"><fmt:message
                key="Change_Password"/></a>
        <a class="btn btn-sm btn-primary" href="/admin/logout"><fmt:message key="LOG_OUT"/></a>
        <br/>
        <br/>
        <c:if test="${message ne null}"><span class="alert alert-success"><fmt:message key="${message}"/></span></c:if>
    </sec:authorize>
</div>
