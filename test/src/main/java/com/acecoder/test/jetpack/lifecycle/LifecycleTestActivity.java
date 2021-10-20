package com.acecoder.test.jetpack.lifecycle;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
/**
 * @Description LifeCycle组件学习
 * @Date   2021/9/9  9:29
 * @Author  AceCoderjghk87-986
 */
public class LifecycleTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        getLifecycle().addObserver(new LifecycleTestPresenter());
    }
}
