package app.my.bigc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sriven on 6/17/2016.
 */
public class Missed_Customer_List_Activity extends Fragment {
    MissedCustomerAdapter missedcustomer;
    int posi;
    ArrayList<Missed_Customer> missedcustomers;
    TextView name,number,brand,model,cus_name,cus_number,cus_mobile,cus_email,cue_requri,cus_brand,cus_model,cus_reason,
            cus_suggestions,cus_date,cus_status,fuifill_date;
    ListView missed_listview;
    LinearLayout back_to_list,statua_ll;
    ViewFlipper viewFlipper;
    FragmentTouchListner mCallBack;
    public interface FragmentTouchListner {

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallBack = (Dashboard_Activity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement Listner");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.missedcustomerlist, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getView();
        missedcustomers=new ArrayList<>();
        viewFlipper=(ViewFlipper)v.findViewById(R.id.viewFlipper2);
        name = (TextView)v.findViewById(R.id.missed_name);
        number = (TextView)v.findViewById(R.id.missed_number);
        brand = (TextView)v.findViewById(R.id.missed_brand);
        model = (TextView)v.findViewById(R.id.missed_model);
        cus_name = (TextView)v.findViewById(R.id.cus_name_missed);
        cus_number = (TextView)v.findViewById(R.id.cus_number_missed);
        cus_mobile = (TextView)v.findViewById(R.id.cus_mobile_missed);
        cus_email = (TextView)v.findViewById(R.id.cus_email_missed);
        cue_requri = (TextView)v.findViewById(R.id.cus_req_missed);
        cus_brand = (TextView)v.findViewById(R.id.cus_brand_missed);
        cus_model = (TextView)v.findViewById(R.id.cus_model_missed);
        fuifill_date = (TextView)v.findViewById(R.id.fulfill_date_tv);
        cus_reason = (TextView)v.findViewById(R.id.reason_not_purch);
        cus_suggestions = (TextView)v.findViewById(R.id.cus_suggestions);
        cus_date = (TextView)v.findViewById(R.id.submited_date_missed);
        cus_status = (TextView)v.findViewById(R.id.missed_status);
        back_to_list=(LinearLayout)v.findViewById(R.id.back_list_ll);
        statua_ll=(LinearLayout)v.findViewById(R.id.status_ll_missed);
        missedcustomers=new ArrayList<>();
        missedcustomer=new MissedCustomerAdapter(getActivity(),missedcustomers);
        missed_listview = (ListView)v.findViewById(R.id.missed_listview);
        missed_listview.setAdapter(missedcustomer);
        missed_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewFlipper.setDisplayedChild(1);
                posi = i;
                cus_name.setText       (missedcustomers.get(i).name);
                cus_number.setText     (missedcustomers.get(i).phone);
                cus_mobile.setText     (missedcustomers.get(i).type);
                cus_email.setText      (missedcustomers.get(i).email);
                cue_requri.setText     (missedcustomers.get(i).requirement);
                cus_brand.setText      (missedcustomers.get(i).brand);
                cus_model.setText      (missedcustomers.get(i).model);
                cus_reason.setText     (missedcustomers.get(i).reason);
                cus_suggestions.setText(missedcustomers.get(i).suggestions);
                fuifill_date.setText(missedcustomers.get(i).feedback);
                cus_date.setText       ("Submitted Date: " + missedcustomers.get(i).date);
                if(missedcustomers.get(i).status.equals("Open"))
                    cus_status.setText("Close");
                else
                    cus_status.setText("Open");

            }
        });

        back_to_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.setDisplayedChild(0);
                getMissedcustomer();
            }
        });
        statua_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_status_close();
        }
        });
        getMissedcustomer();

    }
    private void getMissedcustomer(){
        String url;
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait.....");
        progressDialog.show();
        progressDialog.setCancelable(false);
        url = Settings.SERVER_URL+"missed-customer-list.php?member_id="+Settings.get_emp_id(getActivity());
        Log.e("url", url);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                Log.e("reponse", jsonArray.toString());
                try {
                    missedcustomers.clear();
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
    private void  get_status_close(){
        String url;
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait.....");
        progressDialog.show();
        progressDialog.setCancelable(false);
        url = Settings.SERVER_URL+"missed-customer-update.php?missed_customer_id="+missedcustomers.get(posi).id;
        Log.e("url", url);
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                Log.e("reponse", jsonObject.toString());

                try {
                    String reply=jsonObject.getString("status");
                    if(reply.equals("Success")) {
                        String msg = jsonObject.getString("message");
                       back_to_list.performClick();
                     //   Toast.makeText(Missed_Customer_List_Activity.this, msg, Toast.LENGTH_SHORT).show();
                    }else {
                        String msg = jsonObject.getString("message");
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
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
