package com.acecoder.test.mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.acecoder.network.okhttp3.OkhttpUtils;
import com.acecoder.test.R;
import com.acecoder.test.jetpack.livedata.LiveDataTestActivity;
import com.acecoder.common.rxjava.RxJavaTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class TestActivity extends AppCompatActivity {

    private Button mButton;
    private Button mJump;

    private RxJavaTest mRxJavaTest;
    private OkhttpUtils mOkhttpUtils;

    private Handler subHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sp);
        mButton = findViewById(R.id.button);
        init();
        initListener();
    }

    public void init() {
        mRxJavaTest = new RxJavaTest();
    }

    public void initListener() {
        mButton.setOnClickListener((view)->{
            ThreadA threadA = new ThreadA();
            ThreadB threadB = new ThreadB();
            threadA.start();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            threadB.start();
        });
    }

    public void jumpToActivityTest() {
        Intent intent = new Intent(this, LiveDataTestActivity.class);
        startActivity(intent);
    }

    public void checkClassLoader() {
        ClassLoader loader = TestSPActivity.class.getClassLoader();
        while (loader != null) {
            Log.i("aceCoder", loader.toString());
            loader = loader.getParent();
        }
    }

    public void testHttp() {
//        OkhttpUtils.testGetRequest();
        OkhttpUtils.testPostRequest();
    }

    public void testHttpConfig() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .cache(new Cache(getExternalCacheDir(), 500*2034*1024))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        String url = request.url().toString();
                        Log.i("testHttpConfig", "intercept: proceed start: url"+ url+ ", at "+System.currentTimeMillis());
                        Response response = chain.proceed(request);
                        ResponseBody body = response.body();
                        Log.i("testHttpConfig", "intercept: proceed end: url"+ url+ ", at "+System.currentTimeMillis());
                        return response;
                    }
                }).build();
    }

    class ThreadA extends Thread {
        @Override
        public void run() {
            Looper.prepare();
            subHandler = new Handler();
            Looper.loop();
        }
    }

    class ThreadB extends Thread {
        @Override
        public void run() {
            Log.i("Handler-Test", "post: " + Thread.currentThread().getName());
            subHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.i("Handler-Test", "run: " + Thread.currentThread().getName());
                }
            });
        }
    }
}