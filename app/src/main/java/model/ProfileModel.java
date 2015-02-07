package model;

public class ProfileModel {

	private String firstName;
	private String lastName;
	private String imageUri;
	private String coverImageUri;

	public ProfileModel() {
		firstName = "";
		lastName = "";
		imageUri = "";
		coverImageUri = "";
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public String getCoverImageUri() {
		return coverImageUri;
	}

	public void setCoverImageUri(String coverImageUri) {
		this.coverImageUri = coverImageUri;
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

}
