package com.example.chivas.testspinneredittext.view;

import android.app.Activity;
import android.os.Bundle;

import com.example.chivas.commui.view.SpinnerEditText;
import com.example.chivas.testspinneredittext.R;
import com.example.chivas.testspinneredittext.adater.MultiSpinnerEditTextListAdapter;
import com.example.chivas.testspinneredittext.adater.SpinnerEditTextListAdapter;
import com.example.chivas.testspinneredittext.bean.MultiValueBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private SpinnerEditText<String> mSpnSingle;
    private SpinnerEditText<MultiValueBean> mSpnMulti;

    private SpinnerEditTextListAdapter mSingleAdapter;
    private MultiSpinnerEditTextListAdapter mMultiAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpnSingle = (SpinnerEditText<String>) findViewById(R.id.spn_single);
        mSpnMulti = (SpinnerEditText<MultiValueBean>) findViewById(R.id.spn_multi);

        initSingle();
        initMulti();
    }

    private void initSingle() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("Single" + i);
        }
        mSingleAdapter = new SpinnerEditTextListAdapter(this);
        mSpnSingle.setAdapter(mSingleAdapter);
        mSingleAdapter.replaceList(list);
    }

    private void initMulti() {
        List<MultiValueBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new MultiValueBean(String.valueOf(i), "Multi" + i));
        }
        mMultiAdapter = new MultiSpinnerEditTextListAdapter(this);
        mSpnMulti.setAdapter(mMultiAdapter);
        mMultiAdapter.replaceList(list);
    }


}
