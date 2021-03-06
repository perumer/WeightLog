package de.pathmaperuma.weightlog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import de.pathmaperuma.weightlog.configuration.Daniel;
import de.pathmaperuma.weightlog.values.ValueAdapter;
import org.achartengine.ChartFactory;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.Date;
import java.util.List;

public class Graph {

    public Intent createIntent(Context context, List<DataPoint> dataPoints) {
        final TimeSeries weightSeries = new TimeSeries("weight (kg)");
        final TimeSeries fatSeries = new TimeSeries("fat (%)");
        final TimeSeries waterSeries = new TimeSeries("water (%)");
        final TimeSeries muscleSeries = new TimeSeries("muscle (%)");
        final TimeSeries kcalSeries = new TimeSeries("kcal/100");
        final TimeSeries boneSeries = new TimeSeries("bone (kg)");

        for (DataPoint dataPoint : dataPoints) {
            final Date timeTaken = dataPoint.getTimeTaken().toDate();
            dataPoint.valueFor(Daniel.ClassifierWeight).welcome(new ValueAdapter() {
                @Override
                public void visit(float value) {
                    weightSeries.add(timeTaken, value);
                }
            });
            dataPoint.valueFor(Daniel.ClassifierKiloKalories).welcome(new ValueAdapter() {
                @Override
                public void visit(int value) {
                    kcalSeries.add(timeTaken, value / 100);
                }
            });
            dataPoint.valueFor(Daniel.ClassifierMuscle).welcome(new ValueAdapter() {
                @Override
                public void visit(float value) {
                    muscleSeries.add(timeTaken, value);
                }
            });
            dataPoint.valueFor(Daniel.ClassifierFat).welcome(new ValueAdapter(){
                @Override
                public void visit(float value) {
                    fatSeries.add(timeTaken, value);
                }
            });
            dataPoint.valueFor(Daniel.ClassifierWater).welcome(new ValueAdapter(){
                @Override
                public void visit(float value) {
                    waterSeries.add(timeTaken, value);
                }
            });
            dataPoint.valueFor(Daniel.ClassifierBone).welcome(new ValueAdapter(){
                @Override
                public void visit(float value) {
                   boneSeries.add(timeTaken, value);
                }
            });
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
