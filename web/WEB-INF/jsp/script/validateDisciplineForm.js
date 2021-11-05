function validate_form(){
    let disciplineForm = document.getElementById("disciplineForm");
    if(disciplineForm.name.value === ""){
        alert("Введите название дисциплины!");
        return false;
    }
    if(disciplineForm.course.value == "" || Number.parseInt(document.discipline.course.value)<=0){
        alert("Курс должен быть положительным числом!");
        return false;
    }
    if(disciplineForm.semester.value == "" || Number.parseInt(document.discipline.semester.value)<=0){
        alert("Семестр должен быть положительным числом!");
        return false;
    }
    if(disciplineForm.lectureHours.value == "" || Number.parseInt(document.discipline.lectureHours.value)<0){
        alert("Количество лекционных часов не должно быть отрицательным!");
        return false;
    }
    if(document.disciplineForm.practiceHours.value == "" || Number.parseInt(document.discipline.practiceHours.value)<0){
        alert("Количество практических часов не должно быть отрицательным!");
        return false;
    }
    if(disciplineForm.labsHours.value == "" || Number.parseInt(document.discipline.labsHours.value)<0){
        alert("Количество лабораторных часов не должно быть отрицательным!");
        return false;
    }
    return true;
}