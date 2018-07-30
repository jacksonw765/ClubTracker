package com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.backend.Club;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Database {

    //KEYS
    public final static String CLUB_KEY = "SHOT_KEY";
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    private Context context;
    private Gson gson;

    public Database(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        gson = new Gson();
    }

    public void saveArrayList(ArrayList<Club> list, String key) {
        editor = prefs.edit();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public ArrayList<Club> getArrayList(String key) {
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Club>>() {
        }.getType();
        return gson.fromJson(json, type);
    }


}
