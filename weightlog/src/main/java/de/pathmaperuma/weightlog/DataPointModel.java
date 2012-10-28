package de.pathmaperuma.weightlog;

import org.joda.time.DateTime;

import java.util.Date;

public class DataPointModel {
    private float weight;
    private float percentBodyFat;
    private float percentBodyWater;
    private float percentBodyMuscle;
    private float boneWeight;
    private int kilokalorien;
    private DateTime timeTaken;

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setPercentBodyFat(float percentBodyFat) {
        this.percentBodyFat = percentBodyFat;
    }

    public void setPercentBodyWater(float percentBodyWater) {
        this.percentBodyWater = percentBodyWater;
    }

    public void setPercentBodyMuscle(float percentBodyMuscle) {
        this.percentBodyMuscle = percentBodyMuscle;
    }

    public void setBoneWeight(float boneWeight) {
        this.boneWeight = boneWeight;
    }

    public void setKilokalorien(int kilokalorien) {
        this.kilokalorien = kilokalorien;
    }

    public void setTimeTaken(DateTime timeTaken) {
        this.timeTaken = timeTaken;
    }

    public DataPoint createDataPoint() {
        return new DataPoint(weight, percentBodyFat, percentBodyWater, percentBodyMuscle, kilokalorien, boneWeight, timeTaken==null?new Date():timeTaken.toDate());
    }
}