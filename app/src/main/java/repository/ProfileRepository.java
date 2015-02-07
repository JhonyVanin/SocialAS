package repository;

import helper.AutoMapper;
import helper.Requester;
import model.ProfileModel;

public class ProfileRepository {

	public ProfileModel getProfileModel(String id, String password) {
		String response = Requester.sendPost("http://wall-call.com/androidApi/myPage.php", "UserId=" + id + "&pass=" + password);

		return AutoMapper.MapProfileModel(response);
	}

}
