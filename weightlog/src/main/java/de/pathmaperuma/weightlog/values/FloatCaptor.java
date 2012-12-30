package de.pathmaperuma.weightlog.values;

import de.pathmaperuma.weightlog.values.ValueAdapter;

public class FloatCaptor extends ValueAdapter {
    private float value;

    @Override
    public void visit(float value) {
        this.value = value;
    }

    public float value() {
        return value;
    }
}
