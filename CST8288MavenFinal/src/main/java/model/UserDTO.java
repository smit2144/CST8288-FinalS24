package model;

public class UserDTO {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String userType;

    // Constructor, getters, and setters
    public UserDTO() {}

    public UserDTO(int userId, String name, String email, String password, String userType) {
        this.userId = userId;
    	this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    // Getters and setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", userType=" + userType + "]";
	}
}

