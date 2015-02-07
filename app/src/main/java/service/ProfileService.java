package service;

import model.ProfileModel;
import repository.ProfileRepository;

public class ProfileService {

	private ProfileRepository repository;

	public ProfileService() {
		repository = new ProfileRepository();
	}

	public ProfileModel getProfileModel(String id, String password) {
		ProfileModel result = repository.getProfileModel(id, password);

		// TODO any logic here

		return result;
	}

}
