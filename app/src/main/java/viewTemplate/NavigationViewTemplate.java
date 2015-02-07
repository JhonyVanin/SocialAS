package viewTemplate;

import android.widget.TextView;

public class NavigationViewTemplate {
	TextView menuItem;

	public NavigationViewTemplate(TextView menuItem) {
		this.menuItem = menuItem;
	}

	public void setText(CharSequence text) {
		this.menuItem.setText(text);
	}
}
