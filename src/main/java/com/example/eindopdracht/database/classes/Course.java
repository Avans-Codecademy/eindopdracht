package com.example.eindopdracht.database.classes;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.eindopdracht.database.classes.Enums.Level;
public class Course {
    private String name;
    private String subject;
    private String introductiontext;
    private Level level;
    private ArrayList<Module> modules;
    private ArrayList<Course> interestingCourses;
    //by checking every module in course and checking if the boolean is true(meaning its completed) it generates an certificate for user
    private HashMap<Module,Boolean> completed;

    //constructor for Course
    public Course(String name, String subject, String introductiontext, Level level, ArrayList<Module> modules, ArrayList<Course> interestingCourses) {
        this.name = name;
        this.subject = subject;
        this.introductiontext = introductiontext;
        this.level = level;
        this.modules = new ArrayList<Module>();
        this.interestingCourses = new ArrayList<Course>();
    }

    //adds the module to the hasmap completed, indicating its done
    public void finishModule(Module module) {
        Boolean var = completed.get(module);
        if (var = false) {
            completed.put(module, true);
        } else if (var = true){
            System.out.println("Module already completed");
        }
    }


    //gets all available modules and then sees if the method is one of them, and then add it to the modules of course
    public void addModule(Module module) {
        ArrayList<Module> holder = Module.getAllModules();
        ArrayList<Module> test = getAvailableModule(holder);
        if (test.contains(module)) {
            modules.add(module);
            module.setCourse(name);
        }
    }

    //method to get all modules with no course
    static ArrayList<Module> getAvailableModule(ArrayList<Module> allModules) {
        ArrayList<Module> available = new ArrayList<Module>();
        for (int index = 0; index < Module.getAllModules().size(); index++) {
            Module temp = Module.getAllModules().get(index);
            String check = temp.getCourse();
            if(check == null){
                available.add(temp);
            }
        }
        return available;

    }

    //adds interesting courses
     public void addInterestingCourses(Course name) {
        interestingCourses.add(name);
    }

    //below are getter and setters
    public String getIntroductiontext() {
        return introductiontext;
    }

    public void setIntroductiontext(String introductiontext) {
        this.introductiontext = introductiontext;
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
    //end getter setters


    //tostring, still needs new values
    @Override
    public String toString() {
        return "Cursus name:" + name + ", Subject:" + subject + ", Introductiontext:" + introductiontext + ", Level:" + level + ", modules:" + modules;
    }
}
