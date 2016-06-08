package app.my.bigc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sriven on 5/31/2016.
 */
public class BigC_Login_Activity extends Activity {
    TextView username,password;
    LinearLayout signin;
    String username_str,password_str;
    String type = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bigc_login_screen);
        type = getIntent().getStringExtra("type");
        username = (TextView)findViewById(R.id.uname);
        password = (TextView)findViewById(R.id.pwd);
        signin = (LinearLayout)findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username_str = username.getText().toString();
                password_str = password.getText().toString();
                if(username_str.equals("")){
                    Toast.makeText(BigC_Login_Activity.this, "please enter username", Toast.LENGTH_SHORT).show();
                }
                else if(password_str.equals("")){
                    Toast.makeText(BigC_Login_Activity.this, "please enter password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(BigC_Login_Activity.this, "values are correct", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        private void login(){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("loading data...");
            progressDialog.show();
            progressDialog.setCancelable(false);

            String url = "http://clients.yellowsoft.in/tourism/api/login.php?email="+username_str+"&password="+password_str;
            Log.e("url", url);

            Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.e("res", jsonObject.toString());
                    try {
                        String temp = jsonObject.getString("status");
                        if (temp.equals("Success"))
                            Toast.makeText(BigC_Login_Activity.this, "welcome user", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(BigC_Login_Activity.this, "enter a valid username", Toast.LENGTH_SHORT).show();

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

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null,listener,errorListener);

            AppController.getInstance().addToRequestQueue(request);

        }



    }


