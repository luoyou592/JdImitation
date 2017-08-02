package com.young.jdmall.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.young.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/1 0001 21:38
 *  描述：    TODO
 */
public class AddRecepitAddressActivity extends AppCompatActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.rb_man)
    RadioButton mRbMan;
    @BindView(R.id.rb_women)
    RadioButton mRbWomen;
    @BindView(R.id.rg_sex)
    RadioGroup mRgSex;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.ib_delete_phone)
    ImageButton mIbDeletePhone;
    @BindView(R.id.ib_add_phone_other)
    ImageButton mIbAddPhoneOther;
    @BindView(R.id.et_phone_other)
    EditText mEtPhoneOther;
    @BindView(R.id.ib_delete_phone_other)
    ImageButton mIbDeletePhoneOther;
    @BindView(R.id.rl_phone_other)
    RelativeLayout mRlPhoneOther;
    @BindView(R.id.et_receipt_address)
    EditText mEtReceiptAddress;
    @BindView(R.id.et_detail_address)
    EditText mEtDetailAddress;
    @BindView(R.id.tv_label)
    TextView mTvLabel;
    @BindView(R.id.ib_select_label)
    ImageView mIbSelectLabel;
    @BindView(R.id.bt_ok)
    Button mBtOk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_receipt_address);
        ButterKnife.bind(this);
//        processIntent();
//        mAddressDao = new AddressDao(this);
        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s)){
                    mIbDeletePhone.setVisibility(View.INVISIBLE);
                }else {
                    mIbDeletePhone.setVisibility(View.VISIBLE);
                }
            }
        });
        mEtPhoneOther.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s)){
                    mIbDeletePhoneOther.setVisibility(View.INVISIBLE);
                }else {
                    mIbDeletePhoneOther.setVisibility(View.VISIBLE);
                }
            }
        });
        mEtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
    }

 /*   private void processIntent() {
        if(getIntent()!=null){
            mAddressBean = (RecepitAddressBean) getIntent().getSerializableExtra("address");
            if(mAddressBean != null){
                mTvTitle.setText("修改地址");
                mIbDelete.setVisibility(View.VISIBLE);
                mIbDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteAddress();
                    }
                });
                mTvName.setText(mAddressBean.getName());
                String sex = mAddressBean.getSex();
                if("女士".equals(sex)){
                    mRbWomen.setChecked(true);
                }else {
                    mRbMan.setChecked(true);
                }
                mEtPhoneOther.setText(mAddressBean.getPhoneOther());
                mEtPhone.setText(mAddressBean.getPhone());
                mEtReceiptAddress.setText(mAddressBean.getAddress());
                mEtDetailAddress.setText(mAddressBean.getDetailAddress());
                mTvLabel.setText(mAddressBean.getLable());
                mTvLabel.setTextColor(Color.BLACK);
            }
        }
    }

    private void deleteAddress() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认删除吗？");
        builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAddressDao.deleteRecepitAddressBean(mAddressBean);
                finish();
            }
        });
        builder.show();
    }

    @OnClick({R.id.ib_back, R.id.ib_delete, R.id.ib_delete_phone, R.id.ib_add_phone_other, R.id.ib_delete_phone_other, R.id.ib_select_label,R.id.bt_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.ib_delete_phone:
                mEtPhone.setText("");
                break;
            case R.id.ib_add_phone_other:
                mRlPhoneOther.setVisibility(View.VISIBLE);
                break;
            case R.id.ib_delete_phone_other:
                mEtPhoneOther.setText("");
                break;
            case R.id.ib_select_label:
                alertLable();
                break;
            case R.id.bt_ok:
                boolean isOk = checkReceiptAddressInfo();
                if(isOk){
                    if(mAddressBean == null){

                        insertAddress();
                    }else {
                        updateAddress();
                    }
                }
                break;
        }
    }

    private void updateAddress() {
        String name = mEtName.getText().toString().trim();
        String sex = "女士";
        if(mRbMan.isChecked()){
            sex = "先生";
        }
        String phone = mEtPhone.getText().toString().trim();
        String phoneOther = mEtPhoneOther.getText().toString().trim();
        String receiptAddress = mEtReceiptAddress.getText().toString().trim();
        String detailAddress = mEtDetailAddress.getText().toString().trim();
        String label = mTvLabel.getText().toString().trim();
        mAddressBean.setName(name);
        mAddressBean.setSex(sex);
        mAddressBean.setAddress(receiptAddress);
        mAddressBean.setDetailAddress(detailAddress);
        mAddressBean.setPhone(phone);
        mAddressBean.setPhoneOther(phoneOther);
        mAddressBean.setLable(label);
        boolean isUpdate = mAddressDao.updateRecepitAddressBean(new RecepitAddressBean(999, name, sex, receiptAddress, detailAddress, phone, phoneOther, label, "37"));
        if(isUpdate){
            Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "请仔细检查资料", Toast.LENGTH_SHORT).show();
        }
    }

    private void insertAddress() {
        String name = mEtName.getText().toString().trim();
        String sex = "女士";
        if(mRbMan.isChecked()){
            sex = "先生";
        }
        String phone = mEtPhone.getText().toString().trim();
        String phoneOther = mEtPhoneOther.getText().toString().trim();
        String receiptAddress = mEtReceiptAddress.getText().toString().trim();
        String detailAddress = mEtDetailAddress.getText().toString().trim();
        String label = mTvLabel.getText().toString().trim();
        boolean isAddSuccess = mAddressDao.addRecepitAddressBean(new RecepitAddressBean(999, name, sex, receiptAddress, detailAddress, phone, phoneOther, label, "37"));
        if(isAddSuccess){
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "请仔细检查资料", Toast.LENGTH_SHORT).show();
        }
    }*/

    private String[] mTitles = new String[]{"无","家","学校","公司"};
    private String[] mColors = new String[]{"#aabbcc","#678923","#dd9966","#2233ff"};
    private void alertLable() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择地址标签");
        builder.setItems(mTitles, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTvLabel.setText(mTitles[which]);
                mTvLabel.setBackgroundColor(Color.parseColor(mColors[which]));
            }
        });
        builder.show();
    }

    public boolean checkReceiptAddressInfo() {
        String name = mEtName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请填写联系人", Toast.LENGTH_SHORT).show();
            return false;
        }
        String phone = mEtPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isMobileNO(phone)) {
            Toast.makeText(this, "请填写合法的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        String receiptAddress = mEtReceiptAddress.getText().toString().trim();
        if (TextUtils.isEmpty(receiptAddress)) {
            Toast.makeText(this, "请填写收获地址", Toast.LENGTH_SHORT).show();
            return false;
        }
        String address = mEtDetailAddress.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请填写详细地址", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean isMobileNO(String phone) {
        String telRegex = "[1][358]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return phone.matches(telRegex);
    }
}