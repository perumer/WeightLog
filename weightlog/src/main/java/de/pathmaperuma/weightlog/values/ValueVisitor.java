package de.pathmaperuma.weightlog.values;

public interface ValueVisitor {
    void visit(float value);

    void visit(int value);
}
