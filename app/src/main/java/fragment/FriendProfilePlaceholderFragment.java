package fragment;

import helper.Config;
import helper.ImageCache;
import model.FriendModel;
import service.FriendService;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.social.R;

public class FriendProfilePlaceholderFragment extends Fragment {

	TextView fullName;
	ImageView imageUri;
	ImageView coverImageUri;

	public FriendProfilePlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_friend_profile, container, false);

		fullName = (TextView) rootView.findViewById(R.id.fullName);
		imageUri = (ImageView) rootView.findViewById(R.id.imageUri);
		coverImageUri = (ImageView) rootView.findViewById(R.id.coverImageUri);

		Bundle params = getArguments();

		FriendService friendService = new FriendService();
		FriendModel friendModel = friendService.getSelectedFriend(Config.getInstance().getUserId(), Config.getInstance()
				.getUserPassword(), params.getString("friendId"));

		fullName.setText(friendModel.getFullName());
		imageUri.setImageBitmap(ImageCache.getInstance().getImage(friendModel.getImageUri()));
		coverImageUri.setImageBitmap(ImageCache.getInstance().getImage(friendModel.getCoverImageUri()));

		return rootView;
	}
}