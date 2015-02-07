package viewTemplate;

import android.widget.TextView;

public class MessageViewTemplate {
	TextView text;

	public MessageViewTemplate(TextView text) {
		this.text = text;
	}

	public void setText(String text) {
		this.text.setText(text);
	}
}