package com.app.potatoidentifer.activities;

public class GlossaryRowBean {

	//Java Bean Class.
		//With getters and setters.
		
		private String title;
	    private int icon;

	    public GlossaryRowBean(String title, int icon) {
	        this.title = title;
	        this.icon = icon;

	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public int getIcon() {
	        return icon;
	    }

	    public void setIcon(int icon) {
	        this.icon = icon;
	    }
	
}
