package com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.ui;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

import com.jacksonw765.clubtracker.R;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.adapters.ClubCustomAdapter;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.backend.Club;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.database.Database;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("ValidFragment")
public class ActivityClubs extends Fragment {

    private ListView clubsList;
    private ArrayList<Club> clubs;
    private FloatingActionButton fab;
    private Database database;
    private Toolbar toolbar;
    private TextView textViewAddClub;

    @SuppressLint("ValidFragment")
    public ActivityClubs(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View layoutView = inflater.inflate(R.layout.fragment_clubs, container, false);

        clubsList = layoutView.findViewById(R.id.clubs_listView);
        fab = layoutView.findViewById(R.id.clubs_fab);
        database = new Database(getContext());
        toolbar.setTitle("Clubs");

        clubs = database.getArrayList(Database.CLUB_KEY);
        if (clubs == null) {
            clubs = new ArrayList<>();
        }

//        clubs.clear();
//
//        for(int x=0; x < 5; ++x) {
//            Club club = new Club("Club " + x);
//            club.addDistance(x);
//            clubs.add(club);
//        }
//
//        for(Club club: clubs) {
//            for(int x=0; x < 5; ++x){
//                club.addDistance(x);
//            }
//        }

        final ClubCustomAdapter adapter = new ClubCustomAdapter(layoutView.getContext(), clubs);
        clubsList.setAdapter(adapter);

        clubsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toolbar.setTitle(clubs.get(i).getName());
                android.support.v4.app.FragmentManager fragmentManager2 = getFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                ActivityShot displayer = new ActivityShot(clubs, toolbar, i, getContext());
                fragmentTransaction2.addToBackStack("xyz");
                fragmentTransaction2.replace(getId(), displayer);
                fragmentTransaction2.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction2.commit();
            }
        });

        clubsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, final View deleteView, final int pos, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(deleteView.getContext(), android.R.style.Theme_DeviceDefault_Dialog_Alert);

                builder.setTitle("Delete Club")
                        .setMessage("Are you sure you want to delete this club?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                clubs.remove(pos);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(deleteView.getContext(), "Club deleted", Toast.LENGTH_SHORT).show();
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


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                final View dialogView = inflater.inflate(R.layout.dialog_addclub, null);
                dialog.setView(dialogView);
                dialog.setTitle("Add club");
                dialog.setCancelable(true);
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            textViewAddClub = dialogView.findViewById(R.id.text_clubName);
                            if (textViewAddClub != null) {
                                String clubName = String.valueOf(textViewAddClub.getText());
                                if (clubName.equals("")) {
                                    Snackbar.make(view, "Club cannot be blank", Snackbar.LENGTH_SHORT).show();
                                } else if (containsIllegalChar(clubName)) {
                                    Snackbar.make(view, "Club cannot contain illegal characters", Snackbar.LENGTH_SHORT).show();
                                } else {
                                    clubs.add(new Club(clubName));
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(view.getContext(), "Club added", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Snackbar.make(view, "Error adding club", Snackbar.LENGTH_LONG).show();
                        }
                    }
                });
                dialog.create();
                dialog.show();

            }
        });

        return layoutView;
    }

    private boolean containsIllegalChar(String clubName) {
        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
        Matcher matcher = pattern.matcher(clubName);
        return matcher.find();
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
