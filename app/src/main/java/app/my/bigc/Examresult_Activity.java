package app.my.bigc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sriven on 6/7/2016.
 */
public class Examresult_Activity extends Activity {
    LinearLayout reviewexam,backtoresults;
    String correct,wrong,number;
    TextView correct_tv,wrong_tv,you_scored,time_tacken,date,time;
    Exam exams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examresult);
        try {
            exams=new Exam(new JSONObject(getIntent().getStringExtra("exam")));
            number=String.valueOf(exams.questions.size());
            correct=exams.correctt;
            wrong=exams.wrong;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        correct_tv=(TextView)findViewById(R.id.correct_answer);
        wrong_tv=(TextView)findViewById(R.id.wrong_answer);
        time_tacken=(TextView)findViewById(R.id.time_taken);
        you_scored=(TextView)findViewById(R.id.you_scored);
        date = (TextView)findViewById(R.id.date_tv);
        time = (TextView)findViewById(R.id.time_tv);
        reviewexam = (LinearLayout)findViewById(R.id.review_exam);
        backtoresults = (LinearLayout)findViewById(R.id.back_results);
        correct_tv.setText(correct);
        wrong_tv.setText(wrong);
        you_scored.setText("You Scored "+correct+"/"+number);

        String CurrentString = exams.started;
        String[] separated = CurrentString.split(" ");
        if(separated.length>0)
            date.setText("Date : " + separated[0]);
        else
            date.setText("Date : Not Available");

        String CurrentString2 = exams.started;
        String CurrentString3 = exams.ended;
        String[] separated2 = CurrentString2.split(" ");
        String[] separated3 = CurrentString3.split(" ");

        if(separated2.length>1 && separated3.length>1)
            time.setText("Time : "+separated2[1]+" - "+separated3[1] );
        else
            time.setText("");

        reviewexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Examresult_Activity.this, Employee_reexam_Activity.class);
                intent.putExtra("exam", exams.jsonObject.toString());
                startActivity(intent);
            }
        });
        backtoresults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
    }
}
