package viewTemplate;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendViewTemplate {
	TextView fullName;
	TextView rating;
	TextView countPostComplite;
	TextView location;
	ImageView image;

	public FriendViewTemplate(ImageView image, TextView fullName, TextView rating, TextView countPostComplite, TextView location) {
		this.image = image;
		this.fullName = fullName;
		this.rating = rating;
		this.countPostComplite = countPostComplite;
		this.location = location;
	}

	public void setCountPostComplite(CharSequence countPostComplite) {
		this.countPostComplite.setText(countPostComplite);
	}

	public void setRating(CharSequence rating) {
		this.rating.setText(rating);
	}

	public void setLocation(CharSequence location) {
		this.location.setText(location);
	}

	public void setImage(Bitmap bitmap) {
		this.image.setImageBitmap(bitmap);
	}

	public void setFullName(CharSequence fullName) {
		this.fullName.setText(fullName);
	}

}