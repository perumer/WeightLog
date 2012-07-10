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

public class WeightLogActivity extends Activity {
    UserFeedback feedback;
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
    	if( item.getItemId() == R.id.showgraph){
    		this.showGraph();
    		return false;
    	}if( item.getItemId() == R.id.showdatabasedump){
    		showDatabase();
    		return false;
    	}if(item.getItemId() == R.id.importFromFile){
    		handleImport();
    		return false;
    	}if(item.getItemId() == R.id.exportToFile){
    		handleExport();
    		return false;
    	}if(item.getItemId() == R.id.delete){
    		delete();
    		return false;
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
    
    private void delete(){
    	new AlertDialog.Builder(this).setTitle("Daten löschen") 
        .setMessage("Achtung: alle gespeicherten Daten werden gelöscht!")
        .setNeutralButton("Jaja, passt schon..", null)
        .show();
    	Toast.makeText(this, "Database deleted", Toast.LENGTH_SHORT).show();
    	this.dataManipulator.deleteAll();
    }
    
    private void showGraph(){
    	Toast.makeText(this, "generating chart...", Toast.LENGTH_SHORT).show();
    	
		DataManipulator dh = new DataManipulator(this);
		List<String[]> rows = dh.selectLast();
		

    	Graph graph = new Graph();
    	Intent intent = graph.getIntent(this, rows);
    	startActivity(intent);
    }
    
    
    public void saveButtonHandler(View view){
    	TextView lastSaved = (TextView) findViewById(R.id.lastSavedDate);
    	
    	DataPoint dp = new DataPoint();

    	EditText et = (EditText) findViewById(R.id.weightField);
		dp.setWeight(et);

		et = (EditText) findViewById(R.id.fatField);
		dp.setFat(et);
		
		
		et = (EditText) findViewById(R.id.waterField);
		dp.setWater(et);
		
		et = (EditText) findViewById(R.id.muscleField);
		dp.setMuscle(et);
		
		et = (EditText) findViewById(R.id.kcalField);
		dp.setKcal(et);
		
		et = (EditText) findViewById(R.id.boneField);
		dp.setBone(et);
		
		Date now = new Date();
		dp.setDate(now.getTime());
		
		
		lastSaved.setText("Last saved: "+dp.getNiceDateFromUnixTime());
		
		dataManipulator.insertReading(dp);
		return;
    }
    
    
    private void showDatabase(){
    	startActivity(new Intent(this, ShowTableActivity.class)  );
    }

    
    private void handleImport(){
    	WeightDataIO io = new WeightDataIO();
    	List<DataPoint> dps = io.readImportData(feedback);
    	for (DataPoint dp : dps){
    		dataManipulator.insertReading(dp);
    	}
    }
    
    private void handleExport(){
    	DataManipulator dh = new DataManipulator(this);
    	List<String[]> rows = dh.selectAll();
    	String exportData = new String();
    	
    	for (String[] row : rows){
    		for (String field :row){
    			exportData += field+";";
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
    static String getNiceDateFromUnixTime(long date){
		Date readingDate = new Date(date);
		String niceDate = (1900+readingDate.getYear())+"-"+readingDate.getMonth()+"-"+readingDate.getDate();
    	return niceDate;
    }
}
