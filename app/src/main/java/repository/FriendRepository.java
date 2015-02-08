package repository;

import org.json.JSONException;

import java.util.List;

import helper.AutoMapper;
import helper.Config;
import helper.KeyValuePairCollection;
import helper.Requester;
import model.FriendModel;

public class FriendRepository {
    public List<FriendModel> getAllFriends() {

        String address = "http://wall-call.com/androidApi/listFriends.php";

        KeyValuePairCollection params = new KeyValuePairCollection();
        params.addPair("UserId", Config.getInstance().getUserId());
        params.addPair("Password", Config.getInstance().getUserPassword());
        params.addPair("Event", "all");

        String response = Requester.sendPost(address, params.toParams());
        return AutoMapper.MapFriendsModel(response);
    }

    public FriendModel getFriendById(String friendId) {

        String address = "http://wall-call.com/androidApi/friendPage.php";

        KeyValuePairCollection params = new KeyValuePairCollection();
        params.addPair("UserId", Config.getInstance().getUserId());
        params.addPair("pass", Config.getInstance().getUserPassword());
        params.addPair("FriendId", friendId);

        String response = Requester.sendPost(address, params.toParams());

        return AutoMapper.MapSelectedFriendModel(response);
    }
}
