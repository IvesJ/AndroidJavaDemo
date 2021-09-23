package com.acecoder.test.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.acecoder.test.R;
import com.acecoder.test.jetpack.lifecycle.LifecycleTestActivity;
import com.acecoder.test.jetpack.livedata.LiveDataTestActivity;
import com.acecoder.test.jetpack.viewmodel.ViewModelTestActivity;

public class TestActivity extends AppCompatActivity {

    private Button jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        jump = findViewById(R.id.jump);
        init();
        initListener();

        ClassLoader classLoader = getClassLoader();
        Log.i("classLoader", "onCreate: " + classLoader);

        ClassLoader classLoader1 = Activity.class.getClassLoader();
        Log.i("classLoader", "onCreate: " + classLoader1);
    }

    public void jumpToJetpackTest() {
        Intent intent = new Intent(this, LiveDataTestActivity.class);
        startActivity(intent);
    }

    public void init() {
    }

    public void initListener() {
        jump.setOnClickListener((view)->{
            jumpToJetpackTest();
        });
    }
}