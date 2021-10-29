package domain;

public class Topic extends Entity{
    private Boolean ness;
    private Long disciplineId;
    private String name;
    private Integer lectureHours;
    private Integer practiceHours;
    private Integer labsHours;

    public Long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getNess() {
        return ness;
    }

    public void setNess(Boolean ness) {
        this.ness = ness;
    }
}
