package repository;

import helper.AutoMapper;
import helper.Requester;

import java.util.List;

import model.FriendModel;

public class FriendRepository {
	public List<FriendModel> getAllFriends(String userId, String password) {
		String response = Requester
				.sendPost("http://netlife.by/androidApi/listFriends.php", "id=" + userId + "&pass=" + password);

		return AutoMapper.MapFriendsModel(response);
	}

	public FriendModel getFriendById(String userId, String password, String friendId) {
		String response = Requester.sendPost("http://netlife.by/androidApi/friendPage.php", "id=" + userId + "&pass=" + password
				+ "&id_f=" + friendId);

		return AutoMapper.MapSelectedeFriendModel(response);
	}
}
