package app.my.bigc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sriven on 6/7/2016.
 */
public class Missed_Customer_feedback_Activity extends Fragment {
    String brand_id="0";
    String model_id="0";
    String fulfill_date="0";
    private int  mYear, mMonth, mDay,mHour, mMinute;
    TextView selectbrand,selectmodel,no_of_days_tv;
    ArrayList<String> brands_id;
    ArrayList<String> brands_title;
    ArrayList<String> models_id;
    ArrayList<String> models_title;
    ArrayList<String> no_of_days;
    EditText customername,contactnumber,emailid,customerrequirement,reason,suggestions;
    RadioButton mobile,accessories;
    LinearLayout submit,select_brand_ll,select_model_ll,no_of_days_ll;
    String customer_str,contact_str,email_str,requirement_str,brand_str,model_str,reason_str,suggestions_str,mobile_acs,no_days;
    FragmentTouchListner mCallBack;
    public interface FragmentTouchListner {
        public  void go_back();
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
        return inflater.inflate(R.layout.missed_customer_feedback, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getView();
        brand_str="";
        model_str="";
        mobile_acs="";

        mobile = (RadioButton)v.findViewById(R.id.mobile);
        accessories = (RadioButton)v.findViewById(R.id.accessories);
        mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessories.setChecked(false);
                mobile_acs="mobile";
            }
        });

        accessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile.setChecked(false);
                mobile_acs="accessories";

            }
        });
        no_of_days= new ArrayList<String>();
        for(int i=1;i<=7;i++)
        no_of_days.add(String.valueOf(i));

        brands_id= new ArrayList<String>();
        brands_title=new ArrayList<String>();
        models_id= new ArrayList<String>();
        models_title=new ArrayList<String>();
        customername = (EditText)v.findViewById(R.id.customer_name);
        contactnumber = (EditText)v.findViewById(R.id.contact_number);
        emailid = (EditText)v.findViewById(R.id.email_id);
        customerrequirement = (EditText)v.findViewById(R.id.customer_req);
        selectbrand = (TextView)v.findViewById(R.id.select_brand);
        selectmodel = (TextView)v.findViewById(R.id.select_model);
        no_of_days_tv = (TextView)v.findViewById(R.id.no_of_days_tv);
        reason = (EditText)v.findViewById(R.id.reason);
        suggestions = (EditText)v.findViewById(R.id.suggestions);
        mobile = (RadioButton)v.findViewById(R.id.mobile);
        accessories = (RadioButton)v.findViewById(R.id.accessories);

        select_brand_ll = (LinearLayout)v.findViewById(R.id.select_brand_ll);
        select_brand_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("CHOOSE BRAND");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, brands_title);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(ChooseSubjectActivity.this, level_title.get(which), Toast.LENGTH_SHORT).show();
                        brand_id = brands_id.get(which);
                        brand_str = brands_title.get(which);
                        model_id = "not selected";
                        model_str = "not selected";
                        selectbrand.setText(brands_title.get(which));
                        selectmodel.setText("Select Model");
                        get_model();
                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        get_brand();
        select_model_ll = (LinearLayout)v.findViewById(R.id.select_model_ll);
        select_model_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (brand_id.equals(""))
                    Toast.makeText(getActivity(), "Please select Brand", Toast.LENGTH_SHORT).show();
                else if(models_title.size()==0){
                    Toast.makeText(getActivity(), "No models available for this brand", Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("CHOOSE MODEL");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, models_title);
                    builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(ChooseSubjectActivity.this, level_title.get(which), Toast.LENGTH_SHORT).show();
                            model_id = models_id.get(which);
                            model_str = models_title.get(which);
                            selectmodel.setText(models_title.get(which));
                        }
                    });

                    final AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
        no_of_days_ll = (LinearLayout)v.findViewById(R.id.no_of_dayss);

        /* no_of_days_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String temp=String.valueOf(monthOfYear+1);
                        if(temp.length()<2)
                            temp="0"+temp;
                        String temp1=String.valueOf(dayOfMonth);
                        if(temp1.length()<2)
                            temp1="0"+temp1;
                        fulfill_date = year + "-" + temp + "-" +temp1;

                        String strThatDay = fulfill_date;
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date d = null;
                        try {
                            d = formatter.parse(strThatDay);//catch exception
                            Calendar thatDay = Calendar.getInstance();
                            thatDay.setTime(d);
                            Calendar today = Calendar.getInstance();
                            long diff =   today.getTimeInMillis() - thatDay.getTimeInMillis();
                            long days = diff / (24 * 60 * 60 * 1000);
                            Log.e("date dif",String.valueOf(diff));
                            Log.e("today",today.toString());
                            Log.e("selectedday",thatDay.toString());

                            if(thatDay.before(today)){
                                fulfill_date="";
                                no_of_days_tv.setText(fulfill_date);
                                Toast.makeText(getActivity(),"please select a date after today.",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                no_of_days_tv.setText(fulfill_date);
                            }

                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                                            }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        }); */

        no_of_days_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Select no of days");
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, no_of_days);
                    builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(ChooseSubjectActivity.this, level_title.get(which), Toast.LENGTH_SHORT).show();

                            fulfill_date = no_of_days.get(which);
                            no_of_days_tv.setText(no_of_days.get(which));
                        }
                    });

                    final AlertDialog dialog = builder.create();
                    dialog.show();

            }
        });

        submit = (LinearLayout)v.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customer_str = customername.getText().toString();
                contact_str = contactnumber.getText().toString();
                email_str = emailid.getText().toString();
                requirement_str = customerrequirement.getText().toString();
                model_str = selectmodel.getText().toString();
                reason_str = reason.getText().toString();
                suggestions_str = suggestions.getText().toString();
                String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                if(customer_str.equals("")){
                    Toast.makeText(getActivity(), "please enter username", Toast.LENGTH_SHORT).show();
                }
                else if(contact_str.equals("")){
                    Toast.makeText(getActivity(), "please enter contact number", Toast.LENGTH_SHORT).show();
                }
                else if((!email_str.equals(""))&& (!email_str.matches(emailPattern))){
                    Toast.makeText(getActivity(), "please enter valid email", Toast.LENGTH_SHORT).show();
                }

                else if(mobile_acs.equals("")){
                    Toast.makeText(getActivity(), "please select mobile or accessories", Toast.LENGTH_SHORT).show();
                }
               // else if(brand_str.equals("")){
                 //   Toast.makeText(Missed_Customer_feedback_Activity.this, "please select the brand", Toast.LENGTH_SHORT).show();
               // }
             //   else if(model_str.equals("")){
              //     Toast.makeText(Missed_Customer_feedback_Activity.this, "please select the model", Toast.LENGTH_SHORT).show();
             //   }
                else if(fulfill_date.equals("")||fulfill_date.equals("0")){
                    Toast.makeText(getActivity(), "please select FullFill days", Toast.LENGTH_SHORT).show();
                }
                else if(reason_str.equals("")){
                    Toast.makeText(getActivity(), "please enter the reason", Toast.LENGTH_SHORT).show();
                }
              //  else if(suggestions_str.equals("")){
              //      Toast.makeText(Missed_Customer_feedback_Activity.this, "please enter your suggestions", Toast.LENGTH_SHORT).show();
              //  }
                else {
                    submit.setEnabled(false);
                    send_missed_customer_feedback();
//                   Toast.makeText(Missed_Customer_feedback_Activity.this, "thank you for your feedback", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void get_brand(){
        String url=Settings.SERVER_URL+"brands.php";
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonArray.toString());
                try {
                    brands_title.clear();
                    brands_id.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sub = jsonArray.getJSONObject(i);
                        String brand_name = sub.getString("title");
                        String bran_id = sub.getString("id");
                        brands_id.add(bran_id);
                        brands_title.add(brand_name);
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
                Toast.makeText(getActivity(),"Server not connected",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        // Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }


    private void get_model(){
        String url=Settings.SERVER_URL+"models.php?brand="+brand_id;
        Log.e("url--->", url);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                progressDialog.dismiss();
                Log.e("response is: ", jsonArray.toString());
                try {
                    models_title.clear();
                    models_id.clear();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject sub = jsonArray.getJSONObject(i);
                        String model_name = sub.getString("title");
                        String mode_id = sub.getString("id");
                        models_id.add(mode_id);
                        models_title.add(model_name);
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
                Toast.makeText(getActivity(),"Server not connected",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        // Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
    public  void send_missed_customer_feedback(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait.. we are processing");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String url = Settings.SERVER_URL+"missed-customer.php?";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Log.e("response",jsonObject.toString());
                    String reply=jsonObject.getString("status");
                    if(reply.equals("Success")) {
                        String msg = jsonObject.getString("message");
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
//                        finish();
                        mCallBack.go_back();
                    }
                    else {
                        String msg=jsonObject.getString("message");
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(progressDialog!=null)
                            progressDialog.dismiss();
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("store_id",Settings.get_store(getActivity()));
                params.put("member_id",Settings.get_emp_id(getActivity()));
                params.put("name",customer_str);
                params.put("phone",contact_str);
                params.put("email",email_str);
                params.put("requirement",requirement_str);
                params.put("type",mobile_acs);
                params.put("brand",brand_id);
                params.put("model",model_id);
                params.put("reason",reason_str);
                params.put("fulfill_days",fulfill_date);
                params.put("suggestions",suggestions_str);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
