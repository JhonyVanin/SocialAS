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

	public List<ChatModel> getAllChats() {
		List<ChatModel> result = repository.getAllChats();

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

	public List<MessageModel> getAllMessages(String friendId) {
		List<MessageModel> result = repository.getAllMessages(friendId);

		// TODO any logic here

		return result;
	}

}
