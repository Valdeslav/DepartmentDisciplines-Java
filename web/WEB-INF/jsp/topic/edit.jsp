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
    <form action="${saveUrl}" method="post">
        <c:if test="${not empty topic.id}">
            <input type="hidden" name="id" value="${topic.id}">
        </c:if>
        <label for="name">Название</label><br>
        <input id="name" name="name" value="${topic.name}"><br>

        <label for="lecture-hours">Лекционные часы</label><br>
        <input id="lecture-hours" name="lectureHours" value="${topic.lectureHours}" type="number"><br>

        <label for="practice-hours">Практические часы</label><br>
        <input id="practice-hours" name="practiceHours" value="${topic.practiceHours}" type="number"><br>

        <label for="labs-hours">Лабораторные часы</label><br>
        <input id="labs-hours" name="labsHours" value="${topic.labsHours}" type="number"><br>

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
            <button>Удалить</button>
        </form>
    </c:if>
    <script type="text/javascript">
        function validate_form(){
            if(document.topic.lectureHours.value == "" ||
                Number.parseInt(document.topic.lectureHours.value)<0){
                alert("Количество лекционных часов не должно быть отрицательным!");
                return false;
            }
            if(document.topic.practiceHours.value == "" || Number.parseInt(document.topic.practiceHours.value)<0){
                alert("Количество практических часов не должно быть отрицательным!");
                return false;
            }
            if(document.topic.labsHours.value == "" || Number.parseInt(document.topic.labsHours.value)<0){
                alert("Количество лабораторных часов не должно быть отрицательным!");
                return false;
            }
            return true;
        }
    </script>
</u:html>
