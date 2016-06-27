package app.my.bigc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by sriven on 6/7/2016.
 */
public class HomeFragment extends Fragment {
    LinearLayout backtoresults;
    ImageView employee,customer,menu;
    FragmentTouchListner mCallBack;
    public interface FragmentTouchListner {
        public void  to_exam_list();
        public  void to_customer();
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
        return inflater.inflate(R.layout.home_screen, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getView();
        employee = (ImageView)v.findViewById(R.id.employee);
        customer = (ImageView)v.findViewById(R.id.customer);
        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Settings.get_emp_id(getActivity()).equals("-1")){
                    Intent intent = new Intent(getActivity(),BigC_Login_Activity.class);
                    intent.putExtra("type","emp");
                    intent.putExtra("goto","exam");
                    startActivity(intent);
                }else {
                    mCallBack.to_exam_list();

                }
            }

        });
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.to_customer();

            }
        });
    }
}
