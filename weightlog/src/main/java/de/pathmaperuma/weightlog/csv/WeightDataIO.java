package de.pathmaperuma.weightlog.csv;

import android.os.Environment;
import de.pathmaperuma.weightlog.DataPoint;
import de.pathmaperuma.weightlog.UserFeedback;
import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeightDataIO {

    private static final String FILENAME = "WeightLogExport.csv";

    public void writeExportData(String data, UserFeedback feedback) {
        try {
            // TODO: eigenes Verzeichnis erstellen
            File myFile = new File(Environment.getExternalStorageDirectory(), FILENAME);
            feedback.out("Path is" + myFile.getAbsolutePath());
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter writer = new OutputStreamWriter(fOut);
            writer.append(data);
            writer.close();
            fOut.close();
            feedback.out("Data successfully written");
        } catch (Exception e) {
            feedback.outLong(e.getMessage());

        }
    }

    public List<DataPoint> readImportData(UserFeedback feedback) {
        ArrayList<DataPoint> list = new ArrayList<DataPoint>();
        try {
            File myFile = new File(Environment.getExternalStorageDirectory(), FILENAME);

            FileInputStream fIn = new FileInputStream(myFile);
            InputStreamReader reader = new InputStreamReader(fIn);
            BufferedReader bReader = new BufferedReader(reader);
            String line;
            while ((line = bReader.readLine()) != null) {
                System.out.println(line);
                DataPoint dp = deSerializeDataPointFromCsv(line);
                list.add(dp);
            }
            bReader.close();
            reader.close();
        } catch (Exception e) {
            feedback.outLong(e.getMessage());
        }

        return list;
    }

    public void writeExportData(List<DataPoint> rows, UserFeedback feedback) {
        StringBuilder builder = new StringBuilder();
        for (DataPoint row : rows) {
            serializeDataPointAsCsv(builder, row);
        }
        writeExportData(builder.toString(), feedback);
    }

    private static void serializeDataPointAsCsv(StringBuilder builder, DataPoint row) {
        builder.append("[ignored id]").append(";");
        builder.append(row.getTimeTaken().getMillis()).append(";");
        builder.append(row.getWeight()).append(";");
        builder.append(row.getPercentBodyFat()).append(";");
        builder.append(row.getPercentBodyWater()).append(";");
        builder.append(row.getPercentBodyMuscle()).append(";");
        builder.append(row.getKilokalorien()).append(";");
        builder.append(row.getBoneWeight()).append(";");
        builder.append("\n");
    }

    private static DataPoint deSerializeDataPointFromCsv(String line) {
        String[] fields = line.split(";");
        DateTime timeTaken = new DateTime(new Date(Long.valueOf(fields[1])));
        float weight = Float.valueOf(fields[2]);
        float fat = Float.valueOf(fields[3]);
        float water = Float.valueOf(fields[4]);
        float muscle = Float.valueOf(fields[5]);
        int kcal = Integer.valueOf(fields[6]);
        float bone = Float.valueOf(fields[7]);
        return DataPoint.Daniel(weight, fat, water, muscle, kcal, bone, timeTaken);
    }
}
