<%@ page contentType="text/html;charset=UTF-8" language="java"
pageEncoding="UTF-8" session="false" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Список Дисциплин">
    <c:choose>
        <c:when test="${not empty disciplines}">
            <table border="1">
                <tr>
                    <th>Название</th>
                    <th>Преподаватель</th>
                    <th>Специальность</th>
                    <th>Курс</th>
                    <th>Семестр</th>
                    <th>Дата начала</th>
                    <th>Дата окончания</th>
                    <th>Лекционные часы</th>
                    <th>Практические часы</th>
                    <th>Лабораторные часы</th>
                    <th>Лекционные занятия</th>
                    <th>Практические занятия</th>
                    <th>Лабораторные занятия</th>
                    <td></td>
                </tr>
                <c:forEach var="discipline" items="${disciplines}">
                    <c:url var="editUrl" value="/discipline/edit.html">
                        <c:param name="id" value="${discipline.id}"/>
                    </c:url>
                    <tr>
                        <td>${discipline.name}</td>
                        <c:url var="topicsUrl" value="/topic/list.html"/>
                        <td><a href="${topicsUrl}">Список тем</a></td>
                        <td>${discipline.teacher}</td>
                        <td>${discipline.speciality}</td>
                        <td>${discipline.course}</td>
                        <td>${discipline.semester}</td>
                        <td>${discipline.startDateStr}</td>
                        <td>${discipline.endDateStr}</td>
                        <td>${discipline.lectureHours}</td>
                        <td>${discipline.practiceHours}</td>
                        <td>${discipline.labsHours}</td>
                        <td>${discipline.lectureLessons}</td>
                        <td>${discipline.practiceLessons}</td>
                        <td>${discipline.labsLessons}</td>
                        <td><a href="${editUrl}">редактировать</a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>Список дисциплин пуст</p>
        </c:otherwise>
    </c:choose>
    <c:url var="editUrl" value="/discipline/edit.html"/>
    <p><a href="${editUrl}">Добавить новую дисциплину</a></p>
</u:html>
