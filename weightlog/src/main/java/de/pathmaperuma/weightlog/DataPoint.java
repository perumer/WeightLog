package de.pathmaperuma.weightlog;

import org.joda.time.DateTime;

public class DataPoint {
    private final float weight;
    private final float fat;
    private final float water;
    private final float muscle;
    private final int kcal;
    private final float bone;
    private final DateTime timeTaken;

    public DataPoint(float weight, float fat, float water, float muscle, int kcal, float bone, DateTime date) {
        this.weight = weight;
        this.fat = fat;
        this.water = water;
        this.muscle = muscle;
        this.kcal = kcal;
        this.bone = bone;
        this.timeTaken = date;
    }

    public String getNiceDateFromUnixTime() {
        return timeTaken.toString("yyyy-MM-dd");
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
