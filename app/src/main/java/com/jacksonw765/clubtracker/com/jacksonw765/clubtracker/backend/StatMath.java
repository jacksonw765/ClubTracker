package com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.backend;

import java.util.ArrayList;

public class StatMath {
    private final ArrayList<Integer> shotArray;

    public StatMath(ArrayList<Integer> shots) {
        this.shotArray = shots;
    }

    public int getMode() {
        int maxValue = 0;
        int maxCount = 0;

        for (int i = 0; i < shotArray.size(); ++i) {
            int count = 0;
            for (int j = 0; j < shotArray.size(); ++j) {
                if (shotArray.get(j) == shotArray.get(i)) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = shotArray.get(i);
            }
        }
        return maxValue;
    }

    public int getMedian() {
        int middle = shotArray.size() / 2;
        if (shotArray.size() % 2 == 1) {
            return shotArray.get(middle);
        } else {
            return (shotArray.get(middle - 1) + shotArray.get(middle)) / 2;
        }
    }

    public int getMaxValue() {
        int maxValue = shotArray.get(0);
        for (int i = 1; i < shotArray.size(); i++) {
            if (shotArray.get(i) > maxValue) {
                maxValue = shotArray.get(i);
            }
        }
        return maxValue;
    }

    public int getMinValue() {
        int minValue = shotArray.get(0);
        for (int i = 1; i < shotArray.size(); i++) {
            if (shotArray.get(i) < minValue) {
                minValue = shotArray.get(i);
            }
        }
        return minValue;
    }

    public double getMean() {
        double sum = 0;
        for (int i = 0; i < shotArray.size(); i++) {
            sum += shotArray.get(i);
        }
        return sum / shotArray.size();
    }
}
