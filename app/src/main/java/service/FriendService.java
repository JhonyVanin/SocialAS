package service;

import org.json.JSONException;

import java.util.List;

import model.FriendModel;
import repository.FriendRepository;

public class FriendService {

	private FriendRepository repository;

	public FriendService() {
		repository = new FriendRepository();
	}

	public List<FriendModel> getAllFriends() {
        List<FriendModel> result = repository.getAllFriends();

        // TODO any logic here

		return result;
	}
	
	public FriendModel getSelectedFriend(String friendId) {
		FriendModel result = repository.getFriendById(friendId);

		// TODO any logic here

		return result;
	}
}
