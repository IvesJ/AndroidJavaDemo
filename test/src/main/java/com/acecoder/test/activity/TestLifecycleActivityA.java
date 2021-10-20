package com.acecoder.test.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.acecoder.test.R;
import com.acecoder.test.fragment.TestFragmentA;

public class TestLifecycleActivityA extends FragmentActivity {
    private static final String TAG = "TestJumpActivityA";

    private static FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_test_jump_a);
        mFragmentManager = getSupportFragmentManager();
        bindDefaultFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    private void bindDefaultFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("remark", "TestLifecycleActivityA 加载 TestFragmentA");
        TestFragmentA testFragmentA = new TestFragmentA();
        testFragmentA.setArguments(bundle);
        changeFragment(testFragmentA, true);
    }

    public static void changeFragment(Fragment fragment, boolean init) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(R.id.test_container, fragment);
        if (!init) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }
}
