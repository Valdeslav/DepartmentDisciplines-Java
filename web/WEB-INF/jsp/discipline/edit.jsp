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
    <form action="${saveUrl}" method="post" id="disciplineForm" name="disciplineForm" onsubmit="return validate_form();">
        <c:if test="${not empty discipline.id}">
            <input type="hidden" name="id" value="${discipline.id}">
        </c:if>
        <label for="name">Название</label><br>
        <input id="name" name="name" value="${discipline.name}"><br>

        <label for="teacher">Преподаватель</label><br>
        <input id="teacher" name="teacher" value="${discipline.teacher}" required><br>

        <label for="speciality">Специальность</label><br>
        <input id="speciality" name="speciality" value="${discipline.speciality}" required><br>

        <label for="course">Курс</label><br>
        <input id="course" name="course" value="${discipline.course}" type="number" required><br>

        <label for="semester">Семестр</label><br>
        <input id="semester" name="semester" value="${discipline.semester}" type="number" required><br>

        <label for="start-date">Дата начала</label><br>
        <input id="start-date" name="startDate" value="${discipline.startDateStr}" type="date" required><br>

        <label for="end-date">Дата окончания</label><br>
        <input id="end-date" name="endDate" value="${discipline.endDateStr}" type="date" required><br>

        <label for="lecture-hours">Лекционные часы</label><br>
        <input id="lecture-hours" name="lectureHours" value="${discipline.lectureHours}" type="number" required><br>

        <label for="practice-hours">Практические часы</label><br>
        <input id="practice-hours" name="practiceHours" value="${discipline.practiceHours}" type="number" required><br>

        <label for="labs-hours">Лабораторные часы</label><br>
        <input id="labs-hours" name="labsHours" value="${discipline.labsHours}" type="number" required><br>

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
            let disciplineForm = document.getElementById("disciplineForm");
            if(disciplineForm.name.value === ""){
                alert("Введите название дисциплины!");
                return false;
            }
            if(disciplineForm.course.value == "" || Number.parseInt(disciplineForm.course.value)<=0){
                alert("Курс должен быть положительным числом!");
                return false;
            }
            if(disciplineForm.semester.value == "" || Number.parseInt(disciplineForm.semester.value)<=0){
                alert("Семестр должен быть положительным числом!");
                return false;
            }
            if(disciplineForm.lectureHours.value == "" || Number.parseInt(disciplineForm.lectureHours.value)<0){
                alert("Количество лекционных часов не должно быть отрицательным!");
                return false;
            }
            if(document.disciplineForm.practiceHours.value == "" || Number.parseInt(disciplineForm.practiceHours.value)<0){
                alert("Количество практических часов не должно быть отрицательным!");
                return false;
            }
            if(disciplineForm.labsHours.value == "" || Number.parseInt(disciplineForm.labsHours.value)<0){
                alert("Количество лабораторных часов не должно быть отрицательным!");
                return false;
            }
            return true;
        }
    </script>
</u:html>
