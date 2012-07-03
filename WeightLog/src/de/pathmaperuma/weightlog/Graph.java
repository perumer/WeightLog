package de.pathmaperuma.weightlog;

import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
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
		
		int[] date = new int[setSize];
		double[] weights = new double[setSize];
		double[] fats = new double[setSize];
		double[] water = new double[setSize];
		double[] muscle = new double[setSize];
		double[] kcal = new double[setSize];
		double[] bone = new double[setSize];

		int count = 0;
		for (String[] s : rows) {
			date[count] = (int) Long.parseLong(s[1])/100000;
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
			weightSeries.add(date[i], weights[i]);
			fatSeries.add(date[i], fats[i]);
			waterSeries.add(date[i], water[i]);
			muscleSeries.add(date[i], muscle[i]);
			kcalSeries.add(date[i], kcal[i]);
			boneSeries.add(date[i], bone[i]);
		}

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		// dataset.addSeries(series1);
		dataset.addSeries(weightSeries);
		dataset.addSeries(fatSeries);
		dataset.addSeries(waterSeries);
		dataset.addSeries(muscleSeries);
		dataset.addSeries(kcalSeries);
		dataset.addSeries(boneSeries);

		XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer renderer1 = new XYSeriesRenderer();
		renderer1.setColor(Color.RED);
		renderer1.setPointStyle(PointStyle.POINT);
		renderer1.setFillPoints(true);
		mrenderer.addSeriesRenderer(renderer1);

		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setColor(Color.BLUE);
		renderer2.setPointStyle(PointStyle.CIRCLE);
		renderer2.setFillPoints(false);
		mrenderer.addSeriesRenderer(renderer2);

		XYSeriesRenderer renderer3 = new XYSeriesRenderer();
		mrenderer.addSeriesRenderer(renderer3);
		renderer3.setColor(Color.CYAN);
		XYSeriesRenderer renderer4 = new XYSeriesRenderer();
		mrenderer.addSeriesRenderer(renderer4);
		renderer4.setColor(Color.WHITE);
		XYSeriesRenderer renderer5 = new XYSeriesRenderer();
		mrenderer.addSeriesRenderer(renderer5);
		renderer5.setColor(Color.YELLOW);
		XYSeriesRenderer renderer6 = new XYSeriesRenderer();
		mrenderer.addSeriesRenderer(renderer6);
		renderer6.setColor(Color.MAGENTA);


		Intent intent = ChartFactory.getLineChartIntent(context, dataset,
				mrenderer, "Histogram");

		return intent;

	}

}
