package repository;

import helper.AutoMapper;
import helper.Config;
import helper.KeyValuePairCollection;
import helper.Requester;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import model.ChatModel;
import model.MessageModel;

public class ChatRepository {

	public List<ChatModel> getAllChats() {

        String address = "http://wall-call.com/androidApi/chat.php";

        KeyValuePairCollection params = new KeyValuePairCollection();
        params.addPair("UserId", Config.getInstance().getUserId());
        params.addPair("Password", Config.getInstance().getUserPassword());
        params.addPair("Event", "ListFriends");

        String response = Requester.sendPost(address, params.toParams());
		return AutoMapper.MapChatsModel(response);
	}

	public List<MessageModel> getAllMessages(String friendId) {

        String address = "http://wall-call.com/androidApi/chat.php";

        KeyValuePairCollection params = new KeyValuePairCollection();
        params.addPair("UserId", Config.getInstance().getUserId());
        params.addPair("Password", Config.getInstance().getUserPasswordMD5());
        params.addPair("FriendId", friendId);
        params.addPair("Event", "ListMessag");

        String response = Requester.sendPost(address, params.toParams());
		return AutoMapper.MapMessagesModel(response);
	}
}
