package fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.List;

import helper.Config;
import helper.KeyValuePairCollection;
import helper.SocketIO;
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

    SocketIO socket;
    String globalData;

    public ConversationPlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try {
            socket = SocketIO.getInstance();

            socket.setOnConnectListener(OnConnect);
            socket.setOnDisconnectListener(OnDisconnect);
            socket.setOnErrorListener(OnError);
            socket.setOnDataListener(OnData);

            socket.connect();

            KeyValuePairCollection params = new KeyValuePairCollection();
            params.addPair("Place", "User");
            params.addPair("Event", "log");
            params.addPair("UserId", Config.getInstance().getUserId());
            params.addPair("Password", Config.getInstance().getUserPasswordMD5());

            socket.send(params.toJSON());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        rootView = inflater.inflate(R.layout.fragment_conversation, container, false);
        context = inflater.getContext();

        message = (EditText) rootView.findViewById(R.id.message);
        send = (Button) rootView.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                KeyValuePairCollection params = new KeyValuePairCollection();
                params.addPair("Place", "Chat");
                params.addPair("Event", "SendMessag");
                params.addPair("Message", message.getText().toString());
                params.addPair("FriendId", friendId);

                socket.send(params.toJSON());

                MessageModel model = new MessageModel();
                model.setTime("Только что");
                model.setText(message.getText().toString());
                model.setIsMyMessage("1");

                chatAdapter.add(model);

                message.setText("");
            }
        });

        Bundle params = getArguments();
        friendId = params.getString("friendId");

        new ConversationAsyncLogic().execute();

        return rootView;
    }

    private Emitter.Listener OnConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            //TODO OnConnect
        }
    };

    private Emitter.Listener OnDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            //TODO OnDisconnect
        }
    };

    private Emitter.Listener OnError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            //TODO OnError
        }
    };

    private Emitter.Listener OnData = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            globalData = args[0].toString();

            FragmentActivity activity = getActivity();
            if (activity == null) {
                return;
            }

            activity.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        JSONObject jsonObject = new JSONObject(globalData);
                        if (jsonObject.get("Event").toString().compareTo("ReceiveMessage") == 0) {

                            MessageModel model = new MessageModel();
                            model.setTime("Только что");
                            model.setText(jsonObject.get("Message").toString());
                            model.setIsMyMessage("0");

                            chatAdapter.add(model);
                        }
                    } catch (Exception ex) {

                    }
                }
            });
        }
    };


    public class ConversationAsyncLogic extends AsyncTask<Void, Void, List<MessageModel>> {
        @Override
        protected List<MessageModel> doInBackground(Void... params) {

            ChatService chatService = new ChatService();
            List<MessageModel> messages = chatService.getAllMessages(friendId);

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
