package com.app.potatoidentifer.models;

/**
 * Created by Ewan on 23/09/2014.
 */
public class CharacteristicBean {

    private int ID;
    private String Name;
    private String Question;

    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    public String getName() { return Name; }
    public void setName(String s) { Name = s; }

    public String getQuestion() { return Question; }
    public void setQuestion(String Q) { Question = Q; }
}
