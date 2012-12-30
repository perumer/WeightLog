package de.pathmaperuma.weightlog.values;

public interface Value {
    Value increment();

    Value decrement();

    void welcome(ValueVisitor visitor);

}
