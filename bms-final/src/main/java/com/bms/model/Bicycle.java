package com.bms.model;

public class Bicycle {
	private int id;
	private String bicycle_no;
	private String CUUID;
	private String SUUID;

	public Bicycle(int id, String bicycle_no, String cUUID, String sUUID) {
		super();
		this.id = id;
		this.bicycle_no = bicycle_no;
		CUUID = cUUID;
		SUUID = sUUID;
	}

	public Bicycle(String bicycle_no, String cUUID, String sUUID) {
		super();
		this.bicycle_no = bicycle_no;
		CUUID = cUUID;
		SUUID = sUUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBicycle_no() {
		return bicycle_no;
	}

	public void setBicycle_no(String bicycle_no) {
		this.bicycle_no = bicycle_no;
	}

	public String getCUUID() {
		return CUUID;
	}

	public void setCUUID(String cUUID) {
		CUUID = cUUID;
	}

	public String getSUUID() {
		return SUUID;
	}

	public void setSUUID(String sUUID) {
		SUUID = sUUID;
	}

}
