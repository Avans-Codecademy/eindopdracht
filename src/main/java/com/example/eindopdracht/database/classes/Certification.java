package com.example.eindopdracht.database.classes;

public class Certification {
    private String certificatiodID;
    private int grade;
    private String workername;

    public Certification(String certificatiodID, int grade, String workername) {
        this.certificatiodID = certificatiodID;
        this.grade = grade;
        this.workername = workername;
    }

    public String getCertificatiodID() {
        return certificatiodID;
    }

    public void setCertificatiodID(String certificatiodID) {
        this.certificatiodID = certificatiodID;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getWorkername() {
        return workername;
    }

    public void setWorkername(String workername) {
        this.workername = workername;
    }
    
    
}
