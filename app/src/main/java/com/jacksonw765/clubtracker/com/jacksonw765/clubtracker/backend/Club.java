package com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.backend;

import java.util.ArrayList;

public class Club {
    private ArrayList<Integer> distances;
    private String name;
    public Club(String name) {
        this.name = name;
        distances = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void deleteDistance(int index) {
        if(index >= 0)
            distances.remove(index);
    }

    public void addDistance(int yds) {
        if(yds > 0)
            distances.add(yds);
    }

    public ArrayList<Integer> getClubDistances() {
        return distances;
    }
}
