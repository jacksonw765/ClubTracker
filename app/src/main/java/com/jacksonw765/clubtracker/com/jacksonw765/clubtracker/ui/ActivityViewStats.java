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
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.backend.StatMath;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ActivityViewStats extends Fragment {
    private ListView shotsList;
    private ArrayList<Integer> clubDistances;
    private StatMath statMath;

    private TextView textMin, textTop25, textBottom25, textAverage, textMedian, textMode, textMax;

    @SuppressLint("ValidFragment")
    public ActivityViewStats(ArrayList<Integer> clubDistances) {
        this.clubDistances = clubDistances;
        statMath = new StatMath(clubDistances);
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

        textMin.setText("" + statMath.getMinValue() + "yds");
        textAverage.setText("" + statMath.getMean() + "yds");
        textMedian.setText("" + statMath.getMedian() + "yds");
        textMode.setText("" + statMath.getMode() + "yds");
        textMax.setText("" + statMath.getMaxValue() + "yds");


        return view;
    }
}
