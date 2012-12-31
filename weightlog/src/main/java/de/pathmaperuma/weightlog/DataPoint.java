package de.pathmaperuma.weightlog;

import com.google.inject.internal.util.$Maps;
import de.pathmaperuma.weightlog.csv.ValueClassifier;
import de.pathmaperuma.weightlog.sql.IntegerCaptor;
import de.pathmaperuma.weightlog.values.FloatCaptor;
import de.pathmaperuma.weightlog.values.FloatValue;
import de.pathmaperuma.weightlog.values.IntegerValue;
import de.pathmaperuma.weightlog.values.Value;
import org.joda.time.DateTime;

import java.util.Map;
import java.util.Set;

public class DataPoint {

    public static final ValueClassifier ClassifierWeight = new ValueClassifier("weight");
    public static final ValueClassifier ClassifierFat = new ValueClassifier("fat");
    public static final ValueClassifier ClassifierWater = new ValueClassifier("water");
    public static final ValueClassifier ClassifierMuscle = new ValueClassifier("muscle");
    public static final ValueClassifier ClassifierKiloKalories = new ValueClassifier("kcal");
    public static final ValueClassifier ClassifierBone = new ValueClassifier("bone");

    public static DataPoint Daniel(float weight, float fat, float water, float muscle, int kcal, float bone, DateTime date) {
        DataPoint dataPoint = new DataPoint(date);
        dataPoint.measured(ClassifierWeight, new FloatValue(weight));
        dataPoint.measured(ClassifierFat, new FloatValue(fat));
        dataPoint.measured(ClassifierWater, new FloatValue(water));
        dataPoint.measured(ClassifierMuscle, new FloatValue(muscle));
        dataPoint.measured(ClassifierKiloKalories, new IntegerValue(kcal));
        dataPoint.measured(ClassifierBone, new FloatValue(bone));
        return dataPoint;
    }

    private final Map<ValueClassifier, Value> values = $Maps.newHashMap();
    private final DateTime timeTaken;

    public DataPoint(DateTime date) {
        this.timeTaken = date;
    }

    public String getNiceDateFromUnixTime() {
        return timeTaken.toString("yyyy-MM-dd");
    }

    public float getWeight() {
        return getFloat(valueFor(ClassifierWeight));
    }

    public float getPercentBodyFat() {
        return getFloat(valueFor(ClassifierFat));
    }

    public float getPercentBodyWater() {
        return getFloat(valueFor(ClassifierWater));
    }

    public float getPercentBodyMuscle() {
        return getFloat(valueFor(ClassifierMuscle));
    }

    public float getBoneWeight() {
        return getFloat(valueFor(ClassifierBone));
    }

    public int getKilokalorien() {
        IntegerCaptor captor = new IntegerCaptor();
        valueFor(ClassifierKiloKalories).welcome(captor);
        return captor.value();
    }

    public DateTime getTimeTaken() {
        return timeTaken;
    }

    public void measured(ValueClassifier identifier, Value value) {
        values.put(identifier, value);
    }

    public Set<ValueClassifier> classifiers() {
        return values.keySet();
    }

    public Value valueFor(ValueClassifier classifier) {
        return values.get(classifier);
    }

    private float getFloat(Value property) {
        FloatCaptor captor = new FloatCaptor();
        property.welcome(captor);
        return captor.value();
    }
}