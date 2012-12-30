package de.pathmaperuma.weightlog.values;

import de.pathmaperuma.weightlog.values.ValueVisitor;

public class ValueAdapter implements ValueVisitor {
    @Override
    public void visit(float value) {
        //do nothing by default
    }

    @Override
    public void visit(int value) {
        //do nothing by default
    }
}
