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
import com.young.jdmall.ui.activity.MessageActivity;
import com.young.jdmall.ui.activity.OrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 钟志鹏 on 2017/7/30.
 */

public class MyFragment extends Fragment {

    private static final int REQUEST = 100;
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
    @BindView(R.id.iv_message_sign)
    ImageView mIvMessageSign;
    @BindView(R.id.iv_setting_sign)
    ImageView mIvSettingSign;
    @BindView(R.id.iv_signOk)
    ImageView mIvSignOk;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.rl_signOk)
    RelativeLayout mRlSignOk;
    @BindView(R.id.tv_order)
    TextView mTvOrder;


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

    @OnClick({R.id.iv_message, R.id.iv_setting, R.id.iv_signOrReg, R.id.rl_signOrReg, R.id.tv_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_message:
                Intent intent = new Intent(getActivity(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_setting:
                Intent intent2 = new Intent(getActivity(), AccountSetting.class);
                startActivity(intent2);
                break;
            case R.id.iv_signOrReg:
                break;
            case R.id.rl_signOrReg:
                Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent4);
                break;
            case R.id.tv_order:
                Intent intent5 = new Intent(getActivity(), OrderActivity.class);
                startActivity(intent5);
                break;
        }
    }

    private void login() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent, REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST) {
            String name = data.getStringExtra("name");
            mRlSignOrReg.setVisibility(View.GONE);
            mRlSignOk.setVisibility(View.VISIBLE);
            mTvUsername.setText(name);
        }
    }
}
