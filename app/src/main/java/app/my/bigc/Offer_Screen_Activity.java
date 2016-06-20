package app.my.bigc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sriven on 6/7/2016.
 */
public class Offer_Screen_Activity extends Activity {
    OfferAdapter offerAdapter;
    ArrayList<Offers> offers;
    ListView offer_list;
    LinearLayout backtooffers,offersdisplay;
    TextView title,discription,expiry,expirydate;
    ImageView offers_image,status;
    ViewFlipper offer_details;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers_screen);
        offers=new ArrayList<>();
        offersdisplay = (LinearLayout)findViewById(R.id.offers_dispaly);
        offer_details = (ViewFlipper)findViewById(R.id.viewFlipper);
        title = (TextView)findViewById(R.id.offers_title);
        discription = (TextView)findViewById(R.id.offers_discription);
        expiry = (TextView)findViewById(R.id.offers_expiry);
        expirydate = (TextView)findViewById(R.id.offers_expiry_date);
        offers_image = (ImageView)findViewById(R.id.offers_image_ll);
        backtooffers = (LinearLayout)findViewById(R.id.back_offers_ll);
        offerAdapter=new OfferAdapter(this,offers);
        offer_list=(ListView)findViewById(R.id.offer_list);
        offer_list.setAdapter(offerAdapter);
        status=(ImageView)findViewById(R.id.offer_status_page);
        offer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.e("reponse", offers.get(position).title);
                offer_details.setDisplayedChild(1);
               title.setText(offers.get(position).title);
               Picasso.with(getApplicationContext()).load(offers.get(position).image).into(offers_image);
                offers_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Offer_Screen_Activity.this,ImageZoomActivity.class);
                        intent.putExtra("url",offers.get(position).image);
                        startActivity(intent);
                    }
                });
              discription.setText(offers.get(position).discription);
                expirydate.setText(offers.get(position).expirydate);
                if(offers.get(position).status.equals("Opened"))
                    status.setImageResource(R.drawable.active_img);
                else
                    status.setImageResource(R.drawable.expired_img);
            }
        });
        getOffers();

        backtooffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offer_details.setDisplayedChild(0);
                getOffers();
            }
        });


    }
    private void getOffers(){
        String url;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait.....");
        progressDialog.show();
        progressDialog.setCancelable(false);
        url = Settings.SERVER_URL+"offers.php";
        Log.e("url", url);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                Log.e("reponse", jsonArray.toString());
                try {
                    offers.clear();
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject tmp_json = jsonArray.getJSONObject(i);
                        Offers offer=new Offers(tmp_json);
                        offers.add(offer);
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

}
