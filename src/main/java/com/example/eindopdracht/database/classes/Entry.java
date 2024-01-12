package com.example.eindopdracht.database.classes;

import java.time.LocalDate;

public class Entry {
    private int entryId;
    private LocalDate entryDate;
    private String studentEmail;
    private String courseName;

    public Entry(int entryId, LocalDate entryDate, String studentEmail, String courseName) {
        this.entryId = entryId;
        this.entryDate = entryDate;
        this.studentEmail = studentEmail;
        this.courseName = courseName;
    }

    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
