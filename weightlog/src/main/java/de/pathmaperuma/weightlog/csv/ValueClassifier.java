package de.pathmaperuma.weightlog.csv;

import com.google.inject.internal.util.$Objects;

public class ValueClassifier {

    private final String name;

    public ValueClassifier(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ValueClassifier)) {
            return false;
        }
        ValueClassifier that = (ValueClassifier) o;
        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return $Objects.hashCode(name);
    }
}