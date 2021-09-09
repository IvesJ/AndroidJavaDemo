package com.acecoder.test.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.acecoder.test.R;
import com.acecoder.test.jetpack.lifecycle.LifecycleTestActivity;
import com.acecoder.test.jetpack.livedata.LiveDataTestActivity;

public class TestActivity extends AppCompatActivity {

    private Button jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        jump = findViewById(R.id.jump);
        init();
        initListener();
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