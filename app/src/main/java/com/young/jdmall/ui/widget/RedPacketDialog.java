package com.young.jdmall.ui.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.young.jdmall.R;
import com.young.jdmall.ui.activity.FillingOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by BjyJyk on 2017/8/2.
 */

public class RedPacketDialog extends Dialog {


    @BindView(R.id.btn_hongbao)
    Button mBtnHongbao;
    private Context mContext;

    public RedPacketDialog(Context context) {
        this(context, 0);
    }

    public RedPacketDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        //以view的方式引入，然后回调activity方法，setContentView，实现自定义布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_hongbao, null);
        ButterKnife.bind(this, view);
        setContentView(view);
        //radiobutton的初始化


        // groupBroadcast.setOnCheckedChangeListener(listener);
        //设置dialog大小，这里是一个小赠送，模块好的控件大小设置
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) mContext).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.CENTER);//设置对话框位置
        Display d = manager.getDefaultDisplay(); // 获取屏幕宽、高度
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
        params.height = (int) (d.getHeight() * 0.8);
        dialogWindow.setAttributes(params);

    }

    @OnClick(R.id.btn_hongbao)
    public void onViewClicked() {
        Intent intent =new Intent(mContext, FillingOrderActivity.class);
        this.dismiss();
        mContext.startActivity(intent);
    }
}
