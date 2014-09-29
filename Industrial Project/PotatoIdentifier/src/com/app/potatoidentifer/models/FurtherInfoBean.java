package com.app.potatoidentifer.models;

public class FurtherInfoBean {
    private int id;
    private String symptom;
    private String type;
    private byte[] imageID1;
    private byte[] imageID2;
    private byte[] imageID3;
    private byte[] imageID4;
    private byte[] imageID5;
    private byte[] imageID6;
    private String basicFacts;
    private String control;
    private String diagnostics;

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setImageID(byte[] imageID1) {
        this.imageID1 = imageID1;
    }

    public byte[] getImageID() {
        return imageID1;
    }

    public void setImageID2(byte[] imageID2) {
        this.imageID2 = imageID2;
    }

    public byte[] getImageID2() {
        return imageID2;
    }

    public void setImageID3(byte[] imageID3) {
        this.imageID3 = imageID3;
    }

    public byte[] getImageID3() {
        return imageID3;
    }

    public void setImageID4(byte[] imageID4) {
        this.imageID4 = imageID4;
    }

    public byte[] getImageID4() {
        return imageID4;
    }

    public void setImageID5(byte[] imageID5) {
        this.imageID5 = imageID5;
    }

    public byte[] getImageID5() {
        return imageID5;
    }

    public void setImageID6(byte[] imageID6) {
        this.imageID6 = imageID6;
    }

    public byte[] getImageID6() {
        return imageID6;
    }

    public void setBasicFacts(String basicFacts) {
        this.basicFacts = basicFacts;
    }

    public String getBasicFacts() {
        return basicFacts;
    }

    public void setDiagnostics(String diagnostics) {
        this.diagnostics = diagnostics;
    }

    public String getDiagnostics() {
        return diagnostics;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getControl() {
        return control;
    }
}
