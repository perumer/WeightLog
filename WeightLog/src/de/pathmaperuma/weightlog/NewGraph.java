package de.pathmaperuma.weightlog;

import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NewGraph extends Activity {

	
	public Intent getIntent(Context context){
		
		int[] x ={1,2,3,4,5};
		int[] y = {12,34,64,7,23};
		
		

		DataManipulator dh = new DataManipulator(this);

		
		Toast.makeText(context, "generating chart...", Toast.LENGTH_SHORT).show();
//		TextView content = (TextView) findViewById(R.id.dbContent);
		
		List<String[]> rows = dh.selectLast();

		int setSize = rows.size();
		
		int[] date = new int[setSize];
		double[] weights = new double[setSize];
		double[] fats = new double[setSize];
		

		int count = 0;
		for (String[] s : rows) {
			date[count] = (int) Long.parseLong(s[1]);
			weights[count] = Double.parseDouble(s[2]);
			fats[count] = Double.parseDouble(s[3]);
			count++;
		}
		
		TimeSeries weightSeries = new TimeSeries("weight (kg)");
		TimeSeries fatSeries = new TimeSeries("fat (%)");
		
		for (int i=0; i<setSize; i++){
			weightSeries.add(date[i], weights[i]);
			fatSeries.add(date[i], fats[i]);
		}
		
		TimeSeries series1 = new TimeSeries("weight");
		for (int i=0; i<x.length; i++){
			series1.add(x[i], y[i]);
		}
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
//		dataset.addSeries(series1);
		dataset.addSeries(weightSeries);
		dataset.addSeries(fatSeries);
		
		
		XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		mrenderer.addSeriesRenderer(renderer);
		
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mrenderer, "LineGraphTitle");
		
		
		return intent;
		
	}

}
