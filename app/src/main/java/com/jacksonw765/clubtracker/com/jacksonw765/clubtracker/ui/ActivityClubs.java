package com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jacksonw765.clubtracker.R;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.backend.Club;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.backend.ClubCustomAdapter;

import java.util.ArrayList;

public class ActivityClubs extends Fragment {

    private ListView clubsList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);

        clubsList = view.findViewById(R.id.clubs_listView);

        ArrayList<Club> clubs = new ArrayList<>();
        for(int x=0; x < 20; ++x) {
            Club club = new Club("Club " + x);
            club.addDistance(x);
            clubs.add(club);
        }
        ClubCustomAdapter adapter = new ClubCustomAdapter(view.getContext(), clubs);
        clubsList.setAdapter(adapter);

        return view;
    }
}
