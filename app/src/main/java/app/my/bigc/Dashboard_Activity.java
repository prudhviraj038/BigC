package app.my.bigc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sriven on 6/7/2016.
 */
public class Dashboard_Activity extends Activity {
    ImageView employee,customer;
    LinearLayout home,customerfeedback,missedcustomerfeedback,employeexam,result,notification;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);
        employee = (ImageView)findViewById(R.id.employee);
        customer = (ImageView)findViewById(R.id.customer);
        home = (LinearLayout)findViewById(R.id.home_layout);
        customerfeedback = (LinearLayout)findViewById(R.id.customer_layout);
        missedcustomerfeedback = (LinearLayout)findViewById(R.id.missedcustomer_layout);
        employeexam = (LinearLayout)findViewById(R.id.employeeexam_layout);
        result = (LinearLayout)findViewById(R.id.result_layout);
        notification = (LinearLayout)findViewById(R.id.notification_layout);

        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_Activity.this,BigC_Login_Activity.class);
                intent.putExtra("type","emp");
                startActivity(intent);

            }
        });
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_Activity.this,BigC_Login_Activity.class);
                intent.putExtra("type","cus");
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        customerfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_Activity.this,CustomerFeedback_Activity.class);
                startActivity(intent);
            }
        });
        missedcustomerfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_Activity.this,Missed_Customer_feedback_Activity.class);
                startActivity(intent);

            }
        });
        employeexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_Activity.this,Employee_exam_Activity.class);
                startActivity(intent);
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_Activity.this,Examresult_Activity.class);
                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard_Activity.this,Offer_Screen_Activity.class);
                startActivity(intent);
            }
        });
    }

}
