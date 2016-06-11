package app.my.bigc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by sriven on 6/7/2016.
 */
public class Offer_Screen_Activity extends Activity {
    OfferAdapter offerAdapter;
    ArrayList<Offers> offers;
    ListView offer_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers_screen);
        offers=new ArrayList<>();
        offerAdapter=new OfferAdapter(this,offers);
        offer_list=(ListView)findViewById(R.id.offer_list);
        offer_list.setAdapter(offerAdapter);

    }
}
