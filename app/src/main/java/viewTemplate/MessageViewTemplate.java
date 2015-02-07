package viewTemplate;

import android.widget.TextView;

public class MessageViewTemplate {
    TextView text;
    TextView dateTime;


    public MessageViewTemplate(TextView text, TextView dateTime) {
        this.text = text;
        this.dateTime = dateTime;
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public void setDateTime(String dateTime) {
        this.dateTime.setText(dateTime);
    }
}