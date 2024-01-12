package com.example.eindopdracht.database.classes;

public class Certification {

    private String certificationName;

    //constructor for certification
    public Certification(String certificationName){
        this.certificationName = certificationName;
    }

    //getter and setter
    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }

    
    
}
