package app.my.bigc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NotificationAdapter extends BaseAdapter{
    Context context;
    ArrayList<Notifications> notificationses;
    private static LayoutInflater inflater=null;
    public NotificationAdapter(Activity mainActivity, ArrayList<Notifications> notificationses) {
        // TODO Auto-generated constructor stu
        context=mainActivity;
        this.notificationses=notificationses;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return notificationses.size();
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

        ImageView offerimage,act_img,status;
        TextView msg,title;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        if(convertView==null)
        rowView = inflater.inflate(R.layout.notification_item, parent,false);
        else
        rowView = convertView;
        holder.offerimage=(ImageView) rowView.findViewById(R.id.notification_image);
        holder.msg=(TextView) rowView.findViewById(R.id.notification_msg);
        holder.msg.setText(notificationses.get(position).message);
        holder.title=(TextView) rowView.findViewById(R.id.notification_title);
        holder.title.setText(notificationses.get(position).title);
        Picasso.with(context).load(notificationses.get(position).image).into(holder.offerimage);
        return rowView;
    }

}