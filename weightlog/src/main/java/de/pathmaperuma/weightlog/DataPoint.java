package de.pathmaperuma.weightlog;

import de.pathmaperuma.weightlog.sql.IntegerCaptor;
import de.pathmaperuma.weightlog.values.FloatCaptor;
import de.pathmaperuma.weightlog.values.FloatValue;
import de.pathmaperuma.weightlog.values.IntegerValue;
import de.pathmaperuma.weightlog.values.Value;
import org.joda.time.DateTime;

public class DataPoint {
    private final Value weight;
    private final Value fat;
    private final Value water;
    private final Value muscle;
    private final Value kcal;
    private final Value bone;
    private final DateTime timeTaken;

    public DataPoint(float weight, float fat, float water, float muscle, int kcal, float bone, DateTime date) {
        this.weight = new FloatValue(weight);
        this.fat = new FloatValue(fat);
        this.water = new FloatValue(water);
        this.muscle = new FloatValue(muscle);
        this.kcal = new IntegerValue(kcal);
        this.bone = new FloatValue(bone);
        this.timeTaken = date;
    }

    public String getNiceDateFromUnixTime() {
        return timeTaken.toString("yyyy-MM-dd");
    }

    public float getWeight() {
        return getFloat(weight);
    }

    public float getPercentBodyFat() {
        return getFloat(fat);
    }

    private float getFloat(Value property) {
        FloatCaptor captor = new FloatCaptor();
        property.welcome(captor);
        return captor.value();
    }

    public float getPercentBodyWater() {
        return getFloat(water);
    }

    public float getPercentBodyMuscle() {
        return getFloat(muscle);
    }

    public float getBoneWeight() {
        return getFloat(bone);
    }

    public int getKilokalorien() {
        IntegerCaptor captor = new IntegerCaptor();
        kcal.welcome(captor);
        return captor.value();
    }

    public DateTime getTimeTaken() {
        return timeTaken;
    }
}