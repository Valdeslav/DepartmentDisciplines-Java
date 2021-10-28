package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.lang.ArithmeticException;

public class Discipline extends Entity{
    private String name;
    private String teacher;
    private String speciality;
    private Integer course;
    private Integer semester;
    private Calendar startDate;
    private String startDateStr;
    private Calendar endDate;
    private String endDateStr;
    private Integer lectureHours;
    private Integer practiceHours;
    private Integer labsHours;
    private Integer lectureLessons;
    private Integer practiceLessons;
    private Integer labsLessons;

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.startDateStr = df.format(startDate.getTime());
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr() {
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        this.endDateStr = df.format(endDate.getTime());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = Calendar.getInstance();
        this.startDate.setTime(startDate.getTime());
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = Calendar.getInstance();
        this.endDate.setTime(endDate.getTime());
    }

    public Integer getLectureHours() {
        return lectureHours;
    }

    public void setLectureHours(Integer lectureHours) {
        this.lectureHours = lectureHours;
    }

    public Integer getPracticeHours() {
        return practiceHours;
    }

    public void setPracticeHours(Integer practiceHours) {
        this.practiceHours = practiceHours;
    }

    public Integer getLabsHours() {
        return labsHours;
    }

    public void setLabsHours(Integer labsHours) {
        this.labsHours = labsHours;
    }

    public Integer getLectureLessons() {
        return lectureLessons;
    }

    public void setLectureLessons() {
        try{
            if(lectureHours == 0)
                throw new ArithmeticException();
            int weeks = endDate.get(Calendar.WEEK_OF_YEAR) - startDate.get(Calendar.WEEK_OF_YEAR);
            if (weeks == 0){
                this.lectureLessons = lectureHours / 2;
                return;
            }
            this.lectureLessons = (int) Math.ceil(((double)lectureHours / 2.0) / (double)weeks);
        } catch( NullPointerException | ArithmeticException e){
            this.lectureLessons = 0;
        }
    }

    public Integer getPracticeLessons() {
        return practiceLessons;
    }

    public void setPracticeLessons() {
        try{
            if(practiceHours == 0)
                throw new ArithmeticException();
            int weeks = endDate.get(Calendar.WEEK_OF_YEAR) - startDate.get(Calendar.WEEK_OF_YEAR);
            if (weeks == 0){
                this.practiceLessons = practiceHours / 2;
                return;
            }
            this.practiceLessons = (int) Math.ceil(((double)practiceHours / 2.0) / (double)weeks);
        } catch( NullPointerException | ArithmeticException e){
            this.practiceLessons = 0;
        }
    }

    public Integer getLabsLessons() {
        return labsLessons;
    }

    public void setLabsLessons() {
        try{
            if(labsHours == 0)
                throw new ArithmeticException();
            int weeks = endDate.get(Calendar.WEEK_OF_YEAR) - startDate.get(Calendar.WEEK_OF_YEAR);
            if (weeks == 0){
                this.labsLessons = labsHours / 2;
                return;
            }
            this.labsLessons = (int)Math.ceil(((double)labsHours / 2.0) / (double) weeks);
        } catch( NullPointerException | ArithmeticException e){
            this.labsLessons = 0;
        }
    }
}
