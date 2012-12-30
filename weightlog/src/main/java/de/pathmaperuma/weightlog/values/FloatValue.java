package de.pathmaperuma.weightlog.values;

public class FloatValue implements Value {

    private final float delta = 0.1f;
    private final float value;

    public FloatValue(float value) {
        this.value = value;
    }

    @Override
    public Value increment() {
        return new FloatValue(value+ delta);
    }

    @Override
    public Value decrement() {
        return new FloatValue(value- delta);
    }

    @Override
    public void welcome(ValueVisitor visitor) {
        visitor.visit(value);
    }
}
