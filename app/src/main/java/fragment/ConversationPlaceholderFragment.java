package fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.social.R;

import java.util.List;

import helper.Config;
import model.MessageModel;
import service.ChatService;
import viewTemplate.MessageViewTemplate;

public class ConversationPlaceholderFragment extends Fragment {
    ImageView loadingImage;
    ListView messageListView;
    View rootView;
    Context context;
    String friendId;

    EditText message;
    Button send;
    ConversationAdapter chatAdapter;

    public ConversationPlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_conversation, container, false);
        context = inflater.getContext();

        message = (EditText) rootView.findViewById(R.id.message);
        send = (Button) rootView.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                MessageModel m1 = new MessageModel();
                m1.setId(Config.getInstance().getUserId());
                m1.setText("Привет? Как твои дела? Что делаешь? чем поиваешь?");
                m1.setTime("18 сентября 2014 в 12:30");
                m1.setIsMyMessage("1");
                chatAdapter.add(m1);

                MessageModel m2 = new MessageModel();
                m2.setId(Config.getInstance().getUserId());
                m2.setText("Привет? Как твои дела? Что делаешь? чем поиваешь?");
                m2.setTime("18 сентября 2014 в 12:30");
                m2.setIsMyMessage("0");
                chatAdapter.add(m2);
            }
        });

        Bundle params = getArguments();
        friendId = params.getString("friendId");

        new ConversationAsyncLogic().execute();

        return rootView;
    }

    public class ConversationAsyncLogic extends AsyncTask<Void, Void, List<MessageModel>> {
        @Override
        protected List<MessageModel> doInBackground(Void... params) {

            ChatService chatService = new ChatService();
            List<MessageModel> messages = chatService.getAllMessages(Config.getInstance().getUserId(), Config.getInstance()
                    .getUserPassword(), friendId);

            return messages;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<MessageModel> result) {
            super.onPostExecute(result);

            chatAdapter = new ConversationAdapter(context, result);

            messageListView = (ListView) rootView.findViewById(R.id.messageListView);
            messageListView.setAdapter(chatAdapter);

            loadingImage = (ImageView) rootView.findViewById(R.id.loadingImage);
            loadingImage.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    public class ConversationAdapter extends ArrayAdapter<MessageModel> {

        private List<MessageModel> objects;

        public ConversationAdapter(Context context, List<MessageModel> objects) {
            super(context, R.id.text, objects);

            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MessageViewTemplate messageViewTemplate = null;
            final MessageModel messageModel = objects.get(position);

            convertView = null;

            if (convertView == null) {

                if (messageModel.getIsMyMessage().compareTo("1") == 0) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_message_layout, null);
                } else {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.friend_message_layout, null);
                }

                TextView text = (TextView) convertView.findViewById(R.id.text);
                TextView dateTime = (TextView) convertView.findViewById(R.id.dateTime);

                messageViewTemplate = new MessageViewTemplate(text, dateTime);
                convertView.setTag(messageViewTemplate);

            } else {
                messageViewTemplate = (MessageViewTemplate) convertView.getTag();
            }

            messageViewTemplate.setText(messageModel.getText());
            messageViewTemplate.setDateTime(messageModel.getTime());

            return convertView;
        }
    }
}
