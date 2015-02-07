package repository;

import helper.AutoMapper;
import helper.Requester;

import java.util.List;

import model.ChatModel;
import model.MessageModel;

public class ChatRepository {
	public List<ChatModel> getAllChats(String userId, String password) {
		String response = Requester.sendPost("http://netlife.by/androidApi/chat.php", "id=" + userId + "&pass=" + password
				+ "&asd=friends");

		return AutoMapper.MapChatsModel(response);
	}

	public List<MessageModel> getAllMessages(String userId, String password, String friendId) {
		String response = Requester.sendPost("http://netlife.by/androidApi/chat.php", "id=" + userId + "&pass=" + password
				+ "&id_f=" + friendId + "&asd=viewListMeseger");

		return AutoMapper.MapMessagesModel(response);
	}
}
