<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" session="false" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
    <c:when test="${not empty discipline}">
        <c:set var="title" value="Редактирование дисциплины ${discipline.name}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление новой дисциплины"/>
        <jsp:useBean id="discipline" class="domain.Discipline"/>
    </c:otherwise>
</c:choose>

<u:html title="Список Дисциплин">
    <c:url var="saveUrl" value="/discipline/save.html"/>
    <form action="${saveUrl}" method="post">
        <c:if test="${not empty discipline.id}">
            <input type="hidden" name="id" value="${discipline.id}">
        </c:if>
        <label for="name">Название</label><br>
        <input id="name" name="name" value="${discipline.name}"><br>

        <label for="teacher">Преподаватель</label><br>
        <input id="teacher" name="teacher" value="${discipline.teacher}"><br>

        <label for="speciality">Специальность</label><br>
        <input id="speciality" name="speciality" value="${discipline.speciality}"><br>

        <label for="course">Курс</label><br>
        <input id="course" name="course" value="${discipline.course}"><br>

        <label for="semester">Семестр</label><br>
        <input id="semester" name="semester" value="${discipline.semester}"><br>

        <label for="start-date">Дата начала</label><br>
        <input id="start-date" name="startDate" value="${discipline.startDateStr}"><br>

        <label for="end-date">Дата окончания</label><br>
        <input id="end-date" name="endDate" value="${discipline.endDateStr}"><br>

        <label for="lecture-hours">Лекционные часы</label><br>
        <input id="lecture-hours" name="lectureHours" value="${discipline.lectureHours}"><br>

        <label for="practice-hours">Практические часы</label><br>
        <input id="practice-hours" name="practiceHours" value="${discipline.practiceHours}"><br>

        <label for="labs-hours">Лабораторные часы</label><br>
        <input id="labs-hours" name="labsHours" value="${discipline.labsHours}"><br>

        <label for="lecture-lessons">Лабораторные занятия</label><br>
        <input id="lecture-lessons" name="lectureLessons" value="${discipline.lectureLessons}"><br>

        <label for="practice-lessons">Практические занятия</label><br>
        <input id="practice-lessons" name="practiceLessons" value="${discipline.practiceLessons}"><br>

        <label for="labs-lessons">Лабораторные занятия</label><br>
        <input id="labs-lessons" name="labsLessons" value="${discipline.labsLessons}"><br>
        <button>Сохранить</button>
    </form>
    <c:if test="${not empty discipline.id}">
        <c:url var="deleteUrl" value="/discipline/delete.html"/>
        <form action="${deleteUrl}" method="post">
            <input type="hidden" name="id" value="${discipline}">
            <button>Удалить</button>
        </form>
    </c:if>
</u:html>
