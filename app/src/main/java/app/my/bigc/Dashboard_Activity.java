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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_screen);
        employee = (ImageView)findViewById(R.id.employee);
        customer = (ImageView)findViewById(R.id.customer);
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
        }

}
