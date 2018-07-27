package com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jacksonw765.clubtracker.R;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.backend.Club;

import java.util.ArrayList;


public class ShotCustomAdapter extends BaseAdapter
{
    ArrayList<Integer> distances;
    Context mContext;

    public ShotCustomAdapter(Context context, ArrayList<Integer> distances) {
        this.mContext = context;
        this.distances = distances;
    }

    @Override
    public int getCount() {
        return distances.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int distance = distances.get(position);
        int positionName = position+1;
        convertView = LayoutInflater.from(mContext).inflate(R.layout.shots_listview_template,null);
        TextView shotNumber = (TextView) convertView.findViewById(R.id.listView_ShotNumber);
        TextView shotDistance = (TextView) convertView.findViewById(R.id.listView_shotData);

        shotNumber.setText("Shot " + positionName);
        shotDistance.setText(distance + "yds");
        return convertView;
    }
}
