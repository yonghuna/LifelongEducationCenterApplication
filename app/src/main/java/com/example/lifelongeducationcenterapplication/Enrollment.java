package com.example.lifelongeducationcenterapplication;

import com.google.gson.annotations.SerializedName;

public class Enrollment {

    @SerializedName("subjectnumber")
    int subjectnumber;

    @SerializedName("subjectyear")
    int subjectyear;

    @SerializedName("subjectsemester")
    int subjectsemester;

    @SerializedName("grade")
    String grade;

    @SerializedName("payment")
    String payment;

    @SerializedName("Certificaterealname")
    String Certificaterealname;

    @SerializedName("Certificaterandomname")
    String Certificaterandomname;

    @SerializedName("name")
    String name;

    @SerializedName("paymentnumber")
    String paymentnumber;

    @SerializedName("subjectname")
    String subjectname;

    @SerializedName("studyfee")
    String studyfee;

    @SerializedName("division")
    public String division;

    @SerializedName("startdate")
    public String startDate;

    @SerializedName("enddate")
    public  String endDate;

    @SerializedName("phonenumber")
    public  String phonenumber;

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }



    public String getStudyfee() {
        return studyfee;
    }

    public void setStudyfee(String studyfee) {
        this.studyfee = studyfee;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getPaymentnumber() {
        return paymentnumber;
    }

    public void setPaymentnumber(String paymentnumber) {
        this.paymentnumber = paymentnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubjectyear() {
        return subjectyear;
    }

    public void setSubjectyear(int subjectyear) {
        this.subjectyear = subjectyear;
    }

    public int getSubjectsemester() {
        return subjectsemester;
    }

    public void setSubjectsemester(int subjectsemester) {
        this.subjectsemester = subjectsemester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getCertificaterealname() {
        return Certificaterealname;
    }

    public void setCertificaterealname(String certificaterealname) {
        Certificaterealname = certificaterealname;
    }

    public String getCertificaterandomname() {
        return Certificaterandomname;
    }

    public void setCertificaterandomname(String certificaterandomname) {
        Certificaterandomname = certificaterandomname;
    }

    public int getSubjectnumber() {
        return subjectnumber;
    }

    public void setSubjectnumber(int subjectnumber) {
        this.subjectnumber = subjectnumber;
    }
}
