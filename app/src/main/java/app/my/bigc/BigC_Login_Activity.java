package app.my.bigc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by sriven on 5/31/2016.
 */
public class BigC_Login_Activity extends Activity {
    TextView username,password,login_name;
    LinearLayout signin;
    String username_str,password_str;
    String type = "0";
    String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bigc_login_screen);
        type = getIntent().getStringExtra("type");
        login_name = (TextView)findViewById(R.id.sta_login_name);
        if(type.equals("0"))
            login_name.setText("Store Login");
        else
            login_name.setText("Employee Login");

        username = (TextView)findViewById(R.id.uname);
        password = (TextView)findViewById(R.id.pwd);
        signin = (LinearLayout)findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username_str = username.getText().toString();
                password_str = password.getText().toString();
                if(username_str.equals("")){
                    Toast.makeText(BigC_Login_Activity.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(password_str.equals("")){
                    Toast.makeText(BigC_Login_Activity.this, "please enter password", Toast.LENGTH_SHORT).show();
                }
                else {
                   // Toast.makeText(BigC_Login_Activity.this, "values are correct", Toast.LENGTH_SHORT).show();
                    login();
                }
            }
        });
    }

        private void login(){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("please wait...");
            progressDialog.show();
            progressDialog.setCancelable(false);
            String url = "";
            if(type.equals("0"))
                url = Settings.SERVER_URL+"store-login.php?email="+username_str+"&password="+password_str;
            else
            url = Settings.SERVER_URL+"member-login.php?code="+username_str+"&password="+password_str;
            Log.e("url", url);

            Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonObject) {
                    Log.e("res", jsonObject.toString());
                    try {
                        String temp = jsonObject.getJSONObject(0).getString("status");
                        if (temp.equals("Failure"))
                            Toast.makeText(BigC_Login_Activity.this, jsonObject.getJSONObject(0).getString("message"), Toast.LENGTH_SHORT).show();
                        else {
                            if(type.equals("0")) {
                                String login_type= jsonObject.getJSONObject(0).getString("type");
                                String name= jsonObject.getJSONObject(0).getString("name");
                                String store_id= jsonObject.getJSONObject(0).getString("store_id");
                                Toast.makeText(BigC_Login_Activity.this, "welcome to "+name+" store", Toast.LENGTH_SHORT).show();
                                Settings.set_store(getApplicationContext(),store_id,login_type,name);
                                Intent intent = new Intent(BigC_Login_Activity.this, Dashboard_Activity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                String login_type= jsonObject.getJSONObject(0).getString("type");
                                String name= jsonObject.getJSONObject(0).getString("name");
                                String mem_id= jsonObject.getJSONObject(0).getString("member_id");
                                Toast.makeText(BigC_Login_Activity.this, "welcome  "+name, Toast.LENGTH_SHORT).show();
                                Settings.set_emp_id(getApplicationContext(), mem_id, login_type, name);
                                Intent intent = new Intent(BigC_Login_Activity.this, Exams_list_Activity.class);

                                if(getIntent().getStringExtra("goto").equals("exam"))
                                    intent = new Intent(BigC_Login_Activity.this, Exams_list_Activity.class);

                                else if(getIntent().getStringExtra("goto").equals("reexam"))
                                    intent = new Intent(BigC_Login_Activity.this, Exams_list_Activity.class);

                                else if(getIntent().getStringExtra("goto").equals("missed"))
                                    intent = new Intent(BigC_Login_Activity.this, Missed_Customer_feedback_Activity.class);

                                startActivity(intent);
                                finish();

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    progressDialog.dismiss();

                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("res", volleyError.toString());
                    progressDialog.dismiss();
                }
            };

            JsonArrayRequest request = new JsonArrayRequest(url,listener,errorListener);

            AppController.getInstance().addToRequestQueue(request);

        }
    }


