package com.acecoder.test.jetpack.viewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.acecoder.test.R;

/**
 * @Description ViewModel组件学习
 * @Date   2021/9/9  14:36
 * @Author  sjfang
 */
public class ViewModelTestActivity extends AppCompatActivity {

    private TestViewModel mViewModel;
    private TextView mDataView;
    private Button mAdd;

    private int number = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_viewmodel_test);
        mDataView = findViewById(R.id.data);
        mAdd = findViewById(R.id.add);

        mViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        mViewModel.getIntLive().observe(this, (integer -> {
            mDataView.setText(String.valueOf(integer));
        }));

        mAdd.setOnClickListener(view -> mViewModel.getIntLive().setValue(mViewModel.getIntLive().getValue() + 1));
    }
}
