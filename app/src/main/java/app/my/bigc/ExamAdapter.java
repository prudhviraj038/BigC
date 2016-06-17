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

public class ExamAdapter extends BaseAdapter{
    Context context;
    ArrayList<Exam> users;
    private static LayoutInflater inflater=null;
    public ExamAdapter(Activity mainActivity, ArrayList<Exam> users) {
        // TODO Auto-generated constructor stu
        context=mainActivity;
        this.users=users;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public boolean isEnabled (int position) {
        return true;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return users.size();
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

        TextView time,score;
        TextView date,title;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.review_result, parent,false);
        holder.time=(TextView) rowView.findViewById(R.id.review_time);
        holder.score=(TextView) rowView.findViewById(R.id.review_score_ll);
        holder.date=(TextView) rowView.findViewById(R.id.review_date);
        holder.title=(TextView) rowView.findViewById(R.id.review_exam);
        holder.title.setText(users.get(position).title);
        holder.score.setText(users.get(position).correctt+ "/" +users.get(position).total);
        if(!users.get(position).status.equals("Completed")){
            holder.score.setText("Write this exam now");
        }
        String CurrentString = users.get(position).started;
        String[] separated = CurrentString.split(" ");
        if(separated.length>0)
            holder.date.setText(separated[0]);
        else
            holder.date.setText("");

        String CurrentString2 = users.get(position).started;
        String CurrentString3 = users.get(position).ended;
        String[] separated2 = CurrentString2.split(" ");
        String[] separated3 = CurrentString3.split(" ");

        if(separated2.length>1 && separated3.length>1)
            holder.time.setText(separated2[1]+" - "+separated3[1] );
        else
            holder.time.setText("");


//        Picasso.with(context).load(users.get(position).image).into(holder.offerimage);
//        holder.date.setText(users.get(position).date);
//        holder.title.setText(users.get(position).title);

        return rowView;
    }

}