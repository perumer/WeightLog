package de.pathmaperuma.weightlog;

import android.content.Context;
import android.widget.Toast;

public class UserFeedback {

	private Context context;

	public UserFeedback(Context context) {
		this.context = context;

	}

	void out(String s){
		Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
	}

	void outLong(String s){
		Toast.makeText(context, s, Toast.LENGTH_LONG).show();
	}

}
