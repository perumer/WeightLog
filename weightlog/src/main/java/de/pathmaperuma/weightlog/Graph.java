package de.pathmaperuma.weightlog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import org.achartengine.ChartFactory;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Graph {

    public Intent getIntent(Context context, List<String[]> rows) {
        int setSize = rows.size();
        Date[] date = new Date[setSize];
        float[] weights = new float[setSize];
        float[] fats = new float[setSize];
        float[] water = new float[setSize];
        float[] muscle = new float[setSize];
        int[] kcal = new int[setSize];
        float[] bone = new float[setSize];

        List<DataPoint> dataPoints = new ArrayList<DataPoint>();

        int count = 0;
        for (String[] s : rows) {
            date[count] = new Date(Long.parseLong(s[1]));
            weights[count] = Float.parseFloat(s[2]);
            fats[count] = Float.parseFloat(s[3]);
            water[count] = Float.parseFloat(s[4]);
            muscle[count] = Float.parseFloat(s[5]);
            kcal[count] = Integer.parseInt(s[6]) / 100;
            bone[count] = Float.parseFloat(s[7]);
            dataPoints.add(new DataPoint(weights[count], fats[count], water[count], muscle[count], kcal[count], bone[count], new DateTime(date[count])));
            count++;
        }

        TimeSeries weightSeries = new TimeSeries("weight (kg)");
        TimeSeries fatSeries = new TimeSeries("fat (%)");
        TimeSeries waterSeries = new TimeSeries("water (%)");
        TimeSeries muscleSeries = new TimeSeries("muscle (%)");
        TimeSeries kcalSeries = new TimeSeries("kcal/100");
        TimeSeries boneSeries = new TimeSeries("bone (kg)");


        for (int i = 0; i < dataPoints.size(); i++) {
            Date dateTaken = dataPoints.get(i).getTimeTaken().toDate();
            kcalSeries.add(dateTaken, kcal[i]);
            muscleSeries.add(dateTaken, muscle[i]);
            fatSeries.add(dateTaken, fats[i]);
            waterSeries.add(dateTaken, water[i]);
            weightSeries.add(dateTaken, weights[i]);
            boneSeries.add(dateTaken, bone[i]);
        }


        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(kcalSeries);
        dataset.addSeries(fatSeries);
        dataset.addSeries(waterSeries);
        dataset.addSeries(muscleSeries);
        dataset.addSeries(weightSeries);
        dataset.addSeries(boneSeries);

        XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
        mrenderer.setApplyBackgroundColor(true);
        mrenderer.setBackgroundColor(Color.argb(128, 49, 46, 39));
        mrenderer.setMarginsColor(Color.argb(255, 27, 26, 24));
        mrenderer.setGridColor(Color.argb(128, 150, 150, 150));
        mrenderer.setShowGrid(true);
        mrenderer.setLabelsTextSize(10);
        mrenderer.setXLabelsAngle(30);


        XYSeriesRenderer renderer1 = new XYSeriesRenderer();
        renderer1.setColor(Color.argb(255, 222, 55, 41));
        renderer1.setFillBelowLine(true);
        renderer1.setFillBelowLineColor(Color.argb(64, 111, 27, 21));
        renderer1.setGradientEnabled(true);
        renderer1.setLineWidth(1);
        mrenderer.addSeriesRenderer(renderer1);


        XYSeriesRenderer renderer2 = new XYSeriesRenderer();
        renderer2.setColor(Color.argb(255, 244, 278, 73));
        renderer2.setFillBelowLine(true);
        renderer2.setFillBelowLineColor(Color.argb(64, 122, 89, 36));
        renderer2.setGradientEnabled(true);
        renderer2.setLineWidth(1);
        mrenderer.addSeriesRenderer(renderer2);

        XYSeriesRenderer renderer3 = new XYSeriesRenderer();
        renderer3.setColor(Color.argb(255, 73, 118, 181));
        renderer3.setFillBelowLine(true);
        renderer3.setFillBelowLineColor(Color.argb(64, 36, 59, 90));
        renderer3.setGradientEnabled(true);
        renderer3.setLineWidth(2);
        mrenderer.addSeriesRenderer(renderer3);

        XYSeriesRenderer renderer4 = new XYSeriesRenderer();
        renderer4.setColor(Color.argb(255, 76, 110, 23));
        renderer4.setFillBelowLine(true);
        renderer4.setFillBelowLineColor(Color.argb(64, 38, 55, 11));
        renderer4.setGradientEnabled(true);
        renderer4.setLineWidth(1);
        mrenderer.addSeriesRenderer(renderer4);

        XYSeriesRenderer renderer5 = new XYSeriesRenderer();
        renderer5.setColor(Color.argb(255, 99, 66, 206));
        renderer5.setFillBelowLine(true);
        renderer5.setFillBelowLineColor(Color.argb(64, 50, 33, 103));
        renderer5.setGradientEnabled(true);
        renderer5.setLineWidth(3);
        mrenderer.addSeriesRenderer(renderer5);

        XYSeriesRenderer renderer6 = new XYSeriesRenderer();
        renderer6.setColor(Color.argb(255, 171, 45, 80));
        renderer6.setFillBelowLine(true);
        renderer6.setFillBelowLineColor(Color.argb(64, 85, 22, 40));
        renderer6.setGradientEnabled(true);
        renderer6.setLineWidth(1);
        mrenderer.addSeriesRenderer(renderer6);

        return ChartFactory.getTimeChartIntent(context, dataset, mrenderer, "Histogram");

    }

}
