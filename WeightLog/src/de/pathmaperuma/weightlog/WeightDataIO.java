package de.pathmaperuma.weightlog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

public class WeightDataIO {

	private static final String FILENAME = "WeightLogExport.csv";

	public void writeExportData(String data, UserFeedback feedback) {
		try {
			// TODO: eigenes Verzeichnis erstellen
			File myFile = new File(Environment.getExternalStorageDirectory(),
					FILENAME);
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

	public List<DataPoint> readImportData(UserFeedback feedback){
		ArrayList<DataPoint> list = new ArrayList<DataPoint>();
		try{
			File myFile = new File(Environment.getExternalStorageDirectory(),FILENAME);
			
			FileInputStream fIn = new FileInputStream(myFile);
			InputStreamReader reader = new InputStreamReader(fIn);
			BufferedReader bReader = new BufferedReader(reader);
			String line;
			while( (line = bReader.readLine())!= null){
				System.out.println(line);
				DataPoint dp = new DataPoint(line);
				list.add(dp);
			}
			bReader.close();
			reader.close();
		}
		catch(Exception e){
			feedback.outLong(e.getMessage());
		}
				
		return list;
	}
}
