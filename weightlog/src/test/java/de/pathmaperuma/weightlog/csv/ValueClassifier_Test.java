package de.pathmaperuma.weightlog.csv;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValueClassifier_Test {
    private final ValueClassifier first = new ValueClassifier("fun");
    private final ValueClassifier second = new ValueClassifier("fun");

    @Test
    public void equalityIsBasedOnName() throws Exception {
        assertThat(first, equalTo(second));
    }

    @Test
    public void hashIsBasedOnName() throws Exception {
        assertThat(first.hashCode(), is(second.hashCode()));
    }
}
