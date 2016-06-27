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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sriven on 6/7/2016.
 */
public class ChangePasswordActivity extends Fragment {
    LinearLayout submit,acc_type;
    EditText new_password, confirm_password,old_password;
    ArrayList<String> account_type;
    TextView typ_tv;
    String typee="stores";
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
        return inflater.inflate(R.layout.change_password, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getView();
        account_type=new ArrayList<>();
        account_type.add("Store");
        account_type.add("Employee");
        typ_tv=(TextView)v.findViewById(R.id.type_tv);
        old_password = (EditText) v.findViewById(R.id.old_password);
        new_password = (EditText)v.findViewById(R.id.new_password);
        confirm_password = (EditText)v.findViewById(R.id.confirm_password);
               acc_type = (LinearLayout)v.findViewById(R.id.type_cpassword);
        if(Settings.get_emp_id(getActivity()).equals("-1"))
            acc_type.setVisibility(View.GONE);
        else
            acc_type.setVisibility(View.VISIBLE);

        acc_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert1 = new AlertDialog.Builder(getActivity());
                alert1.setTitle("Choose Account Type");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, account_type);
                alert1.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            typ_tv.setText("Store");
                            typee="stores";
                        }else{
                            typ_tv.setText("Employee");
                            typee="members";
                        }
                    }

                });
                final AlertDialog dialog = alert1.create();
                dialog.show();

            }
        });
        submit = (LinearLayout)v.findViewById(R.id.pass_submit_ll);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_password();
            }
        });

    }

    public void change_password() {
        final String new_pass = new_password.getText().toString();
        final String confirm_pass = confirm_password.getText().toString();
//        if (new_pass.length() < 6)
//            alert.showAlertDialog(getActivity(), "Info", Settings.getword(getActivity(), "password_lenth"), false);
//            Toast.makeText(ChangePasswordActivity.this,"Password lenth should begreather than 6 characters", Toast.LENGTH_SHORT).show();
//        else
        if (!new_pass.equals(confirm_pass)) {
//            alert.showAlertDialog(getActivity(), "Info", Settings.getword(getActivity(), "please_enter_same_password"), false);
            Toast.makeText(getActivity(), "Please enter same password", Toast.LENGTH_SHORT).show();
            confirm_password.setText("");
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Please wait........");
            progressDialog.show();
            progressDialog.setCancelable(false);
            String url = Settings.SERVER_URL + "change-password.php?";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (progressDialog != null)
                        progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String reply = jsonObject.getString("status");
                        if (reply.equals("Success")) {
                            String msg = jsonObject.getString("message");
                            Log.e("msg", msg);
                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
//                            finish();
                        } else {
                            String msg = jsonObject.getString("message");
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
                            if (progressDialog != null)
                                progressDialog.dismiss();
                            Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("type", typee);
                    if(typee.equals("store"))
                        params.put("member_id", Settings.get_store(getActivity()));
                    else
                        params.put("member_id", Settings.get_emp_id(getActivity()));
                    params.put("opassword",old_password.getText().toString());
                    params.put("password",new_pass);
                    params.put("cpassword",confirm_pass);
                    return params;
                }
            };
            AppController.getInstance().addToRequestQueue(stringRequest);
        }
    }
}
