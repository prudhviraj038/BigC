package app.my.bigc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sriven on 6/7/2016.
 */
public class Exams_list_Activity extends Fragment {
    ExamAdapter offerAdapter;
    ArrayList<Exam> exams;
    ListView offer_list;
    ArrayList<String> lang;
    FragmentTouchListner mCallBack;
    public interface FragmentTouchListner {
        public  void exam_result(String exam);
        public  void emp_exam(String exam);
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
        return inflater.inflate(R.layout.reviewresult, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getView();
        exams=new ArrayList<>();
        lang=new ArrayList<>();
        lang.add("English");
        lang.add("Telugu");
        offerAdapter=new ExamAdapter(getActivity(),exams);
        offer_list=(ListView)v.findViewById(R.id.exam_list);
        offer_list.setAdapter(offerAdapter);
        offer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.e("reponse", exams.get(position).title);
                show_alert(position);

            }
        });


    }


    private void getQuestions(){
        String url;
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait.....");
        progressDialog.show();
        progressDialog.setCancelable(false);
        url = Settings.SERVER_URL+"member_exams.php?member_id="+Settings.get_emp_id(getActivity());
        Log.e("url", url);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                Log.e("reponse", jsonArray.toString());
                exams.clear();
                try {
                    jsonArray = jsonArray.getJSONObject(0).getJSONArray("exams");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject tmp_json = jsonArray.getJSONObject(i);
                        Exam exam=new Exam(tmp_json,getActivity());
                        exams.add(exam);
                    }
                    offerAdapter.notifyDataSetChanged();
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
    @Override
    public void onResume() {
        super.onResume();
        getQuestions();
    }
    public void show_alert(final int position){
        AlertDialog.Builder alert1 = new AlertDialog.Builder(getActivity());
        alert1.setTitle("Choose Language");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, lang);
        alert1.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    Settings.set_user_language(getActivity(), "en");
                    if (exams.get(position).status.equals("Completed")) {
                        mCallBack.exam_result(exams.get(position).jsonObject.toString());
                    } else {
                        if (exams.get(position).questions.size() == 0) {
                            Toast.makeText(getActivity(), "No Questions added to this Exam", Toast.LENGTH_SHORT).show();
                        } else {
                            mCallBack.emp_exam(exams.get(position).jsonObject.toString());
                        }
                    }
                } else {
                    Settings.set_user_language(getActivity(), "te");
                    if (exams.get(position).status.equals("Completed")) {
                        mCallBack.exam_result(exams.get(position).jsonObject.toString());
                    } else {
                        if (exams.get(position).questions.size() == 0) {
                            Toast.makeText(getActivity(), "No Questions added to this Exam", Toast.LENGTH_SHORT).show();
                        } else {
                            mCallBack.emp_exam(exams.get(position).jsonObject.toString());
                        }
                    }
                }
            }

        });
        final AlertDialog dialog = alert1.create();
        dialog.show();
    }
    }
