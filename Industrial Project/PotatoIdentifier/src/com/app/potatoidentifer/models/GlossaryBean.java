package com.app.potatoidentifer.models;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mark on 23/09/2014.
 */
public class GlossaryBean implements Parcelable {

	private int id;
	private String symptom;
	private String type;
	private byte[] imageId;
	private byte[] imageId2;
	
	@SuppressWarnings("unused")
	// It is used...
	private ArrayList<GlossaryBean> beanList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImageId() {
		return imageId;
	}

	public void setImageId(byte[] imageId) {
		this.imageId = imageId;
	}

	public byte[] getImageId2() {
		return imageId2;
	}

	public void setImageId2(byte[] imageId2) {
		this.imageId2 = imageId2;
	}

	@SuppressWarnings("unchecked")
	public GlossaryBean(Parcel in) {
	    beanList = (ArrayList<GlossaryBean>) in.readArrayList(GlossaryBean.class.getClassLoader());
	}

	public GlossaryBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[] { this.symptom, this.type });
	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		
		public GlossaryBean createFromParcel(Parcel in) {
			return new GlossaryBean(in);
		}

		public GlossaryBean[] newArray(int size) {
			return new GlossaryBean[size];
		}
	};

}