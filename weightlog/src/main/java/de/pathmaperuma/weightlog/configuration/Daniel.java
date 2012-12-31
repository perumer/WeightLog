package de.pathmaperuma.weightlog.configuration;

import de.pathmaperuma.weightlog.DataPoint;
import de.pathmaperuma.weightlog.csv.ValueClassifier;
import de.pathmaperuma.weightlog.values.FloatValue;
import de.pathmaperuma.weightlog.values.IntegerValue;
import org.joda.time.DateTime;

public class Daniel {

    public static final ValueClassifier ClassifierWeight = new ValueClassifier("weight");
    public static final ValueClassifier ClassifierFat = new ValueClassifier("fat");
    public static final ValueClassifier ClassifierWater = new ValueClassifier("water");
    public static final ValueClassifier ClassifierMuscle = new ValueClassifier("muscle");
    public static final ValueClassifier ClassifierKiloKalories = new ValueClassifier("kcal");
    public static final ValueClassifier ClassifierBone = new ValueClassifier("bone");

    public static DataPoint DataPoint(float weight, float fat, float water, float muscle, int kcal, float bone, DateTime date) {
        DataPoint dataPoint = new DataPoint(date);
        dataPoint.measured(ClassifierWeight, new FloatValue(weight));
        dataPoint.measured(ClassifierFat, new FloatValue(fat));
        dataPoint.measured(ClassifierWater, new FloatValue(water));
        dataPoint.measured(ClassifierMuscle, new FloatValue(muscle));
        dataPoint.measured(ClassifierKiloKalories, new IntegerValue(kcal));
        dataPoint.measured(ClassifierBone, new FloatValue(bone));
        return dataPoint;
    }
}
