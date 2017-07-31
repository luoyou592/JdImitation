package com.young.jdmall.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.jdmall.R;
import com.young.jdmall.ui.activity.AccountSetting;
import com.young.jdmall.ui.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class MyFragment extends Fragment {

    @BindView(R.id.iv_message)
    ImageView mIvMessage;
    @BindView(R.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R.id.iv_signOrReg)
    ImageView mIvSignOrReg;
    @BindView(R.id.rl_signOrReg)
    RelativeLayout mRlSignOrReg;
    Unbinder unbinder;
    @BindView(R.id.textView3)
    TextView mTextView3;
    @BindView(R.id.textView2)
    TextView mTextView2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText("我的");
        View view = inflater.inflate(R.layout.activity_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_message, R.id.iv_setting, R.id.iv_signOrReg, R.id.rl_signOrReg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_message:
                break;
            case R.id.iv_setting:
                Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.iv_signOrReg:
                break;
            case R.id.rl_signOrReg:
                Intent intent4 = new Intent(getActivity(), AccountSetting.class);
                startActivity(intent4);
                break;
        }
    }
}
