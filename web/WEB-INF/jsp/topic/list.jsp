<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<u:html title="Список тем">
    <c:choose>
        <c:when test="${not empty topics}">
            <table border="1">
                <tr>
                    <th>Название</th>
                    <th>Лекционные часы</th>
                    <th>Практические часы</th>
                    <th>Лабораторные часы</th>
                    <th>Обязательность</th>
                </tr>
                <c:forEach var="topic" items="${topics}">
                    <c:url var="editUrl" value="/topic/edit.html">
                        <c:param name="id" value="${topic.id}"/>
                        <c:param name="disciplineId" value="${topic.disciplineId}"/>
                    </c:url>
                    <tr>
                        <td>${topic.name}</td>
                        <td>${topic.lectureHours}</td>
                        <td>${topic.practiceHours}</td>
                        <td>${topic.labsHours}</td>
                        <c:if test="${topic.ness}">
                            <td>Обязательно</td>
                        </c:if>
                        <c:if test="${not topic.ness}">
                            <td>Необязательно</td>
                        </c:if>
                        <td><a href="${editUrl}">редактировать</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>Список тем пустой</p>
        </c:otherwise>
    </c:choose>
    <c:url var="editUrl" value="/topic/edit.html">
        <c:param name="disciplineId" value="${disciplineId}"/>
    </c:url>
    <p><a href="${editUrl}">Добавить новую тему</a></p>
</u:html>