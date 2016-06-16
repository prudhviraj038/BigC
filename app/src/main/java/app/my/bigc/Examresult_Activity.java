package app.my.bigc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sriven on 6/7/2016.
 */
public class Examresult_Activity extends Activity {
    LinearLayout reviewexam,backtoresults;
    String correct,wrong,number;
    TextView correct_tv,wrong_tv,you_scored,time_tacken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examresult);
        number=getIntent().getStringExtra("number");
        correct=getIntent().getStringExtra("correct");
        wrong=getIntent().getStringExtra("wrong");
        correct_tv=(TextView)findViewById(R.id.correct_answer);
        wrong_tv=(TextView)findViewById(R.id.wrong_answer);
        time_tacken=(TextView)findViewById(R.id.time_taken);
        you_scored=(TextView)findViewById(R.id.you_scored);
        reviewexam = (LinearLayout)findViewById(R.id.review_exam);
        backtoresults = (LinearLayout)findViewById(R.id.back_results);
        correct_tv.setText(correct);
        wrong_tv.setText(wrong);
        you_scored.setText("You Scored "+correct+"/"+number);
        reviewexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Examresult_Activity.this, Employee_exam_Activity.class);


            }
        });
        backtoresults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }
}
