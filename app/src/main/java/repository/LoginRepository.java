package repository;

import helper.AutoMapper;
import helper.Requester;
import model.LoginModel;

public class LoginRepository {

	public LoginModel getLoginModel(String email, String password) {
		String response = Requester.sendPost("http://netlife.by/androidApi/login.php", "email=" + email + "&pass=" + password);

		return AutoMapper.MapLoginModel(response);
	}

}
