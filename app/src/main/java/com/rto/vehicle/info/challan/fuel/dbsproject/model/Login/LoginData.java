package com.rto.vehicle.info.challan.fuel.dbsproject.model.Login;

import com.google.gson.annotations.SerializedName;

public class LoginData {


	private String Username;
	private String Password;

	@SerializedName("Message")
	private String message;

	@SerializedName("Data")
	private Data data;

	@SerializedName("StatusCode")
	private int statusCode;

	public LoginData(String s, String s1) {
		Username = s;
		Password = s1;
	}

	public String getMessage(){
		return message;
	}

	public Data getData(){
		return data;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public static class Data{

		@SerializedName("Email")
		private String email;

		@SerializedName("Username")
		private String username;

		@SerializedName("UserId")
		private int userId;

		@SerializedName("FirstName")
		private String firstName;

		@SerializedName("Token")
		private String token;

		@SerializedName("LastName")
		private String lastName;

		@SerializedName("Mobile")
		private String mobile;

		public String getEmail(){
			return email;
		}

		public String getUsername(){
			return username;
		}

		public int getUserId(){
			return userId;
		}

		public String getFirstName(){
			return firstName;
		}

		public String getToken(){
			return token;
		}

		public String getLastName(){
			return lastName;
		}

		public String getMobile(){
			return mobile;
		}
	}




}