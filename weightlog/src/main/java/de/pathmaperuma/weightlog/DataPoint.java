package de.pathmaperuma.weightlog;

import org.joda.time.DateTime;

import java.util.Date;

public class DataPoint {
    private final float weight;
    private final float fat;
    private final float water;
    private final float muscle;
    private final int kcal;
    private final float bone;
    private final DateTime timeTaken;

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
        this.weight = Float.valueOf(fields[2]);
        fat = Float.valueOf(fields[3]);
        water = Float.valueOf(fields[4]);
        muscle = Float.valueOf(fields[5]);
        kcal = Integer.valueOf(fields[6]);
        bone = Float.valueOf(fields[7]);
        this.timeTaken = new DateTime(new Date(Long.valueOf(fields[1])));
    }

    public DataPoint(float weight, float fat, float water, float muscle, int kcal, float bone, Date date) {
        this.weight = weight;
        this.fat = fat;
        this.water = water;
        this.muscle = muscle;
        this.kcal = kcal;
        this.bone = bone;
        this.timeTaken = new DateTime(date);
    }

    public String getNiceDateFromUnixTime() {
        return timeTaken.toString("yyyy-MM-dd");
    }

    public String getUnixTimeString() {
        long millis = timeTaken.toDate().getTime();
        return Long.valueOf(millis).toString();
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
