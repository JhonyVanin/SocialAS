package repository;

import helper.AutoMapper;
import helper.Requester;

import java.util.List;

import model.FriendModel;

public class FriendRepository {
	public List<FriendModel> getAllFriends(String userId, String password) {
		String response = Requester
				.sendPost("http://wall-call.com/androidApi/listFriends.php", "UserId=" + userId + "&pass=" + password + "&Event=all");

		return AutoMapper.MapFriendsModel(response);
	}

	public FriendModel getFriendById(String userId, String password, String friendId) {
		String response = Requester.sendPost("http://wall-call.com/androidApi/friendPage.php", "UserId=" + userId + "&pass=" + password
				+ "&FriendId=" + friendId);

		return AutoMapper.MapSelectedFriendModel(response);
	}
}
