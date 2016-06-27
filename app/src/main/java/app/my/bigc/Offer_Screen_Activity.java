package app.my.bigc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
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


public class Offer_Screen_Activity extends Fragment {
    OfferAdapter offerAdapter;
    NotificationAdapter notificationAdapter;
    ArrayList<Offers> offers;
    ArrayList<Notifications> notificationses;
    ListView offer_list,noti_list;
    LinearLayout backtooffers,offersdisplay;
    TextView title,discription,expiry,expirydate;
    ImageView offers_image,status;
    ViewFlipper offer_details;
    int position;
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
        return inflater.inflate(R.layout.offers_screen, container, false);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getView();
        offers=new ArrayList<>();
        notificationses=new ArrayList<>();
        offersdisplay = (LinearLayout)v.findViewById(R.id.offers_dispaly);
        offer_details = (ViewFlipper)v.findViewById(R.id.viewFlipper);
        title = (TextView)v.findViewById(R.id.offers_title);
        discription = (TextView)v.findViewById(R.id.offers_discription);
        expiry = (TextView)v.findViewById(R.id.offers_expiry);
        expirydate = (TextView)v.findViewById(R.id.offers_expiry_date);
        offers_image = (ImageView)v.findViewById(R.id.offers_image_ll);
        backtooffers = (LinearLayout)v.findViewById(R.id.back_offers_ll);
        offerAdapter=new OfferAdapter(getActivity(),offers);
        offer_list=(ListView)v.findViewById(R.id.offer_list);
        offer_list.setAdapter(offerAdapter);
        status=(ImageView)v.findViewById(R.id.offer_status_page);
        noti_list=(ListView)v.findViewById(R.id.noti_list_view);
        notificationAdapter=new NotificationAdapter(getActivity(),notificationses);
        noti_list.setAdapter(notificationAdapter);
        offer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.e("reponse", offers.get(position).title);
                offer_details.setDisplayedChild(1);
                title.setText(offers.get(position).title);
                Picasso.with(getActivity()).load(offers.get(position).image).into(offers_image);
                offers_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ImageZoomActivity.class);
                        intent.putExtra("url", offers.get(position).image);
                        startActivity(intent);
                    }
                });
                discription.setText(offers.get(position).discription);
                expirydate.setText("From : " + offers.get(position).startdate + " To : " + offers.get(position).expirydate);
                if (offers.get(position).status.equals("Opened"))
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

//    public void onBackPressed() {
//        if (offer_details.getDisplayedChild()==1)
//            offer_details.setDisplayedChild(0);
//        else
//            super.onBackPressed();
//    }


    private void getOffers(){
        String url;
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
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
                    setListViewHeightBasedOnItems(offer_list);
                    getNotifications();
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
    private void getNotifications(){
        String url;
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("please wait.....");
        progressDialog.show();
        progressDialog.setCancelable(false);
        url = Settings.SERVER_URL+"push-notifications.php?store_id="+Settings.get_store(getActivity());
        Log.e("url", url);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {
                if(progressDialog!=null)
                    progressDialog.dismiss();
                Log.e("reponse", jsonArray.toString());
                try {
                    notificationses.clear();
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject tmp_json = jsonArray.getJSONObject(i);
                        Notifications notifications=new Notifications(tmp_json);
                        notificationses.add(notifications);

                    }
                    notificationAdapter.notifyDataSetChanged();
                    setListViewHeightBasedOnItems(noti_list);
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
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }
    }
}
