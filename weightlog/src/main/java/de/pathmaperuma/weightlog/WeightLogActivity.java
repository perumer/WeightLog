package de.pathmaperuma.weightlog;

import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.joda.time.DateTime;

public class WeightLogActivity extends Activity {
    private UserFeedback feedback;
    private DataManipulator dataManipulator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedback = new UserFeedback(getApplicationContext());
        setContentView(R.layout.main);
        this.dataManipulator = new DataManipulator(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_data_input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.showgraph) {
            this.showGraph();
            return false;
        }
        if (item.getItemId() == R.id.showdatabasedump) {
            showDatabase();
            return false;
        }
        if (item.getItemId() == R.id.importFromFile) {
            handleImport();
            return false;
        }
        if (item.getItemId() == R.id.exportToFile) {
            handleExport();
            return false;
        }
        if (item.getItemId() == R.id.delete) {
            delete();
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

    private void delete() {
        new AlertDialog.Builder(this).setTitle("Daten löschen")
                .setMessage("Achtung: alle gespeicherten Daten werden gelöscht!")
                .setNeutralButton("Jaja, passt schon..", null)
                .show();
        Toast.makeText(this, "Database deleted", Toast.LENGTH_SHORT).show();
        this.dataManipulator.deleteAll();
    }

    private void showGraph() {
        Toast.makeText(this, "generating chart...", Toast.LENGTH_SHORT).show();
        List<String[]> rows = dataManipulator.selectLast();
        Graph graph = new Graph();
        Intent intent = graph.getIntent(this, rows);
        startActivity(intent);
    }


    public void saveButtonHandler(View view) {
        TextView lastSaved = (TextView) findViewById(R.id.lastSavedDate);

        DataPoint dataPoint = new DataPoint();

        EditText et = (EditText) findViewById(R.id.weightField);

        dataPoint.setWeight(Float.parseFloat(et.getText().toString()));

        et = (EditText) findViewById(R.id.fatField);
        dataPoint.setBodyFatInPercent(Float.parseFloat(et.getText().toString()));

        et = (EditText) findViewById(R.id.waterField);
        dataPoint.setBodyWaterInPercent(Float.parseFloat(et.getText().toString()));

        et = (EditText) findViewById(R.id.muscleField);
        dataPoint.setBoyMuscleInPercent(Float.parseFloat(et.getText().toString()));

        et = (EditText) findViewById(R.id.kcalField);
        dataPoint.setKilokalorien(Integer.parseInt(et.getText().toString()));

        et = (EditText) findViewById(R.id.boneField);
        dataPoint.setBoneWeightInKg(Float.parseFloat(et.getText().toString()));

        dataPoint.setDate(new DateTime());

        lastSaved.setText("Last saved: " + dataPoint.getNiceDateFromUnixTime());

        dataManipulator.insertReading(dataPoint);
    }


    private void showDatabase() {
        startActivity(new Intent(this, ShowTableActivity.class));
    }


    private void handleImport() {
        WeightDataIO io = new WeightDataIO();
        feedback.out("reading import file..");
        List<DataPoint> dps = io.readImportData(feedback);
        for (DataPoint dp : dps) {
            dataManipulator.insertReading(dp);
        }
        feedback.out(dps.size() + " datasets imported");
    }

    private void handleExport() {
        List<String[]> rows = dataManipulator.selectAll();
        String exportData = "";

        for (String[] row : rows) {
            for (String field : row) {
                exportData += field + ";";
            }
            exportData += "\n";
        }
        WeightDataIO io = new WeightDataIO();
        io.writeExportData(exportData, feedback);
    }

    /**
     * @param date (Unix Timestamp)
     * @return 2012-5-27
     */
    static String getNiceDateFromUnixTime(long date) {
        Date readingDate = new Date(date);
        return (1900 + readingDate.getYear()) + "-" + readingDate.getMonth() + "-" + readingDate.getDate();
    }
}
