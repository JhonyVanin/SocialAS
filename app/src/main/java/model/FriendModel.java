package model;

public class FriendModel {

	private String id;
	private String imageUri;
	private String coverImageUri;
	private String firstName;
	private String lastName;
	private String rating;
	private String countPostComplit;
	private String location;

	public FriendModel() {
		id = "";
		imageUri = "";
		coverImageUri = "";
		firstName = "";
		lastName = "";
		rating = "";
		countPostComplit = "";
		location = "";
	}
	
	public String getUserId() {
		return id;
	}

	public void setUserId(String id) {
		this.id = id;
	}

	public String getCountPostComplite() {
		return countPostComplit;
	}

	public void setCountPostComplite(String countPostComplit) {
		this.countPostComplit = countPostComplit;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
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

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}
	
	public String getCoverImageUri() {
		return coverImageUri;
	}

	public void setCoverImageUri(String coverImageUri) {
		this.coverImageUri = coverImageUri;
	}
}
