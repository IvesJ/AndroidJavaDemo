package com.acecoder.common.rxjava;

import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @Description RxJava测试
 * @Date 2021/9/28  9:22
 * @Author AceCoder
 */
public class RxJavaTest {

    private static String TAG = "RxJavaTest";

    public void Test() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                Log.i(TAG, "create-ObservableOnSubscribe-subscribe: ");
                emitter.onNext("200");
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Throwable {
                Log.i(TAG, "map-Function-apply: " + s);
                return Integer.valueOf(s);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.i(TAG, "subscribe-Observer-onSubscribe: " + d);
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.i(TAG, "subscribe-Observer-onNext: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.i(TAG, "subscribe-Observer-onError: " + e);
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "subscribe-Observer-onComplete: complete");
            }
        });
    }

    public void test() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {

            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Throwable {
                return null;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
