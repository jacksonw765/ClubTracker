package com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.jacksonw765.clubtracker.R;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.backend.Helper;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ActivityViewStats extends Fragment {
    private ListView shotsList;
    private ArrayList<Integer> clubDistances;
    private Helper helper;

    private TextView textMin, textTop25, textBottom25, textAverage, textMedian, textMode, textMax;

    @SuppressLint("ValidFragment")
    public ActivityViewStats(ArrayList<Integer> clubDistances) {
        this.clubDistances = clubDistances;
        helper = new Helper(clubDistances);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewshotstats, container, false);

        textMin = view.findViewById(R.id.textMin);
        textMax = view.findViewById(R.id.textMax);
        textTop25 = view.findViewById(R.id.textTop25);
        textBottom25 = view.findViewById(R.id.textBottom25);
        textAverage = view.findViewById(R.id.textAverage);
        textMedian = view.findViewById(R.id.textMedian);
        textMode = view.findViewById(R.id.textMode);

        textBottom25.setText("" + helper.getPercentile(25) + "yds");
        textTop25.setText("" + helper.getPercentile(75) + "yds");
        textMin.setText("" + helper.getMinValue() + "yds");
        textAverage.setText("" + helper.getMean() + "yds");
        textMedian.setText("" + helper.getMedian() + "yds");
        textMode.setText("" + helper.getMode() + "yds");
        textMax.setText("" + helper.getMaxValue() + "yds");

        return view;
    }
}
