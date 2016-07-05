package app.my.bigc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sriven on 6/7/2016.
 */
public class Dashboard_Activity extends FragmentActivity implements ChangePasswordActivity.FragmentTouchListner,
        CustomerFeedback.FragmentTouchListner, Employee_exam_Activity.FragmentTouchListner,
        Employee_reexam_Activity.FragmentTouchListner, Examresult_Activity.FragmentTouchListner,
        Exams_list_Activity.FragmentTouchListner, FeedBack_Activity.FragmentTouchListner,
        Missed_Customer_feedback_Activity.FragmentTouchListner, Missed_Customer_List_Activity.FragmentTouchListner,
        Offer_Details_Activity.FragmentTouchListner, Offer_Screen_Activity.FragmentTouchListner,
        Review_Exam_Activity.FragmentTouchListner, HomeFragment.FragmentTouchListner {
    ImageView menu;
    LinearLayout home,customerfeedback,missedcustomerfeedback,missedcustomerlist,employeexam,result,notifications,logout,logout_store,change_password;
    TextView store_name;
    DrawerLayout mDrawerLayout;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        store_name=(TextView)findViewById(R.id.store_name_navigation);
        menu = (ImageView)findViewById(R.id.nav_menu_img);
        home = (LinearLayout)findViewById(R.id.home_layout);
        customerfeedback = (LinearLayout)findViewById(R.id.customer_layout);
        missedcustomerfeedback = (LinearLayout)findViewById(R.id.missedcustomer_layout);
        missedcustomerlist = (LinearLayout)findViewById(R.id.missedcustomerlist_layout);
        employeexam = (LinearLayout)findViewById(R.id.employeeexam_layout);
        result = (LinearLayout)findViewById(R.id.result_layout);
        notifications = (LinearLayout)findViewById(R.id.notification_layout);
        change_password=(LinearLayout) findViewById(R.id.change_password);
        logout=(LinearLayout) findViewById(R.id.logout);
        logout_store =(LinearLayout) findViewById(R.id.logout_store);
        fragmentManager = getSupportFragmentManager();
        HomeFragment fragment1 = new HomeFragment();
        fragmentManager.beginTransaction().replace(R.id.container_main, fragment1).commit();
        if(Settings.get_emp_id(this).equals("-1")){

            logout.setVisibility(View.GONE);

        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Settings.set_emp_id(Dashboard_Activity.this,"-1","-1","-1");
                logout.setVisibility(View.GONE);
                if(mDrawerLayout!=null)
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                HomeFragment fragment1 = new HomeFragment();
                fragmentManager.beginTransaction().replace(R.id.container_main, fragment1).commit();
            }
        });
        logout_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Settings.set_store(Dashboard_Activity.this,"-1","-1","-1");
                logout.setVisibility(View.GONE);
                if(mDrawerLayout!=null)
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(Dashboard_Activity.this, BigC_Login_Activity.class);
                intent.putExtra("type","0");
                startActivity(intent);
                finish();

            }
        });
        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDrawerLayout!=null)
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    ChangePasswordActivity fragment1 = new ChangePasswordActivity();
                    fragmentManager.beginTransaction().replace(R.id.container_main, fragment1).addToBackStack(null).commit();

            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDrawerLayout!=null)
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDrawerLayout!=null)
                mDrawerLayout.closeDrawer(GravityCompat.START);
                HomeFragment fragment1 = new HomeFragment();
                fragmentManager.beginTransaction().replace(R.id.container_main, fragment1).addToBackStack(null).commit();
            }
        });
        store_name.setText(Settings.get_store_name(getApplicationContext()));
        customerfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDrawerLayout!=null)
                mDrawerLayout.closeDrawer(GravityCompat.START);
                CustomerFeedback fragment1 = new CustomerFeedback();
                fragmentManager.beginTransaction().replace(R.id.container_main, fragment1).addToBackStack(null).commit();
            }
        });
        missedcustomerfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDrawerLayout!=null)
                mDrawerLayout.closeDrawer(GravityCompat.START);
               if (Settings.get_emp_id(Dashboard_Activity.this).equals("-1")) {
                    Intent intent = new Intent(Dashboard_Activity.this, BigC_Login_Activity.class);
                    intent.putExtra("type", "emp");
                    intent.putExtra("goto", "missed");
                   startActivityForResult(intent, 4);

                } else {
                   Missed_Customer_feedback_Activity fragment1 = new Missed_Customer_feedback_Activity();
                   fragmentManager.beginTransaction().replace(R.id.container_main, fragment1).addToBackStack(null).commit();
                }
            }
        });
        missedcustomerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDrawerLayout!=null)
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if (Settings.get_emp_id(Dashboard_Activity.this).equals("-1")) {
                    Intent intent = new Intent(Dashboard_Activity.this, BigC_Login_Activity.class);
                    intent.putExtra("type", "emp");
                    intent.putExtra("goto", "missed_list");
                    startActivityForResult(intent,4);
                } else {
                    Missed_Customer_List_Activity fragment1 = new Missed_Customer_List_Activity();
                    fragmentManager.beginTransaction().replace(R.id.container_main, fragment1).addToBackStack(null).commit();
                }
            }
        });
        employeexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDrawerLayout!=null)
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if(Settings.get_emp_id(Dashboard_Activity.this).equals("-1")){
                    Intent intent = new Intent(Dashboard_Activity.this,BigC_Login_Activity.class);
                    intent.putExtra("type","emp");
                    intent.putExtra("goto", "exam");
                    startActivityForResult(intent, 4);
                }else {
                    Exams_list_Activity fragment1 = new Exams_list_Activity();
                    fragmentManager.beginTransaction().replace(R.id.container_main, fragment1).addToBackStack(null).commit();
                }
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDrawerLayout!=null)
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if(Settings.get_emp_id(Dashboard_Activity.this).equals("-1")){
                    Intent intent = new Intent(Dashboard_Activity.this,BigC_Login_Activity.class);
                    intent.putExtra("type","emp");
                    intent.putExtra("goto","reexam");
                    startActivityForResult(intent,4);
                }else {
                    Exams_list_Activity fragment1 = new Exams_list_Activity();
                    fragmentManager.beginTransaction().replace(R.id.container_main, fragment1).addToBackStack(null).commit();
            }}
        });
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDrawerLayout!=null)
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Offer_Screen_Activity fragment1 = new Offer_Screen_Activity();
                fragmentManager.beginTransaction().replace(R.id.container_main, fragment1).addToBackStack(null).commit();
            }
        });

       String gotos =  getIntent().getStringExtra("goto");
        if(gotos!=null){
            if(gotos.equals("Offer")){
                notifications.performClick();
            }
            else if(gotos.equals("Missed_Customer")){
                missedcustomerlist.performClick();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(mDrawerLayout!=null)
            mDrawerLayout.closeDrawer(GravityCompat.START);

        if(Settings.get_emp_id(this).equals("-1")){

            logout.setVisibility(View.GONE);

        }
        else{
            logout.setVisibility(View.VISIBLE);
        }
    }
  @Override
  public  void exam_result(String exam){
      FragmentManager fragmentManager = getSupportFragmentManager();
      Examresult_Activity examresult_activity = new Examresult_Activity();
      Bundle bundle = new Bundle();
      bundle.putSerializable("exam",exam);
      examresult_activity.setArguments(bundle);
      fragmentManager.beginTransaction().replace(R.id.container_main, examresult_activity).addToBackStack(null).commit();
  }
    @Override
    public  void emp_exam(String exam){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Employee_exam_Activity employee_exam_activity = new Employee_exam_Activity();
        Bundle bundle = new Bundle();
        bundle.putSerializable("exam",exam);
        employee_exam_activity.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container_main, employee_exam_activity).addToBackStack(null).commit();
    }
    @Override
    public  void reexam(String exam){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Employee_reexam_Activity employee_reexam_activity = new Employee_reexam_Activity();
        Bundle bundle = new Bundle();
        bundle.putSerializable("exam",exam);
        employee_reexam_activity.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.container_main, employee_reexam_activity).addToBackStack(null).commit();
    }
    @Override
    public  void to_exam_list(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Exams_list_Activity exams_list_activity = new Exams_list_Activity();
        fragmentManager.beginTransaction().replace(R.id.container_main, exams_list_activity).addToBackStack(null).commit();
    }
    @Override
    public  void to_customer(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        CustomerFeedback customerFeedback = new CustomerFeedback();
        fragmentManager.beginTransaction().replace(R.id.container_main, customerFeedback).addToBackStack(null).commit();
    }

    @Override
    public  void to_feedback(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FeedBack_Activity customerFeedback = new FeedBack_Activity();
        fragmentManager.beginTransaction().replace(R.id.container_main, customerFeedback).commit();
    }

    @Override
    public  void to_back(){
        onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==4&&data!=null){
            Toast.makeText(Dashboard_Activity.this,"Employee Login success",Toast.LENGTH_SHORT).show();
//            Exams_list_Activity exams_list_activity = new Exams_list_Activity();
//            fragmentManager.beginTransaction().replace(R.id.container_main, exams_list_activity).addToBackStack(null).commit();

            if(data.getStringExtra("goto").equals("exam")) {
                Exams_list_Activity exams_list_activity1 = new Exams_list_Activity();
                fragmentManager.beginTransaction().replace(R.id.container_main, exams_list_activity1).addToBackStack(null).commit();
            }
            else if(data.getStringExtra("goto").equals("reexam")){
                Exams_list_Activity exams_list_activity2 = new Exams_list_Activity();
                fragmentManager.beginTransaction().replace(R.id.container_main, exams_list_activity2).addToBackStack(null).commit();
            }

            else if(data.getStringExtra("goto").equals("missed")){
                Missed_Customer_feedback_Activity missed_customer_feedback_activity = new Missed_Customer_feedback_Activity();
                fragmentManager.beginTransaction().replace(R.id.container_main, missed_customer_feedback_activity).addToBackStack(null).commit();
            }
            else if(data.getStringExtra("goto").equals("missed_list")) {
                Missed_Customer_List_Activity missed_customer_list_activity = new Missed_Customer_List_Activity();
                fragmentManager.beginTransaction().replace(R.id.container_main, missed_customer_list_activity).addToBackStack(null).commit();
            }
        }else{
            Toast.makeText(Dashboard_Activity.this,"Employee Login Failed",Toast.LENGTH_SHORT).show();
        }

    }
}
