package app.my.bigc;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sriven on 5/26/2016.
 */
public class Offers implements Serializable {
    String image,thumb,date,title,discription,expirydate;


    Offers(JSONObject object){
        try {
            date = object.getString("date");
            thumb = object.getString("thumb");
            image = object.getString("image");
            title = object.getString("title");
            discription = object.getString("discription");
            expirydate = object.getString("expirydate");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
