package de.pathmaperuma.weightlog;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import de.pathmaperuma.weightlog.sql.DataManipulator;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

public class ShowTableActivity extends RoboActivity {
    @InjectView(R.id.dbContent) TextView content;
    private final DataManipulator dataManipulator = new DataManipulator(this);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dataview);

        Toast.makeText(this, "Loading Values...", Toast.LENGTH_SHORT).show();
        content.setText("DB Content: \n--------------------------\n");
        content.append("ID, Date, kg, % fat, % water, muscle, kcal, bone(kg)" + "\n");
        List<String[]> rows = dataManipulator.selectAllDescendingByDate();
        for (String[] row : rows) {
            String niceDate = WeightLogActivity.getNiceDateFromUnixTime(Long.parseLong(row[1]));
            content.append(String.format("%s | %s | %s | %s | %s | %s | %s | %s\n", row[0], niceDate, row[2], row[3], row[4], row[5], row[6], row[7]));
        }
    }
}