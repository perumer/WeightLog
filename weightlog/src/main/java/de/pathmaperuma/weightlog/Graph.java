package de.pathmaperuma.weightlog;

import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class Graph {

	public Intent getIntent(Context context, List<String[]> rows) {

		int setSize = rows.size();
		
		long[] date = new long[setSize];
		double[] weights = new double[setSize];
		double[] fats = new double[setSize];
		double[] water = new double[setSize];
		double[] muscle = new double[setSize];
		double[] kcal = new double[setSize];
		double[] bone = new double[setSize];

		int count = 0;
		for (String[] s : rows) {
			date[count] = Long.parseLong(s[1]);
			weights[count] = Double.parseDouble(s[2]);
			fats[count] = Double.parseDouble(s[3]);
			water[count] = Double.parseDouble(s[4]);
			muscle[count] = Double.parseDouble(s[5]);
			kcal[count] = Double.parseDouble(s[6])/100;
			bone[count] = Double.parseDouble(s[7]);
			count++;
		}

		TimeSeries weightSeries = new TimeSeries("weight (kg)");
		TimeSeries fatSeries = new TimeSeries("fat (%)");
		TimeSeries waterSeries = new TimeSeries("water (%)");
		TimeSeries muscleSeries = new TimeSeries("muscle (%)");
		TimeSeries kcalSeries = new TimeSeries("kcal/100");
		TimeSeries boneSeries = new TimeSeries("bone (kg)");

		
		for (int i = 0; i < setSize; i++) {
			kcalSeries.add(new Date(date[i]), kcal[i]);
			muscleSeries.add(new Date(date[i]), muscle[i]);
			fatSeries.add(new Date(date[i]), fats[i]);
			waterSeries.add(new Date(date[i]), water[i]);
			weightSeries.add(new Date(date[i]), weights[i]);
			boneSeries.add(new Date(date[i]), bone[i]);
		}
		

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		// dataset.addSeries(series1);
		dataset.addSeries(kcalSeries);
		dataset.addSeries(fatSeries);
		dataset.addSeries(waterSeries);
		dataset.addSeries(muscleSeries);
		dataset.addSeries(weightSeries);
		dataset.addSeries(boneSeries);

		XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
		mrenderer.setApplyBackgroundColor(true);
		mrenderer.setBackgroundColor(Color.argb(128, 49, 46, 39));
		mrenderer.setMarginsColor(Color.argb(255, 27,26,24));
//		mrenderer.setAntialiasing(true);
		mrenderer.setGridColor(Color.argb(128, 150, 150, 150));
		mrenderer.setShowGrid(true);
		mrenderer.setLabelsTextSize(10);
		mrenderer.setXLabelsAngle(30);
//		mrenderer.setXTitle("Date");
//		mrenderer.setYAxisAlign(Align.LEFT, 0);
//		mrenderer.setZoomButtonsVisible(true);

		
		XYSeriesRenderer renderer1 = new XYSeriesRenderer();
		renderer1.setColor(Color.argb(255, 222,55,41));
		renderer1.setFillBelowLine(true);
		renderer1.setFillBelowLineColor(Color.argb(64, 111,27,21));
		renderer1.setGradientEnabled(true);
		renderer1.setLineWidth(1);
		mrenderer.addSeriesRenderer(renderer1);
		

		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setColor(Color.argb(255, 244,278,73));
		renderer2.setFillBelowLine(true);
		renderer2.setFillBelowLineColor(Color.argb(64, 122,89,36));
		renderer2.setGradientEnabled(true);
		renderer2.setLineWidth(1);
		mrenderer.addSeriesRenderer(renderer2);

		XYSeriesRenderer renderer3 = new XYSeriesRenderer();
		renderer3.setColor(Color.argb(255, 73,118,181));
		renderer3.setFillBelowLine(true);
		renderer3.setFillBelowLineColor(Color.argb(64,36,59,90));
		renderer3.setGradientEnabled(true);
		renderer3.setLineWidth(2);
		mrenderer.addSeriesRenderer(renderer3);

		XYSeriesRenderer renderer4 = new XYSeriesRenderer();
		renderer4.setColor(Color.argb(255,76,110,23));
		renderer4.setFillBelowLine(true);
		renderer4.setFillBelowLineColor(Color.argb(64, 38,55,11));
		renderer4.setGradientEnabled(true);
		renderer4.setLineWidth(1);
		mrenderer.addSeriesRenderer(renderer4);

		XYSeriesRenderer renderer5 = new XYSeriesRenderer();
		renderer5.setColor(Color.argb(255, 99,66,206));
		renderer5.setFillBelowLine(true);
		renderer5.setFillBelowLineColor(Color.argb(64,50,33,103));
		renderer5.setGradientEnabled(true);
		renderer5.setLineWidth(3);
		mrenderer.addSeriesRenderer(renderer5);
		
		XYSeriesRenderer renderer6 = new XYSeriesRenderer();
		renderer6.setColor(Color.argb(255, 171,45,80));
		renderer6.setFillBelowLine(true);
		renderer6.setFillBelowLineColor(Color.argb(64,85,22,40));
		renderer6.setGradientEnabled(true);
		renderer6.setLineWidth(1);
		mrenderer.addSeriesRenderer(renderer6);


//		Intent intent = ChartFactory.getLineChartIntent(context, dataset,
		Intent intent = ChartFactory.getTimeChartIntent(context, dataset,
				mrenderer, "Histogram");

		return intent;

	}

}
