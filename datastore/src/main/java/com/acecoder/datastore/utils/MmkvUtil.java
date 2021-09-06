package com.acecoder.datastore.utils;

import android.content.Context;
import android.os.Build;

import com.getkeepsafe.relinker.ReLinker;
import com.tencent.mmkv.MMKV;

import java.lang.ref.WeakReference;

/**
 * @Description MMKV的使用工具类
 * @Date   2021/9/6  11:14
 * @Author  AceCoder
 */
public class MmkvUtil {
    private volatile static MmkvUtil instance;
    private WeakReference<Context> mWeakReference;

    /**
     * 是否加密
     */
    private boolean encrypt;

    private MmkvUtil() {

    }

    public static MmkvUtil getInstance() {
        if (instance == null) {
            synchronized (MmkvUtil.class) {
                if (instance == null) {
                    instance = new MmkvUtil();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        mWeakReference = new WeakReference<>(context);
        String rootPath = context.getFilesDir().getAbsolutePath() + "/mmkv";
        if (Build.VERSION.SDK_INT == 19) {
            MMKV.initialize(context, rootPath, libName -> ReLinker.loadLibrary(context, libName));
        } else {
            MMKV.initialize(context);
        }
    }
}
