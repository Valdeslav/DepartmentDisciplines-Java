<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" session="false" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:choose>
    <c:when test="${not empty topic}">
        <c:set var="title" value="Редактирование темы ${topic.name}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="Добавление новой темы"/>
        <jsp:useBean id="topic" class="domain.Topic"/>
    </c:otherwise>
</c:choose>

<u:html title="${title}">
    <c:url var="saveUrl" value="/topic/save.html"/>
    <form action="${saveUrl}" method="post" id="topicForm" name="topicForm" onsubmit="return validate_form();">
        <c:if test="${not empty topic.id}">
            <input type="hidden" name="id" value="${topic.id}">
        </c:if>
        <label for="name">Название</label><br>
        <input id="name" name="name" value="${topic.name}" required><br>

        <label for="lecture-hours">Лекционные часы</label><br>
        <input id="lecture-hours" name="lectureHours" value="${topic.lectureHours}" type="number" required><br>

        <label for="practice-hours">Практические часы</label><br>
        <input id="practice-hours" name="practiceHours" value="${topic.practiceHours}" type="number" required><br>

        <label for="labs-hours">Лабораторные часы</label><br>
        <input id="labs-hours" name="labsHours" value="${topic.labsHours}" type="number" required><br>

        <label for="ness">Обязательность темы</label><br>
        <input id="ness" name="ness" type="checkbox"
        <c:if test="${topic.ness == true}"> checked</c:if>><br>
        <input name="disciplineId" type="hidden" value="${disciplineId}">

        <button>Сохранить</button>
    </form>
    <c:if test="${not empty topic.id}">
        <c:url var="deleteUrl" value="/topic/delete.html"/>
        <form action="${deleteUrl}" method="post">
            <input type="hidden" name="id" value="${topic.id}">
            <input type="hidden" name="disciplineId" value="${topic.disciplineId}">
            <button>Удалить</button>
        </form>
    </c:if>
    <script type="text/javascript">
        function validate_form(){
            let topicForm = document.getElementById("topicForm");
            if(document.topicForm.name === ""){
                alert("Введите название темы!");
                return false;
            }
            if(topicForm.lectureHours.value == "" ||
                Number.parseInt(topicForm.lectureHours.value)<0){
                alert("Количество лекционных часов не должно быть отрицательным!");
                return false;
            }
            if(topicForm.practiceHours.value == "" || Number.parseInt(topicForm.practiceHours.value)<0){
                alert("Количество практических часов не должно быть отрицательным!");
                return false;
            }
            if(topicForm.labsHours.value == "" || Number.parseInt(topicForm.labsHours.value)<0){
                alert("Количество лабораторных часов не должно быть отрицательным!");
                return false;
            }
            return true;
        }
    </script>
</u:html>
