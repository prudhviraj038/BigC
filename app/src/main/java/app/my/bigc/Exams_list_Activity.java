package app.my.bigc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
public class Exams_list_Activity extends Activity {
    ExamAdapter offerAdapter;
    ArrayList<Exam> exams;
    ListView offer_list;
    ArrayList<String> lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviewresult);
        exams=new ArrayList<>();
        lang=new ArrayList<>();
        lang.add("English");
        lang.add("Telugu");
        offerAdapter=new ExamAdapter(this,exams);
        offer_list=(ListView)findViewById(R.id.exam_list);
        offer_list.setAdapter(offerAdapter);
        offer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("reponse", exams.get(position).title);
                show_alert(position);

            }
        });


    }


    private void getQuestions(){
        String url;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait.....");
        progressDialog.show();
        progressDialog.setCancelable(false);
        url = Settings.SERVER_URL+"member_exams.php?member_id="+Settings.get_emp_id(getApplicationContext());
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
                        Exam exam=new Exam(tmp_json);
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
    protected void onResume() {
        super.onResume();
        getQuestions();
    }
    public void show_alert(final int position){
        AlertDialog.Builder alert1 = new AlertDialog.Builder(this);
        alert1.setTitle("Choose Language");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, lang);
        alert1.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    Settings.set_user_language(Exams_list_Activity.this, "en");
                    if (exams.get(position).status.equals("Completed")) {
                        Intent intent = new Intent(Exams_list_Activity.this, Examresult_Activity.class);
                        intent.putExtra("exam", exams.get(position).jsonObject.toString());
                        startActivity(intent);
                    } else {
                        if (exams.get(position).questions.size() == 0) {
                            Toast.makeText(Exams_list_Activity.this, "No Questions added to this Exam", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(Exams_list_Activity.this, Employee_exam_Activity.class);
                            intent.putExtra("exam", exams.get(position).jsonObject.toString());
                            startActivity(intent);
                        }
                    }
                } else {
                    Settings.set_user_language(Exams_list_Activity.this, "te");
                    if (exams.get(position).status.equals("Completed")) {
                        Intent intent = new Intent(Exams_list_Activity.this, Examresult_Activity.class);
                        intent.putExtra("exam", exams.get(position).jsonObject.toString());
                        startActivity(intent);
                    } else {
                        if (exams.get(position).questions.size() == 0) {
                            Toast.makeText(Exams_list_Activity.this, "No Questions added to this Exam", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(Exams_list_Activity.this, Employee_exam_Activity.class);
                            intent.putExtra("exam", exams.get(position).jsonObject.toString());
                            startActivity(intent);
                        }
                    }
                }
            }

        });
        final AlertDialog dialog = alert1.create();
        dialog.show();
    }
    }
