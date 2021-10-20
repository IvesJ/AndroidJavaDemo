package com.acecoder.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.acecoder.test.R;

public class TestFragmentB extends Fragment {

    private Button button;
    private TextView textView;

    private String textContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            textContent = getArguments().getString("remark");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test_b, container, false);
        initFragment(rootView);
        return rootView;
    }

    private void initFragment(View view) {
        button = view.findViewById(R.id.buttonB);
        textView = view.findViewById(R.id.textViewB);
        initListener();
    }

    private void initListener() {
        textView.setText(textContent);
    }
}
