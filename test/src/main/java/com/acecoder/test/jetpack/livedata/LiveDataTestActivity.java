package com.acecoder.test.jetpack.livedata;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * @Description LiveData组件学习
 * @Date   2021/9/9  10:56
 * @Author  sjfang
 */
public class LiveDataTestActivity extends AppCompatActivity {

    private static final String TAG = "LiveDataTestActivity";

    public static MutableLiveData<String> mLiveData = new MutableLiveData<>("Test");

    public static Observer<String> sObserver = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            Log.i("onChanged", s);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLiveData.observe(this, sObserver);
        Log.i(TAG, "onCreate------------");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart------------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume------------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop------------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy------------");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart------------");
    }
}
