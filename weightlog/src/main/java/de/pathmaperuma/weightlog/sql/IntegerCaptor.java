package de.pathmaperuma.weightlog.sql;

import de.pathmaperuma.weightlog.values.ValueAdapter;

public class IntegerCaptor extends ValueAdapter{
    private int value;

    @Override
    public void visit(int value) {
        this.value = value;
    }

    public int value(){
        return value;
    }

}
