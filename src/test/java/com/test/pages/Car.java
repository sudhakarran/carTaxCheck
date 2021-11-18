package com.test.pages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "REGISTRATION","MAKE","MODEL","COLOR","YEAR" })
public class Car {

    public String REGISTRATION;
    public String MAKE;
    public String MODEL;
    public String COLOR;
    public String YEAR;

    public Car(String REGISTRATION, String MAKE, String MODEL, String COLOR, String YEAR) {
        this.REGISTRATION = REGISTRATION;
        this.MAKE = MAKE;
        this.MODEL = MODEL;
        this.COLOR = COLOR;
        this.YEAR = YEAR;
    }

    public Car() {
        //Dummey Constructor
    }

    public String getREGISTRATION() {
        return REGISTRATION;
    }

    public void setREGISTRATION(String REGISTRATION) {
        this.REGISTRATION = REGISTRATION;
    }

    public String getMAKE() {
        return MAKE;
    }

    public void setMAKE(String MAKE) {
        this.MAKE = MAKE;
    }

    public String getMODEL() {
        return MODEL;
    }

    public void setMODEL(String MODEL) {
        this.MODEL = MODEL;
    }

    public String getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

}
