package com.rto.vehicle.info.challan.fuel.dbsproject.model.Login;

import com.google.gson.annotations.SerializedName;

public class NewUser {

	@SerializedName("Message")
	private String message;

	@SerializedName("StatusCode")
	private int statusCode;

	private int Id;

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	private String FirstName;
	private String LastName;
	private String Mobile;
	private String Email;
	private String Password;

	public NewUser(int i, String string, String string1, String string2, String string3, String string4) {
		Id = i;
		FirstName = string;
		LastName = string1;
		Mobile = string2;
		Email = string3;
		Password = string4;

	}

	public String getMessage(){
		return message;
	}

	public int getStatusCode(){
		return statusCode;
	}
}