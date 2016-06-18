package app.my.bigc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sriven on 6/17/2016.
 */
public class Missed_Customer_List_Activity extends Activity {
    MissedCustomerAdapter missedcustomer;
    ArrayList<Missed_Customer> missedcustomers;
    TextView name,number,brand,model;
    ListView missed_listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.missedcustomerlist);
        name = (TextView)findViewById(R.id.missed_name);
        number = (TextView)findViewById(R.id.missed_number);
        brand = (TextView)findViewById(R.id.missed_brand);
        model = (TextView)findViewById(R.id.missed_model);
        missedcustomers=new ArrayList<>();
        missedcustomer=new MissedCustomerAdapter(this,missedcustomers);
        missed_listview = (ListView)findViewById(R.id.missed_listview);
        missed_listview.setAdapter(missedcustomer);
        missed_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        getMissedcustomer();

    }
    private void getMissedcustomer(){
        String url;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait.....");
        progressDialog.show();
        progressDialog.setCancelable(false);
        url = Settings.SERVER_URL+"missed-customer-list.php?member_id=="+Settings.get_emp_id(getApplicationContext());
        Log.e("url", url);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                Log.e("reponse", jsonArray.toString());
                try {
                    jsonArray = jsonArray.getJSONObject(0).getJSONArray("missedcustomers");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject tmp_json = jsonArray.getJSONObject(i);
                        Missed_Customer missedcustomer=new Missed_Customer(tmp_json);
                        missedcustomers.add(missedcustomer);
                    }
                    missedcustomer.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                if(progressDialog!=null)
                    progressDialog.dismiss();

            }
        });

// Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }
}
