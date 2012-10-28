package de.pathmaperuma.weightlog;

import android.text.Editable;
import android.text.TextWatcher;

public class IntegerTextWatcher implements TextWatcher {

    private Closure<Integer> afterTextChange;

    IntegerTextWatcher(Closure<Integer> afterTextChange) {
        this.afterTextChange = afterTextChange;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //nothing to do
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //nothing to do
    }

    @Override
    public void afterTextChanged(Editable s) {
        int value = Integer.parseInt(s.toString());
        afterTextChange.execute(value);
    }
}
