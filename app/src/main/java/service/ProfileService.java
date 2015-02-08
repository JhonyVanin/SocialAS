package service;

import model.ProfileModel;
import repository.ProfileRepository;

public class ProfileService {

	private ProfileRepository repository;

	public ProfileService() {
		repository = new ProfileRepository();
	}

	public ProfileModel getProfileModel() {
		ProfileModel result = repository.getProfileModel();

		// TODO any logic here

		return result;
	}

}
