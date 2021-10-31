<%@page errorPage="true" language="java" contentType="text/html;
        charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Ошибка"></u:html>
<c:choose>
    <c:when test="${not empty message}">
        <p style="color: red;">${message}</p>
    </c:when>
    <c:when test="${not empty pageContext.exception}">
        <p style="color: red;">Проблемы с сервером. Обратитесь к системному администратору</p>
    </c:when>
    <c:when test="${not empty pageContext.errorData.requestURI}">
        <p style="color: red;">Запрошенная страница ${pageContext.errorData.requestURI} нe найдена на сервере</p>
    </c:when>
    <c:otherwise>
        <p style="color: red;">Непредвиденная ошибка приожения</p>
    </c:otherwise>
</c:choose>