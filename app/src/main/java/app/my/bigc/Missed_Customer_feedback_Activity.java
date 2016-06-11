package app.my.bigc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sriven on 6/7/2016.
 */
public class Missed_Customer_feedback_Activity extends Activity {
    TextView selectbrand;
    EditText customername,contactnumber,emailid,customerrequirement,selectmodel,reason,suggestions;
    RadioButton mobile,accessories;
    LinearLayout submit,select_brand_ll;
    String customer_str,contact_str,email_str,requirement_str,brand_str,model_str,reason_str,suggestions_str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.missed_customer_feedback);
        customername = (EditText)findViewById(R.id.customer_name);
        contactnumber = (EditText)findViewById(R.id.contact_number);
        emailid = (EditText)findViewById(R.id.email_id);
        customerrequirement = (EditText)findViewById(R.id.customer_req);
        selectbrand = (TextView)findViewById(R.id.select_brand);
        selectmodel = (EditText)findViewById(R.id.select_model);
        reason = (EditText)findViewById(R.id.reason);
        suggestions = (EditText)findViewById(R.id.suggestions);
        mobile = (RadioButton)findViewById(R.id.mobile);
        accessories = (RadioButton)findViewById(R.id.accessories);
        select_brand_ll = (LinearLayout)findViewById(R.id.select_brand_ll);
        select_brand_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        submit = (LinearLayout)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customer_str = customername.getText().toString();
                contact_str = contactnumber.getText().toString();
                email_str = emailid.getText().toString();
                requirement_str = customerrequirement.getText().toString();
                brand_str = selectbrand.getText().toString();
                model_str = selectmodel.getText().toString();
                reason_str = reason.getText().toString();
                suggestions_str = suggestions.getText().toString();
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                if(customer_str.equals("")){
                    Toast.makeText(Missed_Customer_feedback_Activity.this, "please enter username", Toast.LENGTH_SHORT).show();
                }
                else if(contact_str.equals("")){
                    Toast.makeText(Missed_Customer_feedback_Activity.this, "please enter contact number", Toast.LENGTH_SHORT).show();
                }
                else if(!email_str.matches(emailPattern)){
                    Toast.makeText(Missed_Customer_feedback_Activity.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                }
                else if(requirement_str.equals("")){
                    Toast.makeText(Missed_Customer_feedback_Activity.this, "please enter the requirement", Toast.LENGTH_SHORT).show();
                }
                else if(brand_str.equals("")){
                    Toast.makeText(Missed_Customer_feedback_Activity.this, "please select the brand", Toast.LENGTH_SHORT).show();
                }
                else if(model_str.equals("")){
                    Toast.makeText(Missed_Customer_feedback_Activity.this, "please select the model", Toast.LENGTH_SHORT).show();
                }
                else if(reason_str.equals("")){
                    Toast.makeText(Missed_Customer_feedback_Activity.this, "please enter the reason", Toast.LENGTH_SHORT).show();
                }
                else if(suggestions_str.equals("")){
                    Toast.makeText(Missed_Customer_feedback_Activity.this, "please enter your suggestions", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Missed_Customer_feedback_Activity.this, "thank you for your feedback", Toast.LENGTH_SHORT).show();
                }
                Intent intent;
                intent = new Intent(Missed_Customer_feedback_Activity.this,Offer_Screen_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
