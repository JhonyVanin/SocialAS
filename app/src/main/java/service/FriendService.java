package service;

import java.util.List;

import model.FriendModel;
import repository.FriendRepository;

public class FriendService {

	private FriendRepository repository;

	public FriendService() {
		repository = new FriendRepository();
	}

	public List<FriendModel> getAllFriends(String userId, String password) {
		List<FriendModel> result = repository.getAllFriends(userId, password);

		// TODO any logic here

		return result;
	}
	
	public FriendModel getSelectedFriend(String userId, String password, String friendId) {
		FriendModel result = repository.getFriendById(userId, password, friendId);

		// TODO any logic here

		return result;
	}
}
