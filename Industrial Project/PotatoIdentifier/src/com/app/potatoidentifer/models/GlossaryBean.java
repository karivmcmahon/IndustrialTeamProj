package com.app.potatoidentifer.models;

/**
 * Created by Mark on 23/09/2014.
 */
public class GlossaryBean {
    private int id;
    private String symptom;
    private String type;
    private String imageId;
    private String imageId2;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSymptom() { return symptom; }
    public void setSymptom(String symptom) { this.symptom = symptom; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getImageId() { return imageId; }
    public void setImageId(String imageId) { this.imageId = imageId; }
    
    public String getImageId2() { return imageId2; }
    public void setImageId2(String imageId2) { this.imageId2 = imageId2; }
    

}
