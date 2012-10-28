package de.pathmaperuma.weightlog;

import org.joda.time.DateTime;

import java.util.Date;

public class DataPoint {

    private float weight;
    private float fat;
    private float water;
    private float muscle;
    private int kcal;
    private float bone;
    private Date date;
    private DateTime timeTaken;


    public String getWeightString() {
        return Float.valueOf(weight).toString();
    }

    public String getFatString() {
        return Float.valueOf(fat).toString();
    }

    public String getWaterString() {
        return Float.valueOf(water).toString();
    }

    public String getMuscleString() {
        return Float.valueOf(muscle).toString();
    }

    public String getKcalString() {
        return Integer.valueOf(kcal).toString();
    }

    public DataPoint(String line) {
        String[] fields = line.split(";");
        date = new Date(Long.valueOf(fields[1]));
        setWeight(Float.valueOf(fields[2]));
        fat = Float.valueOf(fields[3]);
        water = Float.valueOf(fields[4]);
        muscle = Float.valueOf(fields[5]);
        kcal = Integer.valueOf(fields[6]);
        bone = Float.valueOf(fields[7]);
    }

    public DataPoint(float weight, float fat, float water, float muscle, int kcal, float bone, Date date) {
        this.weight = weight;
        this.fat = fat;
        this.water = water;
        this.muscle = muscle;
        this.kcal = kcal;
        this.bone = bone;
        this.date = date;
        this.timeTaken = new DateTime(date);
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getNiceDateFromUnixTime() {
        return (1900 + date.getYear()) + "-" + date.getMonth() + "-" + date.getDate();
    }

    public String getUnixTimeString() {
        return Long.valueOf(date.getTime()).toString();
    }

    public String getBoneString() {
        return Float.valueOf(bone).toString();
    }

    public float getWeight() {
        return weight;
    }

    public float getPercentBodyFat() {
        return fat;
    }

    public float getPercentBodyWater() {
        return water;
    }

    public float getPercentBodyMuscle() {
        return muscle;
    }

    public float getBoneWeight() {
        return bone;
    }

    public int getKilokalorien() {
        return kcal;
    }

    public DateTime getTimeTaken() {
        return timeTaken;
    }
}
