package com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jacksonw765.clubtracker.R;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.adapters.ShotCustomAdapter;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.backend.Club;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.database.Database;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ActivityShot extends Fragment {
    private ListView shotsList;
    private ArrayList<Integer> shotDistances;
    private ArrayList<Club> clubs;
    private FloatingActionButton fabStats;
    private Toolbar toolbar;
    private int clubIndex;
    private Database database;

    @SuppressLint("ValidFragment")
    public ActivityShot(ArrayList<Club> clubs, Toolbar toolbar, int index, Context context) {
        this.clubs = clubs;
        this.toolbar = toolbar;
        this.clubIndex = index;
        shotDistances = clubs.get(index).getClubDistances();
        database = new Database(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shots, container, false);

        toolbar.setTitle(clubs.get(clubIndex).getName());

        shotsList = view.findViewById(R.id.shots_listView);
        fabStats = view.findViewById(R.id.fab_viewshotstats);
        final ShotCustomAdapter shotAdapter = new ShotCustomAdapter(view.getContext(), shotDistances);
        shotsList.setAdapter(shotAdapter);

        fabStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shotDistances.isEmpty()) {
                    Snackbar.make(view, "No distances available", Snackbar.LENGTH_SHORT).show();
                } else {
                    android.support.v4.app.FragmentManager fragmentManager2 = getFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                    ActivityViewStats viewStats = new ActivityViewStats(shotDistances);
                    fragmentTransaction2.addToBackStack("abc");
                    fragmentTransaction2.replace(getId(), viewStats);
                    fragmentTransaction2.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    fragmentTransaction2.commit();
                }
            }
        });

        shotsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, final View deleteView, final int pos, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(deleteView.getContext(), android.R.style.Theme_DeviceDefault_Dialog_Alert);

                builder.setTitle("Delete Shot")
                        .setMessage("Are you sure you want to delete this club?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("delete");
                                shotDistances.remove(pos);
                                shotAdapter.notifyDataSetChanged();
                                database.saveArrayList(clubs, Database.CLUB_KEY);
                                Toast.makeText(deleteView.getContext(), "Shot deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                return true;
            }
        });



        return view;
    }

    @Override
    public void onPause() {
        if (clubs.size() != 0) {
            database.saveArrayList(clubs, Database.CLUB_KEY);
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (clubs.size() != 0) {
            database.saveArrayList(clubs, Database.CLUB_KEY);
        }
        super.onDestroy();
    }
}
