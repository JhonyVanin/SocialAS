package helper;

import java.util.ArrayList;
import java.util.List;

import model.ChatModel;
import model.FriendModel;
import model.LoginModel;
import model.MessageModel;
import model.ProfileModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class AutoMapper {

	public static List<FriendModel> MapFriendsModel(String json) {

		try {
			JSONObject jsonObject = new JSONObject(json);
			String jsonString = jsonObject.get("arrayFriends").toString();
			JSONArray jsonArray = new JSONArray(jsonString);

			List<FriendModel> result = new ArrayList<FriendModel>();
			for (int i = 0; i < jsonArray.length(); ++i) {
				result.add(MapFriendModel(jsonArray.get(i).toString()));
			}
			return result;

		} catch (Exception ex) {
			// TODO: write error message to log file
		}

		return new ArrayList<FriendModel>();
	}

	public static FriendModel MapFriendModel(String json) {

		try {
			JSONObject jsonObject = new JSONObject(json);

			FriendModel result = new FriendModel();
			result.setUserId(jsonObject.get("FriendId").toString());
			result.setImageUri(jsonObject.get("avatar").toString());
			result.setFirstName(jsonObject.get("firstName").toString());
			result.setLastName(jsonObject.get("lastName").toString());
			//result.setLocation(jsonObject.get("contry_city").toString());

			return result;

		} catch (Exception ex) {
			// TODO: write error message to log file
		}

		return new FriendModel();
	}

	public static FriendModel MapSelectedFriendModel(String json) {

		try {
			JSONObject jsonObject = new JSONObject(json);

			FriendModel result = new FriendModel();
			result.setImageUri(jsonObject.get("avatar").toString());
			result.setFirstName(jsonObject.get("firstName").toString());
			result.setLastName(jsonObject.get("lastName").toString());
			result.setCoverImageUri(jsonObject.get("ablogka").toString());

			return result;

		} catch (Exception ex) {
			// TODO: write error message to log file
		}

		return new FriendModel();
	}

	public static LoginModel MapLoginModel(String json) {

		try {
			JSONObject jsonObject = new JSONObject(json);

			LoginModel result = new LoginModel();
			result.setStatus(jsonObject.get("status").toString());
			result.setUserId(jsonObject.get("UserId").toString());

			return result;

		} catch (Exception ex) {
			// TODO: write error message to log file
		}

		return new LoginModel();
	}

	public static ProfileModel MapProfileModel(String json) {

		try {
			JSONObject jsonObject = new JSONObject(json);

			ProfileModel result = new ProfileModel();
			result.setFirstName(jsonObject.get("firstName").toString());
			result.setLastName(jsonObject.get("lastName").toString());
			result.setImageUri(jsonObject.get("MiniPhoto").toString());
			result.setCoverImageUri(jsonObject.get("ablogka").toString());

			return result;

		} catch (Exception ex) {
			// TODO: write error message to log file
		}

		return new ProfileModel();
	}

	public static List<ChatModel> MapChatsModel(String json) {

		try {
			JSONObject jsonObject = new JSONObject(json);
			String jsonString = jsonObject.get("arrayFriends").toString();
			JSONArray jsonArray = new JSONArray(jsonString);

			List<ChatModel> result = new ArrayList<ChatModel>();
			for (int i = 0; i < jsonArray.length(); ++i) {
				result.add(MapChatModel(jsonArray.get(i).toString()));
			}
			return result;

		} catch (Exception ex) {
			// TODO: write error message to log file
		}

		return new ArrayList<ChatModel>();
	}

	public static ChatModel MapChatModel(String json) {

		try {
			JSONObject jsonObject = new JSONObject(json);

			ChatModel result = new ChatModel();
            result.setFriendId(jsonObject.get("id").toString());
			//result.setFriendId(jsonObject.get("FriendId").toString());
			result.setImageUri(jsonObject.get("avatar").toString());
			result.setFirstName(jsonObject.get("firstName").toString());
			result.setLastName(jsonObject.get("lastName").toString());
			result.setNewMessage(String.valueOf((int) (Math.random() * 100)));
			result.setLastMessage("Вчера в 12:34");
			// result.setNewMessage(jsonObject.get("CountNewMes").toString());
			// result.setLastMessage(jsonObject.get("LastMes").toString());

			return result;

		} catch (Exception ex) {
			// TODO: write error message to log file
		}

		return new ChatModel();
	}

	public static List<MessageModel> MapMessagesModel(String json) {

		try {
			JSONObject jsonObject = new JSONObject(json);
			String jsonString = jsonObject.get("listMeseger").toString();
			JSONArray jsonArray = new JSONArray(jsonString);

			List<MessageModel> result = new ArrayList<MessageModel>();
			for (int i = 0; i < jsonArray.length(); ++i) {
				result.add(MapMessageModel(jsonArray.get(i).toString()));
			}
			return result;

		} catch (Exception ex) {
			// TODO: write error message to log file
		}

		return new ArrayList<MessageModel>();
	}

	public static MessageModel MapMessageModel(String json) {

		try {
			JSONObject jsonObject = new JSONObject(json);

			MessageModel result = new MessageModel();

			result.setId(jsonObject.get("idMes").toString());
			result.setIsMyMessage(jsonObject.get("vov").toString());
			result.setText(jsonObject.get("text").toString());
			result.setTime(jsonObject.get("dateTime").toString());
			result.setIsRead(jsonObject.get("write").toString());

			return result;

		} catch (Exception ex) {
			// TODO: write error message to log file
		}

		return new MessageModel();
	}
}
