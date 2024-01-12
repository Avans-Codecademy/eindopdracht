package com.example.eindopdracht.database.classes;

import java.util.ArrayList;

import com.example.eindopdracht.database.classes.Enums.Level;

public class Course {
    private String name;
    private String subject;
    private String introductionText;
    private Level level;
    private ArrayList<Module> modules;
    private ArrayList<Course> interestingCourses;

    public Course(String name, String subject, String introductionText, Level level) {
        this.name = name;
        this.subject = subject;
        this.introductionText = introductionText;
        this.level = level;
        this.modules = new ArrayList<Module>();
        this.interestingCourses = new ArrayList<Course>();
    }

    public void addModule(Module contentItemID) {
        modules.add(contentItemID);
    }

     public void addInterestingCourses(Course name) {
        interestingCourses.add(name);
    }

    public String getIntroductionText() {
        return introductionText;
    }

    public void setIntroductionText(String introductionText) {
        this.introductionText = introductionText;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ArrayList<Course> getInterestingCourses() {
        return interestingCourses;
    }

    public void setInterestingCourses(ArrayList<Course> interestingCourses) {
        this.interestingCourses = interestingCourses;
    }

    @Override
    public String toString() {
        return "Cursus name:" + name + ", Subject:" + subject + ", Introductiontext:" + introductionText + ", Level" + level;
    }
}
