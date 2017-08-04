package com.young.jdmall.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RadioButton;

import com.young.jdmall.R;

import butterknife.BindView;

/**
 * Created by 25505 on 2017/8/3.
 */

@SuppressLint("AppCompatCustomView")
public class RadioBtnView extends RadioButton {
    @BindView(R.id.radio_btn)
    RadioButton mRadioBtn;

    public RadioBtnView(Context context) {
        this(context, null);
    }

    public RadioBtnView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.radio_btn_view, null);
    }

   /* public void setRbText(String text) {
        mRadioBtn.setText(text);
    }*/

}
