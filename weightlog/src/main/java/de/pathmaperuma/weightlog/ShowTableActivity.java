package de.pathmaperuma.weightlog;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import de.pathmaperuma.weightlog.sql.DataManipulator;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

import java.util.Date;
import java.util.List;

public class ShowTableActivity extends RoboActivity {
    @InjectView(R.id.dbContent) TextView content;
    private final DataManipulator dataManipulator = new DataManipulator(this);

    private static String getNiceDateFromUnixTime(long date) {
        Date readingDate = new Date(date);
        return (1900 + readingDate.getYear()) + "-" + readingDate.getMonth() + "-" + readingDate.getDate();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dataview);

        Toast.makeText(this, "Loading Values...", Toast.LENGTH_SHORT).show();
        content.setText("DB Content: \n--------------------------\n");
        content.append("Date, kg, % fat, % water, muscle, kcal, bone(kg)" + "\n");
        List<String[]> rows = dataManipulator.selectAllDescendingByDate();


        for (String[] row : rows) {
            String niceDate = getNiceDateFromUnixTime(Long.parseLong(row[1]));
            content.append(String.format("%s | %s | %s | %s | %s | %s | %s\n", niceDate, row[2], row[3], row[4], row[5], row[6], row[7]));
        }
    }
}