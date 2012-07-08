package de.pathmaperuma.weightlog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    
    public void deleteButtonHandler(View view){
    	new AlertDialog.Builder(this).setTitle("Daten löschen") 
        .setMessage("Achtung: alle gespeicherten Daten werden gelöscht!")
        .setNeutralButton("Jaja, passt schon..", null)
        .show();
    	Toast.makeText(this, "Database deleted", Toast.LENGTH_SHORT).show();
    	this.dataManipulator.deleteAll();
    }
    
    public void graphButtonHandler(View view){
    	Toast.makeText(this, "generating chart...", Toast.LENGTH_SHORT).show();
    	
		DataManipulator dh = new DataManipulator(this);
		List<String[]> rows = dh.selectLast();
		

    	Graph graph = new Graph();
    	Intent intent = graph.getIntent(this, rows);
    	startActivity(intent);
    }
    
    
    public void saveButtonHandler(View view){
    	TextView lastSaved = (TextView) findViewById(R.id.lastSavedDate);
		
		EditText et = (EditText) findViewById(R.id.weightField);
		float weight = Float.parseFloat(et.getText().toString());

		et = (EditText) findViewById(R.id.fatField);
		float fat = Float.parseFloat(et.getText().toString());
		
		et = (EditText) findViewById(R.id.waterField);
		float water = Float.parseFloat(et.getText().toString());
		
		et = (EditText) findViewById(R.id.muscleField);
		float muscle = Float.parseFloat(et.getText().toString());
		
		et = (EditText) findViewById(R.id.kcalField);
		int kcal = Integer.parseInt(et.getText().toString());
		
		et = (EditText) findViewById(R.id.boneField);
		float bone = Float.parseFloat(et.getText().toString());
		
		Date now = new Date();
		
//		DateTime now = new DateTime();
		
		lastSaved.setText("Last saved: "+getNiceDateFromUnixTime(now.getTime()));
		saveReading(now, weight, fat, water, muscle, kcal, bone);
		return;
    }
    
    
    public void loadButtonHandler(View view){
    	

    	startActivity(new Intent(this, ShowTableActivity.class)  );
//    	Toast.makeText(this, "Loading Values...", Toast.LENGTH_SHORT).show();
//    	TextView content = (TextView) findViewById(R.id.dbContent);
//    	content.setText("DB Content: \n--------------------------\n");
//		content.append("ID, Date, kg, % fat, % water, muscle, kcal, bone(kg)"+  "\n");
//    	List<String[]> rows = this.dh.selectLast();
//    	for(String[] s : rows){
//    		String niceDate = getNiceDateFromUnixTime(Long.parseLong(s[1]));
//    		content.append(s[0]+ " | " + niceDate+ " | " + s[2]+" | "+s[3]+" | "+ s[4]+" | "+ s[5]+" | "+ s[6]+" | "+ s[7]+"\n");
//    	}
    }

    public void exportButtonHandler(View view){
    	DataManipulator dh = new DataManipulator(this);
    	List<String[]> rows = dh.selectAll();
    	String exportData = new String();
    	
    	for (String[] row : rows){
    		for (String field :row){
    			exportData += field+";";
    		}
    		exportData += "\n";
    	}
    	WeightDataIo io = new WeightDataIo();
    	writeExportData(exportData, io);
    }
    
    private void saveReading(Date now, float weight, float fat, float water, float muscle, int kcal, float bone) {
//    	System.out.println("Saving Data : "+new java.sql.Date(now.getTime())+", "+ weight);
    	this.dataManipulator.insertReading(new java.sql.Date(now.getTime()), weight, fat, water, muscle, kcal, bone);
    }
    
    /**
     * @param date (Unix Timestamp)
     * @return 2012-5-27
     */
    static String getNiceDateFromUnixTime(long date){
		Date readingDate = new Date(date);
		String niceDate = (1900+readingDate.getYear())+"-"+readingDate.getMonth()+"-"+readingDate.getDate();
    	return niceDate;
    }
    
    private void writeExportData(String data, WeightDataIo io){
    	try{
//    		File myFile = new File(this.getFilesDir()+"WeightLogExport.csv");
    		File myFile = new File(Environment.getExternalStorageDirectory(),"WeightLogExport.csv");
    		feedback.out("Path is"+myFile.getAbsolutePath());
    		myFile.createNewFile();
    		FileOutputStream fOut = new FileOutputStream(myFile);
    		OutputStreamWriter writer = new OutputStreamWriter(fOut);
    		writer.append(data);
    		writer.close();
    		fOut.close();
    		feedback.out("Data successfully written");
    	}
    	catch (Exception e){
    		Toast.makeText(getBaseContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
    		
    	}
    }
}
