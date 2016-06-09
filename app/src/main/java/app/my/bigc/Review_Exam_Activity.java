package app.my.bigc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by sriven on 6/8/2016.
 */
public class Review_Exam_Activity extends Activity {
    LinearLayout next,backtoresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_exam_screen);
        backtoresult = (LinearLayout)findViewById(R.id.back_offers);
        backtoresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
