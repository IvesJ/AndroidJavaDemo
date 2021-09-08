package com.acecoder.test.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.acecoder.test.R;

public class TestActivity extends AppCompatActivity {

    private ViewDataBinding mViewDataBinding;
    private TestViewModel mTestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_test);
        mTestViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        mViewDataBinding.setLifecycleOwner(this);
    }
}