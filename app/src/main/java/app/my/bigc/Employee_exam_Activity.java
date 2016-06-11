package app.my.bigc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sriven on 6/8/2016.
 */
public class Employee_exam_Activity extends Activity {
    ArrayList<Exam> exams;
    TextView ques_name,question,ans1,ans2,ans3,ans4,a,b,c,d,question_count;
    LinearLayout next_ll,ans1_ll,ans2_ll,ans3_ll,ans4_ll;
    int i=0;
    String correct="0";
    TextView mTextField;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_examscreen);
        mTextField = (TextView) findViewById(R.id.time_exam);
        question_count = (TextView) findViewById(R.id.question_count);
        exams=new ArrayList<>();
        ques_name=(TextView)findViewById(R.id.que_name);
        question=(TextView)findViewById(R.id.question);
        ans1=(TextView)findViewById(R.id.ans1);
        ans2=(TextView)findViewById(R.id.ans2);
        ans3=(TextView)findViewById(R.id.ans3);
        ans4=(TextView)findViewById(R.id.ans4);
        a=(TextView)findViewById(R.id.a_ll);
        b=(TextView)findViewById(R.id.b_ll);
        c=(TextView)findViewById(R.id.c_ll);
        d=(TextView)findViewById(R.id.d_ll);
        next_ll=(LinearLayout)findViewById(R.id.next);
        ans1_ll=(LinearLayout)findViewById(R.id.ans1_ll);
        ans2_ll=(LinearLayout)findViewById(R.id.ans2_ll);
        ans3_ll=(LinearLayout)findViewById(R.id.ans3_ll);
        ans4_ll=(LinearLayout)findViewById(R.id.ans4_ll);
       countDownTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText("00:" + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                // mTextField.setText("done!");
                next_ll.performClick();
            }

        };
        ans1_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set_option(1);
                correct="a";
            }
        });
        ans2_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set_option(2);
                correct="b";
            }
        });
        ans3_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set_option(3);
                correct="c";
            }
        });
        ans4_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set_option(4);
                correct="d";
            }
        });

        next_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if(i<exams.get(0).questions.size()){
                    question.setText(exams.get(0).questions.get(i).que);
                    ans1.setText(exams.get(0).questions.get(i).ans1);
                    ans2.setText(exams.get(0).questions.get(i).ans2);
                    ans3.setText(exams.get(0).questions.get(i).ans3);
                    ans4.setText(exams.get(0).questions.get(i).ans4);
                    question_count.setText(String.valueOf(i+1)+"/"+String.valueOf(exams.get(0).questions.size()));
                    countDownTimer.cancel();
                    countDownTimer.start();
                }

            }
        });
        getQuestions();
    }
    private void getQuestions(){
        String url;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait.....");
        progressDialog.show();
        progressDialog.setCancelable(false);
        url = Settings.SERVER_URL+"questions.php?member="+Settings.get_emp_id(getApplicationContext());
        Log.e("url", url);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                Log.e("reponse", jsonArray.toString());
                try {
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject tmp_json = jsonArray.getJSONObject(i);
                        Exam exam=new Exam(tmp_json);
                        exams.add(exam);
                    }
                    ques_name.setText(exams.get(0).title);
                    question.setText(exams.get(0).questions.get(i).que);
                    ans1.setText(exams.get(0).questions.get(i).ans1);
                    ans2.setText(exams.get(0).questions.get(i).ans2);
                    ans3.setText(exams.get(0).questions.get(i).ans3);
                    ans4.setText(exams.get(0).questions.get(i).ans4);
                    question_count.setText(String.valueOf(i+1)+"/"+String.valueOf(exams.get(0).questions.size()));
                    countDownTimer.start();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                Log.e("response is:", error.toString());
                if(progressDialog!=null)
                    progressDialog.dismiss();

            }
        });

// Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }
    public  void Set_option(int i){
    a.setBackgroundResource(R.drawable.examreviewrounded_corners);
    b.setBackgroundResource(R.drawable.examreviewrounded_corners);
    c.setBackgroundResource(R.drawable.examreviewrounded_corners);
    d.setBackgroundResource(R.drawable.examreviewrounded_corners);
    ans1.setBackgroundResource(R.drawable.examreviewrounded_corners);
    ans2.setBackgroundResource(R.drawable.examreviewrounded_corners);
    ans3.setBackgroundResource(R.drawable.examreviewrounded_corners);
    ans4.setBackgroundResource(R.drawable.examreviewrounded_corners);
        switch (i){
            case 1:
                a.setBackgroundResource(R.drawable.examreviewrounded_red_corners);
                a.setTextColor(Color.parseColor("#ffffff"));
                ans1.setBackgroundResource(R.drawable.examreviewrounded_red_corners);
                ans1.setTextColor(Color.parseColor("#ffffff"));
                break;
            case 2:
                b.setBackgroundResource(R.drawable.examreviewrounded_red_corners);
                b.setTextColor(Color.parseColor("#ffffff"));
                ans2.setBackgroundResource(R.drawable.examreviewrounded_red_corners);
                ans2.setTextColor(Color.parseColor("#ffffff"));
                break;
            case 3:
                c.setBackgroundResource(R.drawable.examreviewrounded_red_corners);
                c.setTextColor(Color.parseColor("#ffffff"));
                ans3.setBackgroundResource(R.drawable.examreviewrounded_red_corners);
                ans3.setTextColor(Color.parseColor("#ffffff"));
                break;
            case 4:
                d.setBackgroundResource(R.drawable.examreviewrounded_red_corners);
                d.setTextColor(Color.parseColor("#ffffff"));
                ans4.setBackgroundResource(R.drawable.examreviewrounded_red_corners);
                ans4.setTextColor(Color.parseColor("#ffffff"));
                break;
        }
    }
}


