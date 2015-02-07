package service;

import java.util.List;

import model.ChatModel;
import model.MessageModel;
import repository.ChatRepository;

public class ChatService {

	private ChatRepository repository;

	public ChatService() {
		repository = new ChatRepository();
	}

	public List<ChatModel> getAllChats(String userId, String password) {
		List<ChatModel> result = repository.getAllChats(userId, password);

		// TODO any logic here

		for (ChatModel chatModel : result) {
			if (chatModel.getLastMessage().equals("0")) {
				chatModel.setLastMessage("");
			}

			if (chatModel.getNewMessage().equals("0")) {
				chatModel.setNewMessage("");
			}
		}

		return result;
	}

	public List<MessageModel> getAllMessages(String userId, String password, String friendId) {
		List<MessageModel> result = repository.getAllMessages(userId, password, friendId);

		// TODO any logic here

		return result;
	}

}
