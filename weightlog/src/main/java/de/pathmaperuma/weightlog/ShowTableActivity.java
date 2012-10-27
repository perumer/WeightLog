package de.pathmaperuma.weightlog;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ShowTableActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dataview);

		DataManipulator dh = new DataManipulator(this);

		Toast.makeText(this, "Loading Values...", Toast.LENGTH_SHORT).show();
		TextView content = (TextView) findViewById(R.id.dbContent);
		content.setText("DB Content: \n--------------------------\n");
		content.append("ID, Date, kg, % fat, % water, muscle, kcal, bone(kg)"
				+ "\n");
		List<String[]> rows = dh.selectLast();
		for (String[] s : rows) {
			String niceDate = WeightLogActivity.getNiceDateFromUnixTime(Long
					.parseLong(s[1]));
			content.append(s[0] + " | " + niceDate + " | " + s[2] + " | "
					+ s[3] + " | " + s[4] + " | " + s[5] + " | " + s[6] + " | "
					+ s[7] + "\n");
		}
	}
}