package de.pathmaperuma.weightlog;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DataPoint_DanielRetrieveTest {
    private final DataPoint dataPoint = DataPoint.Daniel(1, 2, 3, 4, 5, 6, new DateTime(2012, 5, 3, 12, 45));

    @Test
    public void weight() throws Exception {
        assertThat(dataPoint.getWeight(), is(1f));
    }

    @Test
    public void bodyFat() throws Exception {
        assertThat(dataPoint.getPercentBodyFat(), is(2f));
    }

    @Test
    public void water() throws Exception {
        assertThat(dataPoint.getPercentBodyWater(), is(3f));
    }

    @Test
    public void muscle() throws Exception {
        assertThat(dataPoint.getPercentBodyMuscle(), is(4f));
    }

    @Test
    public void kcal() throws Exception {
        assertThat(dataPoint.getKilokalorien(), is(5));
    }

    @Test
    public void bone() throws Exception {
        assertThat(dataPoint.getBoneWeight(), is(6f));
    }
}