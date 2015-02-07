package model;

public class LoginModel {

	private String status;
	private String userId;

	public LoginModel() {
		status = "";
		userId = "";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String id) {
		this.userId = id;
	}

}
