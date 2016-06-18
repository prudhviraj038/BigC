package app.my.bigc;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by sriven on 5/26/2016.
 */
public class Missed_Customer implements Serializable {
    String id,name,phone,email,requirement,type,brand,model,reason,suggestions,status,date;


    Missed_Customer(JSONObject object){
        try {
            id= object.getString("id");
            name= object.getString("name");
            phone = object.getString("phone");
            email = object.getString("email");
            requirement = object.getString("requirement");
            type = object.getString("type");
            brand= object.getString("brand");
            model = object.getString("model");
            reason = object.getString("reason");
            suggestions = object.getString("suggestions");
            status = object.getString("status");
            date = object.getString("date");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
