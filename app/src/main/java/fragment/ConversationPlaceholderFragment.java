package fragment;

import helper.Config;

import java.util.List;

import model.MessageModel;
import service.ChatService;
import viewTemplate.MessageViewTemplate;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.social.R;

public class ConversationPlaceholderFragment extends Fragment {

	ImageView loadingImage;
	ListView messageListView;
	View rootView;
	Context context;
	String friendId;

	public ConversationPlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_conversation, container, false);
		context = inflater.getContext();

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

			ConversationAdapter chatAdapter = new ConversationAdapter(context, result);

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

			if (convertView == null) {

				if (messageModel.getIsMyMessage().compareTo("1") == 0) {
					convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_message_layout, null);
				} else {
					convertView = LayoutInflater.from(getContext()).inflate(R.layout.friend_message_layout, null);
				}

				TextView text = (TextView) convertView.findViewById(R.id.text);

				messageViewTemplate = new MessageViewTemplate(text);
				convertView.setTag(messageViewTemplate);

			} else {
				messageViewTemplate = (MessageViewTemplate) convertView.getTag();
			}

			messageViewTemplate.setText(messageModel.getText());

			return convertView;
		}
	}
}
