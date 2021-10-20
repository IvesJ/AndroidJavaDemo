package com.acecoder.test.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.acecoder.test.R;
import com.acecoder.test.activity.TestLifecycleActivityA;
import com.acecoder.test.activity.TestLifecycleActivityB;

public class TestFragmentA extends Fragment {

    private static final String TAG = "TestFragmentA";

    private final long maxGap = 300;

    private Button jump;
    private Button change;

    private String textContent;

    private int clickCount = 0;

    private long lastClickTime;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Runnable oneClick = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getActivity(), "单击事件触发...", Toast.LENGTH_SHORT).show();
            clickCount = 0;
            lastClickTime = 0;
        }
    };

    private Runnable doubleClick = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getActivity(), "双击事件触发...", Toast.LENGTH_SHORT).show();
            clickCount = 0;
            lastClickTime = 0;
        }
    };

    private Runnable threeClick = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getActivity(), "三击事件触发...", Toast.LENGTH_SHORT).show();
            clickCount = 0;
            lastClickTime = 0;
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        if (getArguments() != null) {
            textContent = getArguments().getString("remark");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View rootView = inflater.inflate(R.layout.fragment_test_a, container, false);
        initFragment(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: ");
    }

    private void initFragment(View view) {
        jump = view.findViewById(R.id.bt_fragment_a_jump);
        change = view.findViewById(R.id.bt_fragment_a_change);

        initListener();
        initView();
    }

    private void initListener() {
        jump.setOnClickListener((v) -> {
            Intent intent = new Intent(getActivity(), TestLifecycleActivityB.class);
            startActivity(intent);
        });

        change.setOnClickListener((v) -> {
            Bundle bundle = new Bundle();
            bundle.putString("remark", "TestFragmentA 切换至 TestFragmentB");
            TestFragmentB testFragmentB = new TestFragmentB();
            testFragmentB.setArguments(bundle);
            TestLifecycleActivityA.changeFragment(testFragmentB, false);
        });
    }

    private void initView() {
    }

    private void singleClick() {
        mHandler.removeCallbacks(doubleClick);
        mHandler.removeCallbacks(threeClick);

        mHandler.postDelayed(oneClick, maxGap + 1);
    }

    private void doubleClick() {
        mHandler.removeCallbacks(oneClick);
        mHandler.removeCallbacks(threeClick);

        mHandler.postDelayed(doubleClick, maxGap + 1);
    }

    private void threeClick() {
        mHandler.removeCallbacks(oneClick);
        mHandler.removeCallbacks(doubleClick);

        mHandler.post(threeClick);
    }
    private void customClick() {
        long gap = 0;
        long currentTime = System.currentTimeMillis();
        if (lastClickTime > 0) {
            gap = currentTime - lastClickTime;
        } else {
            lastClickTime = currentTime;
        }
        if (gap > 0 && gap < maxGap) {
            clickCount++;
        } else if (gap == 0 || gap >= maxGap){
            clickCount = 1;
        }
        switch (clickCount) {
            case 1:
                singleClick();
                break;
            case 2:
                doubleClick();
                break;
            case 3:
                threeClick();
                break;
            default:
                break;
        }
    }
}
