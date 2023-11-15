package com.bms.model;

public class Cyclist {
	private int id;
	private String name;
	private String email;
	private String contact;
	private String card_id;
	private String enrolment_number;
	private String photo;
	
	public Cyclist(int id, String name, String email, String contact, String card_id, String enrolment_number,
			String photo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.card_id = card_id;
		this.enrolment_number = enrolment_number;
		this.photo = photo;
	}

	public Cyclist(String name, String email, String contact, String card_id, String enrolment_number, String photo) {
		super();
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.card_id = card_id;
		this.enrolment_number = enrolment_number;
		this.photo = photo;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getEnrolment_number() {
		return enrolment_number;
	}

	public void setEnrolment_number(String enrolment_number) {
		this.enrolment_number = enrolment_number;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
	
}
