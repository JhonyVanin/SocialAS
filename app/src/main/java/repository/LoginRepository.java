package repository;

import helper.AutoMapper;
import helper.Config;
import helper.KeyValuePairCollection;
import helper.Requester;
import model.LoginModel;

public class LoginRepository {

	public LoginModel getLoginModel(String email, String password) {

        String address = "http://wall-call.com/androidApi/login.php";

        KeyValuePairCollection params = new KeyValuePairCollection();
        params.addPair("email", email);
        params.addPair("pass", password);

		String response = Requester.sendPost(address, params.toParams());
		return AutoMapper.MapLoginModel(response);
	}

}
