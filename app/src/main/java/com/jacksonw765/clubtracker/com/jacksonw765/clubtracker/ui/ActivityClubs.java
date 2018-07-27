package com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.ui;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jacksonw765.clubtracker.R;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.adapters.ShotCustomAdapter;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.backend.Club;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.adapters.ClubCustomAdapter;

import java.util.ArrayList;

public class ActivityClubs extends Fragment {

    private ListView clubsList;
    private ArrayList<Club> clubs;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);

        clubsList = view.findViewById(R.id.clubs_listView);

        clubs = new ArrayList<>();
        for(int x=0; x < 20; ++x) {
            Club club = new Club("Club " + x);
            club.addDistance(x);
            clubs.add(club);
        }

        for(Club club: clubs) {
            for(int x=0; x < 20; ++x){
                club.addDistance(x);
            }
        }
        ClubCustomAdapter adapter = new ClubCustomAdapter(view.getContext(), clubs);
        clubsList.setAdapter(adapter);

        clubsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                android.support.v4.app.FragmentManager fragmentManager2 = getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                ShotDisplayer displayer = new ShotDisplayer(clubs.get(i).getClubDistances());
                fragmentTransaction2.addToBackStack("xyz");
                fragmentTransaction2.hide(ActivityClubs.this);
                fragmentTransaction2.add(R.id.fragment_container, displayer);
                fragmentTransaction2.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction2.commit();
            }
        });



        return view;
    }
}
