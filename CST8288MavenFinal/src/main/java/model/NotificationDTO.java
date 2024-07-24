package model;


/**
 * @author Hussein
 */

public class NotificationDTO {

	private int userID;
	private String method;
	private String message;
	
	//Constructors
	public NotificationDTO() {
	}

	public NotificationDTO(int userID, String method, String message) {
		super();
		this.userID = userID;
		this.method = method;
		this.message = message;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
