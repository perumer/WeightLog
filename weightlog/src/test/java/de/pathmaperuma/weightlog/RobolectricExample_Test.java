package de.pathmaperuma.weightlog;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RobolectricTestRunner.class)
public class RobolectricExample_Test {

    @Test
    public void firstRobolectricTest() throws Exception {
        WeightLogActivity activity = new WeightLogActivity();
        activity.onCreate(null);
    }
}
