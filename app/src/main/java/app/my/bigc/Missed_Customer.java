package app.my.bigc;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sriven on 5/26/2016.
 */
public class Missed_Customer implements Serializable {
    String id,name,phone,email,requirement,type,brand,model,reason,suggestions,status;


    Missed_Customer(JSONObject object){
        try {
            name= object.getString("name");
            number = object.getString("number");
            brand = object.getString("brand");
            model = object.getString("model");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
