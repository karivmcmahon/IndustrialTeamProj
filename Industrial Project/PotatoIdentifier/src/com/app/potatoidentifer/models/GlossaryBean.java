package com.app.potatoidentifer.models;

public class GlossaryBean {
	private int id;
	private String symptom;
	private int imageID;
	
	public int getID()
	{
		return id;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public String getSymptom()
	{
		return symptom;
	}
	
	public void setSymptom(String symptom)
	{
		this.symptom = symptom;
	}
	
	public int getImageID()
	{
		return imageID;
	}
	
	public void setImageID(int imageID)
	{
		this.imageID = imageID;
	}
	
	
}
