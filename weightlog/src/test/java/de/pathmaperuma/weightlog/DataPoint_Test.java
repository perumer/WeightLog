package de.pathmaperuma.weightlog;

import org.junit.Test;

public class DataPoint_Test {

    @Test(expected = IndexOutOfBoundsException.class)
    public void testName() throws Exception {
        new DataPoint("");
    }
}
