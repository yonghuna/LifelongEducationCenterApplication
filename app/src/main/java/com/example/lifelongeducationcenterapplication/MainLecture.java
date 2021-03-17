package com.example.lifelongeducationcenterapplication;

public class MainLecture {
    private String name;//강좌명
    private String period;//교육기간
    private String professor;//교수진
    private String time;//수업시간
    private String studyfee;//학습비

    public MainLecture(String name, String period, String professor, String time, String studyfee) {
        this.name = name;
        this.period = period;
        this.professor = professor;
        this.time = time;
        this.studyfee = studyfee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStudyfee() {
        return studyfee;
    }

    public void setStudyfee(String studyfee) {
        this.studyfee = studyfee;
    }
}
