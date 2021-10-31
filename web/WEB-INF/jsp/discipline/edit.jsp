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

<u:html title="${title}">
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
        <input id="course" name="course" value="${discipline.course}" type="number"><br>

        <label for="semester">Семестр</label><br>
        <input id="semester" name="semester" value="${discipline.semester}" type="number"><br>

        <label for="start-date">Дата начала</label><br>
        <input id="start-date" name="startDate" value="${discipline.startDateStr}" type="date"><br>

        <label for="end-date">Дата окончания</label><br>
        <input id="end-date" name="endDate" value="${discipline.endDateStr}" type="date"><br>

        <label for="lecture-hours">Лекционные часы</label><br>
        <input id="lecture-hours" name="lectureHours" value="${discipline.lectureHours}" type="number"><br>

        <label for="practice-hours">Практические часы</label><br>
        <input id="practice-hours" name="practiceHours" value="${discipline.practiceHours}" type="number"><br>

        <label for="labs-hours">Лабораторные часы</label><br>
        <input id="labs-hours" name="labsHours" value="${discipline.labsHours}" type="number"><br>

        <button>Сохранить</button>
    </form>
    <c:if test="${not empty discipline.id}">
        <c:url var="deleteUrl" value="/discipline/delete.html"/>
        <form action="${deleteUrl}" method="post">
            <input type="hidden" name="id" value="${discipline.id}">
            <button>Удалить</button>
        </form>
    </c:if>
    <script type="text/javascript">
        function validate_form(){
            if(document.discipline.course.value == "" || Number.parseInt(document.discipline.course.value)<=0){
                alert("Курс должен быть положительным числом!");
                return false;
            }
            if(document.discipline.semester.value == "" || Number.parseInt(document.discipline.semester.value)<=0){
                alert("Семестр должен быть положительным числом!");
                return false;
            }
            if(document.discipline.lectureHours.value == "" || Number.parseInt(document.discipline.lectureHours.value)<0){
                alert("Количество лекционных часов не должно быть отрицательным!");
                return false;
            }
            if(document.discipline.practiceHours.value == "" || Number.parseInt(document.discipline.practiceHours.value)<0){
                alert("Количество практических часов не должно быть отрицательным!");
                return false;
            }
            if(document.discipline.labsHours.value == "" || Number.parseInt(document.discipline.labsHours.value)<0){
                alert("Количество лабораторных часов не должно быть отрицательным!");
                return false;
            }
            return true;
        }
    </script>
</u:html>
