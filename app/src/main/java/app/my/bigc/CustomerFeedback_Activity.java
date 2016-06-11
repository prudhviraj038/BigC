package app.my.bigc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by sriven on 6/7/2016.
 */
public class CustomerFeedback_Activity extends Activity {
    EditText customername,contactnumber,emailid,customersuggestions;
    LinearLayout submit;
    String customer_str,contact_str,email_str,customer_suggestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_feedback_screen);
        customername = (EditText)findViewById(R.id.cname);
        contactnumber = (EditText)findViewById(R.id.cnumber);
        emailid = (EditText)findViewById(R.id.emailId);
        customersuggestions = (EditText)findViewById(R.id.customer_suggestions);
        submit = (LinearLayout)findViewById(R.id.customer_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customer_str = customername.getText().toString();
                contact_str = contactnumber.getText().toString();
                email_str = emailid.getText().toString();
                customer_suggestions = customersuggestions.getText().toString();
                if(customer_str.equals("")){
                    Toast.makeText(CustomerFeedback_Activity.this, "please enter customername", Toast.LENGTH_SHORT).show();
                }
                else if(contact_str.equals("")){
                    Toast.makeText(CustomerFeedback_Activity.this, "please enter contact number", Toast.LENGTH_SHORT).show();
                }
                else if(email_str.equals("")){
                    Toast.makeText(CustomerFeedback_Activity.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CustomerFeedback_Activity.this, "thank you for your feedback", Toast.LENGTH_SHORT).show();
                }
                        Intent intent;
                intent = new Intent(CustomerFeedback_Activity.this,Offer_Screen_Activity.class);
                startActivity(intent);
                    }
                });

    }
}
