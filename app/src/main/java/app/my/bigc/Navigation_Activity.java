package app.my.bigc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by sriven on 6/10/2016.
 */
public class Navigation_Activity extends Activity {
 LinearLayout home,customerfeedback,missedcustomerfeedback,employeexam,result,notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);
        home = (LinearLayout)findViewById(R.id.home_layout);
        customerfeedback = (LinearLayout)findViewById(R.id.customer_layout);
        missedcustomerfeedback = (LinearLayout)findViewById(R.id.missedcustomer_layout);
        employeexam = (LinearLayout)findViewById(R.id.employeeexam_layout);
        result = (LinearLayout)findViewById(R.id.result_layout);
        notification = (LinearLayout)findViewById(R.id.notification_layout);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        customerfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Navigation_Activity.this,CustomerFeedback_Activity.class);
                startActivity(intent);
            }
        });
        missedcustomerfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Navigation_Activity.this,Missed_Customer_feedback_Activity.class);
                startActivity(intent);

            }
        });
        employeexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Navigation_Activity.this,Employee_exam_Activity.class);
                startActivity(intent);
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Navigation_Activity.this,Examresult_Activity.class);
                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Navigation_Activity.this,Offer_Screen_Activity.class);
                startActivity(intent);
            }
        });
    }
}
