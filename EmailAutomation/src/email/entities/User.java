package email.entities;

import java.util.Date;

public class User {
	
	private int user_id;
	
	private String user_name;
	
	private String user_surname;
	
	private static String user_email;
	
	private String user_password;
	
	private String user_gender;
	
	private Date user_date_of_birth;
	
	private String user_phoneNumber;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_surname() {
		return user_surname;
	}

	public void setUser_surname(String user_surname) {
		this.user_surname = user_surname;
	}

	public static String getUser_email() {
		return user_email;
	}

	public  void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public void setUser_gender(String user_gender) {
		this.user_gender = user_gender;
	}

	public Date getUser_date_of_birth() {
		return user_date_of_birth;
	}

	public void setUser_date_of_birth(Date user_date_of_birth) {
		this.user_date_of_birth = user_date_of_birth;
	}

	public String getUser_phoneNumber() {
		return user_phoneNumber;
	}

	public void setUser_phoneNumber(String user_phoneNumber) {
		this.user_phoneNumber = user_phoneNumber;
	}

	public int getUser_id() {
		return user_id;
	}

}
