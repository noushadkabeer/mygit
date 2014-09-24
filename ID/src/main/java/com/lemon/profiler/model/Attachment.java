package com.lemon.profiler.model;

import java.io.Serializable;

public class Attachment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String title;
	private String downloadURL;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public Attachment() {
	}

	public Attachment(String id, String name, String title, String downloadURL) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.downloadURL = downloadURL;
	}
	
	

}