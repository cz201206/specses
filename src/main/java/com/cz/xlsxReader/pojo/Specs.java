package com.cz.xlsxReader.pojo;

public class Specs {
	public String name,title;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Specs [name=" + name + ", title=" + title + "]";
	}
	
}
