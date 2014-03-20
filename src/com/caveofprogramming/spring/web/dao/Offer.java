package com.caveofprogramming.spring.web.dao;

import javax.validation.constraints.*;

import com.caveofprogramming.spring.web.validation.ValidEmail;

public class Offer {
	private int id;
	
	@NotNull
	@Size(min=5, max=100, message="Name must be between 5 and 100 characters...try again")
	private String name;
	@NotNull
	//@Pattern(regexp=".*\\@.*\\..*", message="Not a valid mail adress Must have an @ ... try again")
	@ValidEmail(min = 6, message="This email adress is not valid.")
	private String email;
	@NotNull
	@Size(min=5, max=100, message="Text must be between 25 and 255 characters...try again")
	private String text;
	
	public Offer(){
		
	}
	
	public Offer(String name, String email, String text) {
		this.name = name;
		this.email = email;
		this.text = text;
	}
	
	public Offer(int id, String name, String email, String text) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", email=" + email
				+ ", text=" + text + "]";
	}

}
