package com.young.jdmall.ui.widget;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.young.jdmall.R;

import butterknife.BindView;

/**
 * Created by BjyJyk on 2017/8/4.
 */

public class PopWinPayWay extends BasePopWin {


    @BindView(R.id.payway_one)
    public TextView mInvoiceOne;
    @BindView(R.id.payway_two)
    public TextView mInvoiceTwo;
    @BindView(R.id.payway_third)
    public TextView mInvoiceThird;
    @BindView(R.id.payway_ok)
    public Button mInvoiceOk;

    public PopWinPayWay(Activity paramActivity, View.OnClickListener paramOnClickListener, int paramInt1, int paramInt2) {
        super(paramActivity, paramOnClickListener, paramInt1, paramInt2);
    }


    private void init() {

    }

    @Override
    public void initSetOnClickListener(View.OnClickListener paramOnClickListener) {
        mInvoiceOne.setOnClickListener(paramOnClickListener);
        mInvoiceTwo.setOnClickListener(paramOnClickListener);
        mInvoiceThird.setOnClickListener(paramOnClickListener);
        mInvoiceOk.setOnClickListener(paramOnClickListener);
    }

    @Override
    public int getlayout() {
        return R.layout.popwin_payway;
    }
}
