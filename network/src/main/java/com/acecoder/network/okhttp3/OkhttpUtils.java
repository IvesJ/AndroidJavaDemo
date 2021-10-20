package com.acecoder.network.okhttp3;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpUtils {

    public static void testGetRequest() {
        //构建客户端对象OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient();

        //构建请求Request
        String url = "https://www.baidu.com/";
        Request getRequest = new Request.Builder()
                .url(url)
                .get()
                .build();

        //生成Call对象
        Call call = okHttpClient.newCall(getRequest);

        //Call发起请求（同步/异步）
        new Thread(() -> {
            try {
                Response response = call.execute();
                Log.i("testOkhttp", "okHttpGet run : response:" + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void testPostRequest() {
        OkHttpClient httpClient = new OkHttpClient();

        MediaType contentType = MediaType.parse("text/x-markdown;charset=utf-8");
        String content = "hello!";
        RequestBody body = RequestBody.create(contentType, content);

        Request postRequest = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(body)
                .build();

        Call call = httpClient.newCall(postRequest);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("testPostRequest", "okHttpPost enqueue: \n onResponse:"+ response.toString() +"\n body:" +response.body().string());
            }
        });
    }

    public static void testOkHttpConfig() {

    }
}
