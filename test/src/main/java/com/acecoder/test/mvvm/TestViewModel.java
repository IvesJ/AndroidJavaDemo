package com.acecoder.test.mvvm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TestViewModel extends ViewModel {
    public MutableLiveData<Integer> mInt;
    private MutableLiveData<String> mTxt;


    public MutableLiveData<Integer> getInt() {
        if (mInt == null) {
            mInt = new MutableLiveData<>();
            mInt.setValue(0);
        }
        return mInt;
    }

    public void addInt() {
        if (mInt == null) {
            mInt = new MutableLiveData<>();
            mInt.setValue(0);
        }
        mInt.setValue(mInt.getValue() + 1);
    }
}
