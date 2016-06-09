package app.my.bigc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by sriven on 6/7/2016.
 */
public class Examresult_Activity extends Activity {
    LinearLayout reviewexam,backtoresults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examresult);
        reviewexam = (LinearLayout)findViewById(R.id.review_exam);
        backtoresults = (LinearLayout)findViewById(R.id.back_results);
        reviewexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        backtoresults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
