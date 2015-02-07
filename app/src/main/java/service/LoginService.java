package service;

import model.LoginModel;
import repository.LoginRepository;

public class LoginService {

	private LoginRepository repository;

	public LoginService() {
		repository = new LoginRepository();
	}

	public LoginModel getLoginModel(String email, String password) {
		LoginModel result = repository.getLoginModel(email, password);

		// TODO any logic here

		return result;
	}

}
