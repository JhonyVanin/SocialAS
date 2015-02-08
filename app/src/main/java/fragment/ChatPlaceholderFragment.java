package fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.social.R;

import java.util.List;

import helper.ImageCache;
import model.ChatModel;
import service.ChatService;
import viewTemplate.ChatViewTemplate;

public class ChatPlaceholderFragment extends Fragment {

    ImageView loadingImage;
    ListView chatListView;
    View rootView;
    Context context;

    public ChatPlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        context = inflater.getContext();

        new ChatAsyncLogic().execute();

        return rootView;
    }

    public class ChatAsyncLogic extends AsyncTask<Void, Void, List<ChatModel>> {
        @Override
        protected List<ChatModel> doInBackground(Void... params) {

            ChatService chatService = new ChatService();
            List<ChatModel> chats = chatService.getAllChats();

            return chats;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ChatModel> result) {
            super.onPostExecute(result);

            ChatAdapter chatAdapter = new ChatAdapter(context, result);

            chatListView = (ListView) rootView.findViewById(R.id.chatListView);
            chatListView.setAdapter(chatAdapter);

            loadingImage = (ImageView) rootView.findViewById(R.id.loadingImage);
            loadingImage.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    public class ChatAdapter extends ArrayAdapter<ChatModel> {

        private List<ChatModel> objects;

        public ChatAdapter(Context context, List<ChatModel> objects) {
            super(context, R.id.fullName, objects);

            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChatViewTemplate chatViewTemplate = null;
            final ChatModel chatModel = objects.get(position);

            if (convertView == null) {

                convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_layout, null);

                ImageView image = (ImageView) convertView.findViewById(R.id.image);
                TextView fullName = (TextView) convertView.findViewById(R.id.fullName);
                TextView lastMessage = (TextView) convertView.findViewById(R.id.lastMessage);
                TextView newMessage = (TextView) convertView.findViewById(R.id.newMessage);

                chatViewTemplate = new ChatViewTemplate(image, fullName, lastMessage, newMessage);
                convertView.setTag(chatViewTemplate);

            } else {
                chatViewTemplate = (ChatViewTemplate) convertView.getTag();
            }

            chatViewTemplate.setImage(ImageCache.getInstance().getImage(chatModel.getImageUri()));
            chatViewTemplate.setFullName(chatModel.getFullName());
            chatViewTemplate.setLastMessage(chatModel.getLastMessage());
            chatViewTemplate.setNewMessage(chatModel.getNewMessage());

            convertView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    ConversationPlaceholderFragment conversationFragment = new ConversationPlaceholderFragment();

                    Bundle params = new Bundle();
                    params.putString("friendId", chatModel.getFriendId());

                    conversationFragment.setArguments(params);

                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.container, conversationFragment).commit();

                }
            });

            return convertView;
        }
    }
}
