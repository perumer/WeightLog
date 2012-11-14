package de.pathmaperuma.weightlog;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DataPoint_Test {

    @Test
    public void formatDateToString() throws Exception {
        DataPoint dataPoint = new DataPoint(0, 0, 0, 0, 0, 0, new DateTime(2012, 5, 3, 12, 45).toDate());
        assertThat(dataPoint.getNiceDateFromUnixTime(), is("2012-05-03"));
    }
}
