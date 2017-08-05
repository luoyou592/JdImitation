package com.young.jdmall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.young.jdmall.R;
import com.young.jdmall.bean.RecepitAddressBean;
import com.young.jdmall.network.BaseObserver;
import com.young.jdmall.network.RetrofitFactory;
import com.young.jdmall.ui.adapter.AddressRvAdapter;
import com.young.jdmall.ui.utils.PreferenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/1 0001 21:35
 *  描述：    TODO
 */
public class RecepitAddressActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.rv_receipt_address)
    RecyclerView mRvReceiptAddress;
    @BindView(R.id.bt_add_address)
    Button mBtAddAddress;
    private AddressRvAdapter mAddressRvAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepit_address);
        ButterKnife.bind(this);
        mRvReceiptAddress.setLayoutManager(new LinearLayoutManager(this));
        mAddressRvAdapter = new AddressRvAdapter(this);
        mRvReceiptAddress.setAdapter(mAddressRvAdapter);
    }

    @OnClick({R.id.iv_back, R.id.bt_add_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_add_address:
                Intent intent = new Intent(this, AddRecepitAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    private static final String TAG = "RecepitAddressActivity";
    @Override
    protected void onStart() {
        super.onStart();
        if(!"".equals(PreferenceUtils.getUserId(this))) {


            Observable<RecepitAddressBean> newsObservable = RetrofitFactory.getInstance().listAddressList(PreferenceUtils.getUserId(this));
            newsObservable.compose(compose(this.<RecepitAddressBean>bindToLifecycle())).subscribe(new BaseObserver<RecepitAddressBean>(this) {
                @Override
                protected void onHandleSuccess(RecepitAddressBean addressListBean) {
                    if(addressListBean.getAddressList() != null){
//                        Toast.makeText(RecepitAddressActivity.this, "访问成功"+ addressListBean.getResponse(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onHandleSuccess: "+ addressListBean.getAddressList().size());
                        mAddressRvAdapter.setAddressBeanList(addressListBean.getAddressList());

                    }

                }
            });
        }
    }
}
