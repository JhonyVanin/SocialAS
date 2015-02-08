package repository;

import helper.AutoMapper;
import helper.Config;
import helper.KeyValuePairCollection;
import helper.Requester;
import model.ProfileModel;

public class ProfileRepository {

	public ProfileModel getProfileModel() {

        String address = "http://wall-call.com/androidApi/myPage.php";

        KeyValuePairCollection params = new KeyValuePairCollection();
        params.addPair("UserId", Config.getInstance().getUserId());
        params.addPair("pass", Config.getInstance().getUserPassword());

		String response = Requester.sendPost(address, params.toParams());
		return AutoMapper.MapProfileModel(response);
	}

}
