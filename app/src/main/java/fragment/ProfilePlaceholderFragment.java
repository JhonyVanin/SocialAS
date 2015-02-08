package fragment;

import helper.Config;
import helper.ImageCache;
import model.ProfileModel;
import service.ProfileService;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.social.R;

public class ProfilePlaceholderFragment extends Fragment {

	TextView fullName;
	ImageView imageUri;
	ImageView coverImageUri;

	public ProfilePlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

		fullName = (TextView) rootView.findViewById(R.id.fullName);
		imageUri = (ImageView) rootView.findViewById(R.id.imageUri);
		coverImageUri = (ImageView) rootView.findViewById(R.id.coverImageUri);

		ProfileService profileService = new ProfileService();
		ProfileModel profileModel = profileService.getProfileModel();

		fullName.setText(profileModel.getFullName());
		imageUri.setImageBitmap(ImageCache.getInstance().getImage(profileModel.getImageUri()));
		coverImageUri.setImageBitmap(ImageCache.getInstance().getImage(profileModel.getCoverImageUri()));

		return rootView;
	}
}