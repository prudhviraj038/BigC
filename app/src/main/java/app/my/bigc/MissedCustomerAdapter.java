package app.my.bigc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MissedCustomerAdapter extends BaseAdapter{
    Context context;
    ArrayList<Missed_Customer> missedcustomers;
    private static LayoutInflater inflater=null;
    public MissedCustomerAdapter(Activity mainActivity, ArrayList<Missed_Customer> missedcustomers) {
        // TODO Auto-generated constructor stu
        context=mainActivity;
        this.missedcustomers=missedcustomers;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return missedcustomers.size();
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

        TextView name,number,brand,model,status;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        if(convertView==null)
        rowView = inflater.inflate(R.layout.missed_customer_list, parent,false);
        else
        rowView = convertView;
        holder.name=(TextView) rowView.findViewById(R.id.missed_name);
        holder.number=(TextView) rowView.findViewById(R.id.missed_number);
        holder.brand=(TextView) rowView.findViewById(R.id.missed_brand);
        holder.model=(TextView) rowView.findViewById(R.id.missed_model);
        holder.status=(TextView) rowView.findViewById(R.id.missed_open);
        holder.name.setText("Name : "+missedcustomers.get(position).name);
        holder.number.setText("Number : "+missedcustomers.get(position).phone);
        holder.brand.setText("Brand : "+missedcustomers.get(position).brand);
        holder.model.setText("Model : "+missedcustomers.get(position).model);
        holder.status.setText(missedcustomers.get(position).status);
//        Picasso.with(context).load(users.get(position).image).into(holder.offerimage);
//        holder.date.setText(users.get(position).date);
//        holder.title.setText(users.get(position).title);

        return rowView;
    }

}