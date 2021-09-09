package com.acecoder.test.jetpack.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @Description 学习jetpack使用的presenter
 * @Date   2021/9/9  9:33
 * @Author  AceCoder
 */
public class LifecycleTestPresenter implements LifecycleObserver {

    private final static String TAG = "JetpackTestPresenter";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void createListener() {
        Log.i(TAG, "OnCreate ....");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void startListener() {
        Log.i(TAG, "OnStart ....");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resumeListener() {
        Log.i(TAG, "OnResume ....");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pauseListener() {
        Log.i(TAG, "OnPause ....");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stopListener() {
        Log.i(TAG, "OnStop ....");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroyListener() {
        Log.i(TAG, "OnDestroy ....");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void anyListener() {
        Log.i(TAG, "Any ....");
    }
}
