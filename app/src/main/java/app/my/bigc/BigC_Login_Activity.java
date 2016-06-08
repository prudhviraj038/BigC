package app.my.bigc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
                if(password_str.equals("")){
                    Toast.makeText(BigC_Login_Activity.this, "please enter password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(BigC_Login_Activity.this, "values are correct", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

        private void login(){


    }

}
