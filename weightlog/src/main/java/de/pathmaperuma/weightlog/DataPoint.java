package de.pathmaperuma.weightlog;

import com.google.inject.internal.util.$Maps;
import de.pathmaperuma.weightlog.configuration.Daniel;
import de.pathmaperuma.weightlog.csv.ValueClassifier;
import de.pathmaperuma.weightlog.sql.IntegerCaptor;
import de.pathmaperuma.weightlog.values.FloatCaptor;
import de.pathmaperuma.weightlog.values.Value;
import org.joda.time.DateTime;

import java.util.Map;
import java.util.Set;

public class DataPoint {

    private final Map<ValueClassifier, Value> values = $Maps.newHashMap();
    private final DateTime timeTaken;

    public DataPoint(DateTime date) {
        this.timeTaken = date;
    }

    public String getNiceDateFromUnixTime() {
        return timeTaken.toString("yyyy-MM-dd");
    }

    public float getWeight() {
        return getFloat(valueFor(Daniel.ClassifierWeight));
    }

    public float getPercentBodyFat() {
        return getFloat(valueFor(Daniel.ClassifierFat));
    }

    public float getPercentBodyWater() {
        return getFloat(valueFor(Daniel.ClassifierWater));
    }

    public float getPercentBodyMuscle() {
        return getFloat(valueFor(Daniel.ClassifierMuscle));
    }

    public float getBoneWeight() {
        return getFloat(valueFor(Daniel.ClassifierBone));
    }

    public int getKilokalorien() {
        IntegerCaptor captor = new IntegerCaptor();
        valueFor(Daniel.ClassifierKiloKalories).welcome(captor);
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