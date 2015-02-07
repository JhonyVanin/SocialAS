package model;

public class ChatModel {

	private String friendId;
	private String imageUri;
	private String firstName;
	private String lastName;
	private String newMessage;
	private String lastMessage;

	public ChatModel() {
		friendId = "";
		imageUri = "";
		firstName = "";
		lastName = "";
		newMessage = "";
		lastMessage = "";
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String userId) {
		this.friendId = userId;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNewMessage() {
		return newMessage;
	}

	public void setNewMessage(String newMessage) {
		this.newMessage = newMessage;
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

}
