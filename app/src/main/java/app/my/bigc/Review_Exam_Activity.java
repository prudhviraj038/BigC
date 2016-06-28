package app.my.bigc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by sriven on 6/8/2016.
 */
public class Review_Exam_Activity extends Fragment{
    LinearLayout next,backtoresult;
    Exam exams;
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
        return inflater.inflate(R.layout.review_exam_screen, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getView();
//        try {
//            exams=new Exam(new JSONObject(getIntent().getStringExtra("exam")),this);

//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        next = (LinearLayout)v.findViewById(R.id.review_next);
        backtoresult = (LinearLayout)v.findViewById(R.id.review_results);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        backtoresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
