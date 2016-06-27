package app.my.bigc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sriven on 6/8/2016.
 */
public class Employee_exam_Activity extends Fragment {
    Exam exams;
    TextView ques_name,question,ans1,ans2,ans3,ans4,a,b,c,d,question_count;
    LinearLayout next_ll,ans1_ll,ans2_ll,ans3_ll,ans4_ll,progress_bar;
    int i=0,temp=0;
    int corect_count=0,wrong_count=0;
    String correct="0";
    TextView mTextField;
    CountDownTimer countDownTimer;
    LinearLayout all_views ;
    ArrayList<TextView> progress_blocks;
    FragmentTouchListner mCallBack;
    public interface FragmentTouchListner {

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallBack = (Dashboard_Activity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement Listner");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.employee_examscreen, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getView();
        mTextField = (TextView)v.findViewById(R.id.time_exam);
        progress_bar = (LinearLayout)v.findViewById(R.id.progress_bar);
        progress_blocks=new ArrayList<>();
        all_views = (LinearLayout)v.findViewById(R.id.all_views);
        all_views.setVisibility(View.GONE);
        question_count = (TextView)v.findViewById(R.id.question_count);
        try {
            exams=new Exam(new JSONObject(getArguments().getString("exam")),getActivity());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ques_name=(TextView)v.findViewById(R.id.que_name);
        question=(TextView)v.findViewById(R.id.question);
        ans1=(TextView)v.findViewById(R.id.ans1);
        ans2=(TextView)v.findViewById(R.id.ans2);
        ans3=(TextView)v.findViewById(R.id.ans3);
        ans4=(TextView)v.findViewById(R.id.ans4);
        a=(TextView)v.findViewById(R.id.a_ll);
        b=(TextView)v.findViewById(R.id.b_ll);
        c=(TextView)v.findViewById(R.id.c_ll);
        d=(TextView)v.findViewById(R.id.d_ll);
        next_ll=(LinearLayout)v.findViewById(R.id.next);
        ans1_ll=(LinearLayout)v.findViewById(R.id.ans1_ll);
        ans2_ll=(LinearLayout)v.findViewById(R.id.ans2_ll);
        ans3_ll=(LinearLayout)v.findViewById(R.id.ans3_ll);
        ans4_ll=(LinearLayout)v.findViewById(R.id.ans4_ll);
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
                temp=1;
                correct="1";
            }
        });
        ans2_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set_option(2);
                temp=2;
                correct="2";
            }
        });
        ans3_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set_option(3);
                temp=3;
                correct="3";
            }
        });
        ans4_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set_option(4);
                temp=4;
                correct="4";
            }
        });

        next_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Set_option(0);
                correct="0";
                if (temp == Integer.parseInt(exams.questions.get(i).correct))
                    corect_count++;
                else
                    wrong_count++;
                Log.e("cor", String.valueOf(corect_count));
                Log.e("wor", String.valueOf(wrong_count));
                i++;
                temp = 0;
                if (i < exams.questions.size()) {
                    question.setText(exams.questions.get(i).que);
                    ans1.setText(exams.questions.get(i).ans1);
                    ans2.setText(exams.questions.get(i).ans2);
                    ans3.setText(exams.questions.get(i).ans3);
                    ans4.setText(exams.questions.get(i).ans4);
                    question_count.setText(String.valueOf(i + 1) + "/" + String.valueOf(exams.questions.size()));
                    countDownTimer.cancel();
                    countDownTimer.start();

                } else {

                    update_exam_status("1");

                }*/
                update_question_status();

            }
        });

        update_exam_status("0");

    }

    public  void Set_option(int i){
        a.setBackgroundResource(R.drawable.examreviewrounded_corners);
        a.setTextColor(Color.parseColor("#000000"));
        b.setBackgroundResource(R.drawable.examreviewrounded_corners);
        b.setTextColor(Color.parseColor("#000000"));
        c.setBackgroundResource(R.drawable.examreviewrounded_corners);
        c.setTextColor(Color.parseColor("#000000"));
        d.setBackgroundResource(R.drawable.examreviewrounded_corners);
        d.setTextColor(Color.parseColor("#000000"));
        ans1.setBackgroundResource(R.drawable.examreviewrounded_corners);
        ans1.setTextColor(Color.parseColor("#000000"));
        ans2.setBackgroundResource(R.drawable.examreviewrounded_corners);
        ans2.setTextColor(Color.parseColor("#000000"));
        ans3.setBackgroundResource(R.drawable.examreviewrounded_corners);
        ans3.setTextColor(Color.parseColor("#000000"));
        ans4.setBackgroundResource(R.drawable.examreviewrounded_corners);
        ans4.setTextColor(Color.parseColor("#000000"));
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

    @Override
    public void onStop() {
        super.onStop();  // Always call the superclass method first

        // Save the note's current draft, because the activity is stopping
        // and we want to be sure the current note progress isn't lost.
        countDownTimer.cancel();
        Log.e("count down ","cleared");

    }


    private void  update_exam_status(final String status){
        String url = Settings.SERVER_URL+"exam-status.php?member_id="+Settings.get_emp_id(getActivity())+"&exam_id="+exams.id+"&status="+status;
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait.....");
        progressDialog.show();
        progressDialog.setCancelable(false);
        Log.e("url", url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                Log.e("reponse", jsonObject.toString());
                if(status.equals("0")) {
                    Set_option(0);
                    temp = 0;
                    if (i < exams.questions.size()) {
                        question.setText(exams.questions.get(i).que);
                        ans1.setText(exams.questions.get(i).ans1);
                        ans2.setText(exams.questions.get(i).ans2);
                        ans3.setText(exams.questions.get(i).ans3);
                        ans4.setText(exams.questions.get(i).ans4);
                        question_count.setText(String.valueOf(i + 1) + "/" + String.valueOf(exams.questions.size()));
                        countDownTimer.start();

                        for(int i =0 ;i<exams.questions.size();i++){
                            TextView temp = new TextView(getActivity());
                            temp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
                            temp.setBackgroundColor(Color.parseColor("#ff2eff12"));
                            temp.setVisibility(View.INVISIBLE);
                            progress_blocks.add(temp);
                            progress_bar.addView(progress_blocks.get(i));
                        }
                        progress_blocks.get(i).setVisibility(View.VISIBLE);

                    } else {

                        update_exam_status("1");

                    }


                    all_views.setVisibility(View.VISIBLE);

                }
                else {

                    /*Intent intent = new Intent(Employee_exam_Activity.this, Examresult_Activity.class);
                    intent.putExtra("number", String.valueOf(exams.questions.size()));
                    intent.putExtra("correct", String.valueOf(corect_count));
                    intent.putExtra("wrong", String.valueOf(wrong_count));
                    startActivity(intent);*/

//                    finish();
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
        all_views.setVisibility(View.GONE);
        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }
    private void  update_question_status(){
        String url = Settings.SERVER_URL+"member_answers.php?member_id="+Settings.get_emp_id(getActivity())+"&exam_id="+exams.id+"&question_id="+exams.questions.get(i).id+"&answer="+correct;
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait.....");
        progressDialog.show();
        progressDialog.setCancelable(false);
        Log.e("url", url);
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonObject) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                Log.e("reponse", jsonObject.toString());
                try {
                    String status = jsonObject.getString("status");
                    if(status.equals("Success")){
                        Set_option(0);
                        correct="0";

                        if (temp == Integer.parseInt(exams.questions.get(i).correct))
                            corect_count++;
                        else
                            wrong_count++;
                        Log.e("cor", String.valueOf(corect_count));
                        Log.e("wor", String.valueOf(wrong_count));
                        i++;
                        temp = 0;
                        if (i < exams.questions.size()) {
                            question.setText(exams.questions.get(i).que);
                            ans1.setText(exams.questions.get(i).ans1);
                            ans2.setText(exams.questions.get(i).ans2);
                            ans3.setText(exams.questions.get(i).ans3);
                            ans4.setText(exams.questions.get(i).ans4);
                            question_count.setText(String.valueOf(i + 1) + "/" + String.valueOf(exams.questions.size()));
                            countDownTimer.cancel();
                            countDownTimer.start();
                            progress_blocks.get(i).setVisibility(View.VISIBLE);

                        } else {

                            update_exam_status("1");


                        }

                    }
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


}


