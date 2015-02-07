package viewTemplate;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatViewTemplate {
	ImageView image;
	TextView fullName;
	TextView lastMessage;
	TextView newMessage;

	public ChatViewTemplate(ImageView image, TextView fullName, TextView lastMessage, TextView newMessage) {
		this.image = image;
		this.fullName = fullName;
		this.lastMessage = lastMessage;
		this.newMessage = newMessage;

	}

	public void setImage(Bitmap bitmap) {
		this.image.setImageBitmap(bitmap);
	}

	public void setFullName(CharSequence fullName) {
		this.fullName.setText(fullName);
	}

	public void setLastMessage(CharSequence lastMessage) {
		this.lastMessage.setText(lastMessage);
	}

	public void setNewMessage(CharSequence newMessage) {
		this.newMessage.setText(newMessage);

		if (newMessage.equals("")) {
			this.newMessage.setVisibility(View.INVISIBLE);
		} else {
			this.newMessage.setVisibility(View.VISIBLE);
		}
	}
}