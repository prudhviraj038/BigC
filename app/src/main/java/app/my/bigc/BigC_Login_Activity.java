package app.my.bigc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
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
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                if(!username_str.matches(emailPattern)){
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
            if(type.equals("emp"))
               url = Settings.SERVER_URL+"store-login.php?email="+username_str+"&password="+password_str;
            else
                url = Settings.SERVER_URL+"store-login.php?email="+username_str+"&password="+password_str;

            Log.e("url", url);

            Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray jsonObject) {
                    Log.e("res", jsonObject.toString());
                    try {
                        String temp = jsonObject.getJSONObject(0).getString("status");
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

            JsonArrayRequest request = new JsonArrayRequest(url,listener,errorListener);

            AppController.getInstance().addToRequestQueue(request);

        }
    }


