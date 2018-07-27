package com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jacksonw765.clubtracker.R;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.adapters.ShotCustomAdapter;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ShotDisplayer extends Fragment {
    private ListView shotsList;
    private ArrayList<Integer> clubDistances;

    @SuppressLint("ValidFragment")
    public ShotDisplayer(ArrayList<Integer> clubDistances) {
        this.clubDistances = clubDistances;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shots, container, false);

        shotsList = view.findViewById(R.id.shots_listView);
        ShotCustomAdapter shotAdapter = new ShotCustomAdapter(view.getContext(), clubDistances);
        shotsList.setAdapter(shotAdapter);

        return view;
    }
}
