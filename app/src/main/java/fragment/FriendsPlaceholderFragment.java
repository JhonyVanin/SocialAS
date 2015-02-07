package fragment;

import helper.Config;
import helper.ImageCache;

import java.util.List;

import model.FriendModel;
import service.FriendService;
import viewTemplate.FriendViewTemplate;
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

public class FriendsPlaceholderFragment extends Fragment {

	ImageView loadingImage;
	ListView friendsListView;
	View rootView;
	Context context;

	public FriendsPlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_friends, container, false);
		context = inflater.getContext();

		new FriendAsyncLogic().execute();

		return rootView;
	}

	public class FriendAsyncLogic extends AsyncTask<Void, Void, List<FriendModel>> {
		@Override
		protected List<FriendModel> doInBackground(Void... params) {

			FriendService friendService = new FriendService();
			List<FriendModel> friends = friendService.getAllFriends(Config.getInstance().getUserId(), Config.getInstance()
					.getUserPassword());

			return friends;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(List<FriendModel> result) {
			super.onPostExecute(result);

			FriendsAdapter friendsAdapter = new FriendsAdapter(context, result);

			friendsListView = (ListView) rootView.findViewById(R.id.friendsListView);
			friendsListView.setAdapter(friendsAdapter);

			loadingImage = (ImageView) rootView.findViewById(R.id.loadingImage);
			loadingImage.setBackgroundColor(Color.TRANSPARENT);
		}
	}

	public class FriendsAdapter extends ArrayAdapter<FriendModel> {

		private List<FriendModel> objects;

		public FriendsAdapter(Context context, List<FriendModel> objects) {
			super(context, R.id.fullName, objects);

			this.objects = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			FriendViewTemplate friendViewTemplate = null;
			final FriendModel friendModel = objects.get(position);

			if (convertView == null) {

				convertView = LayoutInflater.from(getContext()).inflate(R.layout.friend_layout, null);

				ImageView image = (ImageView) convertView.findViewById(R.id.image);
				TextView fullName = (TextView) convertView.findViewById(R.id.fullName);
				TextView rating = (TextView) convertView.findViewById(R.id.rating);
				TextView countPostComplite = (TextView) convertView.findViewById(R.id.countPostComplite);
				TextView location = (TextView) convertView.findViewById(R.id.location);

				friendViewTemplate = new FriendViewTemplate(image, fullName, rating, countPostComplite, location);
				convertView.setTag(friendViewTemplate);

			} else {
				friendViewTemplate = (FriendViewTemplate) convertView.getTag();
			}

			friendViewTemplate.setImage(ImageCache.getInstance().getImage(friendModel.getImageUri()));
			friendViewTemplate.setFullName(friendModel.getFullName());
			friendViewTemplate.setRating(friendModel.getRating());
			friendViewTemplate.setCountPostComplite(friendModel.getCountPostComplite());
			friendViewTemplate.setLocation(friendModel.getLocation());

			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					FriendProfilePlaceholderFragment friendProfileFragment = new FriendProfilePlaceholderFragment();

					Bundle params = new Bundle();
					params.putString("friendId", friendModel.getUserId());

					friendProfileFragment.setArguments(params);

					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction().replace(R.id.container, friendProfileFragment).commit();

				}
			});

			return convertView;
		}
	}
}
