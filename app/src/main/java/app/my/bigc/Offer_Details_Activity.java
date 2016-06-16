package app.my.bigc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by sriven on 6/7/2016.
 */
public class Offer_Details_Activity extends Activity {
    LinearLayout backtoresults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offerscreen);
        backtoresults = (LinearLayout)findViewById(R.id.back_offers_ll);
        backtoresults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
