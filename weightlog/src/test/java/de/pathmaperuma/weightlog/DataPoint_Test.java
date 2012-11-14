package de.pathmaperuma.weightlog;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DataPoint_Test {
    private final DataPoint dataPoint = new DataPoint(0, 0, 0, 0, 0, 0, new DateTime(2012, 5, 3, 12, 45));

    @Test
    public void formatDateToString() throws Exception {
        assertThat(dataPoint.getNiceDateFromUnixTime(), is("2012-05-03"));
    }

    @Test
    public void ensureMilliSecondsSinceStartOf1970() throws Exception {
        assertThat(dataPoint.getUnixTimeString(), is("1336041900000"));

    }
}
