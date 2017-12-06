package com.example.ran0033.tetris;

import java.util.List;
import java.lang.reflect.Field;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<Entry>{

    Context context;
    int layoutResourceId;
    List<Entry> data = null;


    public ListAdapter(Context context, int layoutResourceId, List<Entry> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EntryHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new EntryHolder();
            holder.txtNick = (TextView)row.findViewById(R.id.txtStNick);
            holder.txtLevel = (TextView)row.findViewById(R.id.txtStLevel);
            holder.txtScore = (TextView)row.findViewById(R.id.txtStScore);
            holder.txtDate = (TextView)row.findViewById(R.id.txtStDate);

            row.setTag(holder);
        }
        else
        {
            holder = (EntryHolder)row.getTag();
        }

        Entry entry = data.get(position);
        holder.txtNick.setText(entry.nick);
        holder.txtLevel.setText(Integer.toString(entry.level));
        holder.txtScore.setText(Integer.toString(entry.score));
        holder.txtDate.setText(entry.date);

        return row;
    }

    static class EntryHolder
    {
        TextView txtNick;
        TextView txtLevel;
        TextView txtScore;
        TextView txtDate;
    }
}
