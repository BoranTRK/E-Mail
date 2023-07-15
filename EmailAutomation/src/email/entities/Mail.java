package email.entities;

public class Mail {

	
	private static String mail_id;
	
	public static void setMail_id(String id) {
		Mail.mail_id = id;
	}

	public static String getMail_id() {
		return mail_id;
	}

}
