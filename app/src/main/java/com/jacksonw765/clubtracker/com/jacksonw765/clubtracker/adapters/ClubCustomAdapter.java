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


public class ClubCustomAdapter extends BaseAdapter
{
    ArrayList<Club> clubs;
    Context mContext;


    public ClubCustomAdapter(Context context, ArrayList<Club> studentArrayList) {
        this.mContext = context;
        this.clubs = studentArrayList;
    }

    @Override
    public int getCount() {
        return clubs.size();
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
        Club club = clubs.get(position);
        convertView = LayoutInflater.from(mContext).inflate(R.layout.clubs_listview_template,null);
        TextView clubName = (TextView) convertView.findViewById(R.id.listView_clubName);
        clubName.setText(club.getName());
        return convertView;
    }
}
