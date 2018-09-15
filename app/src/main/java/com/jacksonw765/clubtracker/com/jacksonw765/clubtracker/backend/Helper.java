package com.jacksonw765.clubtracker.com.jacksonw765.clubtracker.backend;

import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    private final ArrayList<Integer> shotArray;

    public Helper(ArrayList<Integer> shots) {
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

    //this is a slow method!
    public double getPercentile(int percentile) {
        Collections.sort(shotArray);
        double[] doubleArray = new double[shotArray.size()];
        for (int i = 0; i < doubleArray.length; i++) {
            doubleArray[i] = shotArray.get(i).doubleValue();
        }
        return StatUtils.percentile(doubleArray, percentile);
    }

    public static boolean containsIllegalChar(String clubName) {
        boolean retVal = false;
        try {
            Matcher matcher = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]").matcher(clubName);
            retVal = matcher.find();
            return retVal;
        } catch (Exception e) {
            return retVal;
        }
    }

    public static boolean isNumeric(String shotData)
    {
        try
        {
            double d = Double.parseDouble(shotData);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
