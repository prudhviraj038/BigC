package app.my.bigc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sriven on 6/7/2016.
 */
public class Examresult_Activity extends Fragment {
    LinearLayout reviewexam,backtoresults;
    String correct,wrong,number;
    TextView correct_tv,wrong_tv,you_scored,time_tacken,date,time,title;
    Exam exams;
    FragmentTouchListner mCallBack;
    public interface FragmentTouchListner {
    public void reexam(String exam);
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
        return inflater.inflate(R.layout.examresult, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getView();
        try {
            exams=new Exam(new JSONObject(getArguments().getString("exam")),getActivity());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        number=String.valueOf(exams.questions.size());
        correct=exams.correctt;
        wrong=exams.wrong;
        title = (TextView)v.findViewById(R.id.exam_title_review);
        correct_tv=(TextView)v.findViewById(R.id.correct_answer);
        wrong_tv=(TextView)v.findViewById(R.id.wrong_answer);
        time_tacken=(TextView)v.findViewById(R.id.time_taken);
        you_scored=(TextView)v.findViewById(R.id.you_scored);
        date = (TextView)v.findViewById(R.id.date_tv);
        time = (TextView)v.findViewById(R.id.time_tv);
        title.setText("Your Result For "+exams.title);
        reviewexam = (LinearLayout)v.findViewById(R.id.review_exam);
        backtoresults = (LinearLayout)v.findViewById(R.id.back_results);
        correct_tv.setText(correct);
        wrong_tv.setText(wrong);
        you_scored.setText("You Scored "+correct+"/"+number);
        time_tacken.setText(exams.duration);
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
                mCallBack.reexam(exams.jsonObject.toString());
            }
        });
        backtoresults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                finish();

            }
        });
    }
}
