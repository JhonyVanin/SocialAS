package repository;

import helper.AutoMapper;
import helper.Requester;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import model.ChatModel;
import model.MessageModel;

public class ChatRepository {

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (Exception ex) {
        }
        return null;
    }

	public List<ChatModel> getAllChats(String userId, String password) {
		String response = Requester.sendPost("http://wall-call.com/androidApi/chat.php", "UserId=" + userId + "&Password=" +  MD5(password)
				+ "&Event=ListFriends");

		return AutoMapper.MapChatsModel(response);
	}

	public List<MessageModel> getAllMessages(String userId, String password, String friendId) {
		String response = Requester.sendPost("http://wall-call.com/androidApi/chat.php", "UserId=" + userId + "&Password=" +  MD5(password)
				+ "&FriendId=" + friendId + "&Event=ListMessag");

		return AutoMapper.MapMessagesModel(response);
	}
}
