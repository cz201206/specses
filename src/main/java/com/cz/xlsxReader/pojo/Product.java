package com.cz.xlsxReader.pojo;

public class Product {

	public String name ,title,fileName;
	
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


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	@Override
	public String toString() {
		return "Product [name=" + name + ", title=" + title + ", fileName=" + fileName + "]";
	}

	
}
