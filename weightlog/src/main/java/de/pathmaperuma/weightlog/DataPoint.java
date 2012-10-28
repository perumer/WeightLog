package de.pathmaperuma.weightlog;

import java.util.Date;

import android.widget.EditText;

public class DataPoint {

	private float weight;
	private float fat;
	private float water;
	private float muscle;
	private int kcal;
	private float bone;
	private Date date;
	
	
	public String getWeightString(){
		return Float.valueOf(weight).toString();
	}

	public String getFatString(){
		return Float.valueOf(fat).toString();
	}

	public String getWaterString(){
		return Float.valueOf(water).toString();
	}

	public String getMuscleString(){
		return Float.valueOf(muscle).toString();
	}

    public String getKcalString(){
		return Integer.valueOf(kcal).toString();
	}

	public DataPoint(String line){
		String[] fields = line.split(";");
		date	= new Date(Long.valueOf(fields[1]));
		weight 	= Float.valueOf(fields[2]);
		fat 	= Float.valueOf(fields[3]);
		water 	= Float.valueOf(fields[4]);
		muscle 	= Float.valueOf(fields[5]);
		kcal 	= Integer.valueOf(fields[6]);
		bone 	= Float.valueOf(fields[7]);
	}

	public DataPoint(float weight, float fat, float water, float muscle,
			int kcal, float bone, Date date) {
		super();
		this.weight = weight;
		this.fat = fat;
		this.water = water;
		this.muscle = muscle;
		this.kcal = kcal;
		this.bone = bone;
		this.date = date;
	}

	public DataPoint() {
		super();
	}

	public void setWeight(EditText et){
		weight = Float.parseFloat(et.getText().toString());
	}

	public void setFat(EditText et){
		fat = Float.parseFloat(et.getText().toString());
	}
	public void setWater(EditText et){
		water = Float.parseFloat(et.getText().toString());
	}
	public void setMuscle(EditText et){
		muscle = Float.parseFloat(et.getText().toString());
	}
	public void setBone(EditText et){
		bone = Float.parseFloat(et.getText().toString());
	}
	
	public void setKcal(EditText et){
		kcal = Integer.parseInt(et.getText().toString());
	}
	
	public void setDate(long dateLong){
		date = new Date(dateLong);
	}
	
    public String getNiceDateFromUnixTime(){
        return (1900+date.getYear())+"-"+date.getMonth()+"-"+date.getDate();
    }
    
    public long getUnixTime(){
    	return date.getTime();
    }
    public String getUnixTimeString(){
    	return Long.valueOf(date.getTime()).toString();
    }

	public String getBoneString() {
		return Float.valueOf(bone).toString();
	}
	
}
