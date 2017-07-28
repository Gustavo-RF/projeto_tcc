package com.projeto.model;

import java.util.List;

public class Artigo {

	private String author;
	private String title;
	private String date;
	private String place;
	private List<String> pontuation;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public List<String> getPontuation() {
		return pontuation;
	}
	public void setPontuation(List<String> pontuation) {
		this.pontuation = pontuation;
	}
}
