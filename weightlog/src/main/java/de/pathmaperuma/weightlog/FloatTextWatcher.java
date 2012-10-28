package de.pathmaperuma.weightlog;

import android.text.Editable;
import android.text.TextWatcher;

public class FloatTextWatcher implements TextWatcher {

    private Closure<Float> afterTextChange;

    FloatTextWatcher(Closure<Float> afterTextChange) {
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
        float value = Float.parseFloat(s.toString());
        afterTextChange.execute(value);

    }
}
