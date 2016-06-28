package app.my.bigc;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sriven on 5/26/2016.
 */
public class Notifications implements Serializable {
    String id,image,thumb,message,title;


    Notifications(JSONObject object){
        try {
            id = object.getString("id");
            title = object.getString("title");
            image = object.getString("image");
            thumb = object.getString("thumb");
            message = object.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
