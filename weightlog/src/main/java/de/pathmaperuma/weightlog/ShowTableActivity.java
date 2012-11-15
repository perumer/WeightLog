package de.pathmaperuma.weightlog;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import de.pathmaperuma.weightlog.sql.DataManipulator;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.util.List;

public class ShowTableActivity extends RoboActivity {
    @InjectView(R.id.dbContent) TextView content;
    private final DataManipulator dataManipulator = new DataManipulator(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dataview);

        Toast.makeText(this, "Loading Values...", Toast.LENGTH_SHORT).show();
        content.setText("DB Content: \n--------------------------\n");
        content.append("Date, kg, % fat, % water, muscle, kcal, bone(kg)" + "\n");
        List<DataPoint> dataPoints = dataManipulator.selectAllDataPointsDescendingByDate();

        StringBuilder builder = new StringBuilder();

        for (DataPoint dataPoint : dataPoints) {
            String date = dataPoint.getTimeTaken().toString("yyyy-MM-dd");
            float weight = dataPoint.getWeight();
            float percentBodyFat = dataPoint.getPercentBodyFat();
            float percentBodyWater = dataPoint.getPercentBodyWater();
            float percentBodyMuscle = dataPoint.getPercentBodyMuscle();
            int kilokalorien = dataPoint.getKilokalorien();
            float boneWeight = dataPoint.getBoneWeight();
            builder.append(String.format("%s | %s | %s | %s | %s | %s | %s\n", date, weight, percentBodyFat, percentBodyWater, percentBodyMuscle, kilokalorien, boneWeight));
        }

        content.append(builder);
    }
}