package model;

public class MessageModel {
	private String id;
	private String isMyMessage;
	private String text;
	private String time;
	private String isRead;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsMyMessage() {
		return isMyMessage;
	}

	public void setIsMyMessage(String isMyMessage) {
		this.isMyMessage = isMyMessage;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

}
