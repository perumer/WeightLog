package de.pathmaperuma.weightlog;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DataPointModel_Test {

    private final DataPointModel model = new DataPointModel();

    @Test
    public void forwardTheWeightToTheDataModel() throws Exception {
        model.setWeight(87.7f);

        DataPoint dataPoint = model.createDataPoint();
        assertThat(dataPoint.getWeight(), is(87.7f));
    }

    @Test
    public void forwardFatPercentageToTheDataModel() throws Exception {
        model.setPercentBodyFat(47.3f);

        DataPoint dataPoint = model.createDataPoint();
        assertThat(dataPoint.getPercentBodyFat(), is(47.3f));
    }

    @Test
    public void forwardWaterPercentageToTheDataModel() throws Exception {
        model.setPercentBodyWater(47.3f);

        DataPoint dataPoint = model.createDataPoint();
        assertThat(dataPoint.getPercentBodyWater(), is(47.3f));
    }

    @Test
    public void forwardMusclePercentageToTheDataModel() throws Exception {
        model.setPercentBodyMuscle(12.3f);

        DataPoint dataPoint = model.createDataPoint();
        assertThat(dataPoint.getPercentBodyMuscle(), is(12.3f));
    }

    @Test
    public void forwardBoneWeightToTheDataModel() throws Exception {
        model.setBoneWeight(45.5f);

        DataPoint dataPoint = model.createDataPoint();
        assertThat(dataPoint.getBoneWeight(), is(45.5f));
    }

    @Test
    public void forwardKilokalorienToTheDataModel() throws Exception {
        model.setKilokalorien(450);

        DataPoint dataPoint = model.createDataPoint();
        assertThat(dataPoint.getKilokalorien(), is(450));
    }

    @Test
    public void forwardTimeTakenToTheDataModel() throws Exception {
        DateTime yesterday = new DateTime().minusDays(1);
        model.setTimeTaken(yesterday);

        DataPoint dataPoint = model.createDataPoint();
        assertThat(dataPoint.getTimeTaken(), is(yesterday));
    }
}
