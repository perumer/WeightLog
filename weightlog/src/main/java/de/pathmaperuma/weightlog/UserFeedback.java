package de.pathmaperuma.weightlog;

import android.content.Context;
import android.widget.Toast;

public class UserFeedback {

	private Context context;

	public UserFeedback(Context context) {
		this.context = context;

	}

	public void out(String s){
		Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
	}

	public void outLong(String s){
		Toast.makeText(context, s, Toast.LENGTH_LONG).show();
	}
}
