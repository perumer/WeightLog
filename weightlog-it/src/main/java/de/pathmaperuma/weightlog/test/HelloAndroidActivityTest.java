package de.pathmaperuma.weightlog.test;

import android.test.ActivityInstrumentationTestCase2;
import de.pathmaperuma.weightlog.*;

public class HelloAndroidActivityTest extends ActivityInstrumentationTestCase2<WeightLogActivity> {

    public HelloAndroidActivityTest() {
        super(WeightLogActivity.class);
    }

    public void testActivity() {
        WeightLogActivity activity = getActivity();
        assertNotNull(activity);
    }
}

