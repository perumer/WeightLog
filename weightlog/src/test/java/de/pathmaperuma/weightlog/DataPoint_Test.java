package de.pathmaperuma.weightlog;

import de.pathmaperuma.weightlog.csv.ValueClassifier;
import de.pathmaperuma.weightlog.values.FloatValue;
import de.pathmaperuma.weightlog.values.Value;
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
        assertThat(Long.valueOf(dataPoint.getTimeTaken().toDate().getTime()).toString(), is("1336041900000"));
    }

    @Test
    public void aNewCreatedDataPointHasNoIdentifiers() throws Exception {
        assertThat(dataPoint.classifiers().size(), is(0));
    }

    @Test
    public void returnAllIdentifiersPreviouslyPut() throws Exception {
        dataPoint.measured(new ValueClassifier("weight"), new FloatValue(82f));
        assertThat(dataPoint.classifiers().size(), is(1));
    }

    @Test
    public void retrieveTheValueByClassifier() throws Exception {
        Value attachedValue = new FloatValue(7f);
        dataPoint.measured(new ValueClassifier("weight"), attachedValue);
        assertThat(dataPoint.valueFor(new ValueClassifier("weight")), is(attachedValue));
    }


}