package com.acecoder.test.mvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.acecoder.test.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSPActivity extends AppCompatActivity {
    private Button btRead;
    private Button btWrite;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sp);
        init();
    }

    private void init() {
        initView();
        initListener();
        initData();
    }

    private void initView() {
        btRead = findViewById(R.id.read);
        btWrite = findViewById(R.id.write);
    }

    private void initListener() {
        btRead.setOnClickListener(view -> testRead());
        btWrite.setOnClickListener(view -> testWrite());
    }

    private void initData() {
        preferences = getSharedPreferences("AndroidJavaDemo", MODE_PRIVATE);
        editor = preferences.edit();
    }

    private void testRead() {
        String time = preferences.getString("time", null);
        int randNum = preferences.getInt("random", 0);
        String result = time == null ? "您暂时还未写入数据" : "写入时间为："
                + time + "\n 上次生成的随机数为：" + randNum;

        Toast.makeText(TestSPActivity.this, result, Toast.LENGTH_SHORT).show();
    }

    private void testWrite() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 " + "hh:mm:ss");

        editor.putString("time", sdf.format(new Date()));
        editor.putInt("random", (int) (Math.random() * 100));

        editor.commit();
    }

    public void testClassLoader() {
        ClassLoader classLoader = getClassLoader();
        Log.i("classLoader", "onCreate: " + classLoader);

        ClassLoader classLoader1 = Activity.class.getClassLoader();
        Log.i("classLoader", "onCreate: " + classLoader1);
    }
}