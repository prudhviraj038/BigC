package app.my.bigc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sriven on 6/7/2016.
 */
public class Dashboard_Activity extends Activity {
    ImageView employee,customer,menu;
    LinearLayout home,customerfeedback,missedcustomerfeedback,employeexam,result,notifications;
    TextView store_name;
    DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        store_name=(TextView)findViewById(R.id.store_name_navigation);
        employee = (ImageView)findViewById(R.id.employee);
        customer = (ImageView)findViewById(R.id.customer);
        menu = (ImageView)findViewById(R.id.nav_menu_img);
        home = (LinearLayout)findViewById(R.id.home_layout);
        customerfeedback = (LinearLayout)findViewById(R.id.customer_layout);
        missedcustomerfeedback = (LinearLayout)findViewById(R.id.missedcustomer_layout);
        employeexam = (LinearLayout)findViewById(R.id.employeeexam_layout);
        result = (LinearLayout)findViewById(R.id.result_layout);
        notifications = (LinearLayout)findViewById(R.id.notification_layout);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

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
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        store_name.setText(Settings.get_store_name(getApplicationContext()));
        customerfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(Dashboard_Activity.this,CustomerFeedback_Activity.class);
                startActivity(intent);
            }
        });
        missedcustomerfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(Dashboard_Activity.this, Missed_Customer_feedback_Activity.class);
                startActivity(intent);

            }
        });
        employeexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if(Settings.get_emp_id(Dashboard_Activity.this).equals("-1")){
                    Intent intent = new Intent(Dashboard_Activity.this,BigC_Login_Activity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(Dashboard_Activity.this, Employee_exam_Activity.class);
                    startActivity(intent);
                }
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(Dashboard_Activity.this,Examresult_Activity.class);
                startActivity(intent);
            }
        });
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(Dashboard_Activity.this,Offer_Screen_Activity.class);
                startActivity(intent);
            }
        });
    }

}
