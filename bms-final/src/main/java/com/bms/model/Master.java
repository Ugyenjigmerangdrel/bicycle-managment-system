package com.bms.model;

public class Master {
	private String transaction_id;
	private String connection_status;
	private String cycle_no;
	private String fromtime;
	private String totime;
	private String used_by;
	private String user_id;
	
	public Master(String transaction_id, String connection_status, String cycle_no, String fromtime, String totime,
			String used_by, String user_id) {
		super();
		this.transaction_id = transaction_id;
		this.connection_status = connection_status;
		this.cycle_no = cycle_no;
		this.fromtime = fromtime;
		this.totime = totime;
		this.used_by = used_by;
		this.user_id = user_id;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getConnection_status() {
		return connection_status;
	}

	public void setConnection_status(String connection_status) {
		this.connection_status = connection_status;
	}

	public String getCycle_no() {
		return cycle_no;
	}

	public void setCycle_no(String cycle_no) {
		this.cycle_no = cycle_no;
	}

	public String getFromtime() {
		return fromtime;
	}

	public void setFromtime(String fromtime) {
		this.fromtime = fromtime;
	}

	public String getTotime() {
		return totime;
	}

	public void setTotime(String totime) {
		this.totime = totime;
	}

	public String getUsed_by() {
		return used_by;
	}

	public void setUsed_by(String used_by) {
		this.used_by = used_by;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	
}
