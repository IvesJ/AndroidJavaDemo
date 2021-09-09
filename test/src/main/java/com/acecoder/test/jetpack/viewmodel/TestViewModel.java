package com.acecoder.test.jetpack.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
/**
 * @Description ViewModel组件学习
 * @Date   2021/9/9  14:47
 * @Author  sjfang
 */
public class TestViewModel extends ViewModel {
    private MutableLiveData<Integer> mIntLive;
    private MutableLiveData<String> mStringLive;

    public TestViewModel() {
        mIntLive = new MutableLiveData<>(0);
        mStringLive = new MutableLiveData<>("");
    }

    public MutableLiveData<Integer> getIntLive() {
        return mIntLive;
    }

    public MutableLiveData<String> getStringLive() {
        return mStringLive;
    }
}
