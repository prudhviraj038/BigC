package app.my.bigc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class OfferAdapter extends BaseAdapter{
    Context context;
    ArrayList<Offers> users;
    private static LayoutInflater inflater=null;
    public OfferAdapter(Activity mainActivity, ArrayList<Offers> users) {
        // TODO Auto-generated constructor stu
        context=mainActivity;
        this.users=users;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 10;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {

        ImageView offerimage,act_img;
        TextView date,title;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        if(convertView==null)
        rowView = inflater.inflate(R.layout.offer_item, parent,false);
        else
        rowView = convertView;
        holder.offerimage=(ImageView) rowView.findViewById(R.id.offer_image);
        holder.act_img=(ImageView) rowView.findViewById(R.id.act_img);
        holder.date=(TextView) rowView.findViewById(R.id.offer_date);
        holder.title=(TextView) rowView.findViewById(R.id.offer_title);
//        Picasso.with(context).load(users.get(position).image).into(holder.offerimage);
//        holder.date.setText(users.get(position).date);
//        holder.title.setText(users.get(position).title);

        return rowView;
    }

}