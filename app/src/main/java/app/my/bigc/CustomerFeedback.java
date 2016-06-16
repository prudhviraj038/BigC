package app.my.bigc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sriven on 6/13/2016.
 */
public class CustomerFeedback extends Activity {
    String newspaper_id = "0";
    String storeambiance_id = "0";
    String staffresponce_id = "0";
    EditText customername,contactnumber,emailid,suggestion;
    ArrayList<String> newspapers_id;
    ArrayList<String> newspapers_title;
    ArrayList<String> staffresponces_id;
    ArrayList<String> staffresponces_title;
    ArrayList<String> storeambiances_id;
    ArrayList<String> storeambiances_title;
    EditText selectnewspaper;
    TextView selectstaffresponce,selectstoreambiance;
    LinearLayout submit,news_paper_ll,staff_responce_ll,store_ambiance_ll;
    String customer_str,contact_str,email_str,newspaper_str,storeambiance_str,staffresponce_str,suggestion_str,visited_str,bill_str;
    RadioButton visited_yes,visited_no,bill_yes,bill_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_feedback_screen);
        visited_yes = (RadioButton) findViewById(R.id.radioButton3);
        visited_no = (RadioButton) findViewById(R.id.radioButton4);

        bill_yes = (RadioButton) findViewById(R.id.radioButton5);
        bill_no = (RadioButton) findViewById(R.id.radioButton6);

            storeambiance_str="";
            staffresponce_str="";
            visited_str="";
            bill_str="";

        visited_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visited_no.setChecked(false);
                visited_str="yes";
            }
        });

        visited_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visited_yes.setChecked(false);
                visited_str="no";

            }
        });

        bill_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bill_no.setChecked(false);
                bill_str="yes";
            }
        });

        bill_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bill_yes.setChecked(false);
                bill_str="no";
            }
        });

        newspapers_id= new ArrayList<String>();
        newspapers_title=new ArrayList<String>();
        staffresponces_id= new ArrayList<String>();
        staffresponces_title=new ArrayList<String>();
        storeambiances_id= new ArrayList<String>();
        storeambiances_title=new ArrayList<String>();
        customername = (EditText)findViewById(R.id.cname);
        contactnumber = (EditText)findViewById(R.id.cnumber);
        emailid = (EditText)findViewById(R.id.emailId);
        suggestion = (EditText)findViewById(R.id.customer_suggestions);
        selectnewspaper = (EditText)findViewById(R.id.newspaperedit);
        selectstaffresponce = (TextView)findViewById(R.id.staff_responce);
        selectstoreambiance = (TextView)findViewById(R.id.store_ambiance);
        news_paper_ll = (LinearLayout)findViewById(R.id.news_paper_ll);
        news_paper_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CustomerFeedback.this);
                builder.setTitle("CHOOSE Newspaper");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustomerFeedback.this, android.R.layout.simple_dropdown_item_1line, newspapers_title);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ChooseSubjectActivity.this, level_title.get(which), Toast.LENGTH_SHORT).show();
                        newspaper_id = newspapers_id.get(which);
                        selectnewspaper.setText(newspapers_title.get(which));
                        get_newspaper();
                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



        store_ambiance_ll = (LinearLayout)findViewById(R.id.store_ambiance_ll);
        store_ambiance_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_storeambiance();
            }
        });
        staff_responce_ll = (LinearLayout)findViewById(R.id.staff_responce_ll);
        staff_responce_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_staffresponce();
                            }
        });
        submit = (LinearLayout)findViewById(R.id.customer_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customer_str = customername.getText().toString();
                contact_str = contactnumber.getText().toString();
                email_str = emailid.getText().toString();
                newspaper_str = selectnewspaper.getText().toString();
                suggestion_str = suggestion.getText().toString();
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                if(customer_str.equals("")){
                    Toast.makeText(CustomerFeedback.this, "please enter username", Toast.LENGTH_SHORT).show();
                }
                else if(contact_str.equals("")){
                    Toast.makeText(CustomerFeedback.this, "please enter contact number", Toast.LENGTH_SHORT).show();
                }
                else if(!email_str.matches(emailPattern)){
                    Toast.makeText(CustomerFeedback.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(newspaper_str.equals("")){
                    Toast.makeText(CustomerFeedback.this, "please enter which newspaper you read", Toast.LENGTH_SHORT).show();
                }
                else if(visited_str.equals("")){
                    Toast.makeText(CustomerFeedback.this, "please select have you ever visited BigC Showroom", Toast.LENGTH_SHORT).show();
                }
                else if(bill_str.equals("")){
                    Toast.makeText(CustomerFeedback.this, "please select the bill type for your purchase", Toast.LENGTH_SHORT).show();
                }
                else if(storeambiance_str.equals("")){
                    Toast.makeText(CustomerFeedback.this, "please select the ambiance", Toast.LENGTH_SHORT).show();
                }
                else if(staffresponce_str.equals("")){
                    Toast.makeText(CustomerFeedback.this, "please select the staffresponce", Toast.LENGTH_SHORT).show();
                }

                else if(suggestion_str.equals("")){
                    Toast.makeText(CustomerFeedback.this, "please enter your suggestions", Toast.LENGTH_SHORT).show();
                }
                else {
//                    Toast.makeText(CustomerFeedback.this, "thank you for your feedback", Toast.LENGTH_SHORT).show();
                    send_customer_feedback();
                }

            }
        });


    }
    private void get_newspaper(){
        String url=Settings.SERVER_URL+"newspaper.php";
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonArray.toString());
                try {
                    newspapers_title.clear();
                    newspapers_id.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sub = jsonArray.getJSONObject(i);
                        String newspaper_name = sub.getString("title");
                        String news_id = sub.getString("id");
                        newspapers_id.add(news_id);
                        newspapers_title.add(newspaper_name);
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
                Toast.makeText(CustomerFeedback.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        // Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
    private void get_storeambiance(){
        String url=Settings.SERVER_URL+"store_ambience.php";
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonArray.toString());
                try {
                    storeambiances_id.clear();
                    storeambiances_title.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sub = jsonArray.getJSONObject(i);
                        String storeambiance_name = sub.getString("title");
                        String store_id = sub.getString("id");
                        storeambiances_id.add(store_id);
                        storeambiances_title.add(storeambiance_name);
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(CustomerFeedback.this);
                    builder.setTitle("CHOOSE AMBIANCE");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustomerFeedback.this, android.R.layout.simple_dropdown_item_1line, storeambiances_title);
                    builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(ChooseSubjectActivity.this, level_title.get(which), Toast.LENGTH_SHORT).show();
                            storeambiance_id = storeambiances_id.get(which);
                            selectstoreambiance.setText(storeambiances_title.get(which));
                            storeambiance_str = storeambiances_title.get(which);
                        }
                    });
                    final AlertDialog dialog = builder.create();
                    dialog.show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(CustomerFeedback.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        // Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
    private void get_staffresponce(){
        String url=Settings.SERVER_URL+"staff_response.php";
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonArray.toString());
                try {
                    staffresponces_title.clear();
                    staffresponces_id.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sub = jsonArray.getJSONObject(i);
                        String staff_responce = sub.getString("response");
                        String staff_id = sub.getString("id");
                        staffresponces_id.add(staff_id);
                        staffresponces_title.add(staff_responce);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(CustomerFeedback.this);
                    builder.setTitle("CHOOSE STAFFRESPONCE");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CustomerFeedback.this, android.R.layout.simple_dropdown_item_1line, staffresponces_title);
                    builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(ChooseSubjectActivity.this, level_title.get(which), Toast.LENGTH_SHORT).show();
                            staffresponce_id = staffresponces_id.get(which);
                            staffresponce_str = staffresponces_title.get(which);
                            selectstaffresponce.setText(staffresponces_title.get(which));

                        }
                    });

                    final AlertDialog dialog = builder.create();
                    dialog.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                Toast.makeText(CustomerFeedback.this, "Server not connected", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        // Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
    public  void send_customer_feedback(){
    final ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("please wait.. we are processing");
    progressDialog.show();
    progressDialog.setCancelable(false);
    String url = Settings.SERVER_URL+"customer-feedback.php?";

    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            if(progressDialog!=null)
                progressDialog.dismiss();
            try {
                JSONObject jsonObject=new JSONObject(response);
                String reply=jsonObject.getString("status");
                if(reply.equals("Success")) {
                    String msg = jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    Intent intent;
                    intent = new Intent(CustomerFeedback.this,FeedBack_Activity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    String msg=jsonObject.getString("message");
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(progressDialog!=null)
                        progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
        @Override
        protected Map<String,String> getParams(){
            Map<String,String> params = new HashMap<String, String>();
            params.put("member_id",Settings.get_emp_id(getApplicationContext()));
            params.put("name",customer_str);
            params.put("phone",contact_str);
            params.put("email",email_str);
            params.put("newspaper",newspaper_str);
            params.put("visited", visited_str);
            Log.e("visit", visited_str);
            params.put("computerised", bill_str);
            Log.e("bill", bill_str);
            params.put("store_ambience",storeambiance_id);
            params.put("staff_response",staffresponce_id);
            params.put("suggestions",suggestion_str);
            return params;
        }
    };
    AppController.getInstance().addToRequestQueue(stringRequest);
}
}
