package app.my.bigc;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sriven on 5/26/2016.
 */
public class Offers implements Serializable {
    String image,thumb,date,title,discription,expirydate,startdate,status;


    Offers(JSONObject object){
        try {
            date = object.getString("expiry_date");
            thumb = object.getString("image");
            image = object.getString("image");
            title = object.getString("title");
            discription = object.getString("message");
            startdate = object.getString("expiry_date");
            expirydate = object.getString("expiry_date");
            status=object.getString("status");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
