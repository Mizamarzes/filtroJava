package com.java.personas.domain;

import java.sql.Date;

public class Persons_skill {
    private int id;
    private Date registration_date;
    private int idperson;
    private int idskill;
    
    public Persons_skill() {
    }

    public Persons_skill(int id, Date registration_date, int idperson, int idskill) {
        this.id = id;
        this.registration_date = registration_date;
        this.idperson = idperson;
        this.idskill = idskill;
    }

    public Persons_skill(Date registration_date, int idperson, int idskill) {
        this.registration_date = registration_date;
        this.idperson = idperson;
        this.idskill = idskill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public int getIdperson() {
        return idperson;
    }

    public void setIdperson(int idperson) {
        this.idperson = idperson;
    }

    public int getIdskill() {
        return idskill;
    }

    public void setIdskill(int idskill) {
        this.idskill = idskill;
    }

    

}
