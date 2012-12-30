package de.pathmaperuma.weightlog.values;

public class IntegerValue implements Value{
    private final int value;
    private final int delta = 1;

    public IntegerValue(int value) {
        this.value = value;
    }

    @Override
    public Value increment() {
        return new IntegerValue(value + delta);
    }

    @Override
    public Value decrement() {
        return new IntegerValue(value - delta);
    }

    @Override
    public void welcome(ValueVisitor visitor) {
        visitor.visit(value);
    }
}
